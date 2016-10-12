package com.java.net.ftp;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * 在JAVA程序中，经常需要和FTP打交道，比如向FTP服务器上传文件、下载文件，本文简单介绍如何利用jakarta commons中的FTPClient（在commons-net包中）实现上传下载文件。所用到的jar包有：  
  commons-net-1.4.1.jar  
  jakarta-oro.jar 
 * @author zhanglibin
 *
 */
public class TestFtpClient {
//	
//	public static void main(String[] args) {
//		TestFtpClient ftpClient = new TestFtpClient();
//		ftpClient.testUpLoadFromString();
//	}
//	
//	/**  
//	 * 在FTP服务器上生成一个文件，并将一个字符串写入到该文件中  
//	 *  
//	 */  
//	public void testUpLoadFromString(){    
//	    try {    
//	        String str = "这是要写入的字符串！";  
//	        InputStream input = new ByteArrayInputStream(str.getBytes("utf-8"));    
//	        boolean flag = FtpUtils.uploadFile("127.0.0.1", 21, "anonymous", "@qq", "test", "test.txt", input);    
//	        System.out.println(flag);    
//	    } catch (UnsupportedEncodingException e) {    
//	        e.printStackTrace();    
//	    }    
//	}  
//	
//	/**  
//	 * 将本地文件上传到FTP服务器上  
//	 *  
//	 */  
//	public void testUpLoadFromDisk(){    
//	    try {    
//	        FileInputStream in=new FileInputStream(new File("D:/test.txt"));    
//	        boolean flag = FtpUtils.uploadFile("127.0.0.1", 21, "administrator", "zyuc2011", "test", "test.txt", in);    
//	        System.out.println(flag);    
//	    } catch (FileNotFoundException e) {    
//	        e.printStackTrace();    
//	    }     
//	}  
//	
//	/**  
//	 * 将FTP服务器上文件下载到本地  
//	 *  
//	 */  
//	public void testDownFile(){  
//	    try {    
//	        boolean flag = FtpUtils.downFile("127.0.0.1", 21, "administrator", "zyuc2011", "test", "test.txt", "D:/");    
//	        System.out.println(flag);    
//	    } catch (Exception e) {    
//	        e.printStackTrace();    
//	    }         
//	}  
//	
}
