package com.hyperion.dashdroid.radio;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyperion.dashdroid.R;

/**
 * Created by Rainer on 11.05.2016.
 */
public class RadioHomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View radioView = inflater.inflate(R.layout.radio_fragment_home, container, false);

        RecyclerView recyclerView = (RecyclerView)radioView.findViewById(R.id.radioList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        // TODO change
        new DirbleAsyncTask(recyclerView).execute(DirbleAsyncTask.JobType.GET_CATEGORY_TREE);

        ((RadioModuleActivity)getActivity()).getSearchView().setRecyclerView(recyclerView);

        return radioView;
    }
}
