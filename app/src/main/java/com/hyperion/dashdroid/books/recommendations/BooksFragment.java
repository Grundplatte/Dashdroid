package com.hyperion.dashdroid.books.recommendations;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.BaseFragment;
import com.hyperion.dashdroid.books.DetailBooks;

/**
 * Created by Valdrin on 6/4/2016.
 */
public class BooksFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ProgressBar progressBar;
    private ListView listView;
    private Bookshelf bookshelf;
    private BooksAdapter adapter;
    private BookCategoriesEnum bookCategory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.books_fragment_list, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.books_progressBar);
        progressBar.getIndeterminateDrawable()
                .setColorFilter(ContextCompat.getColor(getActivity(), R.color.m_color_pressed_1),
                        PorterDuff.Mode.MULTIPLY);
        listView = (ListView) view.findViewById(R.id.books_listView);
        listView.setOnItemClickListener(this);
        listView.setTextFilterEnabled(true);
        new GetBook().execute();
        return view;
    }

    @Override
    public void refresh() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e("Position: ", position + "");
        Intent i = new Intent(getActivity(), DetailBooks.class);
        startActivity(i);
        //TODO: implement the BooksDetailActivity
    }

   public class GetBook extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            BooksRequest booksRequest = new BooksRequest();
            bookshelf = booksRequest.request(bookCategory);
            return null;
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            adapter = new BooksAdapter(BooksFragment.this, bookshelf);
            Log.e("Bookshelf", "" + bookshelf.getItemCount());
            listView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        }
    }

    public BookCategoriesEnum getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategoriesEnum bookCategory) {
        this.bookCategory = bookCategory;
    }
}
