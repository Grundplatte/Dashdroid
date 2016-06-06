package com.hyperion.dashdroid.books.recommendations;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.hyperion.dashdroid.base.BaseFragment;

/**
 * Created by Valdrin on 6/4/2016.
 */
public class BooksFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private String book;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        book = "https://www.googleapis.com/books/v1/volumes?";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void refresh() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private class GetBookInfo extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            return null;
        }
    }
}
