package com.hyperion.dashdroid;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.hyperion.dashdroid.fragments.MainMenuFragment;
import com.hyperion.dashdroid.fragments.MainMenuWidgetsFragment;
import com.hyperion.dashdroid.fragments.NewsFragment;
import com.hyperion.dashdroid.fragments.RSSFragment;
import com.hyperion.dashdroid.fragments.RadioFragment;
import com.hyperion.dashdroid.fragments.TVFragment;
import com.hyperion.dashdroid.fragments.WeatherFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu_bar, menu);

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.content_frame, MainMenuFragment.getInstance()).commit();


    }

    @Override
    public void onBackPressed() {
        View v = findViewById(R.id.gridLayout);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(v == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, MainMenuFragment.getInstance()).commit();

            NavigationView navi = (NavigationView)findViewById(R.id.nav_view);
            navi.getMenu().getItem(0).setChecked(true);
        }else {
            super.onBackPressed();
        }
    }

    public void onClickSettings(MenuItem item){
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void onClickAbout(MenuItem item){
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    public void onClickTileSettings(View v){
        View rootLayout = (View)v.getParent().getParent();
        View tileFront = rootLayout.findViewById(R.id.tileLayoutFront);
        View tileBack = rootLayout.findViewById(R.id.tileLayoutBack);

        FlipAnimation flipAnimation = new FlipAnimation(tileFront, tileBack);

        if (tileFront.getVisibility() == View.GONE)
        {
            flipAnimation.reverse();
        }
        rootLayout.startAnimation(flipAnimation);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (id == R.id.nav_home) {
            fragmentTransaction.replace(R.id.content_frame, MainMenuFragment.getInstance()).commit();
        }else if (id == R.id.nav_radio) {
            fragmentTransaction.replace(R.id.content_frame, RadioFragment.getInstance()).commit();
        } else if (id == R.id.nav_weather) {
            fragmentTransaction.replace(R.id.content_frame, WeatherFragment.getInstance()).commit();
        } else if (id == R.id.nav_tv) {
            fragmentTransaction.replace(R.id.content_frame, TVFragment.getInstance()).commit();
        } else if (id == R.id.nav_rss) {
            fragmentTransaction.replace(R.id.content_frame, RSSFragment.getInstance()).commit();
        } else if (id == R.id.nav_news) {
            fragmentTransaction.replace(R.id.content_frame, NewsFragment.getInstance()).commit();
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_home_widgets) {
            fragmentTransaction.replace(R.id.content_frame, MainMenuWidgetsFragment.getInstance()).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
