package com.zlb.others;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class GetWebContent {
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://news.qq.com/newsgn/rss_newsgn.xml");
		final StringBuffer sb = new StringBuffer();
		String temp = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"gb2312"));
		BufferedInputStream bis = new BufferedInputStream(url.openStream());
		
		int t = 0;
		while((t=bis.read()) != -1) {
//			Buffered
		}
		
//		bis.re
		while((temp = br.readLine()) != null) {
			sb.append(temp).append("\n");
		}
		br.close();
		System.out.println(sb.toString());
	}
}
