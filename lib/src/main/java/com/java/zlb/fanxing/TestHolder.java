package com.java.zlb.fanxing;

public class TestHolder {
	public static void main(String[] args) {
		Person p = new Person("zhanglibin", 27);
		Holder<Person> holder = new Holder<Person>(p);
		System.out.println(holder);
		System.out.println(holder.get());
	}
}

class Holder<T> {
	private T one;
	public Holder(T t) {
		one = t;
	}
	
	public void set(T t) {
		one = t;
	}
	
	public T get() {
		return one;
	}
	@Override
	public String toString() {
		return "holder (" + one.toString() + ")";
	}
}