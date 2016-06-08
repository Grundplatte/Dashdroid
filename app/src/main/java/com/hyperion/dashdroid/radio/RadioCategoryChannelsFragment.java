package com.hyperion.dashdroid.radio;

import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.BaseFragment;
import com.hyperion.dashdroid.radio.data.RadioChannel;
import com.hyperion.dashdroid.radio.dirble.DirbleProvider;

import java.util.ArrayList;

public class RadioCategoryChannelsFragment extends BaseFragment implements ChannelAdapter.OnChannelItemClickListener {

	private View radioListViewContainer;
	private RecyclerView radioList;
	private ProgressBar progressBar;
	private int categoryid;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Bundle bundle = getArguments();
		categoryid = bundle.getInt("rootCategory");

		radioListViewContainer = inflater.inflate(R.layout.radio_fragment_list, container, false);
		radioList = (RecyclerView) radioListViewContainer.findViewById(R.id.radioListView);

		GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
		radioList.setLayoutManager(gridLayoutManager);

		progressBar = (ProgressBar) radioListViewContainer.findViewById(R.id.progressBar);
		progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity(), R.color.m_color_pressed_1), PorterDuff.Mode.MULTIPLY);

		refresh();

		return radioListViewContainer;
	}

	@Override
	public void refresh() {
		RelativeLayout relativeLayout = (RelativeLayout) radioListViewContainer.findViewById(R.id.radioList);

		CategoryChannelAsyncTask dirbleAsyncTask = new CategoryChannelAsyncTask();
		dirbleAsyncTask.execute(categoryid);
	}

	@Override
	public void onItemClick(RadioChannel channel) {
		RadioMainFragment radioMainFragment = (RadioMainFragment) getFragmentManager().findFragmentByTag(RadioMainFragment.TAG);
		radioMainFragment.getRadioPlayer().playRadioChannel(channel);
	}

	public class CategoryChannelAsyncTask extends AsyncTask<Object, Integer, Object> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if(progressBar != null)
				progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected Object doInBackground(Object... params) {
			return DirbleProvider.getInstance().getChannelsForCategory((int) params[0]);
		}

		@Override
		protected void onPostExecute(Object o) {
			ChannelAdapter channelAdapter = new ChannelAdapter((ArrayList<RadioChannel>) o, RadioCategoryChannelsFragment.this);
			radioList.setAdapter(channelAdapter);

			if(progressBar != null)
				progressBar.setVisibility(View.GONE);
		}
	}
}
