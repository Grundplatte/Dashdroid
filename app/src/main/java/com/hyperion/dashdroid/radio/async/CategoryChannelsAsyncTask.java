package com.hyperion.dashdroid.radio.async;

import android.content.Context;
import android.database.Cursor;
import android.view.View;

import com.hyperion.dashdroid.radio.adapter.ChannelAdapter;
import com.hyperion.dashdroid.radio.data.RadioChannel;
import com.hyperion.dashdroid.radio.db.RadioContentProvider;
import com.hyperion.dashdroid.radio.db.RadioDBContract;
import com.hyperion.dashdroid.radio.dirble.DirbleProvider;

import java.util.ArrayList;

public class CategoryChannelsAsyncTask extends DirbleAsyncTask {

    public CategoryChannelsAsyncTask(Context context, View baseView) {
        super(context, baseView);
    }

    @Override
    protected Object doInBackground(Object... params) {

        ArrayList<RadioChannel> radioChannels = DirbleProvider.getInstance().getChannelsForCategory((int) params[0]);

        for (int i = 0; i < radioChannels.size(); i++) {
            String where = RadioDBContract.RadioChannel.COLUMN_NAME_CHANNEL_ID + '=' + radioChannels.get(i).getID();
            Cursor c = context.getContentResolver().query(RadioContentProvider.URI_CHANNELS, null, where, null, null);
            if (c.moveToFirst()) {
                radioChannels.get(i).setFavorited(true);
            }
            c.close();
        }
        return radioChannels;
    }

    @Override
    protected void onPostExecute(Object o) {
        ChannelAdapter channelAdapter = new ChannelAdapter(context, (ArrayList<RadioChannel>) o);
        listView.setAdapter(channelAdapter);

        super.onPostExecute(o);
    }
}