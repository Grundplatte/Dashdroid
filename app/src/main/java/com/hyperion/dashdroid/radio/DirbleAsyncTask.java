package com.hyperion.dashdroid.radio;

import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Rainer on 12.05.2016.
 */
public class DirbleAsyncTask extends AsyncTask<RecyclerView,Integer, ArrayList<RadioChannelCategory>>{

    private RecyclerView recyclerView;

    @Override
    protected ArrayList<RadioChannelCategory> doInBackground(RecyclerView... params) {

        recyclerView = params[0];
        return DirbleProvider.getInstance().getCategoryTree();
    }

    @Override
    protected void onPostExecute(ArrayList<RadioChannelCategory> radioChannelCategories) {
        CategoryAdapter categoryAdapter = new CategoryAdapter(radioChannelCategories);
        recyclerView.setAdapter(categoryAdapter);
    }
}
