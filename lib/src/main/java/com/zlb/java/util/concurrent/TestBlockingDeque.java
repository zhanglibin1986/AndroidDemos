package com.zlb.java.util.concurrent;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class TestBlockingDeque {
	public static void main(String[] args) {
		BlockingDeque<String> deque = new LinkedBlockingDeque<String>(5);
		for(int i = 0; i < 6; i ++) {
			System.out.println(deque.offer("" + i));
//			System.out.println(deque.add("" + i));
		}
	}
}
