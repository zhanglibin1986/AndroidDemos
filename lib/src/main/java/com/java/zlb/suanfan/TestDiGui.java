package com.java.zlb.suanfan;

public class TestDiGui {
	public static void main(String[] args) {
		TestDiGui dg = new TestDiGui();
		System.out.println(dg.jiecheng(4));
		System.out.println(dg.taijie(5));
		System.out.println(dg.tuzi(5));
	}
	
	public int jiecheng(int n) {
		if(n<2) {
			return 1;
		}else {
			return n*jiecheng(n-1);
		}
	}
	
	public int taijie(int n) {
		if(n < 2) {
			return 1;
		}else {
			return taijie(n-2)+taijie(n-1);
		}
	}
	
	public int tuzi(int n) {
		if(n <  2) {
			return 2;
		}else {
			return 2*tuzi(n-1);
		}
	}
	
	public int tuzi2(int n) {
		if((n-1)*2 <  2) {
			return 2;
		}else {
			return 2*tuzi((n-1)*2-1);
		}
	}
}


/*

1	1
2	11	2
3	111 12 21
4	1111 112 121 211 22
5	11111 1112 1211 1121 122 212 221 
6	111111 21111 12111 11211 11121 11112 2211 2121 2112 1221 1212 1122

*/