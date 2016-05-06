package com.hyperion.dashdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hyperion.dashdroid.news.NewsModuleActivity;
import com.hyperion.dashdroid.radio.RadioModuleActivity;
import com.hyperion.dashdroid.tv.TvModuleActivity;
import com.hyperion.dashdroid.weather.WeatherModuleActivity;

public class DashboardActivity extends AppCompatActivity {

	private Button newsButton;
	private Button tvButton;
	private Button radioButton;
	private Button weatherButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard_activity);

		newsButton = (Button) findViewById(R.id.newsButtonId);
		tvButton = (Button) findViewById(R.id.tvButtonId);
		radioButton = (Button) findViewById(R.id.radioButtonId);
		weatherButton = (Button) findViewById(R.id.weatherButtonId);

		newsButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				Log.d(DashboardActivity.class.getSimpleName(), "Switch to news activity");

				Intent intent = new Intent(getApplicationContext(), NewsModuleActivity.class);
				startActivity(intent);

			}
		});

		tvButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				Log.d(DashboardActivity.class.getSimpleName(), "Switch to tv activity");

				Intent intent = new Intent(getApplicationContext(), TvModuleActivity.class);
				startActivity(intent);

			}
		});

		radioButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				Log.d(DashboardActivity.class.getSimpleName(), "Switch to radio activity");

				Intent intent = new Intent(getApplicationContext(), RadioModuleActivity.class);
				startActivity(intent);

			}
		});

		weatherButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				Log.d(DashboardActivity.class.getSimpleName(), "Switch to weather activity");

				Intent intent = new Intent(getApplicationContext(), WeatherModuleActivity.class);
				startActivity(intent);

			}
		});
	}
}
