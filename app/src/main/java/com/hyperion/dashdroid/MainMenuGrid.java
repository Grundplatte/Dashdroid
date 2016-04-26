package com.hyperion.dashdroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Markus on 25.04.2016.
 */
public class MainMenuGrid extends BaseAdapter{

    private Context mContext;
    private final String[] mTileText;
    private final int[] mIconId;
    private int mTileWidth;
    private int mTileHeight;

    public MainMenuGrid(Context c, String[] tileText, int[] iconId) {
        mContext = c;
        mTileText = tileText;
        mIconId = iconId;
        Point displaySize = new Point();
        Display display = ((Activity)mContext).getWindowManager().getDefaultDisplay();
        display.getSize(displaySize);
        mTileWidth = displaySize.x/2;
        mTileHeight = (int) ((float)mTileWidth * 1.5f);
    }

    @Override
    public int getCount() {
        return mTileText.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO: implement
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO: implement
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        View holder;

        Log.d("TEST", "Position: " + position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            grid = inflater.inflate(R.layout.grid_single_tile, null);

            LinearLayout layout = (LinearLayout)grid.findViewById(R.id.tileLayout);
            TextView textView = (TextView) grid.findViewById(R.id.textView);
            ImageView imageView = (ImageView) grid.findViewById(R.id.imageView);

            textView.setText(mTileText[position]);
            imageView.setImageResource(mIconId[position]);

            layout.setLayoutParams(new LinearLayout.LayoutParams(mTileWidth,mTileWidth));
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
