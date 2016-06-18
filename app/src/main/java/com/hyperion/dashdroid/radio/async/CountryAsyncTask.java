package com.hyperion.dashdroid.radio.async;


import android.content.Context;
import android.database.Cursor;
import android.view.View;

import com.hyperion.dashdroid.radio.adapter.CountryAdapter;
import com.hyperion.dashdroid.radio.data.RadioCountry;
import com.hyperion.dashdroid.radio.db.RadioContentProvider;
import com.hyperion.dashdroid.radio.db.RadioDBContract;
import com.hyperion.dashdroid.radio.dirble.DirbleHelper;

import java.util.ArrayList;

public class CountryAsyncTask extends DirbleAsyncTask {


    public CountryAsyncTask(Context context, View baseView) {
        super(context, baseView);
    }

    @Override
    protected Object doInBackground(Object... params) {
        ArrayList<RadioCountry> radioCountries = new ArrayList<>();
        if (params[1].equals("Northern America") || params[1].equals("South America"))  // shitty provider
            params[1] = "Americas";
        String where = RadioDBContract.RadioCountry.COLUMN_NAME_REGION + "='" + params[1] + "'"; // only countries for selected continent

        Cursor c = context.getContentResolver().query(RadioContentProvider.URI_COUNTRIES, null, where, null, null);
        if (c.moveToFirst()) {
            do {
                radioCountries.add(DirbleHelper.buildRadioCountry(c));
            } while (c.moveToNext());
            c.close();
            return radioCountries;
        } else {
            c.close();
            throw new IllegalStateException("No Countries in Database, please update the application!");
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        if (o != null) {
            CountryAdapter countryAdapter = new CountryAdapter(context, (ArrayList<RadioCountry>) o);
            listView.setAdapter(countryAdapter);
        }

        super.onPostExecute(o);
    }
}
