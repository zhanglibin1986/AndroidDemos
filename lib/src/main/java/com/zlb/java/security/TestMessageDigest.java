package com.zlb.java.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 Java 加密技术：消息摘要。
一个消息摘要就是一个数据块的数字指纹。即对一个任意长度的一个数据块进行计算，产生一个唯一指印（对于SHA1是产生一个20字节的二进制数组）。
消息摘要有两个基本属性：
两个不同的报文难以生成相同的摘要 
难以对指定的摘要生成一个报文，而由该报文反推算出该指定的摘要
代表：美国国家标准技术研究所的SHA1和麻省理工学院Ronald Rivest提出的MD5

MessageDigest 类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。   
MessageDigest 对象开始被初始化。该对象通过使用 update（）方法处理数据。任何时候都可以调用 reset（）方法重置摘要。一旦所有需要更新的数据都已经被更新了，应该调用digest() 方法之一完成哈希计算。   
对于给定数量的更新数据，digest 方法只能被调用一次。在调用 digest 之后，MessageDigest 对象被重新设置成其初始状态。

 */
public class TestMessageDigest {
	public static void main(String[] args) {
		TestMessageDigest digest = new TestMessageDigest();
	}
	
	private String md5Helper(String data) {
		byte[] encryptData = null;
		try {
			MessageDigest md = MessageDigest.getInstance("md5");//使用指定加密算法初始化MessageDigest
			md.reset();//初始化
			md.update(data.getBytes("utf-8"));//放入要加密的内容
			encryptData = md.digest();//加密
			//到这里其实加密已经完成了，不过一般最后都会把已加密的byte字节转化成String字符串，这样容易辨认。
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return byte2hex(encryptData);
	}
	/**
	 * 把已加密的byte字节转化成String字符串
	 * @param byteArray
	 * @return
	 */
	private String byte2hex(byte[] byteArray) {
		if(byteArray == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				sb.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			} else {
				sb.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}
		return sb.toString();
	}
	
	//二行制转字符串
	
}
/*

张立宾	2013-08-21 三	09:42:36	20:38:08	技术产品部	袁斌
张立宾	2013-08-22 四			技术产品部	袁斌
张立宾	2013-08-23 五	10:05:20	18:25:26	技术产品部	袁斌
张立宾	2013-08-24 六			技术产品部	袁斌
张立宾	2013-08-25 日			技术产品部	袁斌
张立宾	2013-08-26 一	09:57:02	20:28:14	技术产品部	袁斌
张立宾	2013-08-27 二	10:16:56	20:49:12	技术产品部	袁斌
张立宾	2013-08-28 三	09:59:38	21:49:14	技术产品部	袁斌
张立宾	2013-08-29 四	10:17:20	20:51:00	技术产品部	袁斌
张立宾	2013-08-30 五	10:02:06	18:38:20	技术产品部	袁斌
张立宾	2013-08-31 六			技术产品部	袁斌
张立宾	2013-09-01 日			技术产品部	袁斌
张立宾	2013-09-02 一	10:12:46	20:33:20	技术产品部	袁斌
张立宾	2013-09-03 二	10:11:46	20:48:08	技术产品部	袁斌
张立宾	2013-09-04 三	10:20:44	16:31:52	技术产品部	袁斌
张立宾	2013-09-05 四	10:28:28	20:35:58	技术产品部	袁斌
张立宾	2013-09-06 五			技术产品部	袁斌
张立宾	2013-09-07 六			技术产品部	袁斌
张立宾	2013-09-08 日			技术产品部	袁斌
张立宾	2013-09-09 一			技术产品部	袁斌
张立宾	2013-09-10 二	13:29:20	22:25:44	技术产品部	袁斌
张立宾	2013-09-11 三	10:15:32	23:44:06	技术产品部	袁斌
张立宾	2013-09-12 四	00:55:32	15:06:12	技术产品部	袁斌
张立宾	2013-09-13 五	09:08:44	09:12:34	技术产品部	袁斌
张立宾	2013-09-14 六			技术产品部	袁斌						jiaban
张立宾	2013-09-15 日			技术产品部	袁斌
张立宾	2013-09-16 一	09:24:22		技术产品部	袁斌
张立宾	2013-09-17 二	09:40:10	10:45:56	技术产品部	袁斌
张立宾	2013-09-18 三	08:52:20	18:12:36	技术产品部	袁斌
张立宾	2013-09-19 四			技术产品部	袁斌
张立宾	2013-09-20 五			技术产品部	袁斌

*/
