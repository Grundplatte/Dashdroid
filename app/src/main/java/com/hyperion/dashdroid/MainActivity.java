package com.hyperion.dashdroid;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // FIXME: Just for testing
    private int[] mTileText = {R.string.menu_radio, R.string.menu_weather, R.string.menu_tv, R.string.menu_rss, R.string.menu_news};
    private int[] mIconId = {R.drawable.ic_radio_black_48dp,
            R.drawable.ic_wb_sunny_black_48dp,
            R.drawable.ic_tv_black_48dp,
            R.drawable.ic_rss_feed_black_48dp,
            R.drawable.ic_today_black_48dp};
    private int[] mTileWidth = {1, 1, 2, 1, 1};
    private Class[] mTileLink = {RadioActivity.class, WeatherActivity.class, TVActivity.class, RSSActivity.class, NewsActivity.class};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu_bar, menu);

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        gridLayout.removeAllViews();


        Point displaySize = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(displaySize);
        int mTileWidth = displaySize.x/2;
        int mTileHeight = (int) ((float)mTileWidth * 1.5f);

        //LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater layoutInflater = getLayoutInflater();
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

            /*view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, mTileLink[(int)v.getTag()]);
                    startActivity(intent);
                }
            });*/
            if(i == 0) {
                gridLayout.addView(view, layoutParams);
            }
            else
                gridLayout.addView(view);
        }

    }
    public void onClickSettings(MenuItem item){
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void onClickAbout(MenuItem item){
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    public void onClickTile(View v){
        Intent intent = new Intent(MainActivity.this, mTileLink[(int)((View)v.getParent()).getTag()]);
        startActivity(intent);
    }

    public void onClickTileSettings(View v){
        View rootLayout = (View)v.getParent().getParent();
        View tileFront = rootLayout.findViewById(R.id.tileLayoutFront);
        View tileBack = rootLayout.findViewById(R.id.tileLayoutBack);

        FlipAnimation flipAnimation = new FlipAnimation(tileFront, tileBack);

        if (tileFront.getVisibility() == View.GONE)
        {
            flipAnimation.reverse();
        }
        rootLayout.startAnimation(flipAnimation);
    }
}
