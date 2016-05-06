package com.hyperion.dashdroid.base;

import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.slidingmenu.BaseFragment;
import com.hyperion.dashdroid.base.slidingmenu.SlidingMenuItem;
import com.hyperion.dashdroid.base.slidingmenu.SlidingMenuListAdapter;

import java.util.ArrayList;

/**
 * Created by infinity on 29-Apr-16.
 */
public abstract class AbstractModuleActivity extends AppCompatActivity {

	protected final int MENU_SEARCH_BUTTON_ID = 0;
	protected final int MENU_SETTINGS_BUTTON_ID = 1;
	protected final int MENU_REFRESH_BUTTON_ID = 2;

	protected Menu menu;
	protected ArrayList<BaseFragment> fragments;
	private DrawerLayout drawerLayout;
	private ListView drawerList;
	private ActionBarDrawerToggle drawerToggle;
	private ArrayList<SlidingMenuItem> slidingMenuItems;
	private SlidingMenuListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		fragments = new ArrayList<BaseFragment>();
		slidingMenuItems = new ArrayList<SlidingMenuItem>();

		setContentView(R.layout.base_module_activity);
		addSpecificContent();

		for(int i = 0; i < fragments.size(); i++) {
			slidingMenuItems.add(new SlidingMenuItem(fragments.get(i).getTitle()));
		}

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerList = (ListView) findViewById(R.id.list_slidermenu);

		drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

				displayView(position);

			}
		});

		adapter = new SlidingMenuListAdapter(getApplicationContext(), slidingMenuItems);
		drawerList.setAdapter(adapter);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name) {

			@Override
			public void onDrawerClosed(View drawerView) {

				invalidateOptionsMenu();

			}

			@Override
			public void onDrawerOpened(View drawerView) {

				invalidateOptionsMenu();

			}
		};

		drawerLayout.addDrawerListener(drawerToggle);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		if(savedInstanceState == null) {

			displayView(0);

		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}

	private void displayView(int position) {

		if(fragments != null && fragments.size() > position && fragments.get(position) != null) {

			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragments.get(position)).commit();
			drawerList.setItemChecked(position, true);
			drawerList.setSelection(position);
			getSupportActionBar().setSubtitle(fragments.get(position).getTitle());
			drawerLayout.closeDrawer(drawerList);

		} else {

			Log.e(getClass().getSimpleName(), "Error in creating fragment");

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		this.menu = menu;
		this.menu.add(0, MENU_REFRESH_BUTTON_ID, MENU_REFRESH_BUTTON_ID, "").setIcon(R.drawable.refresh).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		addOtherOptionMenuItems(this.menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if(drawerToggle.onOptionsItemSelected(item)) {

			return true;

		} else {

			switch(item.getItemId()) {

				case MENU_REFRESH_BUTTON_ID:
					refresh();
					break;
				case MENU_SEARCH_BUTTON_ID:
					search();
					break;
				case MENU_SETTINGS_BUTTON_ID:
					settings();
					break;
			}

		}

		return super.onOptionsItemSelected(item);
	}

	protected void search() {
		// Override if necessary
		Log.d(getClass().getSimpleName(), "search() method called...");
	}

	protected void settings() {
		// Override if necessary
		Log.d(getClass().getSimpleName(), "settings() method called...");
	}

	public Menu getMenu() {
		return menu;
	}

	public abstract void addSpecificContent();

	public abstract void addOtherOptionMenuItems(Menu menu);

	public abstract void refresh();
}
