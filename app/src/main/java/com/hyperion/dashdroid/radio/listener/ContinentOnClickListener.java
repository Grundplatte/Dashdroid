package com.hyperion.dashdroid.radio.listener;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.radio.RadioListFragment;
import com.hyperion.dashdroid.radio.adapter.ContinentAdapter;
import com.hyperion.dashdroid.radio.async.CountryAsyncTask;
import com.hyperion.dashdroid.radio.data.RadioContinent;


public class ContinentOnClickListener implements ContinentAdapter.OnContinentItemClickListener {

    private Context context;


    public ContinentOnClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onItemClick(RadioContinent continent) {
        FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();


        Fragment radioCountryFragment = RadioListFragment.makeInstance(CountryAsyncTask.class);
        Bundle bundle = new Bundle();
        bundle.putString("continentName", continent.getName());
        radioCountryFragment.setArguments(bundle);

        transaction.replace(R.id.radioList_container, radioCountryFragment);
        transaction.addToBackStack("continentFragment");

        transaction.commit();
    }
}
