package com.hyperion.dashdroid.news;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.AbstractModuleActivity;

/**
 * Created by infinity on 29-Apr-16.
 */
public class NewsModuleActivity extends AbstractModuleActivity {

	@Override
	public void addSpecificModuleViews(LinearLayout baseLayout) {

		View child = getLayoutInflater().inflate(R.layout.news_test_view, null);
		baseLayout.addView(child);

		getSupportActionBar().setTitle("News");
	}

	@Override
	public void addOptionsMenuItems(Menu menu) {

		menu.add(0, 0, 0, "").setIcon(R.drawable.search).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		menu.add(0, 1, 1, "").setIcon(R.drawable.settings).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

	}

	@Override
	public void refresh() {

	}
}
