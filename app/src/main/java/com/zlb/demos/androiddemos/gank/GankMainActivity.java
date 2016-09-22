package com.zlb.demos.androiddemos.gank;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Window;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;

public class GankMainActivity extends BaseActivity {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition( new Explode() );
        getWindow().setExitTransition( new Explode() );
        setContentView(R.layout.activity_gank);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAfterTransition();
    }
}
