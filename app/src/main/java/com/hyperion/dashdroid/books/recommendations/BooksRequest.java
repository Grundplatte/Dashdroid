package com.hyperion.dashdroid.books.recommendations;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Valdrin on 6/4/2016.
 */
public class BooksRequest {

    private Bookshelf bookshelf;

    //TODO: Method to test the request in Google Books Api
    public Bookshelf request() {

        String url = "https://www.googleapis.com/books/v1/volumes?q=subject:Sports&printType=books&maxResults=20&startIndex=0";

        SyncHttpClient client = new SyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                bookshelf = new Bookshelf();
                String json = new String(responseBody);

                try {
                    JSONObject object = new JSONObject(json);
                    JSONArray array = object.getJSONArray("items");

                    for (int i = 0; i < array.length(); i++) {
                        BooksItem booksItem = new BooksItem();
                        JSONObject item = array.getJSONObject(i);

                        JSONObject volumeInfo = item.getJSONObject("volumeInfo");
                        String title = volumeInfo.getString("title");
                        booksItem.setTitle(title);

                        JSONArray authors = volumeInfo.getJSONArray("authors");
                        String author = authors.getString(0);
                        booksItem.setAuthor(author);

                        JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                        String imageLink = imageLinks.getString("smallThumbnail");
                        booksItem.setBookThumbnail(imageLink);

                        double rating = 0;
                        if(volumeInfo.has("averageRating")) {
                            rating = volumeInfo.getDouble("averageRating");
                        }
                        booksItem.setRating(rating);

                        bookshelf.addBook(booksItem);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return bookshelf;
    }

}
