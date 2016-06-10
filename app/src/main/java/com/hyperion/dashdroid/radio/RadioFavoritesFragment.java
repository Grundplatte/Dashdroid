package com.hyperion.dashdroid.radio;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hyperion.dashdroid.radio.data.RadioChannel;
import com.hyperion.dashdroid.radio.db.RadioContentProvider;
import com.hyperion.dashdroid.radio.db.RadioDBContract;
import com.hyperion.dashdroid.radio.dirble.DirbleHelper;

import java.util.ArrayList;

public class RadioFavoritesFragment extends RadioChannelsFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        refresh();

        return radioListViewContainer;
    }

    @Override
    public void refresh() {
        FavoritesAsyncTask dirbleAsyncTask = new FavoritesAsyncTask();
        dirbleAsyncTask.execute();
    }

    @Override
    public void onItemClick(RadioChannel channel) {
        RadioMainFragment radioMainFragment = (RadioMainFragment) getFragmentManager().findFragmentByTag(RadioMainFragment.TAG);
        radioMainFragment.getRadioPlayer().playRadioChannel(channel);
    }

    public class FavoritesAsyncTask extends AsyncTask<Object, Integer, Object> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (progressBar != null)
                progressBar.setVisibility(View.VISIBLE);
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
                    if (streams_c.moveToFirst()) {
                        RadioChannel channel = DirbleHelper.buildRadioChannel(channels_c, streams_c);
                        channel.setFavorited(true);
                        radioChannels.add(channel);
                    }
                    streams_c.close();
                } while (channels_c.moveToNext());
                channels_c.close();
                return radioChannels;
            } else {
                // no favorites
                channels_c.close();
                return null;
            }

        }

        @Override
        protected void onPostExecute(Object o) {
            if (o != null) {
                ChannelAdapter channelAdapter = new ChannelAdapter((ArrayList<RadioChannel>) o, RadioFavoritesFragment.this);
                radioList.setAdapter(channelAdapter);
            } else {
                ChannelAdapter channelAdapter = new ChannelAdapter(new ArrayList<RadioChannel>(), RadioFavoritesFragment.this);
                radioList.setAdapter(channelAdapter);
                Toast.makeText(getActivity(), "Favorites empty!", Toast.LENGTH_SHORT).show();
            }

            if (progressBar != null)
                progressBar.setVisibility(View.GONE);
        }
    }
}
