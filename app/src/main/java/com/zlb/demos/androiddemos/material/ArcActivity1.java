package com.zlb.demos.androiddemos.material;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.View;

import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;
import com.zlb.demos.androiddemos.utils.Util;

import butterknife.OnClick;
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class ArcActivity1 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc1);


    }


    @OnClick(R.id.arc_img1)
    public void onArcImgClicked(View view) {
        Util.launch(this, new Intent(this, ArcActivity2.class), view, view.getTransitionName());
    }
}
