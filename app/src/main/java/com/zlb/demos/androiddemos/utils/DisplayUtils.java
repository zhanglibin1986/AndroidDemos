package com.zlb.demos.androiddemos.utils;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * DisplayUtils
 * <ul>
 * <li>{@link #getDisplay(Context)}获取显示设备</li>
 * <li>{@link #getScreenWidth(Context)}获取屏幕宽度</li>
 * <li>{@link #getScreenHeight(Context)} 获取屏幕高度大小</li>
 * <li>{@link #dipToPx(Context, float)}dip转换为px</li>
 * <li>{@link #pxToDip(Context, int)}px转换为dip</li>
 * <li>{@link #getDensityDpi(Context)}获取屏幕密度</li>
 * <li>{@link #getStatusBarHeight(Context)}获得状态栏高度</li>
 * </ul>
 * 
 * @author zhanglibin
 */
public class DisplayUtils {
	private static final String TAG = DisplayUtils.class.getSimpleName();

	/**
	 * 获取显示设备参数
	 * 
	 * @param context
	 * @return
	 */
	public static Display getDisplay(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay();
	}

	/**
	 * 获取屏幕宽度大小，单位px
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		Display display = getDisplay(context);
		int screenWidth = display.getWidth();
		return screenWidth;
	}

	/**
	 * 获取屏幕高度大小，单位px
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		Display display = getDisplay(context);
		int screenHeight = display.getHeight();
		return screenHeight;
	}

	/**
	 * dip转换为px
	 * 
	 * @param context
	 * @param dip
	 * @return
	 */
	public static int dipToPx(Context context, float dip) {
        return (int) (dip * getDisplayMetrics(context).density + 0.5f);
    }

	/**
	 * px转换为dip
	 * 
	 * @param context
	 * @param px
	 * @return
	 */
	public static int pxToDip(Context context, int px) {
		return (int) (px / getDisplayMetrics(context).density + 0.5f);
	}

	private static DisplayMetrics getDisplayMetrics(Context context) {
		Display display = getDisplay(context);
		DisplayMetrics dm = new DisplayMetrics();
		display.getMetrics(dm);
		return dm;
	}

	/**
	 * 获取屏幕密度
	 * 
	 * @param context
	 * @return
	 */
	public static int getDensityDpi(Context context) {
		DisplayMetrics dm = getDisplayMetrics(context);
		return dm.densityDpi;
	}

	/**
	 * 获得状态栏高度
	 * 
	 * @param ctx
	 * @return
	 */
	public static int getStatusBarHeight(Context ctx) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = ctx.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return sbar;
	}
	/**
	 * 获取textview文案的宽度
	 * 
	 * @param tv
	 * @return
	 * 		宽度 - px单位
	 */
	public static int getTextStringWidth(TextView tv) {
		Rect bounds = new Rect();
		String text = tv.getText().toString();
		TextPaint paint;

		paint = tv.getPaint();
		paint.getTextBounds(text, 0, text.length(), bounds);
		return bounds.width();
	}
	
	/**
	 * 获取textview文案的宽度
	 * 
	 * @param tv
	 * @return
	 * 		高度 - px单位
	 */
	public static int getTextStringHeight(TextView tv) {
		Rect bounds = new Rect();
		String text = tv.getText().toString();
		TextPaint paint;

		paint = tv.getPaint();
		paint.getTextBounds(text, 0, text.length(), bounds);
		return bounds.height();
	}
}
