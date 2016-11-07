package com.zlb.demos.androiddemos.html.mmjpg;

import android.content.Context;
import android.content.SharedPreferences;
import com.zlb.demos.androiddemos.AppApplication;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/11/7 下午5:19
 */

public class MmjpgPreferenceManager {
    private SharedPreferences preferences;

    private volatile static MmjpgPreferenceManager manager;

    public static MmjpgPreferenceManager getInstance() {
        if(manager == null) {
            synchronized (MmjpgPreferenceManager.class) {
                if(manager == null) {
                    manager = new MmjpgPreferenceManager(AppApplication.getApplication());
                }
            }
        }

        return manager;
    }

    private MmjpgPreferenceManager(Context context) {
        preferences = context.getSharedPreferences("Mmjpg_pref", Context.MODE_PRIVATE);
    }

    public void mark(int year, int second) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("year", year);
        editor.putInt("second", second);
        editor.commit();
    }

    public int getYear() {
        return preferences.getInt("year", 2015);
    }

    public int getSecond() {
        return preferences.getInt("second", 0);
    }
    public void add(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getValue(String key) {
        return preferences.getString(key,"");
    }
}
