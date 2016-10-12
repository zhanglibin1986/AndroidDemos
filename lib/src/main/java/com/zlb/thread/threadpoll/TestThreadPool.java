package com.zlb.thread.threadpoll;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 可参考：http://blog.csdn.net/wangwenhui11/article/details/6760474
 * @author zhanglibin
 *
 */
public class TestThreadPool {
	public static void main(String[] args) {
		MyRunnable r1 = new MyRunnable("r1");
		MyRunnable r2 = new MyRunnable("r2");
		MyRunnable r3 = new MyRunnable("r3");
		MyRunnable r4 = new MyRunnable("r4");
		MyRunnable r5 = new MyRunnable("r5");
		MyRunnable r6 = new MyRunnable("r6");
		
		
//		ExecutorService p1 = Executors.newFixedThreadPool(3);//第一种，固定尺寸线程池
//		p1.execute(r1);
//		p1.execute(r2);
//		p1.execute(r3);
//		p1.execute(r4);
//		p1.execute(r5);
//		p1.shutdown();//还有一种关闭线程池的方法shutdownNow();不等任务执行完毕马上关闭。
////		p1.execute(r6);//将会抛出异常
//		
//		MyRunnable r7 = new MyRunnable("r7");
//		MyRunnable r8 = new MyRunnable("r8");
//		MyRunnable r9 = new MyRunnable("r9");
//		ExecutorService p2 = Executors.newSingleThreadExecutor();//相当于固定尺寸等于1的线程池，可以保证顺序
//		p2.execute(r7);
//		p2.execute(r8);
//		p2.execute(r9);
//		p2.shutdown();
		

//		MyRunnable r10 = new MyRunnable("r10");
//		MyRunnable r11 = new MyRunnable("r11");
//		MyRunnable r12 = new MyRunnable("r12");
//		MyRunnable r13 = new MyRunnable("r13");
//		MyRunnable r14 = new MyRunnable("r14");
//		MyRunnable r15 = new MyRunnable("r15");
//		ExecutorService p3 = Executors.newCachedThreadPool();//可变尺寸线程池不保证顺序
//		p3.execute(r10);
//		p3.execute(r11);
//		p3.execute(r12);
//		p3.execute(r13);
//		p3.execute(r14);
//		p3.execute(r15);
//		p3.shutdown();

		MyRunnable r16 = new MyRunnable("r16");
		MyRunnable r17 = new MyRunnable("r17");
		MyRunnable r18 = new MyRunnable("r18");
		MyRunnable r19 = new MyRunnable("r19");
		ScheduledExecutorService s1 = Executors.newSingleThreadScheduledExecutor();
		s1.schedule(r18, 3, TimeUnit.SECONDS);
		s1.schedule(r16, 2, TimeUnit.SECONDS);
		s1.schedule(r17, 1, TimeUnit.SECONDS);
		s1.schedule(r19, 1, TimeUnit.SECONDS);
		
		ScheduledExecutorService s2 = Executors.newScheduledThreadPool(2);
		BlockingQueue workQueue = new ArrayBlockingQueue(3);
		/**
		 * corePoolSize： 线程池维护线程的最少数量maximumPoolSize：线程池维护线程的最大数量
		 * keepAliveTime： 线程池维护线程所允许的空闲时间
		 * unit： 线程池维护线程所允许的空闲时间的单位workQueue： 线程池所使用的缓冲队列
		 * handler： 线程池对拒绝任务的处理策略
		 */
		ThreadPoolExecutor poo = new ThreadPoolExecutor(3, 5, 4, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3), new ThreadPoolExecutor.DiscardOldestPolicy());
		
	}
}

class MyRunnable implements Runnable {
	String name;

	public MyRunnable(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(name + " is running");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(name + " is end");

		System.out.println();
	}

}