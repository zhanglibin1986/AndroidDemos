package com.zlb.demos.androiddemos.material;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.TransitionInflater;
import butterknife.BindView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;
import com.zlb.demos.androiddemos.fresco.FrescoManager;
import com.zlb.demos.androiddemos.utils.UrlUtil;

public class TransitioinDestinationActivity extends BaseActivity {
    @BindView(R.id.demo_sub_image) protected SimpleDraweeView image;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition( new Explode() );
//        getWindow().setExitTransition( new Explode() );
        setContentView(R.layout.activity_demo1_sub1);
        getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.arc1));
//        //共享元素是SimpleDraweeView的话，目标Activity必须调用下面的方法
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setSharedElementEnterTransition(DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.CENTER_CROP,ScalingUtils.ScaleType.FIT_CENTER));
//            getWindow().setSharedElementReturnTransition(DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.FIT_CENTER,ScalingUtils.ScaleType.CENTER_CROP));
//        }

        FrescoManager.loadUrl(UrlUtil.URL_IMG2).into(image);
    }
}
