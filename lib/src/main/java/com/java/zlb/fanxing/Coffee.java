package com.java.zlb.fanxing;

public class Coffee {
	//static 的属性只在类被加载的时候调用一次。一般类只会被加载一次。
	private static long counter = 0;
	private final long id = counter++;//只在new的时候被调用。
	public String toString() {
		return getClass().getSimpleName() + " " + id;
	}
}

class Latte extends Coffee {
}

class Mocha extends Coffee {
}

class Cappuccino extends Coffee {
}

class Americano extends Coffee {
}

class Breve extends Coffee {
}
