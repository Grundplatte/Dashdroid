package com.hyperion.dashdroid.fragments;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyperion.dashdroid.MainActivity;
import com.hyperion.dashdroid.R;

/**
 * Created by Rainer on 05.05.2016.
 */
public final class MainMenuFragment extends Fragment{

    private static MainMenuFragment instance = null;

    public static synchronized MainMenuFragment getInstance()
    {
        if(instance == null) {
            instance = new MainMenuFragment();
        }
        return instance;
    }

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

            RelativeLayout relativeLayoutFront = (RelativeLayout)view.findViewById(R.id.tileLayoutFront);
            relativeLayoutFront.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickTile(v);
                }
            });

            if(i == 0) {
                gridLayout.addView(view, layoutParams);
            }
            else
                gridLayout.addView(view);
        }
        return gridView;
    }

    public void onClickTile(View v){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        int tag  = (int)((View)v.getParent()).getTag();

        NavigationView navi = (NavigationView)getActivity().findViewById(R.id.nav_view);
        navi.getMenu().getItem(tag+1).setChecked(true);

        //TODO make it more dynamic
        if (tag == 0) {
            fragmentTransaction.replace(R.id.content_frame, RadioFragment.getInstance()).commit();
        } else if (tag == 1) {
            fragmentTransaction.replace(R.id.content_frame, WeatherFragment.getInstance()).commit();
        } else if (tag == 2) {
            fragmentTransaction.replace(R.id.content_frame, TVFragment.getInstance()).commit();
        } else if (tag == 3) {
            fragmentTransaction.replace(R.id.content_frame, RSSFragment.getInstance()).commit();
        } else if (tag == 4) {
            fragmentTransaction.replace(R.id.content_frame, NewsFragment.getInstance()).commit();
        }
    }

}
