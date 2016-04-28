package com.hyperion.dashdroid;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

/**
 * Created by Rainer on 28.04.2016.
 */
public class SettingsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Point displaySize = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(displaySize);
        int windowWidth = (int)(displaySize.x * 0.9);
        int windowHeight = (int)(displaySize.y * 0.9);

        getWindow().setLayout(windowWidth, windowHeight);
    }
}
