package com.hyperion.dashdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hyperion.dashdroid.news.NewsModuleActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

        Button newsButton = (Button) findViewById(R.id.a11);

        newsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d(DashboardActivity.class.getSimpleName(), "Switch to news activity");

                Intent intent = new Intent(getApplicationContext(), NewsModuleActivity.class);
                startActivity(intent);

            }
        });
    }
}
