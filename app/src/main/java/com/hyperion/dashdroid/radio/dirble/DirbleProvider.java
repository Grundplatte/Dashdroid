package com.hyperion.dashdroid.radio.dirble;

import android.util.JsonReader;
import android.util.JsonToken;

import com.hyperion.dashdroid.radio.data.RadioCategory;
import com.hyperion.dashdroid.radio.data.RadioChannel;
import com.hyperion.dashdroid.radio.data.RadioContinent;
import com.hyperion.dashdroid.radio.data.RadioCountry;
import com.hyperion.dashdroid.radio.data.RadioStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Rainer on 11.05.2016.
 */

public class DirbleProvider {

	private static DirbleProvider instance = null;
	private final String API_KEY = "token=17f3be7fbca8347d9b835f63a8";
	private final String SEARCH_URL = "http://api.dirble.com/v2/search/";
	private final String CATEGORIES_URL = "http://api.dirble.com/v2/categories/";
	private final String CATEGORY_STATIONS_URL = "http://api.dirble.com/v2/category/<ID>/stations";
	private final String POPULAR_CHANNELS_URL = "http://api.dirble.com/v2/stations/popular";
	private final String CONTINENTS_URL = "http://api.dirble.com/v2/continents";
	private final String COUNTRIES_URL = "http://api.dirble.com/v2/countries";
	private final String COUNTRY_CHANNELS_URL = "http://api.dirble.com/v2/countries/<code>/stations";


	private DirbleProvider() {
	}


	public static DirbleProvider getInstance() {
		if(instance == null)
			instance = new DirbleProvider();

		return instance;
	}

