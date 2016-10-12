package com.zlb.java.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 网络请求处理类，java实现
 * */

public final class LetvHttpJavaHandler{
	
	/**
	 * GET 请求
	 * */
//	public final String doGet() throws IOException {
//		String baseUrl ;
//		URL url;
//		String response = null ;
//		HttpURLConnection httpConnection = null;
//		InputStream is = null ;
//
//		try {
//			baseUrl = "www.baidu.com";
//			url = new URL(baseUrl);
//			httpConnection = (HttpURLConnection) url.openConnection();
//			
//			httpConnection.setRequestMethod("GET");
//			httpConnection.setReadTimeout(10 * 1000);
//			httpConnection.setConnectTimeout(10 * 1000);
//			httpConnection.connect();
//			int responseCode = httpConnection.getResponseCode();
////			if (responseCode == HttpStatus.SC_OK) {
//			if (responseCode == 200) {
//				is = httpConnection.getInputStream();
//			} else {
//					is = httpConnection.getErrorStream();
//					response = read(is);
//				throw new IOException("responseCode is not 200");
//			}
//			response = read(is);
//			LetvHttpLog.Log(response);
//			
//			if(callback != null){
//				callback.laterRequest(httpConnection);
//			}
//			
//			return response ;
//		} finally {
//			httpConnection.disconnect();
//			if(is != null){try {is.close();} catch (Exception e) {e.printStackTrace();}}
//			url = null ;
//			response = null ;
//			httpConnection = null ;
//			is = null ;
//		}
//	}

	
	/**
	 * POST 请求
	 * */
//	public final String doPost(LetvHttpBaseParameter<?,?,?> params) throws IOException {
//		if(params == null){
//			return null;
//		}
//		URL url;
//		String response = null ;
//		HttpURLConnection httpConnection = null;
//		InputStream is = null ;
//		
//		LetvHttpParameterCallback callback = params.getCallback() ;
//		
//		try {
//			url = new URL(params.getBaseUrl());
//			httpConnection = (HttpURLConnection) url.openConnection();
//			
//			if(callback != null){
//				callback.proRequest(httpConnection);
//			}
//			
//			httpConnection.setRequestMethod("POST");
//			httpConnection.setReadTimeout(LetvHttpConstant.READ_TIMEOUT);
//			httpConnection.setConnectTimeout(LetvHttpConstant.CONNECT_TIMEOUT);
//			httpConnection.setDoOutput(true);
//			httpConnection.connect();
//			String p = params.encodeUrl().toString() ;
//			LetvHttpLog.Log("POST  " + params.getBaseUrl() + "?" + p);
//			httpConnection.getOutputStream().write(p.toString().getBytes());
//
//			int responseCode = httpConnection.getResponseCode();
//
//			if (responseCode == HttpStatus.SC_OK) {
//				is = httpConnection.getInputStream();
//			} else {
//				if(LetvHttpConstant.isDebug){
//					is = httpConnection.getErrorStream();
//					response = read(is);
//					LetvHttpLog.Err(response);
//				}
//				throw new IOException("responseCode is not 200");
//			}
//
//			response = read(is);
//			LetvHttpLog.Log(response);
//			
//			if(callback != null){
//				callback.laterRequest(httpConnection);
//			}
//			
//			return response ;
//		} finally {
//			httpConnection.disconnect();
//			if(is != null){try {is.close();} catch (Exception e) {e.printStackTrace();}}
//			url = null ;
//			response = null ;
//			httpConnection = null ;
//			is = null ;
//		}
//	}
	
	/**
	 * 将回来的IO流转化为字符串
	 * */
	private final String read(InputStream in) throws IOException {
		if(in == null){
			return null ;
		}
		InputStreamReader r = null ;
		String result = null ;
		char[] buf = new char[100] ;
		try{
			StringBuilder sb = new StringBuilder();
			r = new InputStreamReader(in);
			int len = 0 ;
			while ((len = r.read(buf)) != -1) {
				sb.append(buf , 0 , len);
			}
			
			result = sb.toString();
			
			return result ;
		} finally{
			if(r != null){r.close();}
			r = null ;
			result = null ;
			buf = null ;
			in = null ;
		}
	}
}
