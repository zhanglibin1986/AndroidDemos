package com.zlb.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Collections. This class consists exclusively of static methods that operate on or return collections.
 * @author zhanglibin
 *
 */
public class TestCollections {
	public static void main(String[] args) {
		TestCollections collections = new TestCollections();
		List<String> arrayList = new ArrayList<String>();
		
		collections.initList(arrayList);
		collections.testSort(arrayList);
		
	}
	
	private void initList(List<String> arrayList) {
		arrayList.add("zhanglibin");
		arrayList.add("zhaoruijuan");
		arrayList.add("wangdu");
		arrayList.add("zhangyao");
	}
	
	private void print(List<String> arrayList) {
		System.out.println(Arrays.toString(arrayList.toArray()));
	}
	private void testSort(List<String> arrayList) {
		print(arrayList);
		Collections.sort(arrayList);
		print(arrayList);
		
		Collections.sort(arrayList, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				if(o1.length() == o2.length()) {
					return 0;
				}
				if(o1.length() < o2.length()) {
					return -1;
				}
				if(o1.length() > o2.length()) {
					return 1;
				}
				return 0;
			}
		});
		print(arrayList);
	}
	
	private void testOthers() {
		
		List<String> arrayList = new ArrayList<String>();
		Collections.addAll(arrayList, "adb", "abc");
		print(arrayList);
	}
}
