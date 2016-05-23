package com.hyperion.dashdroid.radio;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hyperion.dashdroid.R;

/**
 * Created by Rainer on 11.05.2016.
 */
public class RadioHomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View radioView = inflater.inflate(R.layout.radio_fragment_home, container, false);

        RelativeLayout relativeLayout = (RelativeLayout)radioView.findViewById(R.id.radioList);
        RecyclerView recyclerView = (RecyclerView)relativeLayout.findViewById(R.id.radioListView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        View radioPlayerView = radioView.findViewById(R.id.playerLayout);
        RadioPlayer.getInstance().setRadioView(radioPlayerView);

        // TODO change
        DirbleAsyncTask dirbleAsyncTask = new DirbleAsyncTask(relativeLayout);
        dirbleAsyncTask.setJobType(DirbleAsyncTask.JobType.GET_CATEGORY_TREE);
        dirbleAsyncTask.execute();

        ((RadioModuleActivity)getActivity()).getSearchView().setView(relativeLayout);

        return radioView;
    }
}
