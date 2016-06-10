package com.hyperion.dashdroid.radio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyperion.dashdroid.radio.data.RadioChannel;

import java.util.ArrayList;

public class RadioSearchFragment extends RadioChannelsFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ChannelAdapter adapter = new ChannelAdapter(new ArrayList<RadioChannel>(), RadioSearchFragment.this);
        radioList.setAdapter(adapter);

        return radioListViewContainer;
    }

    @Override
    public void refresh() {
    }
}
