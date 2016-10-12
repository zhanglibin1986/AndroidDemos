package com.java.zlb.fanxing;

public class TestStack<T> {
	private class Node {
		public Node(T item, Node node) {
			this.item = item;
			this.node = node;
		}
		T item;
		Node node;
	}
	
	private Node node;
	
	public void push(T t) {
		node = new Node(t, node);
	}
	
	public T pop() {
		T t = node.item;
		node = node.node;
		return t;
	}
	
	public static void main(String[] args) {
		TestStack<String> stack = new TestStack<String>();
		stack.push("a");
		stack.push("b");
		System.out.println("pop = " + stack.pop());
		stack.push("c");
		System.out.println("pop = " + stack.pop());
		System.out.println("pop = " + stack.pop());
	}
}
