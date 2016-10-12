package com.zlb.thread.shengchanzhe;

public class Container {
	private int num;
	public static final int MAX_NUM = 100;
	
	synchronized public void produce(int n) {
		while(num + n > MAX_NUM) {//关键
			System.out.println(Thread.currentThread().getName() + " want to produce " + n + " , but now num is " + num + " , if u produce , the num will out of bounds, so wait.");
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		num += n;
		System.out.println(Thread.currentThread().getName() + " produced " + n + " , and now num = " + num);
		notifyAll();
	}
	
	synchronized public void consume(int n) {
		while(num < n) {
			System.out.println(Thread.currentThread().getName() + " want to consume " + n + " , but now num is " + num + " , the num is not enouph, so wait.");
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		num -= n;
		System.out.println(Thread.currentThread().getName() + " consumed " + n + " , and now num = " + num);
		notifyAll();
	}
	
}
