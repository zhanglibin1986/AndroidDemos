package com.zlb.demo;

/**
 * 名称:BinarySearch 功能:实现了折半查找(二分查找)的递归和非递归算法. 说明:
 * 1、要求所查找的数组已有序,并且其中元素已实现Comparable接口,如Integer、String等.
 * 2、非递归查找使用search();,递归查找使用searchRecursively();
 * 
 * 本程序仅供编程学习参考
 * 
 * @author: Winty
 * @param <T>
 * @param <T>
 * @date: 2008-8-11
 * @email: wintys@gmail.com
 */
class BinarySearch {
	private String[] data;// 要排序的数据

	public BinarySearch(String[] data) {
		this.data = data;
	}

	public int search(String key) {
		int low;
		int high;
		int mid;
		if (data == null)
			return -1;
		low = 0;
		high = data.length - 1;
		while (low <= high) {
			mid = (low + high) / 2;
			System.out.println("mid " + mid + " mid value:" + data[mid]);// /
			if (key.compareTo(data[mid]) < 0) {
				high = mid - 1;
			} else if (key.compareTo(data[mid]) > 0) {
				low = mid + 1;
			} else if (key.compareTo(data[mid]) == 0) {
				return mid;
			}
		}
		return -1;
	}

	private int doSearchRecursively(int low, int high, String key) {
		int mid;
		int result;
		if (low <= high) {
			mid = (low + high) / 2;
			result = key.compareTo(data[mid]);
			System.out.println("mid " + mid + " mid value:" + data[mid]);// /
			if (result < 0) {
				return doSearchRecursively(low, mid - 1, key);
			} else if (result > 0) {
				return doSearchRecursively(mid + 1, high, key);
			} else if (result == 0) {
				return mid;
			}
		}
		return -1;
	}

	public int searchRecursively(String key) {
		if (data == null)
			return -1;
		return doSearchRecursively(0, data.length - 1, key);
	}

	public static void main(String[] args) {
//		String[] data = { 1, 4, 5, 8, 15, 33, 48, 77, 96 };
//		BinarySearch binSearch = new BinarySearch(data);
		// System.out.println("Key index:" + binSearch.search(33) );
//		System.out.println("Key index:" + binSearch.searchRecursively(3));
		 String [] dataStr = {"A" ,"C" ,"F" ,"J" ,"L" ,"N" ,"T"};
		 BinarySearch binSearch = new BinarySearch(dataStr);
		 System.out.println("Key index:" + binSearch.search("A") );
	}
}
