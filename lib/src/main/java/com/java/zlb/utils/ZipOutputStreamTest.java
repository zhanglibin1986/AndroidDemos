package com.java.zlb.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/**
 * 压缩文件test
 * 我们经常会使用WinZIP等压缩软件将文件进行压缩以方便传输。在java里面也提供了将文件进行压缩以减少传输时的数据量的类，可以很方便的将文件压缩成ZIP、JAR、GZIP等形式，GZIP主要是在Linux系统下的压缩文件。下面主要讲的就是ZIP形式的压缩文件，而JAR、GZIP形式的压缩文件也是类似的用法。ZIP是一种很常见的压缩形式，在java中要实现ZIP的压缩主要用到的是java.util.zip这个包里面的类。主要有ZipFile、 ZipOutputStream、ZipInputStream和ZipEntry。ZipOutputStream是用来压缩文件 的，ZipInputStream和ZipFile是用来解压缩文件的，在压缩和解压缩的过程中，ZipEntry都会用到。在java的Zip压缩文件 中，每一个子文件都是一个ZipEntry对象。
 * @author zhanglibin
 *
 */
public class ZipOutputStreamTest {

	public static void main(String args[]) throws IOException {
		test1();
		test2();
	}
	
	public static void test1() throws IOException {
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("D:\\testZip.zip"));
		//实例化一个名称为ab.txt的ZipEntry对象
		ZipEntry entry = new ZipEntry("ab.txt");
		//设置注释
		zos.setComment("zip测试for单个文件");
		//把生成的ZipEntry对象加入到压缩文件中，而之后往压缩文件中写入的内容都会放在这个ZipEntry对象里面
		zos.putNextEntry(entry);
		InputStream is = new FileInputStream("D:\\ab.txt");
		int len = 0;
		while ((len = is.read()) != -1)
			zos.write(len);
		is.close();
		zos.close();
	}
	
	public static void test2() throws IOException {
		File inFile = new File("D:\\test");
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("D:\\test.zip"));
		zos.setComment("多文件处理");
		zipFile(inFile, zos, "");
		zos.close();
	}
	
	public static void zipFile(File inFile, ZipOutputStream zos, String dir) throws IOException {
		if (inFile.isDirectory()) {
			File[] files = inFile.listFiles();
			for (File file:files)
				zipFile(file, zos, dir + "\\" + inFile.getName());
		} else {
			String entryName = null;
			if (!"".equals(dir))
				entryName = dir + "\\" + inFile.getName();
			else
				entryName = inFile.getName();
			ZipEntry entry = new ZipEntry(entryName);
			zos.putNextEntry(entry);
			InputStream is = new FileInputStream(inFile);
			int len = 0;
			while ((len = is.read()) != -1)
				zos.write(len);
			is.close();
		}

	}
	
}
