package com.zlb.testIO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestDataOutputStream {
	public static void main(String[] args) {
		byte b = 1;
		short s = 2;
		int i = 3;
		long l = 4;
		float f = 1.1F;
		double d = 2.2;
		boolean bool = false;
		char c = 'A';
		String str = "Hello";

		DataOutputStream out;
		try {
			out = new DataOutputStream(new FileOutputStream("d:/a.txt"));
			out.writeByte(b);
			out.writeShort(s);
			out.writeInt(i);
			out.writeLong(l);
			out.writeFloat(f);
			out.writeDouble(d);
			out.writeBoolean(bool);
			out.writeChar(c);
			out.writeUTF(str);
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataInputStream in;
		try {
			in = new DataInputStream(new FileInputStream("d:/a.txt"));
			System.out.println(in.readByte());
			System.out.println(in.readShort());
			System.out.println(in.readInt());
			System.out.println(in.readLong());
			System.out.println(in.readFloat());
			System.out.println(in.readDouble());
			System.out.println(in.readBoolean());
			System.out.println(in.readChar());
			System.out.println(in.readUTF());
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
