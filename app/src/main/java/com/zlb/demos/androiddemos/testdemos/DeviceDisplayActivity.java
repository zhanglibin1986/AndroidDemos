package com.zlb.demos.androiddemos.testdemos;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;
import butterknife.BindView;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/11/1 下午3:55
 */

public class DeviceDisplayActivity extends BaseActivity {
    @BindView(R.id.display_device_info)
    protected TextView deviceInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_display);

        deviceInfo.setText(readDeviceInfo() + "\n" + readDisplayInfo());
    }

    private String readDeviceInfo() {

        StringBuilder sb = new StringBuilder();
        sb.append("Build.BOARD主板：" + Build.BOARD + "\n");
        sb.append("Build.BOOTLOADER系统启动程序版本号：" + Build.BOOTLOADER + "\n");
        sb.append("Build.BRAND系统定制商：" + Build.BRAND + "\n");
        sb.append("Build.CPU_ABIcpu指令集：" + Build.CPU_ABI + "\n");
        sb.append("Build.CPU_ABI2cpu指令集2" + Build.CPU_ABI2 + "\n");
        sb.append("Build.DEVICE设置参数： " + Build.DEVICE + "\n");
        sb.append("Build.DISPLAY显示屏参数：" + Build.DISPLAY + "\n");
        sb.append("Build.getRadioVersion()无线电固件版本：" + Build.getRadioVersion() + "\n");
        sb.append("Build.FINGERPRINT硬件识别码：" + Build.FINGERPRINT + "\n");
        sb.append("Build.HARDWARE硬件名称： " + Build.HARDWARE + "\n");
        sb.append("Build.HOST HOST: " + Build.HOST + "\n");
        sb.append("Build.ID 修订版本列表：" + Build.ID + "\n");
        sb.append("Build.MANUFACTURER 硬件制造商：" + Build.MANUFACTURER + "\n");
        sb.append("Build.MODEL 版本：" + Build.MODEL + "\n");
        sb.append("Build.SERIAL 硬件序列号：" + Build.SERIAL + "\n");
        sb.append("Build.PRODUCT 手机制造商：" + Build.PRODUCT + "\n");
        sb.append("Build.TAGS 描述Build的标签：" + Build.TAGS + "\n");
        sb.append("Build.TIME TIME:" + Build.TIME + "\n");
        sb.append("Build.TYPE builder类型：" + Build.TYPE + "\n");
        sb.append("Build.USER USER:" + Build.USER + "\n");
        return sb.toString();
    }

    private String readDisplayInfo() {
        StringBuffer sb = new StringBuffer();

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(metrics);

        sb.append("metrics.density:" + metrics.density + "\n");
        sb.append("metrics.densityDpi:" + metrics.densityDpi + "\n");
        sb.append("metrics.heightPixels:" + metrics.heightPixels + "\n");
        sb.append("metrics.widthPixels:" + metrics.widthPixels + "\n");
        sb.append("metrics.scaledDensity:" + metrics.scaledDensity + "\n");
        sb.append("metrics.xdpi:" + metrics.xdpi + "\n");
        sb.append("metrics.ydpi:" + metrics.ydpi + "\n");

        return sb.toString();
    }
}
