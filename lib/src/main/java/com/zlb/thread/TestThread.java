package com.zlb.thread;

/**
 * 实现多线程，有两种手段，一种是继续Thread类，另外一种是实现Runable接口。提倡使用runable。
 * 实现Runnable接口比继承Thread类所具有的优势：

1）：适合多个相同的程序代码的线程去处理同一个资源

2）：可以避免java中的单继承的限制

3）：增加程序的健壮性，代码可以被多个线程共享，代码和数据独立。
 * @author zhanglibin
 *
 */
public class TestThread {
	public static void main(String[] args) {
		MyTask task1 = new MyTask();
		Thread t1 = new Thread(task1, "thread1");
		Thread t2 = new Thread(task1, "thread2");
		Thread t3 = new Thread(task1, "thread3");
		
		Thread t4 = new Thread(new Task1(), "thread4");
		t4.setDaemon(true);
		t4.start();
		t1.start();
		t2.start();
		t3.start();
	}
}

class MyTask implements Runnable {
	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			System.out.println(Thread.currentThread().getName()+"'s task is running..."+i);
		}
	}
}

class Task1 implements Runnable {
	public static long a = 0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println("Task1 time " + a++);
		}
	}
	
}
