package com.lenovo.zlb.java.observer;

public class Main {
	public static void main(String[] args) {
		ConcreteSubject cs = new ConcreteSubject();
		ConcreteObserver co = new ConcreteObserver(cs);
		cs.changeData("zhang", "wang");
		co.display();
	}
}
