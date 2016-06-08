package com.hyperion.dashdroid.books.recommendations;

import java.util.List;
import java.util.Vector;

/**
 * Created by Valdrin on 6/4/2016.
 */
public class Bookshelf {

	private List<BooksItem> bookshelf;

	Bookshelf() {
		bookshelf = new Vector<BooksItem>(0);
	}

	void addBook(BooksItem item) {
		bookshelf.add(item);
	}

	public BooksItem getBookItem(int location) {
		return bookshelf.get(location);
	}

	public int getItemCount() {
		return bookshelf.size();
	}

	public List<BooksItem> getBookList() {
		return bookshelf;
	}
}
