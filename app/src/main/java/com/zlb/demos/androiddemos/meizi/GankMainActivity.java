package com.zlb.demos.androiddemos.meizi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.meizi.bean.ResultsList;
import com.zlb.demos.androiddemos.net.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GankMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizi);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MeiziApi api = RetrofitService.createService(MeiziApi.class);
        Call<ResultsList> call = api.requestMeiziList(10, 1);
        call.enqueue(new Callback<ResultsList>() {
            @Override
            public void onResponse(Call<ResultsList> call, Response<ResultsList> response) {
                Log.d("meizi", "-------------" + response.body().getResults());
            }

            @Override
            public void onFailure(Call<ResultsList> call, Throwable t) {
                Log.e("meizi", "----------------error");
                t.printStackTrace();
            }
        });
    }
}
