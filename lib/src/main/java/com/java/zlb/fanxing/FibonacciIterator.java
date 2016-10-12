package com.java.zlb.fanxing;

import java.util.Iterator;

/**
 * 如想编写一个实现了Iterable的Fibonacci生成器，我们的一个选择是重写写这个类，令其实现Iterable接口。不过，你并不是总能拥有源代码的控制权，并且，除非必须这么做，否则，我们
 * 也不愿意重写一个类。我们还有另外一种选择：创建一个适配器，来实现所需的接口！有多种方法可以实现适配器，例如下面，我们可以通过继承来创建适配器类。
 * @author zhanglibin
 *
 */
public class FibonacciIterator extends Fibonacci implements Iterable<Integer>{
	
	public static void main(String args) {
		FibonacciIterator fib = new FibonacciIterator(5);
		for(Integer i : fib) {
			System.out.println(i);
		}
	}
	
	int c = 0;
	public FibonacciIterator(int num) {
		super(num);
		c = num;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		
		return new Iterator<Integer>() {
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return c>0;
			}

			@Override
			public Integer next() {
				// TODO Auto-generated method stub
				c--;
				return FibonacciIterator.this.next();
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				throw new UnsupportedOperationException();
			}
		};
	}
}
