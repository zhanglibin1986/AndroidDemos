package com.java.zlb.fanxing;

import java.util.Arrays;

public class Fibonacci implements Generator<Integer>{
	private int count = 0;
	public Fibonacci(int num) {
		count = num;
	}
	
	private int fib(int n) {
		if(n < 2) {
			return 1;
		}else {
			return fib(n-2)+fib(n-1);
		}
	}
	
	public String printResult() {
		int[] result = new int[count];
		for(int i=1;i<=count;i++) {
			result[i-1] = fib(i);
		}
		return Arrays.toString(result);
	}
	
	public static void main(String[] args) {
		Fibonacci fib = new Fibonacci(10);
		System.out.println(fib.printResult());
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		return fib(count++);
	}
}
