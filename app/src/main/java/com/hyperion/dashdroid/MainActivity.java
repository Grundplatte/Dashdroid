package com.hyperion.dashdroid;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    // FIXME: Just for testing
    GridView mGrid;
    String[] mTileText = {"Radio", "Wetter", "TV", "RSS", "NEWS"};
    int[] mIconId = {R.drawable.ic_radio_black_48dp,
            R.drawable.ic_wb_sunny_black_48dp,
            R.drawable.ic_tv_black_48dp,
            R.drawable.ic_rss_feed_black_48dp,
            R.drawable.ic_today_black_48dp};
    int[] mTileWidth = {1, 1, 2, 1, 1};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // FIXME: Just for testing
        MainMenuGrid adapter = new MainMenuGrid(MainActivity.this, mTileText, mIconId);
        mGrid = (GridView) findViewById(R.id.grid);
        mGrid.setAdapter(adapter);
    }
}
