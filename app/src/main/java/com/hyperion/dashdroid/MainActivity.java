package com.hyperion.dashdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

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
    Class[] mTileLink = {RadioActivity.class, WeatherActivity.class, RadioActivity.class, RadioActivity.class};


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
        mGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(getString(R.string.app_name), "Clicked: " + id);
                // Launch Activity
                Intent intent = new Intent(MainActivity.this, mTileLink[position]);
                startActivity(intent);
            }
        });

        mGrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(getString(R.string.app_name), "Long click: " + id);
                Toast toast = Toast.makeText(MainActivity.this, "Not implemented yet!", Toast.LENGTH_SHORT);
                toast.show();
                return true;
            }
        });
    }
}
