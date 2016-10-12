package com.java.zlb.fanxing;

import java.util.ArrayList;
import java.util.Random;

/**
 * 假设我们需要一个持有特定类型对象的列表，每次调用其上的select()方法时，它可以随机地选取一个元素。（可以应用于各种类型的对象)
 * @author zhanglibin
 *
 */
public class RandomList<T> {
	private ArrayList<T> list = new ArrayList<T>();
	Random rand = new Random(47);
	public RandomList() {
		
	}
	
	public void add(T item) {
		list.add(item);
	}
	
	public T select() {
		return list.get(rand.nextInt(list.size()));
	}
}
