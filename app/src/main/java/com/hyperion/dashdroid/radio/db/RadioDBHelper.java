package com.hyperion.dashdroid.radio.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RadioDBHelper extends SQLiteOpenHelper {
    public RadioDBHelper(Context context) {
        super(context, RadioDBContract.DATABASE_NAME, null, RadioDBContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RadioDBContract.RadioCategory.SQL_CREATE_CATEGORIES);
        db.execSQL(RadioDBContract.RadioChannel.SQL_CREATE_CHANNELS);
        db.execSQL(RadioDBContract.RadioStream.SQL_CREATE_STREAMS);
        db.execSQL(RadioDBContract.RadioContinent.SQL_CREATE_CONTINENTS);
        db.execSQL(RadioDBContract.RadioCountry.SQL_CREATE_COUNTRIES);
        db.execSQL(RadioDBContract.RadioLastChannel.SQL_CREATE_CHANNELS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(RadioDBContract.RadioCategory.SQL_DELETE_CATEGORIES);
        db.execSQL(RadioDBContract.RadioCategory.SQL_CREATE_CATEGORIES);

        db.execSQL(RadioDBContract.RadioContinent.SQL_DELETE_CONTINENTS);
        db.execSQL(RadioDBContract.RadioContinent.SQL_CREATE_CONTINENTS);

        db.execSQL(RadioDBContract.RadioCountry.SQL_DELETE_COUNTRIES);
        db.execSQL(RadioDBContract.RadioCountry.SQL_CREATE_COUNTRIES);
    }
}
