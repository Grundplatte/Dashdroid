package com.hyperion.dashdroid.news.rss;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
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
import android.widget.Toast;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.BaseFragment;
import com.hyperion.dashdroid.news.DetailNews;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Valdrin on 5/18/2016.
 */
public class RSSFragment extends BaseFragment implements AdapterView.OnItemClickListener {

	private ProgressBar progressBar;
	private ListView listView;
	private String fileName;
	private RSSFeed feed;
	private RssFeedUrlEnum rssFeedUrl;
	private RSSAdapter adapter;
	private File feedFile;
	private ConnectivityManager conMgr;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);

		fileName = "Dashdroid.td";

		feedFile = getActivity().getBaseContext().getFileStreamPath(fileName);
		conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.news_fragment_feed_list, container, false);
		progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
		progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity(), R.color.m_color_pressed_1), PorterDuff.Mode.MULTIPLY);
		listView = (ListView) view.findViewById(R.id.modulesListView);
		listView.setOnItemClickListener(this);
		listView.setTextFilterEnabled(true);
		refresh();
		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Log.e("Position: ", position + "");
		Bundle bundle = new Bundle();
		bundle.putSerializable("feed", feed);
		Intent intent = new Intent(getActivity(), DetailNews.class);
		intent.putExtras(bundle);
		intent.putExtra("pos", position);
		startActivity(intent);
	}

	// Method to write the feed to the File
	private void writeFeed(RSSFeed data) {

		FileOutputStream fOut = null;
		ObjectOutputStream osw = null;

		try {
			fOut = getActivity().openFileOutput(fileName, getActivity().MODE_PRIVATE);
			osw = new ObjectOutputStream(fOut);
			osw.writeObject(data);
			osw.flush();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fOut.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Method to read the feed from the File
	private RSSFeed readFeed(String fName) {

		FileInputStream fIn = null;
		ObjectInputStream isr = null;

		RSSFeed _feed = null;
		File feedFile = getActivity().getBaseContext().getFileStreamPath(fileName);
		if(!feedFile.exists())
			return null;

		try {
			fIn = getActivity().openFileInput(fName);
			isr = new ObjectInputStream(fIn);

			_feed = (RSSFeed) isr.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fIn.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return _feed;
	}

	public RssFeedUrlEnum getRssFeedUrl() {
		return rssFeedUrl;
	}

	public void setRssFeedUrl(RssFeedUrlEnum rssFeedUrl) {
		this.rssFeedUrl = rssFeedUrl;
	}

	private class AsyncLoadXMLFeed extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(Void... params) {
			DOMParser myParser = new DOMParser();
			feed = myParser.parseXml(rssFeedUrl.getUrl());
			if(feed != null && feed.getItemCount() > 0) {
				writeFeed(feed);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			adapter = new RSSAdapter(RSSFragment.this, feed);
			listView.setAdapter(adapter);
			progressBar.setVisibility(View.GONE);
		}
	}

	@Override
	public void refresh() {

		if(conMgr.getActiveNetworkInfo() == null) {
			if(!feedFile.exists()) {
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setMessage(
						"Unable to reach server, \nPlease check your connectivity.")
						.setTitle("TD RSS Reader")
						.setCancelable(false)
						.setPositiveButton("Exit",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
														int id) {
										getActivity().finish();
									}
								});

				AlertDialog alert = builder.create();
				alert.show();
			} else {

				Toast toast = Toast.makeText(getActivity(),
						"No connectivity! Reading last update...",
						Toast.LENGTH_LONG);
				toast.show();
				feed = readFeed(fileName);
				RSSAdapter rssAdapter = new RSSAdapter(this, feed);
				listView.setAdapter(rssAdapter);
				progressBar.setVisibility(View.GONE);
			}
		} else {

			new AsyncLoadXMLFeed().execute();

		}
	}

	public RSSFeed getFeed() {
		return feed;
	}

	public RSSAdapter getAdapter() {
		return adapter;
	}
}
