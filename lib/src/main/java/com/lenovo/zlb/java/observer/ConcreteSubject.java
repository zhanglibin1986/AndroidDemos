package com.lenovo.zlb.java.observer;

import java.util.ArrayList;

public class ConcreteSubject implements Subject{
	ArrayList<Observer> observerList;
	String data1,data2;
	public ConcreteSubject() { 
		this.observerList = new ArrayList<Observer>();
	}
	
	public void register(Observer o) {
		// TODO Auto-generated method stub
		this.observerList.add(o);
	}

	public void unregister(Observer o) {
		// TODO Auto-generated method stub
		int index = observerList.indexOf(o);
		observerList.remove(index);
	}

	public void notifyUpdate() {
		// TODO Auto-generated method stub
		for(int i=0;i<observerList.size();i++) {
			Observer o = observerList.get(i);
			o.update(data1,data2);
		}
	}
	
	public void changeData(String d1,String d2) {
		// TODO Auto-generated method stub
		this.data1 = d1;
		this.data2 = d2;
		notifyUpdate();
	}
}
