package com.zlb.others;
import java.util.Calendar;


public class FaPiao {
	public static void main(String[] args) {
//		int total = 56+97+138+13+14+10+10+10+10+10+10+10+60+5+1+1+1+1;
//		total += 232;
//		System.out.println("total = "+total);
		
		Month july = new Month(7);
		july.setAccounts(232);
		System.out.println(july);
		
		Month october = new Month(8);
		october.setAccounts(56,97,138,13,14,10,10,10,10,10,10,10,60,5,1,1,1,1);
		System.out.println(october);
		
		System.out.println("7，8 月 总和： "+(july.getTotal()+october.getTotal()));
		
		Month september = new Month(9);
		september.setAccounts(107,68,90,89,52,5,1,1,1,1,80,49,5);
		System.out.println(september);
		
	}
}



class Month {
	private float sum = 0;
	private String monthTitle;
	private int numBill;
	public float getTotal() {
		return sum;
	}
	/**
	 * 参数为所报销发票的月份
	 * @param month
	 */
	public Month(int month) {
		monthTitle = String.valueOf(month);
	}
	/**
	 * 参数为每张发票的金额
	 * @param item
	 */
	public void setAccounts(int ...item) {
		numBill = item.length;
		for(int i=0;i<item.length;i++) {
			sum += item[i];
		}
	}
	
	public String toString() {
		StringBuffer mStringBuffer = new StringBuffer();
		mStringBuffer.append(Calendar.getInstance().get(Calendar.YEAR)).append("年").append(monthTitle).append("月 报销发票数量:").append(numBill).append("\n")
		.append("总金额：").append(getTotal());
		return mStringBuffer.toString();
	}
	
}