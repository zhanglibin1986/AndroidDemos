package com.zlb.java.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

public class URLio {
	public static void main(String[] args) {
		try {
			URL url = new URL("http://developer.android.com/guide/index.html");
			InputStream in = url.openStream();
			InputStreamReader inputReader = new InputStreamReader(in);
			BufferedReader bufferedReader = new BufferedReader(inputReader);
			
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D://developer.html"));
			PrintWriter printWriter = new PrintWriter(bufferedWriter);
			
			String s; 
			while((s = bufferedReader.readLine()) != null) {
				printWriter.println(s);
			}
			
			printWriter.close();
//			bufferedWriter.flush();
			bufferedWriter.close();
			bufferedReader.close();
			inputReader.close();
			in.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
