package com.zlb.demos.androiddemos.material;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Window;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.DraweeTransition;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;
import com.zlb.demos.androiddemos.fresco.FrescoManager;
import com.zlb.demos.androiddemos.utils.UrlUtil;

import butterknife.Bind;

public class TransitioinDestinationActivity extends BaseActivity {
    @Bind(R.id.demo_sub_image) protected SimpleDraweeView image;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition( new Explode() );
        getWindow().setExitTransition( new Explode() );
        setContentView(R.layout.activity_demo1_sub1);

        //共享元素是SimpleDraweeView的话，目标Activity必须调用下面的方法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementEnterTransition(DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.CENTER_CROP,ScalingUtils.ScaleType.FIT_CENTER));
            getWindow().setSharedElementReturnTransition(DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.FIT_CENTER,ScalingUtils.ScaleType.CENTER_CROP));
        }

        FrescoManager.loadUrl(UrlUtil.URL_IMG2).into(image);
    }
}
