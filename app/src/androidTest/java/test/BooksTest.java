package test;

import android.util.Log;

import com.hyperion.dashdroid.books.recommendations.BooksRequest;
import com.hyperion.dashdroid.books.recommendations.Bookshelf;

import junit.framework.TestCase;

/**
 * Created by Valdrin on 6/6/2016.
 */
public class BooksTest extends TestCase {

    public void testBook() {
        BooksRequest booksRequest = new BooksRequest();
        Bookshelf bookshelf = booksRequest.request("9781118240670");
        Log.e("Result", bookshelf.getItemCount() + "");
    }

}
