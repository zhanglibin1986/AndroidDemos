package com.zlb.demos.androiddemos.utils;

import android.content.Context;
import android.widget.Toast;

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
}
