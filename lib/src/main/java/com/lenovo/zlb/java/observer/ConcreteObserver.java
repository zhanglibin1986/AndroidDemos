package com.lenovo.zlb.java.observer;

public class ConcreteObserver implements Observer,Display{
	Subject s;
	String data1,data2;
	public ConcreteObserver(Subject s) {
		this.s = s;
		s.register(this);
	}
	
	public void update(String data1,String data2) {
		// TODO Auto-generated method stub
		this.data1 = data1;
		this.data2 = data2;
	}

	public void display() {
		// TODO Auto-generated method stub
		System.out.println("now the data1 is "+data1+" and thd data2 is "+ data2);
	}

}
