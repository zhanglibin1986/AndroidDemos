package com.zlb.java.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

/**
 * 字符流的缓冲区，缓冲区的出现提高了对流的操作效率。原理就是对数组进行封装。
 * @author zhanglibin
 *
 */
public class TestBufferedReader {
	public static void main(String[] args) {
		new TestBufferedReader().writeDataToFile(new File("D:/bu.txt"), "测试数据");
	}
	
	public void writeDataToFile(File file, String data) {
		BufferedReader stringReader = null;
		FileWriter fileWriter = null;
		try {
			stringReader = new BufferedReader(new StringReader(data));
			fileWriter = new FileWriter(file, true);
			String line = null;
			while ((line = stringReader.readLine()) != null) {
				fileWriter.write(line);
				fileWriter.write("\r\n");
			}
			stringReader.close();
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stringReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
