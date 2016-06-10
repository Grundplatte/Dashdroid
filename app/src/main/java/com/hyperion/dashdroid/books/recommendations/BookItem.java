package com.hyperion.dashdroid.books.recommendations;

/**
 * Created by Valdrin on 6/4/2016.
 */
public class BookItem implements Comparable<BookItem> {

    private String title;
    private String author;
    private String bookThumbnail;
    private String publish;
    private String description;
    private Double rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookThumbnail() {
        return bookThumbnail;
    }

    public void setBookThumbnail(String bookThumbnail) {
        this.bookThumbnail = bookThumbnail;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public int compareTo(BookItem otherBook) {

        if(otherBook != null && otherBook.getRating() != null) {

            return this.rating.compareTo(otherBook.getRating()) * -1;

        }

        return 0;
    }
}
