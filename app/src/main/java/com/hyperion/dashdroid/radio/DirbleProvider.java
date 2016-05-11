package com.hyperion.dashdroid.radio;

import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rainer on 11.05.2016.
 */

// TODO: move all of the following or some parts to a service?

public class DirbleProvider {

    private final String API_KEY = "token=17f3be7fbca8347d9b835f63a8";
    private final String SEARCH_URL = "http://api.dirble.com/v2/search/";
    private final String CATEGORIES_URL = "http://api.dirble.com/v2/category/tree";
    private final String CATEGORY_STATIONS = "http://api.dirble.com/v2/category/<ID>/stations";

    private static DirbleProvider instance = null;

    public static DirbleProvider getInstance() {
        if(instance == null)
            instance = new DirbleProvider();

        return instance;
    }

    private DirbleProvider() {

    }

    public ArrayList<RadioChannel> search(String query) {
        ArrayList<RadioChannel> radioChannels = new ArrayList<>();

        try {
            URL search_url = new URL(SEARCH_URL + query + "&" + API_KEY);
            URLConnection connection = search_url.openConnection();
            radioChannels = readJSONStream(connection.getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return radioChannels;
    }

    public ArrayList<RadioChannel> getCategories() {
        ArrayList<RadioChannel> radioChannels = new ArrayList<>();

        return radioChannels;
    }

    public ArrayList<RadioChannel> getStationsForCategory(String category) {
        ArrayList<RadioChannel> radioChannels = new ArrayList<>();

        return radioChannels;
    }

    private ArrayList<RadioChannel> readJSONStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));

        try {
            return readChannelArray(reader);
        }
        finally {
                reader.close();
        }
    }

    private ArrayList<RadioChannel> readChannelArray(JsonReader reader) throws IOException{
        ArrayList<RadioChannel> channelArray = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()){
            channelArray.add(readChannel(reader));
        }
        reader.endArray();
        return channelArray;
    }

    private RadioChannel readChannel(JsonReader reader) throws IOException{
        int ID = -1;
        String name = null;
        String country = null;
        String description = null;
        String imageUrl = null;
        String thumbUrl = null;
        String slug = null;
        String website = null;
        ArrayList<RadioStream> radioStreams = new ArrayList<>();
        ArrayList<RadioChannelCategory> radioChannelCategories = new ArrayList<>();

        reader.beginObject();
        while (reader.hasNext()){
            String fieldName = reader.nextName();
            if(fieldName.equals("id")){
                ID = reader.nextInt();
            }
            else if(fieldName.equals("name")){
                name = reader.nextString();
            }
            else if(fieldName.equals("country")){
                country = reader.nextString();
            }
            else if(fieldName.equals("description")){
                description = reader.nextString();
            }
            else if(fieldName.equals("image")){
                reader.beginObject();
                reader.beginObject();
                while(reader.hasNext()) {
                    fieldName = reader.nextName();
                    if (fieldName.equals("url") && reader.peek() != JsonToken.NULL) {
                        imageUrl = reader.nextString();
                    }
                    else if(fieldName.equals("thumb")){
                        reader.beginObject();
                        fieldName = reader.nextName();
                        if (fieldName.equals("url") && reader.peek() != JsonToken.NULL) {
                            thumbUrl = reader.nextString();
                        }
                        else {
                            reader.skipValue();
                        }
                        reader.endObject();
                    }
                }
                reader.endObject();
                reader.endObject();
            }
            else if(fieldName.equals("slug")){
                slug = reader.nextString();
            }
            else if(fieldName.equals("website")){
                website = reader.nextString();
            }
            else if(fieldName.equals("streams")){
                reader.beginArray();
                while (reader.hasNext()){
                    radioStreams.add(readRadioStream(reader));
                }
                reader.endArray();
            }
            else if(fieldName.equals("categories")){
                reader.beginArray();
                while (reader.hasNext()){
                    radioChannelCategories.add(readRadioCategory(reader));
                }
                reader.endArray();
            }
            else{
                reader.skipValue();
            }
        }
        reader.endObject();

        return new RadioChannel(ID, name, country, description, imageUrl, thumbUrl, slug, website, radioStreams, radioChannelCategories);
    }

    private RadioStream readRadioStream(JsonReader reader) throws IOException{

        String stream = null;
        int bitrate = -1;
        String contentType = null;
        int status = -1;
        reader.beginObject();
        while (reader.hasNext()){
            String fieldName = reader.nextName();
            if(fieldName.equals("stream")){
                stream = reader.nextString();
            }
            else if(fieldName.equals("bitrate")){
                bitrate = reader.nextInt();
            }
            else if(fieldName.equals("content_type")){
                contentType = reader.nextString();
            }
            else if(fieldName.equals("status")){
                status = reader.nextInt();
            }
            else
                reader.skipValue();
        }
        reader.endObject();

        return new RadioStream(stream, bitrate, contentType, status);
    }

    private RadioChannelCategory readRadioCategory(JsonReader reader) throws IOException{

        int ID = -1;
        String title = null;
        String description = null;
        String slug = null;
        int ancestry = -1;

        reader.beginObject();
        while (reader.hasNext()){
            String fieldName = reader.nextName();
            if(fieldName.equals("id")){
                ID = reader.nextInt();
            }
            else if(fieldName.equals("title")){
                title = reader.nextString();
            }
            else if(fieldName.equals("description")){
                description = reader.nextString();
            }
            else if(fieldName.equals("slug")){
                slug = reader.nextString();
            }
            else if(fieldName.equals("ancestry")){
                ancestry = reader.nextInt();
            }
            else
                reader.skipValue();
        }
        reader.endObject();

        return new RadioChannelCategory(ID, title, description, slug, ancestry);
    }
}
