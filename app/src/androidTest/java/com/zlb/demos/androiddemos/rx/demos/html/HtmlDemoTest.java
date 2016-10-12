package com.zlb.demos.androiddemos.rx.demos.html;

import android.util.Log;
import com.zlb.demos.androiddemos.gank.GankApi;
import com.zlb.demos.androiddemos.net.OkHttpManager;
import com.zlb.demos.androiddemos.net.retrofit.RetrofitService;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rx.Subscriber;
import rx.functions.Action1;

import static org.junit.Assert.*;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/9/21 下午11:52
 */
public class HtmlDemoTest {
    private OkHttpManager manager;
    private String TEST_URL_1 = "http://www.baidu.com/";

    @Before
    public void setUp() throws Exception {
        manager = OkHttpManager.getInstance();
        Log.d("html", "set up");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test1() throws Exception {
        OkHttpManager.getInstance().request(TEST_URL_1, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("html", "-------onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("html", "--" + response.body().string());
            }
        });
    }

    @Test
    public void requestHtml() throws Exception {
        OkHttpManager.getInstance().request(new Request.Builder().url(TEST_URL_1).build()).subscribe(
                new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("html", "error");
                    }

                    @Override
                    public void onNext(Response response) {
                        try {
                            Log.d("html", "--" + response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    @Test
    public void testRetroUrl() {
        RetrofitService.createService(GankApi.class).request(TEST_URL_1).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d("html", "-----------------------------000 error");
            }

            @Override
            public void onNext(String s) {
                Log.d("html", "-----------------------------s = " + s);
            }
        });
    }
}