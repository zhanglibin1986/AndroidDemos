package com.zlb.others;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class TestMd5 {
	public static void main(String[] args) {
		
	}
	
	public byte[] encrypt(String str) {
		MessageDigest md5 = null;
		byte[] encryptMD5 = null;
		
		try {
			//获得md5消息摘要
			md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes());//添加需要加密的信息
			encryptMD5 = md5.digest();//加密
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(md5.getAlgorithm());//打印加密算法的名称
		System.out.println(md5.getDigestLength());//打印已经加密的信息的字节数组长度
		
		
		return encryptMD5;
	}
	
}

class SecurityTest
{
    // MD5是16位,SHA是20位（这是两种报文摘要的算法） 定义两种加密算法的类
    private static final String MD5_ALGORITHM = "MD5";
    
    private static final String SHA_1_ALGORITHM = "SHA-1";
    
    /** 十六进制 */
    private static final int HEX_NUM = 16;
    
    /** 十六进制码 */
    private static final String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
        "e", "f"};
    /**
     * 转化方式二
     * 将数据转化为16进制进行保存，因为有些byte是不能打印的字符 单字节转化
     * 
     * @param b
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
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
    
    /**
     * 转化方式二
     * 将数据转化为16进制进行保存，因为有些byte是不能打印的字符 字节数组的转化
     * 
     * @param b 字节数组
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
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
}

