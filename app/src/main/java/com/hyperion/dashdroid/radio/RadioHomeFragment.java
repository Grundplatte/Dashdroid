package com.hyperion.dashdroid.radio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.slidingmenu.BaseFragment;

/**
 * Created by infinity on 05-May-16.
 */
public class RadioHomeFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.radio_fragment_home, container, false);

		return rootView;
	}
}
