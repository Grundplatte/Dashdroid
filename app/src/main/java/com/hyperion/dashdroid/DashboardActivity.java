package com.hyperion.dashdroid;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.hyperion.dashdroid.home.MainMenuFragment;

public class DashboardActivity extends AppCompatActivity {

	// private Button newsButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard_activity);

		// newsButton = (Button) findViewById(R.id.newsButtonId);


		/*
		newsButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				Log.d(DashboardActivity.class.getSimpleName(), "Switch to news activity");

				Intent intent = new Intent(getApplicationContext(), NewsModuleActivity.class);
				startActivity(intent);

			}
		});
		*/

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

		fragmentTransaction.replace(R.id.mainFrameLayout, MainMenuFragment.getInstance()).commit();
	}
}
