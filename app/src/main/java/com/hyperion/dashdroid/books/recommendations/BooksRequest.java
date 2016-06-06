package com.hyperion.dashdroid.books.recommendations;

/**
 * Created by Valdrin on 6/4/2016.
 */
public class BooksRequest {

    private Bookshelf bookshelf;

    //TODO: Method to test the request in Google Books Api
    public Bookshelf request(String newText){

        if (newText.length() > 0){
            newText = newText.replace(" ", "+");
            String url = "https://www.googleapis.com/books/v1/volumes?q=";
            url += newText;
        }

        return  null;
    }

}
