package com.hyperion.dashdroid.radio.async;

import android.content.Context;
import android.database.Cursor;
import android.view.View;

import com.hyperion.dashdroid.radio.adapter.CategoryAdapter;
import com.hyperion.dashdroid.radio.data.RadioCategory;
import com.hyperion.dashdroid.radio.db.RadioContentProvider;
import com.hyperion.dashdroid.radio.db.RadioDBContract;
import com.hyperion.dashdroid.radio.dirble.DirbleHelper;

import java.util.ArrayList;

public class CategoryAsyncTask extends DirbleAsyncTask {

    public CategoryAsyncTask(Context context, View baseView) {
        super(context, baseView);
    }

    @Override
    protected Object doInBackground(Object... params) {
        ArrayList<RadioCategory> radioCategories = new ArrayList<>();

        String where = RadioDBContract.RadioCategory.COLUMN_NAME_ANCESTRY + "=" + params[0]; // only root categories

        Cursor c = context.getContentResolver().query(RadioContentProvider.URI_CATEGORIES, null, where, null, null);
        if (c.moveToFirst()) {
            do {
                radioCategories.add(DirbleHelper.buildRadioCategory(c));
            } while (c.moveToNext());
            c.close();
            return radioCategories;
        } else {
            c.close();
            throw new IllegalStateException("No Categories in Database, please update the application!");
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        if (o != null) {
            CategoryAdapter categoryAdapter = new CategoryAdapter(context, (ArrayList<RadioCategory>) o);
            listView.setAdapter(categoryAdapter);
        }

        super.onPostExecute(o);
    }
}