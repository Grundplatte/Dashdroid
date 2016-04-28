package com.hyperion.dashdroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Markus on 26.04.2016.
 */
public class TVActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_radio);
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText("TV!");
    }
}
