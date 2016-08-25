package com.zlb.demos.androiddemos.commens.list.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_activity_triplist);
    }
}
