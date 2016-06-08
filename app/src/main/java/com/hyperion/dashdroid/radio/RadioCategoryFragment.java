package com.hyperion.dashdroid.radio;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.BaseFragment;
import com.hyperion.dashdroid.radio.data.RadioCategory;
import com.hyperion.dashdroid.radio.dirble.DirbleProvider;

import java.util.ArrayList;

public class RadioCategoryFragment extends BaseFragment implements CategoryAdapter.CategoryItemClickedListener {

    private View radioListViewContainer;
    private RecyclerView radioList;
    private ProgressBar progressBar;
    private ImageButton favButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        RelativeLayout relativeLayout = (RelativeLayout) radioListViewContainer.findViewById(R.id.radioList);

        CategoryAsyncTask dirbleAsyncTask = new CategoryAsyncTask();
        dirbleAsyncTask.execute();
    }

    @Override
    public void onItemClicked(RadioCategory category) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment radioCategoryChannelsFragment = new RadioCategoryChannelsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("rootCategory", category.getID());

        radioCategoryChannelsFragment.setArguments(bundle);

        //transaction.hide(RadioCategoryFragment.this);
        transaction.replace(R.id.radioList_container, radioCategoryChannelsFragment);
        transaction.addToBackStack("categoryFragment");
        transaction.commit();
    }

    public class CategoryAsyncTask extends AsyncTask<Object, Integer, Object> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (progressBar != null)
                progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Object doInBackground(Object... params) {
            return DirbleProvider.getInstance().getCategoryTree();
        }

        @Override
        protected void onPostExecute(Object o) {
            if (progressBar != null)
                progressBar.setVisibility(View.GONE);

            CategoryAdapter categoryAdapter = new CategoryAdapter((ArrayList<RadioCategory>) o, RadioCategoryFragment.this);
            radioList.setAdapter(categoryAdapter);

        }
    }
}
