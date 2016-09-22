package com.zlb.demos.androiddemos.commens.list.sample;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.transition.TransitionSet;
import butterknife.BindView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.DraweeTransition;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;
import com.zlb.demos.androiddemos.fresco.FrescoManager;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class SampleActivity5 extends BaseActivity {
    @BindView(R.id.s5_img)
    protected SimpleDraweeView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample5);


        if(!TextUtils.isEmpty(getIntent().getStringExtra("url"))) {
            FrescoManager.loadUrl(getIntent().getStringExtra("url")).into(image);
        }
        //共享元素是SimpleDraweeView的话，目标Activity必须调用下面的方法
        TransitionSet transitionSet = DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.CENTER_CROP, ScalingUtils.ScaleType.FIT_CENTER);
        ChangeBounds changeBounds = new ChangeBounds();
        ArcMotion arcMotion = new ArcMotion();
        arcMotion.setMaximumAngle(90);
        arcMotion.setMinimumHorizontalAngle(15);
        changeBounds.setPathMotion(arcMotion);
        transitionSet.addTransition(changeBounds);
        getWindow().setSharedElementEnterTransition(transitionSet);
        getWindow().setSharedElementReturnTransition(transitionSet);


    }
}
