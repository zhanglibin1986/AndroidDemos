package com.zlb.demos.androiddemos.utils;

import android.webkit.URLUtil;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import retrofit2.http.Url;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/9/2 下午2:24
 */
public class UrlUtil {
    public static final String URL_IMG = "http://breadtrip-dev.qiniudn.com/hunter/product/cover/90559_o_1apd1aok0kkt1q1t1eovri0102f9.jpg?imageView2/5/w/788/h/426/format/jpg/interlace/1/";
    public static final String URL_IMG2 = "http://breadtrip-dev.qiniudn.com/hunter/product/cover/128700_o_1apd4jlb5p4robu1gju14fc1sae9.jpeg?imageView2/5/w/788/h/426/format/jpg/interlace/1/";
    public static final String URL_IMG3 = "http://photos.breadtrip.com/photo_2016_08_29_54331e42b4a04a464851457a991951ed.jpg?imageView/2/w/960/q/85";


    private static URL url;
    private static HttpURLConnection con;
    private static int state = -1;

    /**
     * 功能：检测当前URL是否可连接或是否有效,
     * 描述：最多连接网络 5 次, 如果 5 次都不成功，视为该地址不可用
     * @param urlStr 指定URL网络地址
     * @return URL
     */
    public synchronized URL isConnect(String urlStr) {
        int counts = 0;
        if (urlStr == null || urlStr.length() <= 0) {
            return null;
        }
        while (counts < 5) {
            try {
                url = new URL(urlStr);
                con = (HttpURLConnection) url.openConnection();
                state = con.getResponseCode();
                System.out.println(counts +"= "+state);
                if (state == 200) {
                    System.out.println("URL可用！");
                }
                break;
            }catch (Exception ex) {
                counts++;
                System.out.println("URL不可用，连接第 "+counts+" 次");
                urlStr = null;
                continue;
            }
        }
        return url;
    }

    public boolean isAvailable(String url) {
        try {
            URL u = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)u.openConnection();
            return connection.getResponseCode() == 200;
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
