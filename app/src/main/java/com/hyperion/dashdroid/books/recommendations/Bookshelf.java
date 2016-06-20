package com.hyperion.dashdroid.books.recommendations;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Created by Valdrin on 6/4/2016.
 */
public class Bookshelf implements Serializable {

    private List<BookItem> bookshelf;

    Bookshelf() {
        bookshelf = new Vector<BookItem>(0);
    }

    void addBook(BookItem item) {
        bookshelf.add(item);
    }

    public BookItem getBookItem(int location) {
        return bookshelf.get(location);
    }

    public int getItemCount() {
        return bookshelf.size();
    }

    public List<BookItem> getBookList() {
        return bookshelf;
    }

    public void sort() {
        Collections.sort(bookshelf);
    }
}
