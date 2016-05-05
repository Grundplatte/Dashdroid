package com.hyperion.dashdroid.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyperion.dashdroid.R;

/**
 * Created by Markus on 26.04.2016.
 */
public class WeatherFragment extends Fragment {

    private static WeatherFragment instance = null;

    public static synchronized WeatherFragment getInstance()
    {
        if(instance == null) {
            instance = new WeatherFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }
}
