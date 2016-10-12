package com.zlb.others;
import java.util.Calendar;


public class Test2 {
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		String s = "" + calendar.get(Calendar.YEAR)
				+ (calendar.get(Calendar.MONTH)+1) 
				+ calendar.get(Calendar.DAY_OF_MONTH)
				+ calendar.get(Calendar.HOUR_OF_DAY)
				+ calendar.get(Calendar.MINUTE) 
				+ calendar.get(Calendar.SECOND)
				;
		System.out.println(s);
	}
}
