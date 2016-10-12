package com.java.zlb.fanxing;

import org.omg.CORBA.portable.UnknownException;
/**
 * 下面的程序可以为任何类构造一个Generator，只要该类具有默认构造器！
 * 注意什么时候有<T>的标识！这是一个泛型类和泛型方法的结合。
 * @author zhanglibin
 *
 * @param <T>
 */
public class BasicGenerator<T> implements Generator<T>{
	private Class<T> type;
	private BasicGenerator(Class<T> c) {
		type = c;
	}
	@Override
	public T next() {
		// TODO Auto-generated method stub
		try {
			return type.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T> Generator<T> createGenerator(Class<T> c) {
		return new BasicGenerator<T>(c);
	}
	
	public static void main(String[] args) {
		Generator<CountedObject> bg = BasicGenerator.createGenerator(CountedObject.class);
		for(int i=0;i<5;i++) {                                                                                                                          
			System.out.println(bg.next());
		}
	}
}

