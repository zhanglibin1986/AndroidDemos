package com.zlb.thread.shengchanzhe;

public class Producer extends Thread{
	Container mContainer;
	int num = 0;
	public Producer(Container c,int n) {
		mContainer = c;
		num = n;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		mContainer.produce(num);
	}
}
