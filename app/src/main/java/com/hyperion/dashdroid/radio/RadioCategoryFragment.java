package com.hyperion.dashdroid.radio;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.BaseFragment;

public class RadioCategoryFragment extends BaseFragment implements RecyclerView.OnClickListener{

    private View radioListViewContainer;
    private RecyclerView radioList;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        radioListViewContainer = inflater.inflate(R.layout.radio_fragment_list, container, false);
        radioList = (RecyclerView) radioListViewContainer.findViewById(R.id.radioListView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        radioList.setLayoutManager(gridLayoutManager);

        progressBar = (ProgressBar) radioListViewContainer.findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity(), R.color.m_color_pressed_1), PorterDuff.Mode.MULTIPLY);

        refresh();

        return radioListViewContainer;
    }

    @Override
    public void refresh() {
        RelativeLayout relativeLayout = (RelativeLayout)radioListViewContainer.findViewById(R.id.radioList);

        // TODO change
        DirbleAsyncTask dirbleAsyncTask = new DirbleAsyncTask(radioList);
        dirbleAsyncTask.setJobType(DirbleAsyncTask.JobType.GET_CATEGORY_TREE);
        dirbleAsyncTask.execute();

        ((RadioModuleActivity)getActivity()).getSearchView().setView(relativeLayout);
    }

    @Override
    public void onClick(View v) {

    }
}
