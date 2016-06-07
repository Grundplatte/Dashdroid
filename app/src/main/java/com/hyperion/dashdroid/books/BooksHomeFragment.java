package com.hyperion.dashdroid.books;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.books.recommendations.BooksFragment;

/**
 * Created by infinity on 06-May-16.
 */
public class BooksHomeFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.books_fragment_home, container, false);
		BooksFragment booksFragment = new BooksFragment();
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.fragment_home, booksFragment);
		transaction.addToBackStack(null);
		transaction.commit();
		return rootView;
	}

}
