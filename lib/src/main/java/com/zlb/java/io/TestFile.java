package com.zlb.java.io;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.zlb.util.PublicUtil;

public class TestFile {
	public static void main(String[] args) {
		String path = "D:/t.txt";
		printLogToFile1(path, "test 1");
		printLogToFile2(path, "test 2");
		
	}
	
	public static boolean printLogToFile2(String path, String log) {
		File f = new File(path);
		FileWriter writer = null;
		try {
			if(f.exists()) {//大于100k删掉重建
				if(f.length() > 100000) {
					f.delete();
				}
			}
			writer = new FileWriter(f, true);
			writer.write(PublicUtil.timeStringAll(System.currentTimeMillis()) + " " + log + "\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(writer != null) {
				try {
					writer.close();
					writer = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public static boolean printLogToFile1(String path, String log) {
		File f = new File(path);
		FileOutputStream out = null;
		// StringBufferInputStream?
		BufferedOutputStream bout = null;
		BufferedWriter bw = null;
		try {
			out = new FileOutputStream(f, true);
			bout = new BufferedOutputStream(out);
			bw = new BufferedWriter(new OutputStreamWriter(bout));
			bw.write(log + " , time = " + PublicUtil.timeStringAll(System.currentTimeMillis()) + "\r\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (bw != null) {
					bw.close();
					bw = null;
				}
				if(bout != null) {
					bout.close();
					bout = null;
				}
				if(out != null) {
					out.close();
					out = null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}

		}
		return true;
	}
}
