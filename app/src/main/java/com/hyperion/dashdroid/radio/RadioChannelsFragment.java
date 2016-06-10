package com.hyperion.dashdroid.radio;

import android.database.Cursor;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.BaseFragment;
import com.hyperion.dashdroid.radio.data.RadioChannel;
import com.hyperion.dashdroid.radio.data.RadioStream;
import com.hyperion.dashdroid.radio.db.RadioContentProvider;
import com.hyperion.dashdroid.radio.db.RadioDBContract;
import com.hyperion.dashdroid.radio.dirble.DirbleHelper;

abstract public class RadioChannelsFragment extends BaseFragment implements ChannelAdapter.OnChannelItemClickListener {

    protected View radioListViewContainer;
    protected RecyclerView radioList;
    protected ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        radioListViewContainer = inflater.inflate(R.layout.radio_fragment_list, container, false);
        radioList = (RecyclerView) radioListViewContainer.findViewById(R.id.radioListView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        radioList.setLayoutManager(gridLayoutManager);

        progressBar = (ProgressBar) radioListViewContainer.findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity(), R.color.m_color_pressed_1), PorterDuff.Mode.MULTIPLY);

        return radioListViewContainer;
    }

    @Override
    abstract public void refresh();

    @Override
    public void onItemClick(RadioChannel channel) {
        RadioMainFragment radioMainFragment = (RadioMainFragment) getFragmentManager().findFragmentByTag(RadioMainFragment.TAG);
        radioMainFragment.getRadioPlayer().playRadioChannel(channel);
    }

    @Override
    public void onFavorite(RadioChannel channel) {
        // add to db
        Uri uri = getActivity().getContentResolver().insert(RadioContentProvider.URI_CHANNELS, DirbleHelper.getValuesForChannel(channel));
        for (RadioStream stream : channel.getRadioStreams()) {
            getActivity().getContentResolver().insert(RadioContentProvider.URI_STREAMS, DirbleHelper.getValuesForStream(stream, uri.getLastPathSegment()));
        }
    }

    @Override
    public void onUnFavorite(RadioChannel channel) {
        String channel_where = RadioDBContract.RadioChannel.COLUMN_NAME_CHANNEL_ID + '=' + channel.getID();
        Cursor c = getActivity().getContentResolver().query(RadioContentProvider.URI_CHANNELS, null, channel_where, null, null);
        if (c.moveToFirst()) {
            String stream_where = RadioDBContract.RadioStream.COLUMN_NAME_CHANNEL + '=' + c.getInt(c.getColumnIndex(RadioDBContract.RadioChannel._ID));
            getActivity().getContentResolver().delete(RadioContentProvider.URI_STREAMS, stream_where, null);
        } else
            throw new IllegalStateException("No channels to delete");

        getActivity().getContentResolver().delete(RadioContentProvider.URI_CHANNELS, channel_where, null);

        c.close();
    }

    /*
    public class FavoritesAsyncTask extends AsyncTask<Object, Integer, Object> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object... params) {
            ArrayList<RadioChannel> radioChannels = new ArrayList<>();

            Cursor channels_c = getContext().getContentResolver().query(RadioContentProvider.URI_CHANNELS, null, null, null, null);
            if (channels_c.moveToFirst()) {
                do {
                    int channel_id = channels_c.getInt(channels_c.getColumnIndex(RadioDBContract.RadioChannel._ID));
                    String where = RadioDBContract.RadioStream.COLUMN_NAME_CHANNEL + '=' + channel_id;
                    Cursor streams_c = getContext().getContentResolver().query(RadioContentProvider.URI_STREAMS, null, where, null, null);
                    radioChannels.add(DirbleHelper.buildRadioChannel(channels_c, streams_c));
                } while (channels_c.moveToNext());
                return radioChannels;
            } else {
                // no favorites
                return null;
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            if(o != null) {
                ChannelAdapter channelAdapter = new ChannelAdapter((ArrayList<RadioChannel>) o, RadioFavoritesFragment.this);
                radioList.setAdapter(channelAdapter);
            }
            else {
                Toast.makeText(getActivity(), "Favorites empty!", Toast.LENGTH_SHORT).show();
            }
        }
    }*/
}
