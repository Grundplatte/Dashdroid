package com.hyperion.dashdroid.radio.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.hyperion.dashdroid.radio.data.RadioCategory;
import com.hyperion.dashdroid.radio.data.RadioContinent;
import com.hyperion.dashdroid.radio.data.RadioCountry;
import com.hyperion.dashdroid.radio.dirble.DirbleProvider;

import java.util.ArrayList;

public class RadioContentProvider extends ContentProvider{
    private static final String AUTHORITY = "com.hyperion.dashdroid.radio.db.RadioContentProvider";
    public static final String URL_CATEGORIES = "content://" + AUTHORITY + '/' + RadioDBContract.RadioCategory.TABLE_NAME;
    public static final String URL_CHANNELS = "content://" + AUTHORITY + '/' + RadioDBContract.RadioChannel.TABLE_NAME;
    public static final String URL_STREAMS = "content://" + AUTHORITY + '/' + RadioDBContract.RadioStream.TABLE_NAME;
    public static final String URL_CONTINENTS = "content://" + AUTHORITY + '/' + RadioDBContract.RadioContinent.TABLE_NAME;
    public static final String URL_COUNTRIES = "content://" + AUTHORITY + '/' + RadioDBContract.RadioCountry.TABLE_NAME;
    public static final String URL_LASTCHANNEL = "content://" + AUTHORITY + '/' + RadioDBContract.RadioLastChannel.TABLE_NAME;
    public static final int CATEGORIES = 1;
    public static final int CATEGORY_ID = 2;
    public static final int CHANNELS = 3;
    public static final int CHANNEL_ID = 4;
    public static final int STREAMS = 5;
    public static final int STREAM_ID = 6;
    public static final int CONTINENTS = 7;
    public static final int CONTINENT_ID = 8;
    public static final int COUNTRIES = 9;
    public static final int COUNTRY_ID = 10;
    public static final int LASTCHANNEL = 11;
    public static final Uri URI_CATEGORIES = Uri.parse(URL_CATEGORIES);
    public static final Uri URI_CHANNELS = Uri.parse(URL_CHANNELS);
    public static final Uri URI_STREAMS = Uri.parse(URL_STREAMS);
    public static final Uri URI_CONTINENTS = Uri.parse(URL_CONTINENTS);
    public static final Uri URI_COUNTRIES = Uri.parse(URL_COUNTRIES);
    public static final Uri URI_LASTCHANNEL = Uri.parse(URL_LASTCHANNEL);
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY, RadioDBContract.RadioCategory.TABLE_NAME, CATEGORIES);
        uriMatcher.addURI(AUTHORITY, RadioDBContract.RadioCategory.TABLE_NAME + "/#", CATEGORY_ID);
        uriMatcher.addURI(AUTHORITY, RadioDBContract.RadioChannel.TABLE_NAME, CHANNELS);
        uriMatcher.addURI(AUTHORITY, RadioDBContract.RadioChannel.TABLE_NAME + "/#", CHANNEL_ID);
        uriMatcher.addURI(AUTHORITY, RadioDBContract.RadioStream.TABLE_NAME, STREAMS);
        uriMatcher.addURI(AUTHORITY, RadioDBContract.RadioStream.TABLE_NAME + "/#", STREAM_ID);
        uriMatcher.addURI(AUTHORITY, RadioDBContract.RadioContinent.TABLE_NAME, CONTINENTS);
        uriMatcher.addURI(AUTHORITY, RadioDBContract.RadioContinent.TABLE_NAME + "/#", CONTINENT_ID);
        uriMatcher.addURI(AUTHORITY, RadioDBContract.RadioCountry.TABLE_NAME, COUNTRIES);
        uriMatcher.addURI(AUTHORITY, RadioDBContract.RadioCountry.TABLE_NAME + "/#", COUNTRY_ID);
        uriMatcher.addURI(AUTHORITY, RadioDBContract.RadioLastChannel.TABLE_NAME, LASTCHANNEL);
    }

    private RadioDBHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        dbHelper = new RadioDBHelper(getContext());
        db = dbHelper.getWritableDatabase();

        return db!=null;
    }

    private void loadCategoryData() {
        ArrayList<RadioCategory> categories = DirbleProvider.getInstance().getCategories();

        // write to db
        for (RadioCategory category : categories) {

            ContentValues values = new ContentValues();
            values.put(RadioDBContract.RadioCategory.COLUMN_NAME_TITLE, category.getTitle());
            values.put(RadioDBContract.RadioCategory.COLUMN_NAME_CATEGORY_ID, category.getID());
            values.put(RadioDBContract.RadioCategory.COLUMN_NAME_DESCRIPTION, category.getDescription());
            values.put(RadioDBContract.RadioCategory.COLUMN_NAME_ANCESTRY, category.getAncestry());
            values.put(RadioDBContract.RadioCategory.COLUMN_NAME_SLUG, category.getSlug());
            db.insert(RadioDBContract.RadioCategory.TABLE_NAME, "", values);
        }
    }

    private void loadContinentData() {
        ArrayList<RadioContinent> continents = DirbleProvider.getInstance().getContinents();

        // write to db
        for (RadioContinent continent : continents) {

            ContentValues values = new ContentValues();
            values.put(RadioDBContract.RadioContinent.COLUMN_NAME_CONTINENT_ID, continent.getID());
            values.put(RadioDBContract.RadioContinent.COLUMN_NAME_NAME, continent.getName());
            values.put(RadioDBContract.RadioContinent.COLUMN_NAME_SLUG, continent.getSlug());
            db.insert(RadioDBContract.RadioContinent.TABLE_NAME, "", values);
        }
    }

    private void loadCountryData() {
        ArrayList<RadioCountry> countries = DirbleProvider.getInstance().getCountries();

        // write to db
        for (RadioCountry country : countries) {

            ContentValues values = new ContentValues();
            values.put(RadioDBContract.RadioCountry.COLUMN_NAME_NAME, country.getName());
            values.put(RadioDBContract.RadioCountry.COLUMN_NAME_COUNTRY_CODE, country.getCountry_code());
            values.put(RadioDBContract.RadioCountry.COLUMN_NAME_REGION, country.getRegion());
            values.put(RadioDBContract.RadioCountry.COLUMN_NAME_SUBREGION, country.getSubregion());
            db.insert(RadioDBContract.RadioCountry.TABLE_NAME, "", values);
        }
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;

        switch(uriMatcher.match(uri)){
            case CATEGORIES:
                if (sortOrder == null || sortOrder == "") {
                    sortOrder = RadioDBContract.RadioCategory.COLUMN_NAME_TITLE;
                }
                cursor = db.query(RadioDBContract.RadioCategory.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);

                if (!cursor.moveToFirst()) {
                    // get data from dirble
                    loadCategoryData();
                    cursor = db.query(RadioDBContract.RadioCategory.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                }
                break;
            case CATEGORY_ID:
                if (sortOrder == null || sortOrder == "") {
                    sortOrder = RadioDBContract.RadioCategory.COLUMN_NAME_TITLE;
                }
                cursor = db.query(RadioDBContract.RadioCategory.TABLE_NAME, projection,
                        RadioDBContract.RadioCategory._ID + " = " + uri.getPathSegments().get(1) +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs, null, null, sortOrder);
                if (!cursor.moveToFirst()) {
                    // get data from dirble
                    loadCategoryData();
                    cursor = db.query(RadioDBContract.RadioCategory.TABLE_NAME, projection,
                            RadioDBContract.RadioCategory._ID + " = " + uri.getPathSegments().get(1) +
                                    (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs, null, null, sortOrder);
                }
                break;
            case CHANNELS:
                if (sortOrder == null || sortOrder == "") {
                    sortOrder = RadioDBContract.RadioChannel.COLUMN_NAME_NAME;
                }
                cursor = db.query(RadioDBContract.RadioChannel.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CHANNEL_ID:
                if (sortOrder == null || sortOrder == "") {
                    sortOrder = RadioDBContract.RadioChannel.COLUMN_NAME_NAME;
                }
                cursor = db.query(RadioDBContract.RadioChannel.TABLE_NAME, projection,
                        RadioDBContract.RadioChannel._ID + " = " + uri.getPathSegments().get(1) +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs, null, null, sortOrder);
                break;
            case STREAMS:
                if (sortOrder == null || sortOrder == "") {
                    sortOrder = RadioDBContract.RadioStream.COLUMN_NAME_BITRATE;
                }
                cursor = db.query(RadioDBContract.RadioStream.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case STREAM_ID:
                if (sortOrder == null || sortOrder == "") {
                    sortOrder = RadioDBContract.RadioStream.COLUMN_NAME_BITRATE;
                }
                cursor = db.query(RadioDBContract.RadioStream.TABLE_NAME, projection,
                        RadioDBContract.RadioStream._ID + " = " + uri.getPathSegments().get(1) +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs, null, null, sortOrder);
                break;
            case CONTINENTS:
                if (sortOrder == null || sortOrder == "") {
                    sortOrder = RadioDBContract.RadioContinent.COLUMN_NAME_NAME;
                }
                cursor = db.query(RadioDBContract.RadioContinent.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);

                if (!cursor.moveToFirst()) {
                    // get data from dirble
                    loadContinentData();
                    cursor = db.query(RadioDBContract.RadioContinent.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                }
                break;
            case CONTINENT_ID:
                if (sortOrder == null || sortOrder == "") {
                    sortOrder = RadioDBContract.RadioContinent.COLUMN_NAME_NAME;
                }
                cursor = db.query(RadioDBContract.RadioContinent.TABLE_NAME, projection,
                        RadioDBContract.RadioContinent._ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs, null, null, sortOrder);
                if (!cursor.moveToFirst()) {
                    // get data from dirble
                    loadContinentData();
                    cursor = db.query(RadioDBContract.RadioContinent.TABLE_NAME, projection,
                            RadioDBContract.RadioContinent._ID + " = " + uri.getPathSegments().get(1) +
                                    (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs, null, null, sortOrder);
                }
                break;
            case COUNTRIES:
                if (sortOrder == null || sortOrder == "") {
                    sortOrder = RadioDBContract.RadioCountry.COLUMN_NAME_NAME;
                }
                cursor = db.query(RadioDBContract.RadioCountry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);

                if (!cursor.moveToFirst()) {
                    // get data from dirble
                    loadCountryData();
                    cursor = db.query(RadioDBContract.RadioCountry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                }
                break;
            case COUNTRY_ID:
                if (sortOrder == null || sortOrder == "") {
                    sortOrder = RadioDBContract.RadioCountry.COLUMN_NAME_NAME;
                }
                cursor = db.query(RadioDBContract.RadioCountry.TABLE_NAME, projection,
                        RadioDBContract.RadioCountry._ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs, null, null, sortOrder);
                if (!cursor.moveToFirst()) {
                    // get data from dirble
                    loadCountryData();
                    cursor = db.query(RadioDBContract.RadioCountry.TABLE_NAME, projection,
                            RadioDBContract.RadioCountry._ID + " = " + uri.getPathSegments().get(1) +
                                    (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs, null, null, sortOrder);
                }
                break;
            case LASTCHANNEL:
                cursor = db.query(RadioDBContract.RadioLastChannel.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new SQLException("Table not known: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch(uriMatcher.match(uri)){
            case CATEGORIES:
                return "vnd.android.cursor.dir/vnd.hyperion.dashdroid.radio.data.categories";
            case CATEGORY_ID:
                return "vnd.android.cursor.item/vnd.hyperion.dashdroid.radio.data.categories";
            case CHANNELS:
                return "vnd.android.cursor.dir/vnd.hyperion.dashdroid.radio.data.channels";
            case CHANNEL_ID:
                return "vnd.android.cursor.item/vnd.hyperion.dashdroid.radio.data.channels";
            case STREAMS:
                return "vnd.android.cursor.dir/vnd.hyperion.dashdroid.radio.data.streams";
            case STREAM_ID:
                return "vnd.android.cursor.item/vnd.hyperion.dashdroid.radio.data.streams";
            case CONTINENTS:
                return "vnd.android.cursor.item/vnd.hyperion.dashdroid.radio.data.continents";
            case CONTINENT_ID:
                return "vnd.android.cursor.item/vnd.hyperion.dashdroid.radio.data.continents";
            case COUNTRIES:
                return "vnd.android.cursor.item/vnd.hyperion.dashdroid.radio.data.countries";
            case COUNTRY_ID:
                return "vnd.android.cursor.item/vnd.hyperion.dashdroid.radio.data.countries";
            case LASTCHANNEL:
                return "vnd.android.cursor.item/vnd.hyperion.dashdroid.radio.data.lastchannel";
            default:
                throw new SQLException("Table not known: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Adds a new row to a table
        long rowID;
        switch(uriMatcher.match(uri)){
            case CATEGORIES:
            case CATEGORY_ID:
                rowID = db.insert(RadioDBContract.RadioCategory.TABLE_NAME, "", values);

                if(rowID>=0) {
                    Uri _uri = ContentUris.withAppendedId(URI_CATEGORIES, rowID);
                    getContext().getContentResolver().notifyChange(_uri, null);
                    return _uri;
                }
                break;
            case CHANNELS:
            case CHANNEL_ID:
                rowID = db.insert(RadioDBContract.RadioChannel.TABLE_NAME, "", values);

                if(rowID>=0) {
                    Uri _uri = ContentUris.withAppendedId(URI_CHANNELS, rowID);
                    getContext().getContentResolver().notifyChange(_uri, null);
                    return _uri;
                }
                break;
            case STREAMS:
            case STREAM_ID:
                rowID = db.insert(RadioDBContract.RadioStream.TABLE_NAME, "", values);

                if(rowID>=0) {
                    Uri _uri = ContentUris.withAppendedId(URI_STREAMS, rowID);
                    getContext().getContentResolver().notifyChange(_uri, null);
                    return _uri;
                }
                break;
            case CONTINENTS:
            case CONTINENT_ID:
                rowID = db.insert(RadioDBContract.RadioContinent.TABLE_NAME, "", values);

                if (rowID >= 0) {
                    Uri _uri = ContentUris.withAppendedId(URI_CONTINENTS, rowID);
                    getContext().getContentResolver().notifyChange(_uri, null);
                    return _uri;
                }
                break;
            case COUNTRIES:
            case COUNTRY_ID:
                rowID = db.insert(RadioDBContract.RadioCountry.TABLE_NAME, "", values);

                if (rowID >= 0) {
                    Uri _uri = ContentUris.withAppendedId(URI_COUNTRIES, rowID);
                    getContext().getContentResolver().notifyChange(_uri, null);
                    return _uri;
                }
                break;
            case LASTCHANNEL:
                db.execSQL(RadioDBContract.RadioLastChannel.SQL_DELETE_CHANNELS);
                rowID = db.insert(RadioDBContract.RadioCountry.TABLE_NAME, "", values);

                if (rowID >= 0) {
                    Uri _uri = ContentUris.withAppendedId(URI_COUNTRIES, rowID);
                    getContext().getContentResolver().notifyChange(_uri, null);
                    return _uri;
                }
                break;
            default:
                throw new SQLException("Table not known: " + uri);
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Deletes the specified rows
        int count;
        switch(uriMatcher.match(uri)){
            case CATEGORIES:
                count = db.delete(RadioDBContract.RadioCategory.TABLE_NAME, selection, selectionArgs);
                break;
            case CATEGORY_ID:
                count = db.delete(RadioDBContract.RadioCategory.TABLE_NAME,
                        RadioDBContract.RadioCategory._ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            case CHANNELS:
                count = db.delete(RadioDBContract.RadioChannel.TABLE_NAME, selection, selectionArgs);
                break;
            case CHANNEL_ID:
                count = db.delete(RadioDBContract.RadioChannel.TABLE_NAME,
                        RadioDBContract.RadioChannel._ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            case STREAMS:
                count = db.delete(RadioDBContract.RadioStream.TABLE_NAME, selection, selectionArgs);
                break;
            case STREAM_ID:
                count = db.delete(RadioDBContract.RadioStream.TABLE_NAME,
                        RadioDBContract.RadioStream._ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            case CONTINENTS:
                count = db.delete(RadioDBContract.RadioContinent.TABLE_NAME, selection, selectionArgs);
                break;
            case CONTINENT_ID:
                count = db.delete(RadioDBContract.RadioContinent.TABLE_NAME,
                        RadioDBContract.RadioContinent._ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            case COUNTRIES:
                count = db.delete(RadioDBContract.RadioCountry.TABLE_NAME, selection, selectionArgs);
                break;
            case COUNTRY_ID:
                count = db.delete(RadioDBContract.RadioCountry.TABLE_NAME,
                        RadioDBContract.RadioCountry._ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new SQLException("Table not known: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // Updates the specified rows
        int count;
        switch(uriMatcher.match(uri)){
            case CATEGORIES:
                count = db.update(RadioDBContract.RadioCategory.TABLE_NAME, values, selection, selectionArgs);
                break;
            case CATEGORY_ID:
                count = db.update(RadioDBContract.RadioCategory.TABLE_NAME, values,
                        RadioDBContract.RadioCategory._ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            case CHANNELS:
                count = db.update(RadioDBContract.RadioChannel.TABLE_NAME, values, selection, selectionArgs);
                break;
            case CHANNEL_ID:
                count = db.update(RadioDBContract.RadioChannel.TABLE_NAME, values,
                        RadioDBContract.RadioChannel._ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            case STREAMS:
                count = db.update(RadioDBContract.RadioStream.TABLE_NAME, values, selection, selectionArgs);
                break;
            case STREAM_ID:
                count = db.update(RadioDBContract.RadioStream.TABLE_NAME, values,
                        RadioDBContract.RadioStream._ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            case CONTINENTS:
                count = db.update(RadioDBContract.RadioContinent.TABLE_NAME, values, selection, selectionArgs);
                break;
            case CONTINENT_ID:
                count = db.update(RadioDBContract.RadioContinent.TABLE_NAME, values,
                        RadioDBContract.RadioContinent._ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            case COUNTRIES:
                count = db.update(RadioDBContract.RadioCountry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case COUNTRY_ID:
                count = db.update(RadioDBContract.RadioCountry.TABLE_NAME, values,
                        RadioDBContract.RadioCountry._ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            case LASTCHANNEL:
                count = db.update(RadioDBContract.RadioLastChannel.TABLE_NAME, values,
                        RadioDBContract.RadioLastChannel._ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new SQLException("Table not known: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
