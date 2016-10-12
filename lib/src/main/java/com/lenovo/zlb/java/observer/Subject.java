package com.lenovo.zlb.java.observer;

public interface Subject {
	void register(Observer o);
	void unregister(Observer o);
	void notifyUpdate();
}
