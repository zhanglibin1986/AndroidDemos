package android.zlb.java;

import java.security.MessageDigest;

public class TestMd5 {
	/**
	 * MD5加密
	 * @param paramString
	 * @return
	 */
	public static String md5(String paramString) {
		String returnStr;
		try {
			MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
			localMessageDigest.update(paramString.getBytes());
			returnStr = byteToHexString(localMessageDigest.digest());
			return returnStr;
		} catch (Exception e) {
			return paramString;
		}
	}
	
	/**
	 * 将指定byte数组转换成16进制字符串
	 * @param b
	 * @return
	 */
	public static String byteToHexString(byte[] b) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			hexString.append(hex.toLowerCase());
		}
		return hexString.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(md5("zlb"));
		byte[] bs = new byte[] {1,2,3,10,11,12,16,17,18};
		for(int i = 0; i < bs.length; i ++) {
			System.out.println(Integer.toHexString(bs[i]));
//			System.out.println(Integer.);
		}
		
		
	}
}
