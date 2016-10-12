package com.zlb.demo;
public class SingletonTest {
	
	private static volatile SingletonTest mSingletonTest = null;
	
	private SingletonTest() {

	}
	
	public static SingletonTest getInstance() {
		if(mSingletonTest == null) {
			synchronized (SingletonTest.class) {
				mSingletonTest = new SingletonTest();
			}
		}
		return mSingletonTest;
	}
}
