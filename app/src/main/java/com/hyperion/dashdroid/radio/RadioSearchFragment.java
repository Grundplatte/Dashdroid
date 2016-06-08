package com.hyperion.dashdroid.radio;

import android.graphics.PorterDuff;
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

import java.util.ArrayList;

public class RadioSearchFragment extends BaseFragment implements ChannelAdapter.OnChannelItemClickListener {

	private View radioListViewContainer;
	private RecyclerView radioList;
	private ProgressBar progressBar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		radioListViewContainer = inflater.inflate(R.layout.radio_fragment_list, container, false);
		radioList = (RecyclerView) radioListViewContainer.findViewById(R.id.radioListView);

		ChannelAdapter channelAdapter = new ChannelAdapter(new ArrayList<RadioChannel>(), RadioSearchFragment.this);
		GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
		radioList.setLayoutManager(gridLayoutManager);
		radioList.setAdapter(channelAdapter);

		progressBar = (ProgressBar) radioListViewContainer.findViewById(R.id.progressBar);
		progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity(), R.color.m_color_pressed_1), PorterDuff.Mode.MULTIPLY);

		return radioListViewContainer;
	}

	@Override
	public void refresh() {
	}

	@Override
	public void onItemClick(RadioChannel channel) {
		RadioMainFragment radioMainFragment = (RadioMainFragment) getFragmentManager().findFragmentByTag(RadioMainFragment.TAG);
		radioMainFragment.getRadioPlayer().playRadioChannel(channel);
	}
}
