package com.zlb.java.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
/**
 * 是一个字节打印流，System.out对应的类型就是PrintStream。
它的构造函数函数可以接收三种数据类型的值。
1，字符串路径。
2，File对象。
3，OutputStream。
 * @author zhanglibin
 *
 */
public class TestPrintStream {
	public static void main(String[] args) {
		PrintStream printSteam = null;
		try {
			printSteam = new PrintStream(new BufferedOutputStream(new FileOutputStream("D:/a.txt")));
			printSteam.print("System.out对应的类型就是PrintStream。");
		} catch (IOException e) {
			// TODO: handle exception
		} finally {
				try {if(printSteam != null) {
					printSteam.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}


/**
PrintWriter：
是一个字符打印流。构造函数可以接收四种类型的值。
1，字符串路径。
2，File对象。
对于1，2类型的数据，还可以指定编码表。也就是字符集。

3，OutputStream
4，Writer
对于3，4类型的数据，可以指定自动刷新。*/