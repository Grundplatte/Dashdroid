package com.hyperion.dashdroid.radio;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.BaseSearchView;
import com.hyperion.dashdroid.radio.async.ChannelSearchAsyncTask;

public class RadioSearchView extends BaseSearchView {
    private Context context;

    public RadioSearchView(Context context, Class<?> asyncTaskClass) {
        super(context, asyncTaskClass);
        this.context = context;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        FragmentManager fragmentManager = ((Activity) context).getFragmentManager();

        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        // make new fragment
        fragmentManager.beginTransaction().replace(R.id.radioList_container, RadioListFragment.makeInstance(ChannelSearchAsyncTask.class)).commit();
        fragmentManager.executePendingTransactions();
        setView(((Activity) context).findViewById(R.id.radioList));

        setIconified(true);
        setIconified(true);

        return super.onQueryTextSubmit(query);
    }
}
