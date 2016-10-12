package com.zlb.demos.androiddemos.gank;

import com.zlb.demos.androiddemos.gank.bean.ResultsList;

import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.meizi
 * @date 16/8/12上午10:22
 * @Description
 */
public interface GankApi {
    @GET("data/福利/{month}/{day}")
    Observable<ResultsList> requestGankList(@Path("month") int month, @Path("day") int day);
    @GET
    Observable<String> request(@Url String url);
}
