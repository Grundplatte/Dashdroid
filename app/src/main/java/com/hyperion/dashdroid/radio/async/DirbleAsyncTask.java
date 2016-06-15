package com.hyperion.dashdroid.radio.async;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.hyperion.dashdroid.R;

public abstract class DirbleAsyncTask extends AsyncTask<Object, Integer, Object> {
    protected Context context;

    protected RecyclerView listView;
    protected ProgressBar progressBar;

    public DirbleAsyncTask(Context context, View baseView) {
        this.context = context;
        progressBar = (ProgressBar) baseView.findViewById(R.id.progressBar);
        listView = (RecyclerView) baseView.findViewById(R.id.radioListView);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (progressBar != null)
            progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected abstract Object doInBackground(Object... params);

    @Override
    protected void onPostExecute(Object o) {
        if (progressBar != null)
            progressBar.setVisibility(View.GONE);
    }
}