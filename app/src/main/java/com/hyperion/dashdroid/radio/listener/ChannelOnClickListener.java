package com.hyperion.dashdroid.radio.listener;

import android.app.Activity;
import android.content.Context;

import com.hyperion.dashdroid.radio.RadioMainFragment;
import com.hyperion.dashdroid.radio.adapter.ChannelAdapter;
import com.hyperion.dashdroid.radio.data.RadioChannel;

public class ChannelOnClickListener implements ChannelAdapter.OnChannelItemClickListener {

    private Context context;


    public ChannelOnClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onItemClick(RadioChannel channel) {
        RadioMainFragment radioMainFragment = (RadioMainFragment) ((Activity) context).getFragmentManager().findFragmentByTag(RadioMainFragment.TAG);
        radioMainFragment.getRadioPlayer().playRadioChannel(channel);
    }
}
