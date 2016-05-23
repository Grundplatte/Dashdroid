package com.hyperion.dashdroid.radio;

import android.app.Fragment;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.BaseFragment;

/**
 * Created by Rainer on 11.05.2016.
 */
public class RadioHomeFragment extends BaseFragment {

    private View radioView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        radioView = inflater.inflate(R.layout.radio_fragment_home, container, false);

        RelativeLayout relativeLayout = (RelativeLayout)radioView.findViewById(R.id.radioList);
        RecyclerView recyclerView = (RecyclerView)relativeLayout.findViewById(R.id.radioListView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        ProgressBar progressBar = (ProgressBar)radioView.findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity(), R.color.m_color_pressed_1), PorterDuff.Mode.MULTIPLY);

        View radioPlayerView = radioView.findViewById(R.id.playerLayout);
        RadioPlayer.getInstance().setRadioView(radioPlayerView);

        refresh();

        return radioView;
    }

    @Override
    public void refresh() {
        RelativeLayout relativeLayout = (RelativeLayout)radioView.findViewById(R.id.radioList);

        // TODO change
        DirbleAsyncTask dirbleAsyncTask = new DirbleAsyncTask(relativeLayout);
        dirbleAsyncTask.setJobType(DirbleAsyncTask.JobType.GET_CATEGORY_TREE);
        dirbleAsyncTask.execute();

        ((RadioModuleActivity)getActivity()).getSearchView().setView(relativeLayout);
    }
}
