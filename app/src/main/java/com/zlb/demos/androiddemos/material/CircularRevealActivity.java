package com.zlb.demos.androiddemos.material;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;
import com.zlb.demos.androiddemos.utils.Util;

import butterknife.OnClick;

public class CircularRevealActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal);
    }

    @OnClick(R.id.m_rect)
    public void onRectClicked(View view) {
        Util.showToast(this, "hehe");
    }
}
