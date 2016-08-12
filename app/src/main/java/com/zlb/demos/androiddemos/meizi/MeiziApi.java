package com.zlb.demos.androiddemos.meizi;

import com.zlb.demos.androiddemos.meizi.bean.ResultsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.meizi
 * @date 16/8/12上午10:22
 * @Description
 */
public interface MeiziApi {
    @GET("data/福利/{month}/{day}")
    Call<ResultsList> requestMeiziList(@Path("month") int month, @Path("day") int day);
}
