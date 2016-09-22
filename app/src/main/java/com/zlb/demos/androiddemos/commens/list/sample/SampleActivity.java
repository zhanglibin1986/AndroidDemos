package com.zlb.demos.androiddemos.commens.list.sample;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.DraweeTransition;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.commens.list.sample
 * @date 16/8/24下午5:55
 * @Description
 */
public class SampleActivity extends BaseActivity{
    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SampleActivity.class);
        context.startActivity(intent);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_activity_triplist);

//        ChangeBounds changeBounds = new ChangeBounds();
//        ArcMotion arcMotion = new ArcMotion();
//        arcMotion.setMaximumAngle(90);
//        arcMotion.setMinimumHorizontalAngle(15);
//        changeBounds.setPathMotion(arcMotion);
//        getWindow().setSharedElementEnterTransition(changeBounds);
//        getWindow().setSharedElementReturnTransition(changeBounds);
    }
}
