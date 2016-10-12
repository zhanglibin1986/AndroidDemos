package com.java.zlb.fanxing;

/**
 * Generator的匿名内部类的使用方法示例！注意其无参构造器是私有的哦！
 * @author zhanglibin
 *
 */
public class Customer {
	public static int count = 0;
	public final int id = count++;
	
	private Customer() {
		
	}
	
	public static Generator<Customer> create() {
		return new Generator<Customer>() {
			public Customer next() {
				return new Customer();
			}
		};
	}
	@Override
	public String toString() {
		return "Customer id = " + id;
	}
	
	public static void main(String[] args) {
		Generator<Customer> customerGen = Customer.create();
		for(int i=0;i<5;i++) {
			System.out.println(customerGen.next());
		}
	}
}
