package com.hyperion.dashdroid.radio.listener;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.hyperion.dashdroid.radio.adapter.ChannelAdapter;
import com.hyperion.dashdroid.radio.data.RadioChannel;
import com.hyperion.dashdroid.radio.data.RadioStream;
import com.hyperion.dashdroid.radio.db.RadioContentProvider;
import com.hyperion.dashdroid.radio.db.RadioDBContract;
import com.hyperion.dashdroid.radio.dirble.DirbleHelper;

public class ChannelOnFavoriteListener implements ChannelAdapter.OnChannelFavoriteListener {
    private Context context;

    public ChannelOnFavoriteListener(Context context) {
        this.context = context;
    }

    @Override
    public void onFavorite(RadioChannel channel) {
        // add to db
        Uri uri = context.getContentResolver().insert(RadioContentProvider.URI_CHANNELS, DirbleHelper.getValuesForChannel(channel));
        for (RadioStream stream : channel.getRadioStreams()) {
            context.getContentResolver().insert(RadioContentProvider.URI_STREAMS, DirbleHelper.getValuesForStream(stream, uri.getLastPathSegment()));
        }
    }

    @Override
    public void onUnFavorite(RadioChannel channel) {
        String channel_where = RadioDBContract.RadioChannel.COLUMN_NAME_CHANNEL_ID + '=' + channel.getID();
        Cursor c = context.getContentResolver().query(RadioContentProvider.URI_CHANNELS, null, channel_where, null, null);
        if (c.moveToFirst()) {
            String stream_where = RadioDBContract.RadioStream.COLUMN_NAME_CHANNEL + '=' + c.getInt(c.getColumnIndex(RadioDBContract.RadioChannel._ID));
            context.getContentResolver().delete(RadioContentProvider.URI_STREAMS, stream_where, null);
            c.close();
        } else {
            c.close();
            throw new IllegalStateException("No channels to delete");
        }

        context.getContentResolver().delete(RadioContentProvider.URI_CHANNELS, channel_where, null);

        c.close();
    }
}
