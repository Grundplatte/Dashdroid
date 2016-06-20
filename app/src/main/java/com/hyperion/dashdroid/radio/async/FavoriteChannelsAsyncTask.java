package com.hyperion.dashdroid.radio.async;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.Toast;

import com.hyperion.dashdroid.radio.adapter.ChannelAdapter;
import com.hyperion.dashdroid.radio.data.RadioChannel;
import com.hyperion.dashdroid.radio.db.RadioContentProvider;
import com.hyperion.dashdroid.radio.db.RadioDBContract;
import com.hyperion.dashdroid.radio.dirble.DirbleHelper;

import java.util.ArrayList;

public class FavoriteChannelsAsyncTask extends DirbleAsyncTask {

    public FavoriteChannelsAsyncTask(Context context, View baseView) {
        super(context, baseView);
    }

    @Override
    protected Object doInBackground(Object... params) {
        ArrayList<RadioChannel> radioChannels = new ArrayList<>();

        Cursor channels_c = context.getContentResolver().query(RadioContentProvider.URI_CHANNELS, null, null, null, null);
        if (channels_c.moveToFirst()) {
            do {
                int channel_id = channels_c.getInt(channels_c.getColumnIndex(RadioDBContract.RadioChannel._ID));
                String where = RadioDBContract.RadioStream.COLUMN_NAME_CHANNEL + '=' + channel_id;
                Cursor streams_c = context.getContentResolver().query(RadioContentProvider.URI_STREAMS, null, where, null, null);
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
            ChannelAdapter channelAdapter = new ChannelAdapter(context, (ArrayList<RadioChannel>) o);
            listView.setAdapter(channelAdapter);
        } else {
            ChannelAdapter channelAdapter = new ChannelAdapter(context, new ArrayList<RadioChannel>());
            listView.setAdapter(channelAdapter);
            Toast.makeText(context, "Favorites empty!", Toast.LENGTH_SHORT).show();
        }

        super.onPostExecute(o);
    }
}