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

/**
 * Created by Rainer on 08.06.2016.
 */
public class RadioContentProvider extends ContentProvider{
    private static final String AUTHORITY = "com.hyperion.dashdroid.radio.db.RadioContentProvider";
    public static final String URL_CATEGORIES = "content://" + AUTHORITY + '/' + RadioDBContract.RadioCategory.TABLE_NAME;
    public static final String URL_CHANNELS = "content://" + AUTHORITY + '/' + RadioDBContract.RadioChannel.TABLE_NAME;
    public static final String URL_STREAMS = "content://" + AUTHORITY + '/' + RadioDBContract.RadioStream.TABLE_NAME;
    public static final Uri URI_CATEGORIES = Uri.parse(URL_CATEGORIES);
    public static final Uri URI_CHANNELS = Uri.parse(URL_CHANNELS);
    public static final Uri URI_STREAMS = Uri.parse(URL_STREAMS);

    public static final int CATEGORIES = 1;
    public static final int CATEGORY_ID = 2;
    public static final int CHANNELS = 3;
    public static final int CHANNEL_ID = 4;
    public static final int STREAMS = 5;
    public static final int STREAM_ID = 6;


    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY, RadioDBContract.RadioCategory.TABLE_NAME, CATEGORIES);
        uriMatcher.addURI(AUTHORITY, RadioDBContract.RadioCategory.TABLE_NAME + "/#", CATEGORY_ID);
        uriMatcher.addURI(AUTHORITY, RadioDBContract.RadioChannel.TABLE_NAME, CHANNELS);
        uriMatcher.addURI(AUTHORITY, RadioDBContract.RadioChannel.TABLE_NAME + "/#", CHANNEL_ID);
        uriMatcher.addURI(AUTHORITY, RadioDBContract.RadioStream.TABLE_NAME, STREAMS);
        uriMatcher.addURI(AUTHORITY, RadioDBContract.RadioStream.TABLE_NAME + "/#", STREAM_ID);
    }

    private RadioDBHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        dbHelper = new RadioDBHelper(getContext());
        db = dbHelper.getWritableDatabase();

        return db!=null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        switch(uriMatcher.match(uri)){
            case CATEGORIES:
                cursor = db.query(RadioDBContract.RadioCategory.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CATEGORY_ID:
                cursor = db.query(RadioDBContract.RadioCategory.TABLE_NAME, projection,
                        RadioDBContract.RadioCategory._ID + " = " + uri.getPathSegments().get(1) +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs, null, null, sortOrder);
                break;
            case CHANNELS:
                cursor = db.query(RadioDBContract.RadioChannel.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CHANNEL_ID:
                cursor = db.query(RadioDBContract.RadioChannel.TABLE_NAME, projection,
                        RadioDBContract.RadioChannel._ID + " = " + uri.getPathSegments().get(1) +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs, null, null, sortOrder);
                break;
            case STREAMS:
                cursor = db.query(RadioDBContract.RadioStream.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case STREAM_ID:
                cursor = db.query(RadioDBContract.RadioStream.TABLE_NAME, projection,
                        RadioDBContract.RadioStream._ID + " = " + uri.getPathSegments().get(1) +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs, null, null, sortOrder);
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
            default:
                throw new SQLException("Table not known: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
