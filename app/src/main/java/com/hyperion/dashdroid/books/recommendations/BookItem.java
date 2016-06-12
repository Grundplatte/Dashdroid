package com.hyperion.dashdroid.books.recommendations;

import java.io.Serializable;

/**
 * Created by Valdrin on 6/4/2016.
 */
public class BookItem implements Comparable<BookItem>, Serializable {

    private String title;
    private String author;
    private String bookThumbnail;
    private String publishedDate;
    private String description;
    private Double rating;
    private String isbn10;
    private int pages;
    private String series;
    private String publishing;
    private String bookshelf;
    private String genre;
    private String language;

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

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publish) {
        this.publishedDate = publish;
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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public String getBookshelf() {
        return bookshelf;
    }

    public void setBookshelf(String bookshelf) {
        this.bookshelf = bookshelf;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public int compareTo(BookItem otherBook) {

        if(otherBook != null && otherBook.getRating() != null) {

            return this.rating.compareTo(otherBook.getRating()) * -1;

        }

        return 0;
    }
}
