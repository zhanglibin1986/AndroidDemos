package com.andlisoft.zlb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by zhanglibin on 2014/8/14.
 */
public class TestTime {

    public static void main(String[] args) {
        try {
            daysBetween("2014-08-11", "2014-08-12");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算time1 - time2的值,比如：“2014-08-04” - “2014-08-01” = 3；
     * @return
     */
    public static int daysBetween(String t1,String t2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(t1));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(t2));
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / (1000 * 3600 * 24);
        System.out.print("t1 = " + t1 + " , t2 = " + t1 + ", time1 = " + time1 + " , time2 = " + time2 + " , time2 - time1 = " + (time2 - time1) + " , betweenDays = " + betweenDays );
        return Integer.parseInt(String.valueOf(betweenDays));
    }


}

