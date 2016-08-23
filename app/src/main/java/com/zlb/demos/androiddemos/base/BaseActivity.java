package com.zlb.demos.androiddemos.base;

import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.base
 * @date 16/8/12上午11:32
 * @Description
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }
}
