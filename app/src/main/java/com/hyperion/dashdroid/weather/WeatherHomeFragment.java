package com.hyperion.dashdroid.weather;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyperion.dashdroid.R;

/**
 * Created by infinity on 06-May-16.
 */
public class WeatherHomeFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.weather_fragment_home, container, false);

		return rootView;
	}

}
