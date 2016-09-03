package com.zlb.demos.androiddemos.material;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;
import com.zlb.demos.androiddemos.fresco.FrescoManager;
import com.zlb.demos.androiddemos.utils.UrlUtil;
import com.zlb.demos.androiddemos.utils.Util;

import butterknife.Bind;
import butterknife.OnClick;


public class TransitionSourceActivity extends BaseActivity {


    @Bind(R.id.demo1Image) protected SimpleDraweeView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_transition_demo1);
        FrescoManager.loadUrl(UrlUtil.URL_IMG).into(image);
    }

    @OnClick(R.id.demo1Image)
    public void onDemoImageClicked(View view) {
        Util.launch(this, new Intent(this, TransitioinDestinationActivity.class), view, getString(R.string.trans_imagedemo));
    }
}
