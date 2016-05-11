package com.hyperion.dashdroid.radio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.slidingmenu.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Rainer on 11.05.2016.
 */
public class RadioHomeFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View radioView = inflater.inflate(R.layout.radio_fragment_home, container, false);


        Thread thread = new Thread(){
            @Override
            public void run() {
                ArrayList<RadioChannel> radioChannels;
                radioChannels = DirbleProvider.getInstance().search("raute");

            }
        };
        thread.start();
        return radioView;
    }
}
