package com.zlb.demos.androiddemos.commens.list;

import android.text.TextUtils;

import com.zlb.demos.androiddemos.net.OkHttpManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.commens
 * @date 16/8/15上午11:52
 * @Description
 */
public class CommenRequestHelper {
    private OkHttpManager mOkHttpManager;
    private List<OkRequestListener> mListeners;
    private Request mRequest;
    private CommenListRequest commenListRequest;

    public CommenRequestHelper(CommenListRequest recyclerRequest) {
        mOkHttpManager = OkHttpManager.getInstance();
        this.mListeners = recyclerRequest.getListeners();
        this.commenListRequest = recyclerRequest;
        mRequest = makeRequest(recyclerRequest);
    }

    private Request makeRequest(CommenListRequest recyclerRequest) {
        Request.Builder builder = new Request.Builder();

        appendHeaders(builder, recyclerRequest.getHeaders());

        HttpUrl.Builder httpBuilder = HttpUrl.parse(recyclerRequest.getUrl()).newBuilder();
        if(recyclerRequest.getParams() != null && !recyclerRequest.getParams().isEmpty()) {//读取Map<String, String> params
            Map<String, String> params = recyclerRequest.getParams();
            Set<String> set = params.keySet();
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                if(!TextUtils.isEmpty(key) && key.equals(recyclerRequest.getNextStartKey()) && !TextUtils.isEmpty(recyclerRequest.getNextStartValue()) && recyclerRequest.isHasMoreParam()) {//分页参数滤重
                    continue;
                }
                String value = params.get(key);
                httpBuilder.addQueryParameter(key, value);
            }
        }
        if(recyclerRequest.isHasMoreParam() && !TextUtils.isEmpty(recyclerRequest.getNextStartKey())) {
            httpBuilder.addQueryParameter(recyclerRequest.getNextStartKey(), recyclerRequest.getNextStartValue());
        }
        for(String path : recyclerRequest.getPaths()) {
            httpBuilder.addPathSegment(path);
        }

        builder.url(httpBuilder.build());//读取tag
        if(recyclerRequest.getRequestType() == CommenListRequest.RequestType.METHOD_POST) {
            builder.post(buildeRequestBody(recyclerRequest));
        } else {
            builder.get();
        }
        return builder.build();
    }

    private RequestBody buildeRequestBody(CommenListRequest recyclerRequest) {
        FormBody.Builder builderPair = new FormBody.Builder();
        if(!recyclerRequest.getPostParam().isEmpty()) {
            addNameValuePairParams(builderPair, recyclerRequest.getPostParam());
        }
        return builderPair.build();
    }

    private void addNameValuePairParams(FormBody.Builder builder, Map<String, String> postParams) {
        if (builder == null) {
            throw new IllegalArgumentException("builder can not be null .");
        }
        if (postParams != null && !postParams.isEmpty()) {
            Set<String> keySet = postParams.keySet();
            for(String key : keySet) {
                String value = postParams.get(key);
                if(!TextUtils.isEmpty(value)) {
                    builder.addEncoded(key, value);
                }
            }
        }
    }

    /**
     * 执行请求
     */
    public void execute() {
        execute(commenListRequest, mRequest, mListeners);
    }

    private void execute(CommenListRequest recyclerRequest, Request request, List<OkRequestListener> listeners) {
        mOkHttpManager.execute(recyclerRequest, request, listeners);
    }

    protected void appendHeaders(Request.Builder builder, Map<String, String> headers) {
        if (builder == null) {
            throw new IllegalArgumentException("builder can not be empty!");
        }

        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return;

        for (String key : headers.keySet()) {
            headerBuilder.add(key, headers.get(key));
        }
        builder.headers(headerBuilder.build());
    }

    public Observable<Response> rxExecute() {
        return Observable.create(new Observable.OnSubscribe<Response>() {
            @Override
            public void call(Subscriber<? super Response> subscriber) {
                try {
                    Response response = OkHttpManager.getInstance().getOkHttpClient().newCall(mRequest).execute();
                    if(response.isSuccessful()) {
                        subscriber.onNext(response);
                        subscriber.onCompleted();
                    }
                    subscriber.onError(new Exception(""));
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }

}