	public ArrayList<RadioChannel> search(String query) {
		try {
			URL request = new URL(SEARCH_URL + query + "?" + API_KEY);
			URLConnection connection = request.openConnection();
			InputStream inputStream = connection.getInputStream();
			JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

			try {
				return readChannelArray(reader);
			} finally {
				reader.close();
			}

		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// rewrite so that it tries to read the saved tree first
	public ArrayList<RadioCategory> getCategories() {

		try {
			URL request = new URL(CATEGORIES_URL + "?" + API_KEY);
			URLConnection connection = request.openConnection();
			InputStream inputStream = connection.getInputStream();
			JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

			try {
				return readCategoryArray(reader);
			} finally {
				reader.close();
			}

		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<RadioChannel> getChannelsForCategory(int categoryID) {
		try {
			String request_string = CATEGORY_STATIONS_URL + "?" + API_KEY;
			request_string = request_string.replace("<ID>", Integer.toString(categoryID));
			URL request = new URL(request_string);
			URLConnection connection = request.openConnection();
			InputStream inputStream = connection.getInputStream();
			JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

			try {
				return readChannelArray(reader);
			} finally {
				reader.close();
			}

		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<RadioChannel> getChannelsForCountry(String countryCode) {
		try {
			String request_string = COUNTRY_CHANNELS_URL + "?" + API_KEY;
			request_string = request_string.replace("<code>", countryCode);
			URL request = new URL(request_string);
			URLConnection connection = request.openConnection();
			InputStream inputStream = connection.getInputStream();
			JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

			try {
				return readChannelArray(reader);
			} finally {
				reader.close();
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<RadioChannel> getPopularChannels() {
		try {
			String request_string = POPULAR_CHANNELS_URL + "?" + API_KEY;
			URL request = new URL(request_string);
			URLConnection connection = request.openConnection();
			InputStream inputStream = connection.getInputStream();
			JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

			try {
				return readChannelArray(reader);
			} finally {
				reader.close();
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<RadioContinent> getContinents() {

		try {
			URL request = new URL(CONTINENTS_URL + "?" + API_KEY);
			URLConnection connection = request.openConnection();
			InputStream inputStream = connection.getInputStream();
			JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

			try {
				return readContinentArray(reader);
			} finally {
				reader.close();
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<RadioCountry> getCountries() {
		try {
			String request_string = COUNTRIES_URL + "?" + API_KEY;
			URL request = new URL(request_string);
			URLConnection connection = request.openConnection();
			InputStream inputStream = connection.getInputStream();
			JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

			try {
				return readCountryArray(reader);
			} finally {
				reader.close();
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private ArrayList<RadioCategory> readCategoryArray(JsonReader reader) throws IOException {
		ArrayList<RadioCategory> categoryArray = new ArrayList<>();

		reader.beginArray();
		while(reader.hasNext()) {
			categoryArray.add(readRadioCategory(reader));
		}
		reader.endArray();
		return categoryArray;
	}

	private ArrayList<RadioChannel> readChannelArray(JsonReader reader) throws IOException {
		ArrayList<RadioChannel> channelArray = new ArrayList<>();

		reader.beginArray();
		while(reader.hasNext()) {
			channelArray.add(readChannel(reader));
		}
		reader.endArray();
		return channelArray;
	}

	private ArrayList<RadioContinent> readContinentArray(JsonReader reader) throws IOException {
		ArrayList<RadioContinent> continentArray = new ArrayList<>();

		reader.beginArray();
		while (reader.hasNext()) {
			continentArray.add(readRadioContinent(reader));
		}
		reader.endArray();
		return continentArray;
	}

	private ArrayList<RadioCountry> readCountryArray(JsonReader reader) throws IOException {
		ArrayList<RadioCountry> countryArray = new ArrayList<>();

		reader.beginArray();
		while (reader.hasNext()) {
			countryArray.add(readRadioCountry(reader));
		}
		reader.endArray();
		return countryArray;
	}

	private RadioChannel readChannel(JsonReader reader) throws IOException {
		int ID = -1;
		String name = null;
		String country = null;
		String description = null;
		String imageUrl = null;
		String thumbUrl = null;
		String slug = null;
		String website = null;
		ArrayList<RadioStream> radioStreams = new ArrayList<>();
		ArrayList<RadioCategory> radioChannelCategories = new ArrayList<>();

		reader.beginObject();
		while(reader.hasNext()) {
			String fieldName = reader.nextName();
			if(fieldName.equals("id")) {
				ID = reader.nextInt();
			} else if(fieldName.equals("name")) {
				name = reader.nextString();
			} else if(fieldName.equals("country")) {
				country = reader.nextString();
			} else if(fieldName.equals("description") && reader.peek() != JsonToken.NULL) {
				description = reader.nextString();
			} else if(fieldName.equals("image")) {
				reader.beginObject();
				boolean stacked = false;

				while(reader.hasNext()) {
					fieldName = reader.nextName();
					if(fieldName.equals("image") && !stacked) {
						reader.beginObject();
						stacked = true;
					} else if(fieldName.equals("url") && reader.peek() != JsonToken.NULL) {
						imageUrl = reader.nextString();
					} else if(fieldName.equals("thumb")) {
						reader.beginObject();
						fieldName = reader.nextName();
						if(fieldName.equals("url") && reader.peek() != JsonToken.NULL) {
							thumbUrl = reader.nextString();
						} else {
							reader.skipValue();
						}
						reader.endObject();
					} else {
						reader.skipValue();
					}
				}
				if(stacked) {
					reader.endObject();
				}
				reader.endObject();
			} else if(fieldName.equals("slug")) {
				slug = reader.nextString();
			} else if(fieldName.equals("website")) {
				website = reader.nextString();
			} else if(fieldName.equals("streams")) {
				reader.beginArray();
				while(reader.hasNext()) {
					radioStreams.add(readRadioStream(reader));
				}
				reader.endArray();
			} else if(fieldName.equals("categories")) {
				reader.beginArray();
				while(reader.hasNext()) {
					radioChannelCategories.add(readRadioCategory(reader));
				}
				reader.endArray();
			} else {
				reader.skipValue();
			}
		}
		reader.endObject();

		return new RadioChannel(ID, name, country, description, imageUrl, thumbUrl, slug, website, radioStreams, radioChannelCategories);
	}

	private RadioStream readRadioStream(JsonReader reader) throws IOException {

		String stream = null;
		int bitrate = -1;
		String contentType = null;
		int status = -1;
		reader.beginObject();
		while(reader.hasNext()) {
			String fieldName = reader.nextName();
			if(fieldName.equals("stream")) {
				stream = reader.nextString();
			} else if(fieldName.equals("bitrate")) {
				bitrate = reader.nextInt();
			} else if(fieldName.equals("content_type")) {
				contentType = reader.nextString();
			} else if(fieldName.equals("status")) {
				status = reader.nextInt();
			} else
				reader.skipValue();
		}
		reader.endObject();

		return new RadioStream(stream, bitrate, contentType, status);
	}

	private RadioCategory readRadioCategory(JsonReader reader) throws IOException {

		int ID = -1;
		String title = null;
		String description = null;
		String slug = null;
		int ancestry = -1;

		reader.beginObject();
		while(reader.hasNext()) {
			String fieldName = reader.nextName();
			if(fieldName.equals("id")) {
				ID = reader.nextInt();
			} else if(fieldName.equals("title")) {
				title = reader.nextString();
			} else if(fieldName.equals("description")) {
				description = reader.nextString();
			} else if(fieldName.equals("slug")) {
				slug = reader.nextString();
			} else if(fieldName.equals("ancestry") && reader.peek() != JsonToken.NULL) {
				ancestry = reader.nextInt();
			} else
				reader.skipValue();
		}
		reader.endObject();

		return new RadioCategory(ID, title, description, slug, ancestry);
	}

	private RadioContinent readRadioContinent(JsonReader reader) throws IOException {

		int ID = -1;
		String name = null;
		String slug = null;

		reader.beginObject();
		while (reader.hasNext()) {
			String fieldName = reader.nextName();
			if (fieldName.equals("id")) {
				ID = reader.nextInt();
			} else if (fieldName.equals("name")) {
				name = reader.nextString();
			} else if (fieldName.equals("slug")) {
				slug = reader.nextString();
			} else
				reader.skipValue();
		}
		reader.endObject();

		return new RadioContinent(ID, name, slug);
	}

	private RadioCountry readRadioCountry(JsonReader reader) throws IOException {

		String name = null;
		String country_code = null;
		String region = null;
		String subregion = null;

		reader.beginObject();
		while (reader.hasNext()) {
			String fieldName = reader.nextName();
			if (fieldName.equals("name")) {
				name = reader.nextString();
			} else if (fieldName.equals("country_code")) {
				country_code = reader.nextString();
			} else if (fieldName.equals("region")) {
				region = reader.nextString();
			} else if (fieldName.equals("subregion")) {
				subregion = reader.nextString();
			} else
				reader.skipValue();
		}
		reader.endObject();

		return new RadioCountry(name, country_code, region, subregion);
	}
}
