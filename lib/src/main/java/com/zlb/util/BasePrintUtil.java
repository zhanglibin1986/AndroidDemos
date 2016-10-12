package com.zlb.util;

public abstract class BasePrintUtil implements PrintInterface {
	
	private String string;
	
	public BasePrintUtil(String string) {
		this.string = string;
	}
	
	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println(generatePrint(string));
	}
	
	protected abstract String generatePrint(String string);
}
