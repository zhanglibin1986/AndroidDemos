package com.zlb.demos.androiddemos.fresco;

import android.graphics.Bitmap;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.fresco
 * @date 16/8/2上午11:37
 * @Description
 */
public interface ImageResponseListener {
    /**
     * 下载成功
     * @param bitmap 图片
     */
    void onSuccess(Bitmap bitmap);

    /**
     * 下载失败
     */
    void onFailure();
}
