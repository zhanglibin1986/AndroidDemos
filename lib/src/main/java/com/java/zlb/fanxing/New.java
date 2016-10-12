package com.java.zlb.fanxing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 杠杆利用类型参数推断。在泛型方法中，类型参数推断可以为我们简化一部分工作。下面是一个工具类，专门用来创建各种常用的容器对象。
 * 避免了这样的定义：Map<Person, List<? extends Pet>> petPeople = new HashMap<Person, List<? extends Pet>>();
 * 注意，类型推断只对复制操作有效，其他时候并不起作用。如果你将一个泛型方法调用的结果座位参数，传递给另一个方法，这时编译器并不会执行类型推断。下面LimitsOfInference类证明了这一点。
 * 在泛型方法中可以显式地指明类型，不过这种语法很少使用。这可以解决上面的问题。如下。
 * @author zhanglibin
 *
 */
public class New {
	public static <K, V> Map<K, V> map() {
		return new HashMap<K, V>();
	}
	
	public static <T> List<T> list() {
		return new LinkedList<T>();
	}
	
	public static <T> Set<T> set() {
		return new HashSet<T>();
	}
	//类型参数推断避免了重复的泛型参数列表。
	public static void main(String[] args) {
		Map<String , List<String>> lls = New.map();
		List<String> ls = New.list();
		Set<String> s = New.set();
		
		/*=============================================*/
		//LimitsOfInference.f(New.map());//会报错
		LimitsOfInference.f(New.<Person, List<Coffee>>map());//在泛型方法中可以显式地指明类型
	}
}

class LimitsOfInference {
	static void f(Map<Person, List<Coffee>> map) {
		
	}
	
}








