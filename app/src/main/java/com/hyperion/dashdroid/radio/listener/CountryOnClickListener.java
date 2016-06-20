package com.hyperion.dashdroid.radio.listener;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.radio.RadioListFragment;
import com.hyperion.dashdroid.radio.adapter.CountryAdapter;
import com.hyperion.dashdroid.radio.async.CountryChannelAsyncTask;
import com.hyperion.dashdroid.radio.data.RadioCountry;


public class CountryOnClickListener implements CountryAdapter.OnCountryItemClickListener {

    private Context context;


    public CountryOnClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onItemClick(RadioCountry country) {
        Log.d(getClass().getSimpleName(), "onItemClick: " + country.getName());
        Log.d(getClass().getSimpleName(), "onItemClick: " + country.getRegion());

        FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Fragment radioCountryFragment = RadioListFragment.makeInstance(CountryChannelAsyncTask.class);
        Bundle bundle = new Bundle();
        bundle.putString("countryCode", country.getCountry_code());

        radioCountryFragment.setArguments(bundle);

        transaction.replace(R.id.radioList_container, radioCountryFragment);
        transaction.addToBackStack("countryFragment");

        transaction.commit();

    }
}
