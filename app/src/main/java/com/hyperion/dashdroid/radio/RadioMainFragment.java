package com.hyperion.dashdroid.radio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.BaseFragment;

public class RadioMainFragment extends BaseFragment{

    private View radioMainView;
    private View radioPlayerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        radioMainView = inflater.inflate(R.layout.radio_fragment_main, container, false);

        radioPlayerView = radioMainView.findViewById(R.id.playerLayout);
        RadioPlayer.getInstance().setRadioView(radioPlayerView);

        refresh();

        return radioMainView;
    }

    @Override
    public void refresh() {

    }
}
