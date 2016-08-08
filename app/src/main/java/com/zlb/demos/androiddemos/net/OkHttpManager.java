package com.zlb.demos.androiddemos.net;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.net
 * @date 16/7/27下午2:26
 * @Description
 */
public class OkHttpManager {
    private OkHttpClient okHttpClient = new OkHttpClient();

    public void request(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        request(request, callback);
    }

    public void request(Request request, Callback callback) {
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
