package com.java.zlb.fanxing;

import java.util.Arrays;

public class LinkedStack<T> {
	
	//ÿ��nude��һ���ڵ㡣����ջ��һ��Ԫ��
	private static class Nute<U> {
		U item;
		Nute<U> next;
		//�ж�ջΪ�յ�������
		public Nute() {
			item = null;
			next = null;
		}
		public Nute(U item, Nute nute) {
			this.item = item;
			this.next = nute;
		} 
		
		boolean isEmpty() {
			return (item == null && next == null);
		}
	}
	
	Nute<T> top = new Nute<T>();
	
	public void push(T item) {
		top = new Nute<T>(item, top);
	}
	
	public T pop() {
		T result = top.item;
		if(!top.isEmpty()) {
			top = top.next;
		}
		return result;
	}
	
	public static void main(String[] args) {
		String[] datas = new String[10];
		LinkedStack<String> stack = new LinkedStack<String>();
		for(int i = 0; i < datas.length; i++) {
			datas[i] = String.valueOf(i);
			stack.push(datas[i]);
		}
		System.out.println(Arrays.toString(datas));
		String s;
		while(( s = stack.pop()) != null) {
			System.out.println(s);
		}
	}
	
}
