package com.zlb.thread.shengchanzhe;

public class Main {
	public static void main(String[] args) {
		Container container = new Container();
		Producer p1 = new Producer(container, 20);
		Producer p2 = new Producer(container, 50);
		Producer p3 = new Producer(container, 70);
		
		Consumer c1 = new Consumer(container, 30);
		Consumer c2 = new Consumer(container, 50);
		Consumer c3 = new Consumer(container, 80);
		
		p1.setName("p1");
		p2.setName("p2");
		p3.setName("p3");
		
		c1.setName("c1");
		c2.setName("c2");
		c3.setName("c3");
		
		c1.start();
		p1.start();
		c2.start();
		p2.start();
		p3.start();
		c3.start();
	}
}
