package com.hyperion.dashdroid.news.rss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.news.image.ImageLoader;

/**
 * Created by Valdrin on 5/18/2016.
 */
public class RSSAdapter extends BaseAdapter {

    private RSSFeed feed;
    private LayoutInflater layoutInflater;
    public ImageLoader imageLoader;

    public RSSAdapter(RSSFragment fragment, RSSFeed feed) {

        layoutInflater = (LayoutInflater) fragment.getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = new ImageLoader(fragment.getActivity().getApplicationContext());
        this.feed = feed;
    }

    @Override
    public int getCount() {
        return feed.getItemCount();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Inflate the item layout and set the views
        View listItem = convertView;
        int pos = position;
        if (listItem == null) {
            listItem = layoutInflater.inflate(R.layout.list_item, null);
        }

        // Initialize the views in the layout
        ImageView iv = (ImageView) listItem.findViewById(R.id.thumb);
        TextView tvTitle = (TextView) listItem.findViewById(R.id.title);
        TextView tvDate = (TextView) listItem.findViewById(R.id.date);

        // Set the views in the layout
        imageLoader.DisplayImage(feed.getItem(pos).getImage(), iv);
        tvTitle.setText(feed.getItem(pos).getTitle());
        tvDate.setText(feed.getItem(pos).getDate());

        return listItem;
    }

}
