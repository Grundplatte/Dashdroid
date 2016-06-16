package com.hyperion.dashdroid.radio.listener;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.radio.RadioListFragment;
import com.hyperion.dashdroid.radio.adapter.CategoryAdapter;
import com.hyperion.dashdroid.radio.async.CategoryAsyncTask;
import com.hyperion.dashdroid.radio.async.CategoryChannelsAsyncTask;
import com.hyperion.dashdroid.radio.data.RadioCategory;


public class CategoryOnClickListener implements CategoryAdapter.OnCategoryItemClickListener {

    private Context context;


    public CategoryOnClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onItemClick(RadioCategory category) {
        FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (category.getAncestry() == -1) {
            Fragment radioCategoryFragment = RadioListFragment.makeInstance(CategoryAsyncTask.class);
            Bundle bundle = new Bundle();
            bundle.putInt("rootCategory", category.getID());

            radioCategoryFragment.setArguments(bundle);

            transaction.replace(R.id.radioList_container, radioCategoryFragment);
            transaction.addToBackStack("categoryFragment");
        } else {
            Fragment radioCategoryChannelsFragment = RadioListFragment.makeInstance(CategoryChannelsAsyncTask.class);
            Bundle bundle = new Bundle();
            bundle.putInt("rootCategory", category.getID());

            radioCategoryChannelsFragment.setArguments(bundle);

            transaction.replace(R.id.radioList_container, radioCategoryChannelsFragment);
            transaction.addToBackStack("SubCategoryFragment");
        }
        transaction.commit();
    }
}
