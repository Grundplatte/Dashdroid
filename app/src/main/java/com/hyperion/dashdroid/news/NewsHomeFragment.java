package com.hyperion.dashdroid.news;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.news.rss.RSSFragment;

/**
 * Created by infinity on 05-May-16.
 */
public class NewsHomeFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.news_fragment_home, container, false);
		RSSFragment fragment = new RSSFragment();
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.fragment_home, fragment);
		transaction.addToBackStack(null);
		transaction.commit();
		return rootView;
	}
}
