package com.java.zlb.fanxing;
/**
 * 泛型方法示例。泛型方法能使得该类能够独立于类而产生变化。一个基本的指导原则：无论何时，只要你能做到，你就应该尽量使用泛型方法，也就是说，如果使用泛型方法可以取代将整个类泛型化，
 * 那么就应该只使用泛型方法，因为它可以使事情更清除明白。另外，对于一个static的方法而言，无法访问泛型类的类型参数，所以，如果static方法需要使用泛型能力，就必须十七成为泛型反复。
 * 定义方式：只需将泛型参数列表置于返回值之前。如下。
 * 注意，当使用泛型类时，必须在创建对象的时候指定类型参数的值，而使用泛型方法的时候通常不必指明类型参数类型，因为编译器会为我们找出具体的类型。这成为类型参数推断。因此，我们可以像
 * 调用普通方法一样调用f()， 而且就好像是f()呗无限次地重载过。
 * @author zhanglibin
 *
 */
public class GenericMethods {
	public <T> void f(T x) {
		System.out.println(x.getClass().getSimpleName());
	}
	
	public static void main(String[] args) {
		GenericMethods gm = new GenericMethods();
		gm.f(1);
		gm.f("a");
		gm.f(0.1);
		GenericMethods2 gm2 = new GenericMethods2();
		gm2.f(1, 0.1);
		gm2.f("a", 1);
		
	}
}

class GenericMethods2 {
	public <A, B> void f(A a, B b) {
		System.out.println("["+a.getClass().getSimpleName()+" , "+b.getClass().getSimpleName() + "]");
	}
}