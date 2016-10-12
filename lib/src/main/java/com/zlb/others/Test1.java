package com.zlb.others;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Test1 {
	public static void main(String[] args) throws IOException {
		String file1 = "/home/zhanglibin/icsNebula/IcsNebula/packages/apps/EngineerMode/src/wnc/w806/engineermode/EngineerMode_main.java";
		Test1 t = new Test1();
		String s = t.fun2(new File(file1));
		System.out.println(s);
	}
	
	private byte[] fun1(File f) {
		InputStream in = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			in = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int n;
		try {
			while((n=in.read()) !=-1) {
				out.write(n);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] b = out.toByteArray();
		return b;
	}
	private String fun2(File f) throws IOException {
		StringBuilder buffer = new StringBuilder();
		Reader in = new FileReader(f );
		
		int n;
		while((n=in.read())!=-1) {
			buffer.append((char)n);
		}
		String s = buffer.toString();
		return s;
	}
	
	private List<String> fun3(File f) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(f));
		List<String> list = new ArrayList<String>();
		//StringBuilder buffer = new StringBuilder();
		String s;
		while((s = in.readLine())!=null) {
			list.add(s);
		}
		return list;
	}
}
