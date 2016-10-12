package com.zlb.java.net;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/*
 * HTML格式编码的实用工具类。
 * 
 * URLEncoder类包含将字符串转换为application/x-www-form-urlencoded MIME 格式的静态方法。
网页中的表单使用POST方法提交时，数据内容的类型是 application/x-www-form-urlencoded，这种类型会：
1.字符"a"-"z"，"A"-"Z"，"0"-"9"，"."，"-"，"*"，和"_" 都不会被编码;
2.将空格转换为加号 (+) ;
3.将非文本内容转换成"%xy"的形式,xy是两位16进制的数值;
4.在每个 name=value 对之间放置 & 符号。
*/
public class TestURLEncoder {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String uri1 = "http://119.188.122.49/m3u8/jiangsu/desc.m3u8?tag=live&video_type=m3u8&stream_id=jiangsu&useloc=0&mslice=3&path=119.188.122.43,60.217.237.239&geo=CN-1-0-2&cips=10.58.106.73&tmn=1387782423&pnl=751,751,246&ext=m3u8&sign=live_phone&platid=10&playid=1&termid=2&pay=0&tm=1387786008&splatid=1003&ostype=andriod&hwtype=un&key=2e2a125d56f6793d4e6589d44e7336b6";
		String uri2 = URLEncoder.encode(uri1, "utf-8");
		String uri3 = URLDecoder.decode(uri2, "utf-8");
		System.out.println(uri1);
		System.out.println(uri2);
		System.out.println(uri3);
		//对应的是URLDecoder解码
	}
}
