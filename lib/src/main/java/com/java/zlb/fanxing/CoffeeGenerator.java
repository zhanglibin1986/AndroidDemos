package com.java.zlb.fanxing;

import java.util.Iterator;
import java.util.Random;

import javax.management.RuntimeErrorException;

/**
 * 它能够随机生成不同类型的Coffeed对象
 * @author zhanglibin
 *
 */
public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee>{//这里不能再用<T>会报错的。因为这里是实现。
	Random random = new Random(47);
	private int size;
	public CoffeeGenerator() {
		
	}
	
	public CoffeeGenerator(int size) {
		this.size = size;
	}
	private Class[] types = {
			Latte.class, Mocha.class, Cappuccino.class, Americano.class, Breve.class
	};
	@Override
	public Coffee next() {//这个返回值也必须是确定的类型。因为它是实现。
		// TODO Auto-generated method stub
		try {
			return (Coffee) (types[random.nextInt(types.length)].newInstance());
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);//如果没有这句，会提示没有return语句的错误，为什么加上之后就没有了？
		}
	}
	
	public static void main(String[] args) {
		CoffeeGenerator generator = new CoffeeGenerator();
		//参数指定了迭代器返回的生成的数据的数量
		CoffeeGenerator generator2 = new CoffeeGenerator(5);
		for(int i=0; i<10; i++) {
			System.out.println(generator.next());
		}
		
		//===========下面的代码是实现了Iterable接口必须要有的代码
		//要想使用for-each循环，被循环的对象必须实现了Iterable接口
		System.out.println("=================================");
		for(Coffee c : generator2) {
			System.out.println(c);
		}
	}
	
	
	//这个是实现Iterable接口后必须要实现的方法，你必须返回一个Iterator，其一定实现了iterator方法，返回一个Iterator迭代器，自动调用这个迭代器的next方法。
	@Override
	public Iterator<Coffee> iterator() {
		// TODO Auto-generated method stub
		return new CoffeeIterator();//
	}
	
	//一般不使用泛型的Iterator实现类zhishi没有<Coffee>而已。next返回的将是Object类型
	class CoffeeIterator implements Iterator<Coffee> {
		int count = size;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return count > 0;
		}
		
		//此方法是Iterator的精华所在，决定了你是遍历输出什么内容的。
		@Override
		public Coffee next() {
			count--;
			// TODO Auto-generated method stub
			return CoffeeGenerator.this.next();
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();//一般如果没有什么实现的话，需要抛出这个exception来提示程序员。
		}
		
	}
	
	
	
//	public T next() {
//		return null;
//	}
}
