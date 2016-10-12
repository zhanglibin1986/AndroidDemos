package com.java.zlb.fanxing;

public class CountedObject {
	public static int count = 0;
	public final int id = count++;
	public int id() {
		return id;
	}
	@Override
	public String toString() {
		return "id : "+id;
	}
}
