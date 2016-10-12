package com.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhanglibin on 2014/11/25.
 */
public class Test {

    public static void main(String[] args) {
        print("" + getWeekTime("2014-11-23"));
        print("" + getWeekTime("2014-11-24"));
        print("" + Integer.valueOf("01"));
        print("yue " + monthsBetween("2014-11-23", "2014-10-23"));
    }

    public static void print(String str) {
        System.out.println(str);
    }

    public static int getWeekTime(String sDate){
        try{
            String formater = "yyyy-MM-dd";
            SimpleDateFormat format = new SimpleDateFormat(formater);
            Date date=format.parse(sDate);
            return date.getDay();
        }catch(Exception ex){
            System.out.println("TimeUtil getFullDateWeekTime"+ex.getMessage());
            return -1;
        }
    }

    /**
     * 计算time2 - time1的月的差值,比如：“2014-09-04” - “2014-08-01” = 1；
     * @return
     */
    public static int monthsBetween(String t1,String t2) {
        if(t1.length() > 10) {
            t1 = t1.substring(0, 10);
        }

        if(t2.length() > 10) {
            t2 = t2.substring(0, 10);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = null;
        Date date2 = null;
        try {
            date1 = sdf.parse(t1);
            date2 = sdf.parse(t2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int year1 = date1.getYear();
        int year2 = date2.getYear();

        int m1 = date1.getMonth();
        int m2 = date2.getMonth();

        return (year1 - year2) * 12 + (m1 - m2);
    }


}
