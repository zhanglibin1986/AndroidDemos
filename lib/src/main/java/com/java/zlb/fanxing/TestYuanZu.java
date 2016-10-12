package com.java.zlb.fanxing;

public class TestYuanZu {
	public static void main(String[] args) {
		//你可以存储任何类型的两种对象
		TwoYuanZu<String, Integer> test1 = new TwoYuanZu<String, Integer>("string", 1);
		System.out.println(test1);
	}
}
/**
 * 元组类。它解决了想要return多个对象的问题。可以复用这个类，而不需要每次遇到这样的问题都自定义一个类。如果需要存储多个对象，直接继承这个类就行。
 * @author zhanglibin
 *
 * @param <A>
 * @param <B>
 */
class TwoYuanZu<A, B> {
	public final A first;//为什么是public 的？因为这样可以直接访问这个属性，而不需要set get方法。但是它们无法被赋值，因为是final的。这样安全而简洁。
	public final B second;
	public TwoYuanZu(A a, B b) {
		this.first = a;
		this.second = b;
	}
	
	@Override
	public String toString() {
		
		return "("+first+" , "+second+")";
	}
}

class ThreeYuanZu<A, B, C> extends TwoYuanZu<A, B> {
	public final C third;
	public ThreeYuanZu(A a, B b, C c) {
		super(a, b);
		third = c;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "(" + first+" , " + second + " , " + third + ")";
	}
	
}

class FourYuanZu<A, B, C, D> extends ThreeYuanZu<A, B, C> {
	public final D fourth;
	public FourYuanZu(A a, B b, C c, D d) {
		super(a, b, c);
		fourth = d;
	}
}


class FiveYuanZu<A, B, C, D, E> extends FourYuanZu<A, B, C, D> {
	
	public final E fifth;
	
	public FiveYuanZu(A a, B b, C c, D d, E e) {
		super(a, b, c, d);
		fifth = e;
	}
	@Override
	public String toString() {
		return "(" + first + " , " + second + " , " + third + " , " + fourth + " , " + fifth + ")";
	}
}

























