package com.hyperion.dashdroid.radio.widget;

// ref: https://github.com/DmitryMalkovich/circular-with-floating-action-button/blob/master/progress-fab/src/main/java/com/dmitrymalkovich/android/ProgressFloatingActionButton.java

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.hyperion.dashdroid.R;

public class ProgressImageButton extends FrameLayout {

    private final ProgressImageButtonBehaviour behaviour;
    private ProgressBar progressBar;
    private ImageButton imageButton;


    public ProgressImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        behaviour = new ProgressImageButtonBehaviour();

        progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.colorMenuButtonPressed), PorterDuff.Mode.MULTIPLY);
        progressBar.setVisibility(INVISIBLE);

        imageButton = new ImageButton(context, null);
        imageButton.setBackgroundResource(R.drawable.ic_play_circle_filled_black_48dp);
        imageButton.setId(R.id.playStopButton);
        imageButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        addView(progressBar);
        addView(imageButton);
    }

    public void showProgressbar(){
        progressBar.setVisibility(VISIBLE);
    }

    public void hideProgressbar(){
        progressBar.setVisibility(INVISIBLE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int ringSize = getResources().getDimensionPixelSize(R.dimen.progress_bar_size);
        progressBar.getLayoutParams().height = imageButton.getHeight() + ringSize;
        progressBar.getLayoutParams().width = imageButton.getWidth() + ringSize;
        ((LayoutParams) progressBar.getLayoutParams()).gravity = Gravity.CENTER;
        ((LayoutParams) imageButton.getLayoutParams()).gravity = Gravity.CENTER;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (getLayoutParams() instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) getLayoutParams();
            layoutParams.setBehavior(behaviour);
        }

        int ringSize = getResources().getDimensionPixelSize(R.dimen.progress_bar_size);
        progressBar.getLayoutParams().height = imageButton.getHeight() + ringSize;
        progressBar.getLayoutParams().width = imageButton.getWidth() + ringSize;
        ((LayoutParams) progressBar.getLayoutParams()).gravity = Gravity.CENTER;
        ((LayoutParams) imageButton.getLayoutParams()).gravity = Gravity.CENTER;

    }

    public class ProgressImageButtonBehaviour extends CoordinatorLayout.Behavior<FrameLayout> {

        public ProgressImageButtonBehaviour() {
            super();
        }

        @Override
        public boolean layoutDependsOn(CoordinatorLayout parent, FrameLayout child, View dependency) {
            return true;
        }

        @Override
        public boolean onDependentViewChanged(CoordinatorLayout parent, FrameLayout child, View dependency) {
            float translationY = Math.min(dependency.getTranslationY() - dependency.getHeight(), 0);
            if (child.getBottom() > dependency.getTop()) {
                child.setTranslationY(translationY);
            }
            return true;
        }
    }
}
