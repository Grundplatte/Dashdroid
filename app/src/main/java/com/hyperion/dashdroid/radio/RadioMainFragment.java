package com.hyperion.dashdroid.radio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.BaseFragment;

public class RadioMainFragment extends BaseFragment {

	public static final String TAG = "MainFragment";
	private View radioMainView;
	private View radioPlayerView;

	private RadioPlayer radioPlayer;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		radioMainView = inflater.inflate(R.layout.radio_fragment_main, container, false);
		radioPlayerView = radioMainView.findViewById(R.id.playerLayout);
		radioPlayer = new RadioPlayer(getActivity(), radioPlayerView);

		refresh();

		return radioMainView;
	}

	@Override
	public void refresh() {

	}

	public RadioPlayer getRadioPlayer() {
		return radioPlayer;
	}
}
