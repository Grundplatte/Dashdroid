package com.hyperion.dashdroid.home;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.news.NewsModuleActivity;
import com.hyperion.dashdroid.radio.RadioModuleActivity;
import com.hyperion.dashdroid.tv.TvModuleActivity;
import com.hyperion.dashdroid.weather.WeatherModuleActivity;

/**
 * Created by Rainer on 05.05.2016.
 */

//todo https://github.com/codepath/android_guides/wiki/Using-the-RecyclerView

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
    private int[] mTileText = {R.string.dashboard_radio, R.string.dashboard_weather, R.string.dashboard_tv, R.string.dashboard_news};
    private int[] mIconId = {R.drawable.ic_radio_black_48dp,
            R.drawable.ic_wb_sunny_black_48dp,
            R.drawable.ic_tv_black_48dp,
            R.drawable.ic_today_black_48dp};
    private Class[] mTileLink = {RadioModuleActivity.class, WeatherModuleActivity.class, TvModuleActivity.class, NewsModuleActivity.class};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View gridView = inflater.inflate(R.layout.main_fragment, container, false);

        GridLayout gridLayout = (GridLayout)gridView.findViewById(R.id.gridLayout);
        gridLayout.removeAllViews();
        gridLayout.setColumnCount(2);
        gridLayout.setRowCount((int)Math.ceil(mTileText.length/2));

        Point displaySize = new Point();
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        display.getSize(displaySize);
        int mTileWidth = displaySize.x/2;
        int mTileHeight = (int) ((float)mTileWidth * 1.5f);

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        for(int i = 0; i < mTileText.length; i++) {
            View view = layoutInflater.inflate(R.layout.main_fragment_card,null);
            RelativeLayout relativeLayout = (RelativeLayout)view.findViewById(R.id.tileLayout);
            relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(mTileWidth,mTileHeight));

            GridLayout.LayoutParams layoutParamsDouble = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1),
                    GridLayout.spec(GridLayout.UNDEFINED, 2));
            GridLayout.LayoutParams layoutParamsSingle = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1),
                    GridLayout.spec(GridLayout.UNDEFINED, 1));

            TextView textView = (TextView) view.findViewById(R.id.person_name);
            textView.setText(mTileText[i]);

            // FIXME: Text size should be appropriate to text length and tile width/height
            textView.setTextSize(mTileHeight/25);
            textView.setTextColor(0xFF000000);

            ImageView imageView = (ImageView) view.findViewById(R.id.person_photo);
            imageView.setImageResource(R.drawable.temp);

            // encode tile id into view tag
            view.setTag(i);

            CardView card = (CardView)view.findViewById(R.id.cv);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickTile(v);
                }
            });

            if(i == 0) {
                gridLayout.addView(view, layoutParamsDouble);
            }
            else
                gridLayout.addView(view);
        }
        return gridView;
    }

    public void onClickTile(View v){
        int tile_id = (int)((View)v.getParent()).getTag();

        Intent intent = new Intent(getContext(), mTileLink[tile_id]);
        startActivity(intent);
    }

}