package com.zlb.java.io;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
/**
 * PipedInputStream与PipedOutputStream类用于在应用程序中的创建管道通信。管道通信即发送进程以字符流形式将大量数据送入管道，接收进程可从管道接收数据，二者利用管道进行通信。
 * 使用管道流类，可以实现各个程序模块之间的松耦合通信。这样可以将多个模块的输入流和输出流相连，拼装成满足需要的应用程序，而不需要修改模块内部代码。
 * @author zhanglibin
 *
 */
/**
 * 使用PipedInputStream与PipedOutputStream类编写两个线程间进行通信的程序。
 * @author zhanglibin
 *
 */
public class PipedReader {
	public static void main(String[] args){	   
		Sender sender = new Sender();
		Receiver receiver = new Receiver();
		PipedOutputStream out = sender.getPipedOutputStream();
		PipedInputStream in = receiver.getPipedInputStream();
		try {
			out.connect(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sender.start();
		receiver.start();
	   }

}



class Sender extends Thread{
	PipedOutputStream out = new PipedOutputStream();
	PipedOutputStream getPipedOutputStream(){
		return out;
	}
	public void run(){
		try {
			out.write(new String("hello baby!").getBytes());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Receiver extends Thread{
	PipedInputStream in = new PipedInputStream ();
	PipedInputStream  getPipedInputStream (){
		return in;
	}
	public void run(){
		byte[] b = new byte[1024];
		try {
			int len = in.read(b);
			System.out.println("The following message comes from sender:/n"+
					new String(b,0,len));
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

