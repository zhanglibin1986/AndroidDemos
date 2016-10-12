package com.zlb.demo;

import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Hello {
	public static final String MIYUE53 = "poi345";
	
	public static String format(float f) {
		return null;
	}
	
	/**
	 * 格式化价格 两位小数还是一位小数
	 * */
	public static String formatDoubleNum(double price, int num) {
		DecimalFormat format = null;
		if (num == 1) {
			format = new DecimalFormat("#0.0");
			return format.format(price);
		} else if (num == 2) {
			format = new DecimalFormat("#0.00");
			return format.format(price);
		}

		return String.valueOf(price);
	}
	
	/** 
     * 使用java正则表达式去掉多余的.与0 
     * @param s 
     * @return  
     */  
    public static String subZeroAndDot(String s){  
        if(s.indexOf(".") > 0){  
            s = s.replaceAll("0+?$", "");//去掉多余的0  
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
        }  
        return s;  
    }
	
    private static String addSpace(String text) {
		if(text.length() > 1 && text.startsWith("-")) {
			return "- " + text.substring(1, text.length());
		} else {
			return text;
		}
	}
    
	public static void main(String[] args) {
		String s1 = "123456789";
		
		System.out.println(s1.lastIndexOf("456"));
		
		String r = s1.substring(s1.lastIndexOf("456") + 3, s1.length());
		System.out.println("r = " + r);
		
		String s2 = "2.23";
		
		System.out.println(s1.compareTo(s2));
		
		
		String s3 = "-1";
		String s4 = "-30";
		
		System.out.println("s2 = " + addSpace(s2) + " , s3 = " + addSpace(s3) + " , s4 = " + addSpace(s4));
		System.out.println(formatDoubleNum(1.02, 1));
		
		System.out.println("s1 = " + subZeroAndDot(s1) + " , s2 = " + subZeroAndDot(s2));
		
		System.out.println("Hello Java !!!");
		HashMap<String, String> maps = new HashMap<String, String>();
		//(userId,status,day,version
		maps.put("userId", "18802555");
		maps.put("status", "2");
		maps.put("day", "100");
		maps.put("version", "5.3");
		//45438a4d889f2e0f4b35e5064bf9bd2b
		System.out.println(generSignedKey53(maps));
		String t = "编码问题";
		String t2 = "";
		String t3 = "";
		String t4 = "";
		String t5 = "";
		
		String a = "0";
		float b = Float.valueOf(a);
		System.out.println("a = " + a + " , b = " + b);
		
		try {
			t2 = URLEncoder.encode(t, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		t3 = utf8ToUnicode(t2);
		
		try {
			t4 = new String(t2.getBytes("UTF-8"), "gb2312");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			t5 = URLDecoder.decode(t2, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("t = " + t + " , t2 = " + t2 + " , t3 = " + t3 + " , t4 = " + t4 + " , t5  = " + t5);
		
	}
	
	private static String toHexString(byte[] bytes) {
		StringBuilder hexString = new StringBuilder();

		for (int b : bytes) {
			if (b < 0)
				b += 256;
			if (b < 16)
				hexString.append("0");
			hexString.append(Integer.toHexString(b));
		}
		return hexString.toString();
	}
	
	public static String toMd5(String md5Str) {
		String result = "";
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(md5Str.getBytes("utf-8"));
			result = toHexString(algorithm.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String generSignedKey53(HashMap<String, String> maps) {
		if(maps == null || maps.size() == 0) {
			return null;
		}
		StringBuilder builder = new StringBuilder();
		Set<String> set = maps.keySet();
		String[] keyArray = new String[set.size()];
		set.toArray(keyArray);
		Arrays.sort(keyArray);
		for(int i = 0; i < keyArray.length; i++) {
			builder.append(keyArray[i]);
			builder.append("=");
			builder.append(maps.get(keyArray[i]));
			builder.append("&");
		}
		builder.append("poi345");
		return toMd5(builder.toString());
	}
	
	public static String utf8ToUnicode(String inStr) {
		char[] myBuffer = inStr.toCharArray();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < inStr.length(); i++) {        
		UnicodeBlock ub = UnicodeBlock.of(myBuffer[i]);
		if(ub == UnicodeBlock.BASIC_LATIN){
		//英文及数字等
		sb.append(myBuffer[i]);
		}else if(ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS){
		//全角半角字符
		int j = (int) myBuffer[i] - 65248;
		sb.append((char)j);
		}else{
		//汉字
		short s = (short) myBuffer[i];
		String hexS = Integer.toHexString(s);
		String unicode = "\\u"+hexS;
		sb.append(unicode.toLowerCase());
		}
		}
		return sb.toString();
		}
}
