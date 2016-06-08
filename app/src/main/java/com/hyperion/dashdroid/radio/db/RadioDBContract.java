package com.hyperion.dashdroid.radio.db;

import android.provider.BaseColumns;

public final class RadioDBContract {
    public static final  int    DATABASE_VERSION = 1;
    public static final  String DATABASE_NAME = "radio.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String FLOAT_TYPE = " REAL";
    private static final String COMMA_SEP = ",";

    private RadioDBContract() {}

    public  static abstract class RadioCategory implements BaseColumns {
        public static final String TABLE_NAME = "radiocategory";
        public static final String COLUMN_NAME_CATEGORY_ID = "categoryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_SLUG = "slug";
        public static final String COLUMN_NAME_ANCESTRY = "ancestry";

        public static final String SQL_CREATE_CATEGORIES =
                "CREATE TABLE " + RadioCategory.TABLE_NAME + " (" +
                        RadioCategory._ID + " INTEGER PRIMARY KEY," +
                        RadioCategory.COLUMN_NAME_CATEGORY_ID + INT_TYPE + COMMA_SEP +
                        RadioCategory.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                        RadioCategory.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                        RadioCategory.COLUMN_NAME_SLUG + TEXT_TYPE + COMMA_SEP +
                        RadioCategory.COLUMN_NAME_ANCESTRY + INT_TYPE + ")";

        public static final String SQL_DELETE_CATEGORIES = "DROP TABLE IF EXISTS " + RadioCategory.TABLE_NAME;
    }

    public static abstract class RadioChannel implements BaseColumns {
        public static final String TABLE_NAME = "radiochannel";
        public static final String COLUMN_NAME_CHANNEL_ID = "channelid";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_COUNTRY = "country";
        public static final String COLUMN_NAME_IMAGEURL = "imageurl";
        public static final String COLUMN_NAME_THUMBURL = "thumburl";
        public static final String COLUMN_NAME_SLUG = "slug";
        public static final String COLUMN_NAME_WEBSITE = "website";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_FAVORITE = "favorite";

        public static final String SQL_CREATE_CHANNELS =
                "CREATE TABLE " + RadioChannel.TABLE_NAME + " (" +
                        RadioChannel._ID + " INTEGER PRIMARY KEY," +
                        RadioChannel.COLUMN_NAME_CHANNEL_ID + INT_TYPE + COMMA_SEP +
                        RadioChannel.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                        RadioChannel.COLUMN_NAME_COUNTRY + TEXT_TYPE + COMMA_SEP +
                        RadioChannel.COLUMN_NAME_IMAGEURL + TEXT_TYPE + COMMA_SEP +
                        RadioChannel.COLUMN_NAME_THUMBURL + TEXT_TYPE + COMMA_SEP +
                        RadioChannel.COLUMN_NAME_SLUG + TEXT_TYPE + COMMA_SEP +
                        RadioChannel.COLUMN_NAME_WEBSITE + TEXT_TYPE + COMMA_SEP +
                        RadioChannel.COLUMN_NAME_CATEGORY + INT_TYPE + ")";

        public static final String SQL_DELETE_CHANNELS = "DROP TABLE IF EXISTS " + RadioChannel.TABLE_NAME;
    }

    public static abstract class RadioStream implements BaseColumns {
        public static final String TABLE_NAME = "radiostream";
        public static final String COLUMN_NAME_STREAM = "stream";
        public static final String COLUMN_NAME_BITRATE = "bitrate";
        public static final String COLUMN_NAME_CONTENTTYPE = "contenttype";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_CHANNEL = "channel";

        public static final String SQL_CREATE_STREAMS =
                "CREATE TABLE " + RadioStream.TABLE_NAME + " (" +
                        RadioStream._ID + " INTEGER PRIMARY KEY," +
                        RadioStream.COLUMN_NAME_STREAM + TEXT_TYPE + COMMA_SEP +
                        RadioStream.COLUMN_NAME_BITRATE + INT_TYPE + COMMA_SEP +
                        RadioStream.COLUMN_NAME_CONTENTTYPE + TEXT_TYPE + COMMA_SEP +
                        RadioStream.COLUMN_NAME_STATUS + INT_TYPE + COMMA_SEP +
                        RadioStream.COLUMN_NAME_CHANNEL + INT_TYPE + ")";

        public static final String SQL_DELETE_STREAMS = "DROP TABLE IF EXISTS " + RadioStream.TABLE_NAME;
    }
}
