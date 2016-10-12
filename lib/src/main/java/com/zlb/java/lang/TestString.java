package com.zlb.java.lang;

import java.io.UnsupportedEncodingException;

/**
 * Created by zhanglibin on 2014/11/6.
 */
public class TestString {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String test = "test张立宾";
        System.out.println("test encode = " + getEncoding(test)  + " : " + test);
        String test2 = new String(test.getBytes(), "utf-8");
        System.out.println("test2 encode = " + getEncoding(test2)  + " : " + test2);
        String test3 = new String(test2.getBytes("utf-8"), "GB2312");
        System.out.println("test3 encode = " + getEncoding(test3)  + " : " + test3);

    }


    /**
     * 判断字符串的编码
     *
     * @param str
     * @return
     */
    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }
}
