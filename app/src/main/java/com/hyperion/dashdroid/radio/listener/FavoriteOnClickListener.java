package com.hyperion.dashdroid.radio.listener;

import android.util.Log;
import android.view.View;

/**
 * Created by Rainer on 08.06.2016.
 */
public class FavoriteOnClickListener implements View.OnClickListener {
    private boolean favorited;

    public FavoriteOnClickListener(boolean favorited) {
        this.favorited = favorited;
    }

    @Override
    public void onClick(View v) {
        Log.d(getClass().getSimpleName(), "onClick: fav button clicked");
    }
}
