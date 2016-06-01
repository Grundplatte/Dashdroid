package com.hyperion.dashdroid.radio;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.BaseSearchView;

/**
 * Created by Markus on 01.06.2016.
 */
public class RadioSearchView extends BaseSearchView {
    private Context context;

    public RadioSearchView(Context context, Class<?> asyncTaskClass) {
        super(context, asyncTaskClass);
        this.context = context;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        FragmentManager fragmentManager = ((Activity)context).getFragmentManager();

        // TODO: maybe not the best solution
        if(fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        // make new fragment
        fragmentManager.beginTransaction().replace(R.id.radioList_container, new RadioSearchFragment()).commit();
        fragmentManager.executePendingTransactions();
        setView(((Activity) context).findViewById(R.id.radioList));

        return super.onQueryTextSubmit(query);
    }
}
