package com.zlb.java.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.Arrays;

/**
 * StringReader并不常用，因为通常情况下使用String更简单一些。但是在一些需要Reader作为参数的情况下，
 * 就需要将String读入到StringReader中来使用了。
 * 下面的例子代码中，我们创建了一个StringReader实例，然后将此示例作为参数给StreamTokenizer类，然后数给定字符串中一个有多少个单词。
 * 
 * @author zhanglibin
 * 
 */
public class TestStringReader {
	
	public static void main(String[] args) {
		TestStringReader t = new TestStringReader();
		t.testRead2();
		t.testRead1();
		t.countWordsInAString();
	}
	/**
	 * 读取的字符，如果已到达流的末尾，则返回 -1
	 */
	public void testRead1() {
		String s = "否则";
		StringReader reader = new StringReader(s);
		char[] buffer = new char[1024];
		try {
			while(reader.read(buffer) != -1) {
				System.out.println("length = " + buffer.length + " , " + Arrays.toString(buffer));
			};
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		reader.rea
	}
	
	public void testRead2() {
		String s = "否则";
		StringReader reader = new StringReader(s);
		try {
			int temp ;
			while((temp = reader.read()) != -1) {
				System.out.println((char) temp);
			};
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * StringReader示例代码
	 * 字符串的分割最常用的是java.util.StringTokenizer
	 */
	public void countWordsInAString() {

		StreamTokenizer streamTokenizer = null;

		String stringToBeParsed = "The quick brown fox jumped over 100 35 thelazy dog\n zhang libin";
		StringReader reader = new StringReader(stringToBeParsed);

		int wordCount = 0;

		try {

			streamTokenizer = new StreamTokenizer(reader);

			while (streamTokenizer.nextToken() != StreamTokenizer.TT_EOF) {
				System.out.println(streamTokenizer.sval);
				System.out.println(streamTokenizer.nval);
				if (streamTokenizer.ttype == StreamTokenizer.TT_WORD)
					wordCount++;
			}

			System.out.println("Number of words in file: " + wordCount + " , lines : " + streamTokenizer.lineno());

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
