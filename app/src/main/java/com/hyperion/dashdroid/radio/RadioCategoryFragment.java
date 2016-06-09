package com.hyperion.dashdroid.radio;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
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

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.BaseFragment;
import com.hyperion.dashdroid.radio.data.RadioCategory;
import com.hyperion.dashdroid.radio.db.RadioContentProvider;
import com.hyperion.dashdroid.radio.db.RadioDBContract;
import com.hyperion.dashdroid.radio.dirble.DirbleHelper;

import java.util.ArrayList;

public class RadioCategoryFragment extends BaseFragment implements CategoryAdapter.CategoryItemClickedListener {

    private View radioListViewContainer;
    private RecyclerView radioList;
    private ProgressBar progressBar;
    private ImageButton favButton;
    private int rootCategory = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null)
            rootCategory = bundle.getInt("rootCategory");

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
        CategoryAsyncTask dirbleAsyncTask = new CategoryAsyncTask();
        dirbleAsyncTask.execute();
        // if rootCategory = -1 => load rootCategories
        // else load subcategories
    }

    @Override
    public void onItemClicked(RadioCategory category) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (rootCategory == -1) {
            Fragment radioCategoryFragment = new RadioCategoryFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("rootCategory", category.getID());

            radioCategoryFragment.setArguments(bundle);

            transaction.replace(R.id.radioList_container, radioCategoryFragment);
            transaction.addToBackStack("categoryFragment");
        } else {
            Fragment radioCategoryChannelsFragment = new RadioCategoryChannelsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("rootCategory", category.getID());

            radioCategoryChannelsFragment.setArguments(bundle);

            transaction.replace(R.id.radioList_container, radioCategoryChannelsFragment);
            transaction.addToBackStack("SubCategoryFragment");
        }
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
            ArrayList<RadioCategory> radioCategories = new ArrayList<>();
            //check if data allready in database // check timestamp
            // if data is in db and timestamp is ok, get the data from the db, else get data from server
            String where = RadioDBContract.RadioCategory.COLUMN_NAME_ANCESTRY + "=" + rootCategory; // only root categories
            Cursor c = getContext().getContentResolver().query(RadioContentProvider.URI_CATEGORIES, null, where, null, null);
            if (c.moveToFirst()) {
                do {
                    radioCategories.add(DirbleHelper.buildRadioCategory(c));
                } while (c.moveToNext());
                return radioCategories;
            } else
                throw new IllegalStateException("Rip!");
        }

        @Override
        protected void onPostExecute(Object o) {
            if (progressBar != null)
                progressBar.setVisibility(View.GONE);

            if (o != null) {
                CategoryAdapter categoryAdapter = new CategoryAdapter((ArrayList<RadioCategory>) o, RadioCategoryFragment.this);
                radioList.setAdapter(categoryAdapter);
            }

        }
    }
}
