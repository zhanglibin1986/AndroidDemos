package com.java.zlb.fanxing;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型方法与可变参数列表能够很好的共存。这个例子和边准类库中java.util.Arrays.asList()方法功能相同。
 * @author zhanglibin
 *
 */
public class GenericKeBian {
	public <T> List<T> makeList(T... ts) {
		ArrayList<T> list = new ArrayList<T>();
		for(T t : ts) {
			list.add(t);
		}
		return list;
	}
}
