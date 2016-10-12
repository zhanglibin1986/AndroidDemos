package com.zlb.baidu.zhidao;

import java.util.Scanner;

public class Addition {
	int a;
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入两个整数：");
		int firstNum = input.nextInt();
		int secondNum = input.nextInt();
		String Result = "它们的和是："+Addition.add(firstNum, secondNum);
		System.out.println(Result);
	}
	private static int add(int a, int b) {
		return a+b;
	}
}
