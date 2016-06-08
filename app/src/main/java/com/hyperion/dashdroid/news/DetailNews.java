package com.hyperion.dashdroid.news;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.news.image.ImageLoader;
import com.hyperion.dashdroid.news.rss.RSSFeed;

/**
 * Created by Valdrin on 5/19/2016.
 */
public class DetailNews extends Activity {

	private RSSFeed feed;
	private int position;
	private ImageLoader imageLoader;
	private WebView webView;
	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detailnews);

		imageLoader = new ImageLoader(this);
		webView = (WebView) findViewById(R.id.web_view_detail);
		progressBar = (ProgressBar) findViewById(R.id.progressBar_detail);
		progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this, R.color.m_color_pressed_1), PorterDuff.Mode.MULTIPLY);

		feed = (RSSFeed) getIntent().getExtras().get("feed");
		position = getIntent().getExtras().getInt("pos");

		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setLoadWithOverviewMode(true);
		settings.setUseWideViewPort(true);
		settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		webView.setWebViewClient(new NewsWebClient());

		String lnk = feed.getItem(position).getLink();
		webView.loadUrl(lnk);
	}

	private class NewsWebClient extends WebViewClient {
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			progressBar.setVisibility(view.GONE);
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
