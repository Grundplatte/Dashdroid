package com.hyperion.dashdroid.radio;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyperion.dashdroid.radio.data.RadioChannel;
import com.hyperion.dashdroid.radio.db.RadioContentProvider;
import com.hyperion.dashdroid.radio.db.RadioDBContract;
import com.hyperion.dashdroid.radio.dirble.DirbleProvider;

import java.util.ArrayList;

public class RadioCategoryChannelsFragment extends RadioChannelsFragment {

    private int categoryid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        categoryid = bundle.getInt("rootCategory");

        super.onCreateView(inflater, container, savedInstanceState);

        refresh();

        return radioListViewContainer;
    }

    @Override
    public void refresh() {
        CategoryChannelAsyncTask dirbleAsyncTask = new CategoryChannelAsyncTask();
        dirbleAsyncTask.execute(categoryid);
    }

    public class CategoryChannelAsyncTask extends AsyncTask<Object, Integer, Object> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (progressBar != null)
                progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Object doInBackground(Object... params) {
            ArrayList<RadioChannel> radioChannels = DirbleProvider.getInstance().getChannelsForCategory((int) params[0]);
            for (int i = 0; i < radioChannels.size(); i++) {
                String where = RadioDBContract.RadioChannel.COLUMN_NAME_CHANNEL_ID + '=' + radioChannels.get(i).getID();
                Cursor c = getActivity().getContentResolver().query(RadioContentProvider.URI_CHANNELS, null, where, null, null);
                if (c.moveToFirst()) {
                    radioChannels.get(i).setFavorited(true);
                }
                c.close();
            }
            return radioChannels;
        }

        @Override
        protected void onPostExecute(Object o) {
            ChannelAdapter channelAdapter = new ChannelAdapter((ArrayList<RadioChannel>) o, RadioCategoryChannelsFragment.this);
            radioList.setAdapter(channelAdapter);

            if (progressBar != null)
                progressBar.setVisibility(View.GONE);
        }
    }
}
