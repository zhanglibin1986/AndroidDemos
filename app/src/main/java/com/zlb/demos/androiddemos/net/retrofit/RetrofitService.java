package com.zlb.demos.androiddemos.net.retrofit;

import com.zlb.demos.androiddemos.commens.Constant;
import com.zlb.demos.androiddemos.net.OkHttpManager;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.net.retrofit
 * @date 16/8/10上午10:17
 * @Description
 */
public class RetrofitService {
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .client(OkHttpManager.getInstance().getOkHttpClient())
//            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//            .addConverterFactory(JacksonConverterFactory.create())
            .addConverterFactory(FastJsonConverterFactory.create());


    public static <T> T createService(Class<T> serviceClass) {
        Retrofit retrofit = builder.build();
        return retrofit.create(serviceClass);
    }
}
