package com.hyperion.dashdroid.radio.async;


import android.content.Context;
import android.database.Cursor;
import android.view.View;

import com.hyperion.dashdroid.radio.adapter.ContinentAdapter;
import com.hyperion.dashdroid.radio.data.RadioContinent;
import com.hyperion.dashdroid.radio.db.RadioContentProvider;
import com.hyperion.dashdroid.radio.dirble.DirbleHelper;

import java.util.ArrayList;

public class ContinentAsyncTask extends DirbleAsyncTask {

    public ContinentAsyncTask(Context context, View baseView) {
        super(context, baseView);
    }

    @Override
    protected Object doInBackground(Object... params) {
        ArrayList<RadioContinent> radioContinents = new ArrayList<>();

        Cursor c = context.getContentResolver().query(RadioContentProvider.URI_CONTINENTS, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                radioContinents.add(DirbleHelper.buildRadioContinent(c));
            } while (c.moveToNext());
            return radioContinents;
        } else
            throw new IllegalStateException("No Continents in Database, please update the application!");
    }

    @Override
    protected void onPostExecute(Object o) {
        if (o != null) {
            ContinentAdapter continentAdapter = new ContinentAdapter(context, (ArrayList<RadioContinent>) o);
            listView.setAdapter(continentAdapter);
        }

        super.onPostExecute(o);
    }
}