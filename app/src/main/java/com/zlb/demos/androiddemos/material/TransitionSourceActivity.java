package com.zlb.demos.androiddemos.material;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;
import com.zlb.demos.androiddemos.fresco.FrescoManager;
import com.zlb.demos.androiddemos.utils.UrlUtil;
import com.zlb.demos.androiddemos.utils.Util;


public class TransitionSourceActivity extends BaseActivity {


    @BindView(R.id.demo1Image) protected SimpleDraweeView image;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_transition_demo1);
        image.setTransitionName(getString(R.string.trans_imagedemo));
        FrescoManager.loadUrl(UrlUtil.URL_IMG).into(image);
    }

    @OnClick(R.id.demo1Image)
    public void onDemoImageClicked(View view) {
        Util.launch(this, new Intent(this, TransitioinDestinationActivity.class), view, getString(R.string.trans_imagedemo));
    }

    @OnClick(R.id.demo1_image)
    public void onRBClicked(View view) {
        Util.launch(this, new Intent(this, TransitioinDestinationActivity.class), view, "transition_name_demo");
    }
}
