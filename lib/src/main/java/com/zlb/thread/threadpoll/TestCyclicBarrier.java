package com.zlb.thread.threadpoll;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCyclicBarrier {
	public static void main(String[] args) {
		TestCyclicBarrier tcb = new TestCyclicBarrier();
		tcb.runTask();
	}
	
	public void runTask() {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
			public void run() {
				System.out.println("======The cyclicBarrier is over");
			}
		});
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.execute(new MyRunnable(cyclicBarrier, "task1"));
		executor.execute(new MyRunnable(cyclicBarrier, "task2"));
		executor.execute(new MyRunnable(cyclicBarrier, "task3"));
		executor.execute(new MyRunnable(cyclicBarrier, "task4"));
		executor.shutdown();
	}
	
	class MyRunnable implements Runnable {
		CyclicBarrier cyclicBarrier;
		String name;
		public MyRunnable(CyclicBarrier c, String name) {
			cyclicBarrier = c;
			this.name = name;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(name + " I have Run over");
			try {
				cyclicBarrier.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}



