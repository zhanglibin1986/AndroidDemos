package android.zlb.java;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestC {
	public static void main(String[] args) {
		System.out.println(timeClockString2());
		String s1 = "abc";
		String s2 = "张立宾";
		System.out.println("s1 = " + s1.getBytes().length + " , s2 = " + s2.getBytes().length);
		System.out.println("s1 = " + s1.length() + " , s2 = " + s2.length());
		try {
			System.out.println("s1 = " + s1.getBytes().length + " , s2 = " + s2.getBytes("gbk").length);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println("s1 = " + s1.getBytes("gbk").length + " , s2 = " + s2.getBytes("gbk").length);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String d = "2014-03-02";
		Calendar mCalendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// 设置格式
		Date date = null;
		try {
			date = format.parse(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mCalendar.setTime(date);
		int week_day = mCalendar.get(Calendar.DAY_OF_WEEK);
		System.out.println("" + week_day);
		
	}
	
	public static String timeClockString2() {
		SimpleDateFormat formatTime = new SimpleDateFormat("MM-dd HH:mm");
		Date currentTime = new Date();
		return formatTime.format(currentTime);
	}
	
//	public static String getWeekDayName(String date) {
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置格式
//		String weekDayName = "";
//
//		Date mDate;
//		try {
//			mDate = format.parse(date);
//		} catch (ParseException e) {
//			e.printStackTrace();
//			mDate = new Date();
//		}
//		Calendar mCalendar = Calendar.getInstance();
//		mCalendar.setTime(mDate);
//
//		int week_day = mCalendar.get(Calendar.DAY_OF_WEEK);
//		SimpleDateFormat formatTime = new SimpleDateFormat(TextUtil.getString(R.string.letvutil_time_format));
//		weekDayName = formatTime.format(mCalendar.getTime()) + "(" + getWeekName(week_day) + ")";
//
//		return weekDayName;
//	}
	
	
}
