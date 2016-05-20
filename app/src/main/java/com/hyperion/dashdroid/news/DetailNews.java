package com.hyperion.dashdroid.news;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailnews);
        imageLoader = new ImageLoader(this);
        webView = (WebView) findViewById(R.id.web_view_detail);

        feed = (RSSFeed) getIntent().getExtras().get("feed");
        position = getIntent().getExtras().getInt("pos");

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient());
        String lnk = feed.getItem(position).getLink();
        webView.loadUrl(lnk);
    }
}
