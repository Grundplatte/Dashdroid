package com.hyperion.dashdroid.radio;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.BaseFragment;
import com.hyperion.dashdroid.radio.async.ChannelSearchAsyncTask;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class RadioListFragment extends BaseFragment {

    public void setAsyncClass(Class<?> asyncClass) {
        this.asyncClass = asyncClass;
    }

    private Class<?> asyncClass;
    private View radioListViewContainer;
    private RecyclerView radioList;
    private ProgressBar progressBar;
    private int rootCategory = -1;
    private String continentName = null;
    private String countryCode = null;

    public static RadioListFragment makeInstance(Class<?> asyncClass) {
        RadioListFragment frag = new RadioListFragment();
        frag.setAsyncClass(asyncClass);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            rootCategory = bundle.getInt("rootCategory");
            continentName = bundle.getString("continentName");
            countryCode = bundle.getString("countryCode");
        }

        radioListViewContainer = inflater.inflate(R.layout.radio_fragment_list, container, false);
        radioList = (RecyclerView) radioListViewContainer.findViewById(R.id.radioListView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        radioList.setLayoutManager(gridLayoutManager);

        progressBar = (ProgressBar) radioListViewContainer.findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity(), R.color.m_color_pressed_1), PorterDuff.Mode.MULTIPLY);

        refresh();

        return radioListViewContainer;
    }

    @Override
    public void refresh() {
        Constructor<?> constructor;
        AsyncTask task;

        try {
            constructor = asyncClass.getConstructor(Context.class, View.class);
            task = (AsyncTask) constructor.newInstance(getActivity(), radioListViewContainer);

            if ((task instanceof ChannelSearchAsyncTask) == false)
                task.execute(rootCategory, continentName, countryCode);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
