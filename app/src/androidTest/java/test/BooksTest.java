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

	public void testBook() {
		BooksRequest booksRequest = new BooksRequest();
		Bookshelf bookshelf = booksRequest.request(BookCategoriesEnum.SPORTS);
		Log.e("Result", bookshelf.getItemCount() + "");
	}

}
