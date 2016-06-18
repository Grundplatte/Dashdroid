package com.hyperion.dashdroid.news;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.hyperion.dashdroid.news.rss.DOMParser;
import com.hyperion.dashdroid.news.rss.RSSFeed;
import com.hyperion.dashdroid.news.rss.RSSFragment;
import com.hyperion.dashdroid.news.rss.RSSItem;

import java.util.Iterator;

/**
 * Created by infinity on 23-May-16.
 */
public class NewsSearchTask extends AsyncTask<Object, Object, Object> {

	private RSSFeed feed;

	public NewsSearchTask(Context context, View listView) {
	}

	@Override
	protected Object doInBackground(Object... params) {

		DOMParser myParser = new DOMParser();
		feed = myParser.parseXml(((RSSFragment) NewsModuleActivity.getCurrentSelectedItem().getFragment()).getRssFeedUrl().getUrl());

		String query = ((String) params[0]).trim().toLowerCase();

		Iterator<RSSItem> iterator = feed.getItemList().iterator();
		while(iterator.hasNext()) {

			RSSItem item = iterator.next();

			if(!item.getTitle().toLowerCase().contains(query) && !item.getDescription().toLowerCase().contains(query)) {
				iterator.remove();
			}
		}
		return feed;
	}

	@Override
	protected void onPostExecute(Object o) {
		((RSSFragment) NewsModuleActivity.getCurrentSelectedItem().getFragment()).getAdapter().setFeed((RSSFeed) o);
		((RSSFragment) NewsModuleActivity.getCurrentSelectedItem().getFragment()).getAdapter().notifyDataSetChanged();
	}
}