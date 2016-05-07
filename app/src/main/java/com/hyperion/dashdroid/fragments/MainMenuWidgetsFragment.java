package com.hyperion.dashdroid.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyperion.dashdroid.Person;
import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.WidgetsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rainer on 05.05.2016.
 */
public final class MainMenuWidgetsFragment extends Fragment{

    private static MainMenuWidgetsFragment instance = null;

    public static synchronized MainMenuWidgetsFragment getInstance()
    {
        if(instance == null) {
            instance = new MainMenuWidgetsFragment();
        }
        return instance;
    }

    // FIXME: Just for testing
    private int[] mTileText = {R.string.menu_radio, R.string.menu_weather, R.string.menu_tv, R.string.menu_rss, R.string.menu_news};
    private int[] mIconId = {R.drawable.ic_radio_black_48dp,
            R.drawable.ic_wb_sunny_black_48dp,
            R.drawable.ic_tv_black_48dp,
            R.drawable.ic_rss_feed_black_48dp,
            R.drawable.ic_today_black_48dp};
    private int[] mTileWidth = {1, 1, 2, 1, 1};
    private Class[] mTileLink = {RadioFragment.class, WeatherFragment.class, TVFragment.class, RSSFragment.class, NewsFragment.class};

    private List<Person> persons;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Person("Title", "Interpret", R.mipmap.rautemusik));
        persons.add(new Person("Weather", "additional info", R.drawable.ic_wb_sunny_black_48dp));
        persons.add(new Person("Last TV channel", "additional info", R.drawable.ic_tv_black_48dp));
        persons.add(new Person("RSS", "additional info", R.drawable.ic_rss_feed_black_48dp));
        persons.add(new Person("News", "additional info", R.drawable.ic_today_black_48dp));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View widgetsView = inflater.inflate(R.layout.fragment_widgets, container, false);
        RecyclerView recyclerView = (RecyclerView)widgetsView.findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        initializeData();
        WidgetsAdapter widgetsAdapter = new WidgetsAdapter(persons);
        recyclerView.setAdapter(widgetsAdapter);


        return widgetsView;
    }
}
