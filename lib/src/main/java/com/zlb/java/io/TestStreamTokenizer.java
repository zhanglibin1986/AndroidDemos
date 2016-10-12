package com.zlb.java.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

public class TestStreamTokenizer {
	public static void main(String[] args) {
		
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
