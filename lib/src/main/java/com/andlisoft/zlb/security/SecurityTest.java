package com.andlisoft.zlb.security;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityTest
{
    // MD5是16位,SHA是20位（这是两种报文摘要的算法） 定义两种加密算法的类
    private static final String MD5_ALGORITHM = "MD5";
    
    private static final String SHA_1_ALGORITHM = "SHA-1";
    
    /** 十六进制 */
    private static final int HEX_NUM = 16;
    
    /** 十六进制码 */
    private static final String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
        "e", "f"};

    /**对语句进行单向加密
        * <功能详细描述>
        * @param message   需要加密的信息
        * @return
        * @throws NoSuchAlgorithmException
        * @throws IOException [参数说明]
        * 
        * @return byte[] [返回类型说明]
        * @exception throws [违例类型] [违例说明]
        * @see [类、类#方法、类#成员]
        */
       public byte[] encrypt(String message)
           throws NoSuchAlgorithmException, IOException
       {
           
           // 加密
           MessageDigest encrypt = MessageDigest.getInstance(SecurityTest.MD5_ALGORITHM);
           
           // 添加需要加密的信息
           encrypt.update(message.getBytes());
           
           // 对信息信息加密
           byte[] encryptMD5 = encrypt.digest();
           
           //获得加密算法
           System.out.println(encrypt.getAlgorithm());
           
           //得到加密算法的长度
           System.out.println(encrypt.getDigestLength());
           
           return encryptMD5;
          
       }

       /**
        * 将数据转化为16进制进行保存，因为有些byte是不能打印的字符 字节数组的转化
        * 
        * @param b 字节数组
        * @return [参数说明]
        */
       public static String byteArrayToHexString(byte[] b)
       {
           StringBuffer result = new StringBuffer(128);
           for (int i = 0; i < b.length; i++)
           {
               result.append(byteToHexString(b[i]));
           }
           return result.toString();
       }
       
       /**
        * 将数据转化为16进制进行保存，因为有些byte是不能打印的字符 单字节转化
        * 
        * @param b
        * @return [参数说明]
        */
       public static String byteToHexString(byte b)
       {
           int n = b;
           if (0 > n)
           {
               n = 256 + n;
           }
           int d1 = n / HEX_NUM;
           int d2 = n % HEX_NUM;
           return HEX_DIGITS[d1] + HEX_DIGITS[d2];
       }
       public static void main(String[] args)
    	        throws NoSuchAlgorithmException, IOException
    	    {
    	        SecurityTest t = new SecurityTest();
    	        //得到加密数组
    	        byte[] data = t.encrypt("我的测试信息!");
    	        //转化为字符串,采用base编码方式
    	        
    	        String hexResult = SecurityTest.byteArrayToHexString(data);
    	        
    	        System.out.println("hexResult"+hexResult);
    	        
    	    }
}

