package com.java.zlb.fanxing;

public class TestQueue<T> {
	private class Node<U> {
		U item;
		Node<U> next;
		
		public Node(U item, Node<U> next) {
			this.item = item;
			this.next = next;
		}
	}
	
	
	Node<T> tail;
	Node<T> head;
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void add(T t) {
		if(head == null) {//第一次add的时候， 还是空队列
			head = new Node<T>(t, null);//有了一个节点
			tail = head;
			return;
		}
		
		if(head.next == null) {//第二次add的时候，只有一个节点
			tail = new Node<T>(t, null);
			head.next = tail;
			return;
		}
		
		tail.next = new Node<T>(t, null);
	}
	
	public T getTop() {
		if(head == null) {
			return null;
		}
		T t = head.item;
		head = head.next;
		return t;
	}
	
	public static void main(String[] args) {
		TestQueue<String> queue = new TestQueue<String>();
		System.out.println("queue is empty = " + queue.isEmpty());
		queue.add("a");
		System.out.println("queue is empty = " + queue.isEmpty());
		System.out.println("pop = " + queue.getTop());
		queue.add("b");
		System.out.println("pop = " + queue.getTop());
		queue.add("c");
		queue.add("d");
		System.out.println("pop = " + queue.getTop());
		queue.add("e");
		System.out.println("pop = " + queue.getTop());
		
	}
}
