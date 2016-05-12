package com.hyperion.dashdroid.radio;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

        RecyclerView recyclerView = (RecyclerView)radioView.findViewById(R.id.radioList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        // TODO change
        new DirbleAsyncTask().execute(recyclerView);

        return radioView;
    }
}
