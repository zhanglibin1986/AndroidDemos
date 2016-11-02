package com.zlb.demos.androiddemos.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Set;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.utils
 * @date 16/8/23下午7:07
 * @Description
 */
public class Util {
    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void launch(Activity activity, Intent intent, View view, String transitionName) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, transitionName);//一个共享元素
            ActivityCompat.startActivity(activity, intent, compat.toBundle());
        }
    }

    public static void launch(Activity activity, Intent intent, View view1, String transitionName1, View view2, String transitionName2) {

        Pair<View, String> pair1 = Pair.create(view1, transitionName1);
        Pair<View, String> pair2 = Pair.create(view2, transitionName2);

        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair1, pair2);//共享2个元素
        ActivityCompat.startActivity(activity, intent, compat.toBundle());
    }

    public static void launch(Activity activity, Intent intent, View view1, String transitionName1, View view2, String transitionName2, View view3, String transitionName3) {

        Pair<View, String> pair1 = Pair.create(view1, transitionName1);
        Pair<View, String> pair2 = Pair.create(view2, transitionName2);
        Pair<View, String> pair3 = Pair.create(view3, transitionName3);
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair1, pair2, pair3);//共享3个元素
        ActivityCompat.startActivity(activity, intent, compat.toBundle());
    }

    /**
     *
     * @param activity
     * @param intent
     * @param map
     */
    public static void launch(Activity activity, Intent intent, @NonNull HashMap<String, View> map) {

        if(map == null) {
            throw new RuntimeException("scene transition params map cann't be null.");
        }

        Set<String> set = map.keySet();
        Pair[] pairs = new Pair[map.size()];

        String[] keys = (String[])set.toArray();
        for(int i = 0; i < keys.length; i++) {
            pairs[i] = Pair.create(map.get(keys[i]), keys[i]);
        }
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);//共享多个个元素
        ActivityCompat.startActivity(activity, intent, compat.toBundle());
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference an object reference
     * @return the non-null reference that was validated
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public static String getMD5String(String text) {
        MessageDigest md;
        StringBuffer md5 = new StringBuffer();
        try {
            md = MessageDigest.getInstance("MD5");
            if (md != null && text != null) {
                md.update(text.getBytes());
                byte[] digest = md.digest();
                for (int i = 0; i < digest.length; i++) {
                    md5.append(Character.forDigit((digest[i] & 0xF0) >> 4, 16));
                    md5.append(Character.forDigit((digest[i] & 0xF), 16));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5.toString();
    }

    /**
     * 隐藏输入法
     */
    public final static void hideInput(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

}
