package com.hyperion.dashdroid.fragments;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyperion.dashdroid.R;

/**
 * Created by Rainer on 05.05.2016.
 */
public class MainMenuFragment extends Fragment{

    // FIXME: Just for testing
    private int[] mTileText = {R.string.menu_radio, R.string.menu_weather, R.string.menu_tv, R.string.menu_rss, R.string.menu_news};
    private int[] mIconId = {R.drawable.ic_radio_black_48dp,
            R.drawable.ic_wb_sunny_black_48dp,
            R.drawable.ic_tv_black_48dp,
            R.drawable.ic_rss_feed_black_48dp,
            R.drawable.ic_today_black_48dp};
    private int[] mTileWidth = {1, 1, 2, 1, 1};
    private Class[] mTileLink = {RadioFragment.class, WeatherFragment.class, TVFragment.class, RSSFragment.class, NewsFragment.class};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View gridView = inflater.inflate(R.layout.content_main, container, false);

        GridLayout gridLayout = (GridLayout)gridView.findViewById(R.id.gridLayout);
        gridLayout.removeAllViews();

        Point displaySize = new Point();
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        display.getSize(displaySize);
        int mTileWidth = displaySize.x/2;
        int mTileHeight = (int) ((float)mTileWidth * 1.5f);

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        for(int i = 0; i < mTileText.length; i++) {
            View view = layoutInflater.inflate(R.layout.grid_single_tile,null);
            RelativeLayout relativeLayout = (RelativeLayout)view.findViewById(R.id.tileLayout);
            relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(mTileWidth,mTileHeight));

            GridLayout.Spec rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1);
            GridLayout.Spec colSpec = GridLayout.spec(GridLayout.UNDEFINED, 2);

            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, colSpec);
            layoutParams.height = mTileHeight;
            layoutParams.width = mTileWidth*2;

            TextView textView = (TextView) view.findViewById(R.id.textView);
            textView.setText(mTileText[i]);

            // FIXME: Text size should be appropriate to text length and tile width/height
            textView.setTextSize(mTileHeight/25);
            textView.setTextColor(0xFF000000);

            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            imageView.setImageResource(mIconId[i]);

            imageView.getLayoutParams().height = mTileHeight/2;
            imageView.getLayoutParams().width = mTileWidth/2;
            view.setTag(i);

            if(i == 0) {
                gridLayout.addView(view, layoutParams);
            }
            else
                gridLayout.addView(view);
        }
        return gridView;


    }

}
