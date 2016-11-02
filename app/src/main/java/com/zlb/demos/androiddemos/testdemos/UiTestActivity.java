package com.zlb.demos.androiddemos.testdemos;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewConfiguration;
import butterknife.BindView;
import butterknife.OnClick;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static android.support.design.R.styleable.CoordinatorLayout;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/11/1 上午10:27
 */

public class UiTestActivity extends BaseActivity {

    @BindView(R.id.snack_container)
    protected CoordinatorLayout snackContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_test);
        
        
    }

    @OnClick(R.id.btn1)
    public void onBtn1Clicked(View view) {
        showSnackBar("isMeizu " + isMeizu() + ", has navigationbar " + hasNavBar(this) + " , NavigationBarHeight = " + getNavigationBarHeight(this));
    }

    private void showSnackBar(String msg) {
        Snackbar snackbar =
                Snackbar.make(snackContainer, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private int getScreenHight(){
        // 获取屏幕高
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        int screenHeight = point.y + (isMeizu() ? 0 : getNavigationBarHeight(this));
        return screenHeight;
    }
    /**
     * 获取虚拟按键栏高度
     * @param context
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        if (hasNavBar(context)){
            Resources res = context.getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

    /**
     * 检查是否存在虚拟按键栏
     * @param context
     * @return
     */
    private static boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            // check override flag
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else { // fallback
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    /**
     * 判断虚拟按键栏是否重写
     * @return
     */
    private static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
            }
        }
        return sNavBarOverride;
    }

    /**
     * 判断是否meizu手机
     * @return
     */
    public static boolean isMeizu() {
        return Build.BRAND.equals("Meizu");
    }
    /**
     * 获取魅族手机底部虚拟键盘高度
     * @param context
     * @return
     */
    public static int getSmartBarHeight(Context context)
    {
        try {
            Class c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("mz_action_button_min_height");
            int height = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //获取底部 navigation bar 高度
    public static int getNavigationBarHeight2(Context context) {

        Resources resources = context.getResources();

        int resourceId = resources.getIdentifier("navigation_bar_height","dimen", "android");

        int height = resources.getDimensionPixelSize(resourceId);


        return height;

    }
}
