package com.zlb.demos.androiddemos.net;


import android.database.Observable;
import android.support.annotation.NonNull;

import com.zlb.demos.androiddemos.commens.list.CommenListRequest;
import com.zlb.demos.androiddemos.commens.list.OkRequestListener;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Subscriber;

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
//        okHttpClient.cache()
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

    public rx.Observable<Response> request(Request request) {
        return rx.Observable.create(new rx.Observable.OnSubscribe<Response>() {
            @Override
            public void call(Subscriber<? super Response> subscriber) {
                try {
                    subscriber.onNext(okHttpClient.newCall(request).execute());
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        });
    }

    public void execute(final CommenListRequest commenListRequest, Request okRequest, @NonNull final List<OkRequestListener> listeners) {
        okHttpClient.newCall(okRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(listeners == null || listeners.isEmpty()) {
                    return;
                }
                for(int i = 0; i < listeners.size(); i++) {
                    listeners.get(i).onOkFailure(commenListRequest, e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(listeners == null || listeners.isEmpty()) {
                    return;
                }
                for(int i = 0; i < listeners.size(); i++) {
                    listeners.get(i).onOkResponse(commenListRequest, response);
                }
            }
        });
    }

}
