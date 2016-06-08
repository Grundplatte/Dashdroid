package com.hyperion.dashdroid.radio;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.radio.data.RadioChannel;
import com.hyperion.dashdroid.radio.dirble.DirbleProvider;

import java.util.ArrayList;

public class ChannelSearchAsyncTask extends AsyncTask<Object, Integer, Object> {

	private RecyclerView recyclerView;
	private ProgressBar progressBar;

	public ChannelSearchAsyncTask(View view) {
		this.recyclerView = (RecyclerView) view.findViewById(R.id.radioListView);
		this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

		if(progressBar != null)
			progressBar.setVisibility(View.VISIBLE);
	}

	@Override
	protected Object doInBackground(Object... params) {
		return DirbleProvider.getInstance().search((String) params[0]);
	}

	@Override
	protected void onPostExecute(Object o) {
		((ChannelAdapter) recyclerView.getAdapter()).setItems((ArrayList<RadioChannel>) o);
		recyclerView.getAdapter().notifyDataSetChanged();

		if(progressBar != null)
			progressBar.setVisibility(View.GONE);
	}
}
