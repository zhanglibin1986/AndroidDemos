package com.zlb.demos.androiddemos.material;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import butterknife.BindView;
import butterknife.OnClick;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;

public class CircularRevealActivity extends BaseActivity {
    private static final int ANIM_DURATION = 300;
    @BindView(R.id.m_circle) protected SimpleDraweeView circleView;
    @BindView(R.id.m_rectImage) protected SimpleDraweeView rectView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal);
    }


    @OnClick(R.id.m_btn1)
    public void onBtn1Clicked(View view) {
        if(rectView.getVisibility() == View.VISIBLE) {
            hide(rectView);
        } else {
            show(rectView);
        }
    }

    @OnClick(R.id.m_btn2)
    public void onBtn2Clicked(View view) {
        if (rectView.getVisibility() == View.VISIBLE) {
            hideEage(rectView);
        } else {
            showEage(rectView);
        }
    }

    @OnClick(R.id.m_btn3)
    public void onBtn3Clicked(View view) {
        if(circleView.getVisibility() == View.VISIBLE) {
            hide(circleView);
        } else {
            show(circleView);
        }
    }

    @OnClick(R.id.m_btn4)
    public void onBtn4Clicked(View view) {
        if (circleView.getVisibility() == View.VISIBLE) {
            hideEage(circleView);
        } else {
            showEage(circleView);
        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void show(View view) {
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;

        int finalRadius = Math.max(view.getWidth(), view.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
        anim.setDuration(ANIM_DURATION);
        view.setVisibility(View.VISIBLE);
        anim.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void hide(View view) {
// get the center for the clipping circle
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;

// get the initial radius for the clipping circle
        int initialRadius = view.getWidth();

// create the animation (the final radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0);
        anim.setDuration(ANIM_DURATION);
// make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
            }
        });

// start the animation
        anim.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void showEage(View view) {
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;

        int finalRadius = Math.max(view.getWidth(), view.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(view, 0, 0, 0, finalRadius);
        anim.setDuration(ANIM_DURATION);
        view.setVisibility(View.VISIBLE);
        anim.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void hideEage(View view) {
// get the center for the clipping circle
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;

// get the initial radius for the clipping circle
        int initialRadius = view.getWidth();

// create the animation (the final radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(view, 0, 0, initialRadius, 0);
        anim.setDuration(ANIM_DURATION);
// make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
            }
        });

// start the animation
        anim.start();
    }


}
