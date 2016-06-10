package com.hyperion.dashdroid.radio;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.radio.data.RadioChannel;
import com.hyperion.dashdroid.radio.db.RadioContentProvider;
import com.hyperion.dashdroid.radio.db.RadioDBContract;
import com.hyperion.dashdroid.radio.dirble.DirbleProvider;

import java.util.ArrayList;

public class ChannelSearchAsyncTask extends AsyncTask<Object, Integer, Object> {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Context context;

    public ChannelSearchAsyncTask(Context context, View view) {
        this.recyclerView = (RecyclerView) view.findViewById(R.id.radioListView);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (progressBar != null)
            progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Object doInBackground(Object... params) {
        ArrayList<RadioChannel> radioChannels = DirbleProvider.getInstance().search((String) params[0]);
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
        ((ChannelAdapter) recyclerView.getAdapter()).setItems((ArrayList<RadioChannel>) o);
        recyclerView.getAdapter().notifyDataSetChanged();

        if (progressBar != null)
            progressBar.setVisibility(View.GONE);
    }
}
