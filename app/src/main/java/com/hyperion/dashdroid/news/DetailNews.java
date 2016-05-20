package com.hyperion.dashdroid.news;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailnews);
        imageLoader = new ImageLoader(this);

        feed = (RSSFeed) getIntent().getExtras().get("feed");
        position = getIntent().getExtras().getInt("pos");

        ImageView img_detail = (ImageView) findViewById(R.id.image_detail);
        TextView title_detail = (TextView) findViewById(R.id.title_detail);
        TextView description_detail = (TextView) findViewById(R.id.description_detail);

        imageLoader.DisplayImage(feed.getItem(position).getImage(), img_detail);
        title_detail.setText(feed.getItem(position).getTitle());
        description_detail.setText(feed.getItem(position).getDescription());
    }
}
