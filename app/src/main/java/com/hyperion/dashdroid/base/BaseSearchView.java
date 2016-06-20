package com.hyperion.dashdroid.base;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.hyperion.dashdroid.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Rainer on 13.05.2016.
 */
public class BaseSearchView extends SearchView implements SearchView.OnQueryTextListener {

	private View view;
	private Context context;
	private Class<?> asyncTaskClass;

	public BaseSearchView(Context context, Class<?> asyncTaskClass) {
		super(context);
		this.view = null;
		this.asyncTaskClass = asyncTaskClass;
		this.context = context;

		setBackgroundColor(Color.WHITE);

		int search_button_id = getResources().getIdentifier("android:id/search_button", null, null);
		ImageView search_button_view = (ImageView) findViewById(search_button_id);
		search_button_view.setImageResource(R.drawable.search);
		search_button_view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

		setOnQueryTextListener(this);
	}

	@Override
	public boolean onQueryTextSubmit(String query) {

		AsyncTask task = null;
		Constructor<?> constructor = null;

		try {

			constructor = asyncTaskClass.getConstructor(Context.class, View.class);
			task = (AsyncTask) constructor.newInstance(context, view);

		} catch(NoSuchMethodException e) {
			e.printStackTrace();
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		} catch(InstantiationException e) {
			e.printStackTrace();
		} catch(InvocationTargetException e) {
			e.printStackTrace();
		}

		task.execute(query);

		return true;
	}

	@Override
	public void setOnCloseListener(OnCloseListener listener) {
		super.setOnCloseListener(listener);
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		return true;
	}

	protected void setView(View view) {
		this.view = view;
	}
}
