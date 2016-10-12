package com.zlb.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class PublicUtil {
	private static int lastPrintNum = 0;
	private static int hidePrintNum = 0;
	
	/**
	 * 同System.out.println();
	 * @param string
	 */
	public static void print(String string) {
		System.out.println(string);
	}
	
	/**
	 * 打印的时候会在前面加上数字，如：3 = xxxx
	 * @param num
	 * @param string
	 */
	public static void print(int num, boolean string) {
		if(lastPrintNum != num) {
			lastPrintNum = num;
			System.out.println("\n" + num + " = " + string);
		} else {
			System.out.println(num + " = " + string);
		}
	}
	
	public static void print(int num, String string) {
		if(lastPrintNum != num) {
			lastPrintNum = num;
			PublicUtil.print("\n" + num + " = " + string);
		} else {
			PublicUtil.print(num + " = " + string);
		}
	}
	
	public static <T> void print(int num, T[] string) {
		if(lastPrintNum != num) {
			lastPrintNum = num;
			PublicUtil.print("\n" + num + " = " + Arrays.toString(string));
		} else {
			PublicUtil.print(num + " = " + Arrays.toString(string));
		}
	}
	
	public static String timeStringAll(long time) {
		SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentTime = new Date(time);
		return formatTime.format(currentTime);
	}
	
	public static String timeFormat(long time, String format) throws NullPointerException, IllegalArgumentException {
		SimpleDateFormat formatTime = new SimpleDateFormat(format);
		Date currentTime = new Date(time);
		return formatTime.format(currentTime);
	}

    /**
     * 判断字符串是否为空
     * @param string
     * @return
     */
	public static boolean isEmpty(String string) {
		return string == null ? true : string.trim().length() == 0;
	}
}
