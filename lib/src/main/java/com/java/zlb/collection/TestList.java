package com.java.zlb.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.java.zlb.fanxing.Person;

public class TestList {
	List mList = new ArrayList();
	
	public void fun() {
		Collections.synchronizedList(mList);
//		Collections.
	}
	public static void main(String[] args) {
		
		ArrayList<Person> origin = new ArrayList<Person>();
		for(int i = 0; i < 10; i++) {
			origin.add(new Person(String.valueOf(i), i));
		}
		
		ArrayList<Person> list1 = new ArrayList<Person>();
		ArrayList<Person> list2 = new ArrayList<Person>();
		
		list1.addAll(origin);
		
		list2.addAll(origin);
		
		for(int i = 0; i < list1.size(); i++) {
			list1.get(i).setName("list1:" + i);
		}
		
		System.out.println("origin = ");
		printPerson(origin);
		System.out.println("list1 = ");
		printPerson(list1);
		System.out.println("list2 = ");
		printPerson(list2);
		
		
	}
	
	public static void printPerson(List<Person> list) {
		System.out.println(Arrays.toString(list.toArray()));
	}
	
	
	
	
	
	
	
}
