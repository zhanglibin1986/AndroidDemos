package com.zlb.demos.androiddemos;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos
 * @date 16/8/2上午11:33
 * @Description
 */
public class AppApplication extends Application {
    private static AppApplication appApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        appApplication = this;
        Fresco.initialize(this);
    }

    public static AppApplication getApplication() {
        return appApplication;
    }
}
