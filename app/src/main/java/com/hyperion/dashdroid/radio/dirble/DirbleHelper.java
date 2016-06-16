package com.hyperion.dashdroid.radio.dirble;


import android.content.ContentValues;
import android.database.Cursor;

import com.hyperion.dashdroid.radio.data.RadioCategory;
import com.hyperion.dashdroid.radio.data.RadioChannel;
import com.hyperion.dashdroid.radio.data.RadioContinent;
import com.hyperion.dashdroid.radio.data.RadioCountry;
import com.hyperion.dashdroid.radio.data.RadioStream;
import com.hyperion.dashdroid.radio.db.RadioDBContract;

import java.util.ArrayList;

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

    public static final RadioChannel buildRadioChannel(Cursor channels_c, Cursor streams_c) {
        ArrayList<RadioStream> radioStreams = new ArrayList<>();
        // get streams for Channel
        do {
            RadioStream radioStream = new RadioStream(streams_c.getString(streams_c.getColumnIndex(RadioDBContract.RadioStream.COLUMN_NAME_STREAM)),
                    streams_c.getInt(streams_c.getColumnIndex(RadioDBContract.RadioStream.COLUMN_NAME_BITRATE)),
                    streams_c.getString(streams_c.getColumnIndex(RadioDBContract.RadioStream.COLUMN_NAME_CONTENTTYPE)),
                    streams_c.getInt(streams_c.getColumnIndex(RadioDBContract.RadioStream.COLUMN_NAME_STATUS)));

            radioStreams.add(radioStream);
        } while (streams_c.moveToNext());


        // id, title, description, slug, ancestry
        RadioChannel radioChannel = new RadioChannel(channels_c.getInt(channels_c.getColumnIndex(RadioDBContract.RadioChannel.COLUMN_NAME_CHANNEL_ID)),
                channels_c.getString(channels_c.getColumnIndex(RadioDBContract.RadioChannel.COLUMN_NAME_NAME)),
                channels_c.getString(channels_c.getColumnIndex(RadioDBContract.RadioChannel.COLUMN_NAME_COUNTRY)),
                channels_c.getString(channels_c.getColumnIndex(RadioDBContract.RadioChannel.COLUMN_NAME_DESCRIPTION)),
                channels_c.getString(channels_c.getColumnIndex(RadioDBContract.RadioChannel.COLUMN_NAME_IMAGEURL)),
                channels_c.getString(channels_c.getColumnIndex(RadioDBContract.RadioChannel.COLUMN_NAME_THUMBURL)),
                channels_c.getString(channels_c.getColumnIndex(RadioDBContract.RadioChannel.COLUMN_NAME_SLUG)),
                channels_c.getString(channels_c.getColumnIndex(RadioDBContract.RadioChannel.COLUMN_NAME_WEBSITE)),
                radioStreams);

        return radioChannel;
    }

    public static final RadioContinent buildRadioContinent(Cursor c) {
        // id, name, slug
        RadioContinent radioContinent = new RadioContinent(c.getInt(c.getColumnIndex(RadioDBContract.RadioContinent.COLUMN_NAME_CONTINENT_ID)),
                c.getString(c.getColumnIndex(RadioDBContract.RadioContinent.COLUMN_NAME_NAME)),
                c.getString(c.getColumnIndex(RadioDBContract.RadioContinent.COLUMN_NAME_SLUG)));

        return radioContinent;
    }

    public static final RadioCountry buildRadioCountry(Cursor c) {
        // name, country_code, region, subregion
        RadioCountry radioCountry = new RadioCountry(c.getString(c.getColumnIndex(RadioDBContract.RadioCountry.COLUMN_NAME_NAME)),
                c.getString(c.getColumnIndex(RadioDBContract.RadioCountry.COLUMN_NAME_COUNTRY_CODE)),
                c.getString(c.getColumnIndex(RadioDBContract.RadioCountry.COLUMN_NAME_REGION)),
                c.getString(c.getColumnIndex(RadioDBContract.RadioCountry.COLUMN_NAME_SUBREGION)));

        return radioCountry;
    }

    public static ContentValues getValuesForChannel(RadioChannel channel) {
        ContentValues cv = new ContentValues();
        cv.put(RadioDBContract.RadioChannel.COLUMN_NAME_CHANNEL_ID, channel.getID());
        cv.put(RadioDBContract.RadioChannel.COLUMN_NAME_NAME, channel.getName());
        cv.put(RadioDBContract.RadioChannel.COLUMN_NAME_COUNTRY, channel.getCountry());
        cv.put(RadioDBContract.RadioChannel.COLUMN_NAME_DESCRIPTION, channel.getDescription());
        cv.put(RadioDBContract.RadioChannel.COLUMN_NAME_IMAGEURL, channel.getImageUrl());
        cv.put(RadioDBContract.RadioChannel.COLUMN_NAME_THUMBURL, channel.getThumbUrl());
        cv.put(RadioDBContract.RadioChannel.COLUMN_NAME_SLUG, channel.getSlug());
        cv.put(RadioDBContract.RadioChannel.COLUMN_NAME_WEBSITE, channel.getWebsite());

        return cv;
    }

    public static ContentValues getValuesForStream(RadioStream stream, String lastPathSegment) {
        ContentValues cv = new ContentValues();
        cv.put(RadioDBContract.RadioStream.COLUMN_NAME_STREAM, stream.getStream());
        cv.put(RadioDBContract.RadioStream.COLUMN_NAME_BITRATE, stream.getBitrate());
        cv.put(RadioDBContract.RadioStream.COLUMN_NAME_CONTENTTYPE, stream.getContentType());
        cv.put(RadioDBContract.RadioStream.COLUMN_NAME_STATUS, stream.getStatus());
        cv.put(RadioDBContract.RadioStream.COLUMN_NAME_CHANNEL, Integer.valueOf(lastPathSegment));

        return cv;
    }

    // Todo not needed
    public static ContentValues getValuesForContinent(RadioContinent radioContinent) {
        ContentValues cv = new ContentValues();
        cv.put(RadioDBContract.RadioContinent.COLUMN_NAME_CONTINENT_ID, radioContinent.getID());
        cv.put(RadioDBContract.RadioContinent.COLUMN_NAME_NAME, radioContinent.getName());
        cv.put(RadioDBContract.RadioContinent.COLUMN_NAME_SLUG, radioContinent.getSlug());

        return cv;
    }

    // Todo not needed
    public static ContentValues getValuesForCountry(RadioCountry radioCountry) {
        ContentValues cv = new ContentValues();
        cv.put(RadioDBContract.RadioCountry.COLUMN_NAME_NAME, radioCountry.getName());
        cv.put(RadioDBContract.RadioCountry.COLUMN_NAME_COUNTRY_CODE, radioCountry.getCountry_code());
        cv.put(RadioDBContract.RadioCountry.COLUMN_NAME_REGION, radioCountry.getRegion());
        cv.put(RadioDBContract.RadioCountry.COLUMN_NAME_SUBREGION, radioCountry.getSubregion());

        return cv;
    }
}
