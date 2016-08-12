package com.zlb.demos.androiddemos.net;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.net
 * @date 16/7/27下午2:26
 * @Description
 */
public class OkHttpManager {
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static OkHttpManager okHttpManager = new OkHttpManager();
    public static OkHttpManager getInstance() {
        return okHttpManager;
    }

    private OkHttpManager() {
        initOkHttpClient();
    }

    private void initOkHttpClient() {// TODO: 16/8/10 do init work
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public void request(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        request(request, callback);
    }

    public void request(Request request, Callback callback) {
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
