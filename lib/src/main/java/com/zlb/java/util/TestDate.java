package com.zlb.java.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//http://dlc.sun.com.edgesuite.net/jdk/jdk-api-localizations/jdk-api-zh-cn/builds/latest/html/zh_CN/api/index.html?overview-summary.html
public class TestDate {
	public static void main(String[] args) {
		Calendar mCalendar = Calendar.getInstance();
		GregorianCalendar mGregorianCalendar = new GregorianCalendar();
		
		
		Date date1 = new Date();
		
		//已过时。从 JDK 1.1 开始，由 Calendar.set(year + 1900, month, date) 或 GregorianCalendar(year + 1900, month, date) 取代。
		Date date2 = new Date(2014, 5, 8);
		mCalendar.set(2014, 5, 8);
		mGregorianCalendar.set(2014, 5, 8);
		System.out.println(date2.getYear() 
				+ " , mGregorianCalendar = " + mGregorianCalendar.get(GregorianCalendar.YEAR)
				+ " , mCalendar = " + mCalendar.get(Calendar.YEAR));
		
		//已过时。 从 JDK 1.1 开始，由 Calendar.set(year + 1900, month, date, hrs, min) 或 GregorianCalendar(year + 1900, month, date, hrs, min) 取代。
		Date date3 = new Date(2014, 5, 8, 17, 46, 28);
		mCalendar.set(2014, 5, 8, 17, 46, 28);
		mGregorianCalendar.set(2014, 5, 8, 17, 46, 28);
		
		System.out.println(date3.getYear() 
				+ " , mGregorianCalendar = " + mGregorianCalendar.get(GregorianCalendar.MINUTE)
				+ " , mCalendar = " + mCalendar.get(Calendar.MINUTE));
		
		//已过时。 从 JDK 1.1 开始，由 Calendar.set(year + 1900, month, date, hrs, min, sec) 或 GregorianCalendar(year + 1900, month, date, hrs, min, sec) 取代。
		Date date4 = new Date(2014, 5, 8, 17, 46);
		mCalendar.set(2014, 5, 8, 17, 46);
		mGregorianCalendar.set(2014, 5, 8, 17, 46);
		
		//没过时
		Date date5 = new Date(System.currentTimeMillis());
		
		System.out.println(date4.after(date5));
		
		System.out.println(date4.before(date5));
		
		
		
		
		
		
		date1.setTime(System.currentTimeMillis());//(相当于new Date(System.currentTimeMillis()))
		long time = date1.getTime();
	}
}
