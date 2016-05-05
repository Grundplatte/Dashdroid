package com.hyperion.dashdroid.fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hyperion.dashdroid.R;

/**
 * Created by Markus on 26.04.2016.
 */
public class NewsFragment extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_radio);
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText("NEWS!");
    }
}
