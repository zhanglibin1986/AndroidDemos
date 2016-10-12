package com.zlb.java.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class TestSemaphore {
	public static void main(String[] args) {
		TestSemaphore test = new TestSemaphore();
		ResPool res = test.new ResPool(5);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.execute(test.new UserThread(res, 2, "thread 1"));
		executor.execute(test.new UserThread(res, 2, "thread 2"));
		executor.execute(test.new UserThread(res, 2, "thread 3"));
		executor.shutdown();
	}
	
	class UserThread implements Runnable {
		int count;
		ResPool resPool;
		String name;
		public UserThread(ResPool resPool, int count, String name) {
			this.count = count;
			this.resPool = resPool;
			this.name = name;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				System.out.println(name + " 请求使用资源池中的" + count + "个资源");
				resPool.semaphore.acquire(count);
				System.out.println(name + " 正在使用资源池中的" + count + "个资源");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				//最后一定要释放掉
				System.out.println(name + "使用完毕，释放掉" + count + "个资源");
				resPool.semaphore.release(count);
			}
		}
		
	}
	
	/**
	 * 这个资源池最多提供count个资源使用
	 * @author zhanglibin
	 */
	class ResPool {
		public Semaphore semaphore;
		public ResPool(int count) {
			semaphore = new Semaphore(count);
		}
	}
}
