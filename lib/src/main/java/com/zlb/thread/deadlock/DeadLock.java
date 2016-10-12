package com.zlb.thread.deadlock;

public class DeadLock {
	public static void main(String[] args) {
		Object o1 = new Object();
		Object o2 = new Object();

		T t1 = new T(o1, o2);
		t1.setName("t1");
		T t2 = new T(o2, o1);
		t2.setName("t2");
		t1.start();
		t2.start();
	}
}

class T extends Thread {
	Object o1, o2;

	public T(Object o1, Object o2) {
		this.o1 = o1;
		this.o2 = o2;
	}

	public void run() {
		synchronized (o1) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (o2) {
				System.out.println("end");
			}
		}
	}
}
