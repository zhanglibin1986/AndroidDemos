package com.zlb.testIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
/*use the InputStream and OutputStream to read the source from f1 to f2 , first of all you 
 * should first to make a f1 file on the corresponding path*/
public class TestInputStream {
	public static void main(String[] args) {
		InputStream in;
		OutputStream out;
//		File f1 = new File("/home/zhanglibin/c.txt");
//		File f2 = new File("/home/zhanglibin/e.txt");
		File f1 = new File("D:/a.txt");
		File f2 = new File("D:/e.txt");
		try {
			in = new FileInputStream(f1);
			out = new FileOutputStream(f2);
			// int c;
			// while((c=in.read()) != -1) {
			// out.write(c);
			// }
			byte[] b = new byte[1024];
			int c;
			while ((c = in.read(b)) != -1) {
				out.write(b, 0, c);
			}
			in.close();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
