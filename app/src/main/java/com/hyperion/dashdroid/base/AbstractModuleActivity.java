package com.hyperion.dashdroid.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.hyperion.dashdroid.R;

/**
 * Created by infinity on 29-Apr-16.
 */
public abstract class AbstractModuleActivity extends AppCompatActivity {

	public abstract void refresh();

	public abstract void addSpecificModuleViews(LinearLayout baseLayout);

	public abstract void addOptionsMenuItems(Menu menu);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.base_module_activity);
		addSpecificModuleViews((LinearLayout) findViewById(R.id.baseModuleLayoutId));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		menu.add(0, 2, 2, "").setIcon(R.drawable.refresh).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		addOptionsMenuItems(menu);

		return true;
	}
}
