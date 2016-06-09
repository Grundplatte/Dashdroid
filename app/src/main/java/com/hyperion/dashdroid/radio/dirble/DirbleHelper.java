package com.hyperion.dashdroid.radio.dirble;


import android.database.Cursor;

import com.hyperion.dashdroid.radio.data.RadioCategory;
import com.hyperion.dashdroid.radio.db.RadioDBContract;

public class DirbleHelper {
    public static final RadioCategory buildRadioCategory(Cursor c) {
        // id, title, description, slug, ancestry
        RadioCategory radioCategory = new RadioCategory(c.getInt(c.getColumnIndex(RadioDBContract.RadioCategory.COLUMN_NAME_CATEGORY_ID)),
                c.getString(c.getColumnIndex(RadioDBContract.RadioCategory.COLUMN_NAME_TITLE)),
                c.getString(c.getColumnIndex(RadioDBContract.RadioCategory.COLUMN_NAME_DESCRIPTION)),
                c.getString(c.getColumnIndex(RadioDBContract.RadioCategory.COLUMN_NAME_SLUG)),
                c.getInt(c.getColumnIndex(RadioDBContract.RadioCategory.COLUMN_NAME_ANCESTRY)));

        return radioCategory;
    }
}
