package com.zlb.demos.androiddemos.labels.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.LruCache;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.labels.activitys
 * @date 16/8/2下午4:48
 * @Description
 */
public class LruCacheActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LruCache cache = new LruCache(20);
    }
}
