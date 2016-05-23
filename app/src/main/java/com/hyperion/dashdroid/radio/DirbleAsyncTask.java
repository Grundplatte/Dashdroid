package com.hyperion.dashdroid.radio;

import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.hyperion.dashdroid.R;

import java.util.ArrayList;

/**
 * Created by Rainer on 12.05.2016.
 */
public class DirbleAsyncTask extends AsyncTask<Object,Integer, Object>{

    private RelativeLayout relativeLayout;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private JobType jobType;

    public enum JobType{SEARCH, GET_CATEGORY_TREE, GET_CHANNELS_FOR_CATEGORY};

    public DirbleAsyncTask(View view) {
        this.relativeLayout = (RelativeLayout) view;
        recyclerView = (RecyclerView)relativeLayout.findViewById(R.id.radioListView);
        progressBar = (ProgressBar)relativeLayout.findViewById(R.id.progressBar);
        this.jobType = JobType.SEARCH;              // by default JobType -> SEARCH
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Object doInBackground(Object... params) {
        //jobType = (JobType)params[0];

        switch (jobType){
//            case SEARCH:
//                return DirbleProvider.getInstance().search((String) params[1]);
            case GET_CATEGORY_TREE:
                return DirbleProvider.getInstance().getCategoryTree();
            case GET_CHANNELS_FOR_CATEGORY:
                return DirbleProvider.getInstance().getChannelsForCategory((int)params[0]);
            default:
                return DirbleProvider.getInstance().search((String) params[0]);
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        switch (jobType) {
            case SEARCH:
            case GET_CHANNELS_FOR_CATEGORY:
                ChannelAdapter channelAdapter = new ChannelAdapter((ArrayList<RadioChannel>) o);
                recyclerView.setAdapter(channelAdapter);
                break;
            case GET_CATEGORY_TREE:
                CategoryAdapter categoryAdapter = new CategoryAdapter(relativeLayout, (ArrayList<RadioChannelCategory>) o);
                recyclerView.setAdapter(categoryAdapter);
                break;
            default:
        }
        progressBar.setVisibility(View.GONE);
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }
}
