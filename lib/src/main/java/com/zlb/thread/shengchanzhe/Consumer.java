package com.zlb.thread.shengchanzhe;

public class Consumer extends Thread {
	Container mContainer;
	int num;
	public Consumer(Container c, int n) {
		mContainer = c;
		num = n;
	}
	@Override
	public void run() {
		mContainer.consume(num);
	}
}
