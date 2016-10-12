package com.lenovo.zlb.testenum;

import com.lenovo.zlb.testenum.TestEnumInterface.DOLBY_GENRE;

public class TestEnum {
	static TestEnumInterface.DOLBY_GENRE in;
	public static void main(String[] args) {
		System.out.println("==="+DOLBY_GENRE.MUSIC_Dance);
		
		DOLBY_GENRE[] dolbyArray = DOLBY_GENRE.values();
		System.out.println(dolbyArray[1]);
		
		System.out.println(DOLBY_GENRE.MUSIC_Blues);
		
		System.out.println(DOLBY_GENRE.MUSIC_Blues.name());
		
		//通过指定字符串拿到对象
		DOLBY_GENRE d = DOLBY_GENRE.valueOf("MUSIC_Blues");
		System.out.println("DOLBY_GENRE: "+d);
		
		//Enum类提供了一个ordinal()方法，用来返回枚举对象的序数
		System.out.println(d.ordinal());
		
		
	}
}
