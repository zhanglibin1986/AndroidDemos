package com.java.zlb.fanxing;

/**
 * 这个是一个更通用的元组工具类了！
 * @author zhanglibin
 *
 */
public class Tuple {
	public static <A, B> TwoYuanZu<A, B> TwoTuple(A a, B b) {
		return new TwoYuanZu<A, B>(a, b);
	}
	
	public static <A, B, C> ThreeYuanZu<A, B, C> ThreeTuple(A a, B b, C c) {
		return new ThreeYuanZu<A, B, C>(a, b, c);
	}
}
