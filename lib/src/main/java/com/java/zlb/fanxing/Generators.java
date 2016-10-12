package com.java.zlb.fanxing;

import java.util.ArrayList;
import java.util.Collection;

public class Generators {
	public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int n) {
		for(int i=0;i<n;i++) {
			coll.add(gen.next());
		}
		return coll;
	}
	
	
	public static void main(String[] args) {
		ArrayList<Coffee> coffees = new ArrayList<Coffee>();
		Generators.fill(coffees, new CoffeeGenerator(), 5);
		System.out.println(coffees.toString());
		
	}
}
