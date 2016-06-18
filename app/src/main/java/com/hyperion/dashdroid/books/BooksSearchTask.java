package com.hyperion.dashdroid.books;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.hyperion.dashdroid.books.recommendations.BookItem;
import com.hyperion.dashdroid.books.recommendations.BooksFragment;
import com.hyperion.dashdroid.books.recommendations.BooksRequest;
import com.hyperion.dashdroid.books.recommendations.Bookshelf;

import java.util.Iterator;

/**
 * Created by Valdrin on 6/15/2016.
 */
public class BooksSearchTask extends AsyncTask<Object, Object, Object> {

    private Bookshelf bookshelf;

    public BooksSearchTask(Context context, View listView) {
    }

    @Override
    protected Object doInBackground(Object... params) {

        BooksRequest booksRequest = new BooksRequest();
        bookshelf = booksRequest.fetchByCategory(((BooksFragment) BooksModuleActivity
                .getCurrentSelectedItem().getFragment()).getBookCategory());

        String query = ((String) params[0]).trim().toLowerCase();

        Iterator<BookItem> iterator = bookshelf.getBookList().iterator();
        while (iterator.hasNext()) {
            BookItem bookItem = iterator.next();
            if (!bookItem.getTitle().toLowerCase().contains(query) && !bookItem.getAuthor().toLowerCase().contains(query)) {
                iterator.remove();
            }
        }
        return bookshelf;
    }

    @Override
    protected void onPostExecute(Object o) {
        ((BooksFragment) BooksModuleActivity.getCurrentSelectedItem().getFragment()).getAdapter().setBookshelf((Bookshelf) o);
        ((BooksFragment) BooksModuleActivity.getCurrentSelectedItem().getFragment()).getAdapter().notifyDataSetChanged();
    }
}
