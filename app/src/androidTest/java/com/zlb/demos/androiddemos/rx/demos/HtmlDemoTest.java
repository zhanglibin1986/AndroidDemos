package com.zlb.demos.androiddemos.rx.demos;

import android.util.Log;
import com.zlb.demos.androiddemos.net.OkHttpManager;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/9/21 下午11:52
 */
public class HtmlDemoTest {
    private OkHttpManager manager;
    private String TEST_URL_1 = "http://www.mzitu.com/";

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
}