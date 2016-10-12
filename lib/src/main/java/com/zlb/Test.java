package com.zlb;

import java.util.Arrays;

/**
 * Created by zhanglibin on 2014/8/4.
 */
public class Test {

    public static void main(String[] args) {
        String s1 = "05";
        String s2 = "01";
        String s4 = "01,08";

        String[] s3 = s4.split(",");

        System.out.println("s3 = " + Arrays.toString(s3));
        System.out.println(" s1 - s2 = " + (Integer.valueOf(s1) - Integer.valueOf(s2)));
        calMinuteBetween("13:34", "12:50");
    }

    /**
     * 计算HH:mm 相减的分钟差
     * @param m1
     * @param m2
     * @return
     */
    public static int calMinuteBetween(String m1, String m2) {
        int t1 = (Integer.valueOf(m1.substring(0,2)) - Integer.valueOf(m2.substring(0, 2))) * 60;
        int t2 = Integer.valueOf(m1.substring(3,5)) - Integer.valueOf(m2.substring(3,5));
        System.out.println(m1 + " - " + m2 + " = " + (t1 + t2));
        return t1 + t2;
    }
}
