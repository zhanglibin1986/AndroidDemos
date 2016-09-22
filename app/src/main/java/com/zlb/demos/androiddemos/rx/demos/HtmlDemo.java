package com.zlb.demos.androiddemos.rx.demos;

import android.util.Log;
import com.zlb.demos.androiddemos.net.OkHttpManager;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/9/21 下午11:48
 */

public class HtmlDemo {
    private String TEST_URL_1 = "http://www.mzitu.com/";

    public void test(String url) {
        OkHttpManager.getInstance().request(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("html", "-------onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("html", "--" + response.toString());
            }
        });
    }
}
