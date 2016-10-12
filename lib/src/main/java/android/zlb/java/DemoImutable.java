package android.zlb.java;
/**
 * immutable类不可变类
  1.不可变类，顾名思义就是说类的实例是不可被修改的。实例的信息是在创建的时候提供，并且在整个生命周期中都不可改变。Java中很多class都是immutable，像String，Integer等，它们通常用来作为Map的key.
  2.其实在JDK中， String, the primitive wrapper classes, and BigInteger and BigDecimal都是不变类。那么如果让你设计和immutable的class要怎么做呢？ 
    1.这个类不能被继承。
      1.class 前面要加 final
      2.如果你想在他的基础上定义一个子类也是immutable的。这时候你可以不在class 前面加final，而是在所有的方法上加final，同样可以防止子类重写它的方法。其实不用继承一样可以扩展这个类，通过composite方法（？？）。不推荐允许继承immutable类
    2.属性不能被改变。
    3.不要提供可以改变类状态(成员变量)的方法。【get 方法不要把类里的成员变量让外部客服端引用,当需要访问成员变量时，返回成员变量的copy】
    4.构造函数不要引用外部可变对象。如果需要引用外部可以变量，应该在构造函数里进行defensive copy。

  3.不可变类的好处
    1.不变类是线程安全的，由于不变类的状态在创建以后不再发生变化，所以它可以在线程之间共享，而不需要同步。
    2.不变类的instance可以被reuse.创建类的实例需要耗费CPU的时间，当这个实例不再被引用时，将会被垃圾回收掉，这时候，又需要耗费CPU的时间。对于不变类而言，一个好处就是可以将常用的实例进行缓存，从而减少了对象的创建。举个例子，对于布尔型，
      最常用的便是true and false。JDK中的Boolean类就是一个不变类，并且对这两个实例进行了缓冲。
    3.hashCode这个方法来自于Object这个类，这个方法用来返回对象的hashCode,主要用于将对象放置到hashtable中时，来确定这个对象的存储位置。对于一个不变类的实例，它的hashCode也是不变的，所以就可以缓存这个计算的结果，来提高性能，避免不必
      要的运算，JDK中的String类就是一个例子。
 * @author zhanglibin
 *
 */
public class DemoImutable {
	public static void main(String[] args) {
		int[] arrays = {1,2,3};
		MyImutable mi = new MyImutable(arrays);
		System.out.println(mi);
		arrays[0] = 4;
		arrays[1] = 5;
		arrays[2] = 6;
		System.out.println(mi);
		if(arrays[0] == 4)
			System.out.println("1");
		else
			System.out.println("2");
	}
}

final class MyImutable {
	private final int[] myArray;
	public MyImutable(int[] array) {
//		this.myArray = array;
		this.myArray = array.clone();// defensive copy
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer("Numbers are :");
		for(int i = 0;i<myArray.length;i++) {
			sb.append(myArray[i]).append(" ");
		}
		return sb.toString();
	}
}
