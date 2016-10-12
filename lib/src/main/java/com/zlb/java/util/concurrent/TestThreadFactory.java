package com.zlb.java.util.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * public interface ThreadFactory { Thread newThread(Runnable r); }
 * 
 * 我们可以采用自定义的ThreadFactory工厂，增加对线程创建与销毁等更多的控制， 一个简单的例子，跟踪线程的创建与销毁：
 * 
 * @author zhanglibin
 * 
 */
public class TestThreadFactory {

	private static final int CORE_POOL_SIZE = 5;
	private static final int MAXIMUM_POOL_SIZE = 128;
	private static final int KEEP_ALIVE = 1;

	/**
	 * android 的AsyncTask中是这样使用的
	 */
	private static final ThreadFactory sThreadFactory = new ThreadFactory() {
		private final AtomicInteger mCount = new AtomicInteger(1);

		public Thread newThread(Runnable r) {
			return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
		}
	};

	private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<Runnable>(
			10);

	public static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
			CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS,
			sPoolWorkQueue, sThreadFactory);

	public static void main(String[] args) {

		ExecutorService ctp = Executors
				.newCachedThreadPool(new ThreadFactory() {
					private AtomicInteger count = new AtomicInteger();

					public Thread newThread(Runnable r) {
						int c = count.incrementAndGet();
						System.out.println("create no " + c + " Threads");
						return new WorkThread(r, count);
					}
				});
		ctp.execute(new MyThread());
		ctp.execute(new MyThread());
		ctp.execute(new MyThread());
		ctp.execute(new MyThread());
		ctp.execute(new MyThread());
		ctp.execute(new MyThread());

		ctp.shutdown();
		try {
			ctp.awaitTermination(1200, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		System.out.println("complete a task!!!");
	}
}

class MyThread implements Runnable {

	public void run() {
		System.out.println("complete a task!!!");
	}
}

class WorkThread extends Thread {

	private Runnable target;
	private AtomicInteger counter;

	public WorkThread(Runnable target, AtomicInteger counter) {
		this.target = target;
		this.counter = counter;
	}

	@Override
	public void run() {
		try {
			target.run();
		} finally {
			int c = counter.getAndDecrement();
			System.out.println("terminate no " + c + " Threads");// 通过这个线程可以起到记录线程的目的。
		}
	}
}
