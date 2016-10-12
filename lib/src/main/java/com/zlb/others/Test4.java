package com.zlb.others;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Scanner;

public class Test4 {
	
	public static void main(String[] args) throws IOException {
		Test4 t = new Test4();
		System.out.println("�����������ļ���·��\n�������һ���ļ�·�������̴��ڵģ�");
		Scanner in = new Scanner(System.in);
		String s1 = in.next();
		System.out.println("������ڶ����ļ���·��");
		String s2 = in.next();
		t.fun(s1, s2);
		
		
						
	}
	
	private void fun(String s1,String s2) throws IOException {
		File from = new File(s1);
		File to = new File(s2);
		InputStream in = new FileInputStream(from);
		OutputStream out = new FileOutputStream(to);
		
		int n;
		while((n=in.read())!=-1) {
			out.write(n);
		}
		in.close();
		out.close();
		
		
	}
}
