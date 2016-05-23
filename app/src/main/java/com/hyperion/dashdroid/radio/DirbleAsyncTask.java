package com.hyperion.dashdroid.radio;

import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Rainer on 12.05.2016.
 */
public class DirbleAsyncTask extends AsyncTask<Object,Integer, Object>{

    private RecyclerView recyclerView;
    private JobType jobType;

    public enum JobType{SEARCH, GET_CATEGORY_TREE, GET_CHANNELS_FOR_CATEGORY};

    public DirbleAsyncTask(View view) {
        this.recyclerView = (RecyclerView) view;
        this.jobType = JobType.SEARCH;              // by default JobType -> SEARCH
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
                CategoryAdapter categoryAdapter = new CategoryAdapter(recyclerView, (ArrayList<RadioChannelCategory>) o);
                recyclerView.setAdapter(categoryAdapter);
                break;
            default:
        }
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }
}
