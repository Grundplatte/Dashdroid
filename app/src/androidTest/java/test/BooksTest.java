package test;

import android.util.Log;

import com.hyperion.dashdroid.books.recommendations.BookCategoriesEnum;
import com.hyperion.dashdroid.books.recommendations.BooksRequest;
import com.hyperion.dashdroid.books.recommendations.Bookshelf;

import junit.framework.TestCase;

/**
 * Created by Valdrin on 6/6/2016.
 */
public class BooksTest extends TestCase {

	private BooksRequest booksRequest;

	public BooksTest() {
		booksRequest = new BooksRequest();
	}

	public void testBookRequest() {
		Bookshelf bookshelf = booksRequest.fetchByCategory(BookCategoriesEnum.SPORTS);
		assertTrue(bookshelf.getItemCount() > 0);
	}

	public void testBookCategories() {
		BookCategoriesEnum[] categoriesEnum = BookCategoriesEnum.values();
		for(BookCategoriesEnum categoryEnum : categoriesEnum) {
			Bookshelf bookshelf = booksRequest.fetchByCategory(categoryEnum);
			assertTrue("Failed to fetch books from category: " + categoryEnum.getName() + ".", (bookshelf.getItemCount() > 0));
		}
	}
}
