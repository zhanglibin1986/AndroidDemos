package com.java.zlb.fanxing;

/**
 * 泛型接口 
 * 生成器(generator)是一种专门负责创建对象的类。这也是工厂方法设计模式的一种应用，只是它没有参数而已。工厂方法一般需要参数。
 * @author zhanglibin
 *
 * @param <T>
 */
public interface Generator<T> {
	public T next();
}
