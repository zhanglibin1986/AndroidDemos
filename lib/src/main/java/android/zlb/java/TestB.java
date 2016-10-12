package android.zlb.java;

public class TestB {
	public static void main(String[] args) {
		TestB b = new TestB();
		b.initMonth(8);
		
		String s = "abcd";
		System.out.println(s.substring(s.length() - 2, s.length()));
		
		
		
	}
	/**
	 * 只针对次种格式的时间2014-01-09 17:39:35
	 * @param date
	 * @return
	 */
	public static String getMonth(String date) {
		//2014-01-09 17:39:35
		if(date == null || date.length() < 9) {
			return null;
		}
		return date.substring(5, 7);
	}
	public void initMonth(int month) {
		for(int i = 0; i < 7; i++) {
			int temp = month + i -1;
			if(temp < 1) {
				//去年
				printLastYear(temp);
			} else if(temp < 13){
				//今年
				//第一个显示的是去年12月份时，今年1月份要显示年份
				printCurrentYear(temp, month == 1);
			} else {
				//明年
				printNextYear(temp);
			}
		}
	}
	
	private void printLastYear(int m) {
		printCurrentYear(12 - Math.abs(m), false);
	}
	
	private void printCurrentYear(int m, boolean showYear) {
		switch(m) {
		case 1:
			if(showYear) {
				System.out.println("某年1");
			} else {
				System.out.println("1");
			}
			break;
		case 2:
			System.out.println("2");
			break;
		case 3:
			System.out.println("3");
			break;
		case 4:
			System.out.println("4");
			break;
		case 5:
			System.out.println("5");
			break;
		case 6:
			System.out.println("6");
			break;
		case 7:
			System.out.println("7");
			break;
		case 8:
			System.out.println("8");
			break;
		case 9:
			System.out.println("9");
			break;
		case 10:
			System.out.println("10");
			break;
		case 11:
			System.out.println("11");
			break;
		case 12:
			System.out.println("12");
			break;
		}
	}
	
	private void printNextYear(int m) {
		printCurrentYear(Math.abs(m) - 12, true);
	}
	
}
