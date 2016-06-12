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

    public Bookshelf fetchByCategory(BookCategoriesEnum category) {

        String url = "https://www.googleapis.com/books/v1/volumes?q=subject:" + category.getUrlPart() + "&printType=books&maxResults=20&startIndex=0";

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
                        BookItem book = new BookItem();
                        JSONObject item = array.getJSONObject(i);

                        JSONObject volumeInfo = null;
                        if(item.has("volumeInfo")) {
                            volumeInfo = item.getJSONObject("volumeInfo");
                        }

                        if(volumeInfo != null) {

                            String title = "";
                            if(volumeInfo.has("title")) {
                                title = volumeInfo.getString("title");
                            }
                            book.setTitle(title);

                            String author = "";
                            if(volumeInfo.has("authors")) {
                                JSONArray authors = volumeInfo.getJSONArray("authors");
                                author = authors.getString(0);
                            }
                            book.setAuthor(author);

                            String imageLink = "";
                            if(volumeInfo.has("imageLinks")) {
                                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                                imageLink = imageLinks.getString("smallThumbnail");
                            }
                            book.setBookThumbnail(imageLink);

                            Double rating = 0.0;
                            if(volumeInfo.has("averageRating")) {
                                rating = volumeInfo.getDouble("averageRating");
                            }
                            book.setRating(rating);

                            //TODO: isbn

                            int pageCount = 0;
                            if (volumeInfo.has("pageCount")){
                                pageCount = volumeInfo.getInt("pageCount");
                            }
                            book.setPages(pageCount);

                            String publishing = "";
                            if (volumeInfo.has("publisher")){
                                publishing = volumeInfo.getString("publisher");
                            }
                            book.setPublishing(publishing);

                            String publishedDate = "";
                            if (volumeInfo.has("publishedDate")){
                                publishedDate = volumeInfo.getString("publishedDate");
                            }
                            book.setPublishedDate(publishedDate);

                            String categorie = "";
                            if (volumeInfo.has("categories")){
                                JSONArray categories = volumeInfo.getJSONArray("categories");
                                categorie = categories.getString(0);
                            }
                            book.setGenre(categorie);

                            String language = "";
                            if (volumeInfo.has("language")){
                                language = volumeInfo.getString("language");
                            }
                            book.setLanguage(language);

                            String description = "";
                            if (volumeInfo.has("description")){
                                description = volumeInfo.getString("description");
                            }
                            book.setDescription(description);

                            bookshelf.addBook(book);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        bookshelf.sort();

        return bookshelf;
    }

}
