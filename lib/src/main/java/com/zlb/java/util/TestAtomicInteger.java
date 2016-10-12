package com.zlb.java.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhanglibin on 2014/11/29.
 */
public class TestAtomicInteger {
    public static void main(String[] args) {
        TestAtomicInteger atomicInteger = new TestAtomicInteger();
        atomicInteger.test1();
    }

    private void test1() {
        AtomicInteger mSequenceGenerator = new AtomicInteger();
        print(mSequenceGenerator);//0
        print(mSequenceGenerator.getAndAdd(2));//0
        print(mSequenceGenerator);//2
        print(mSequenceGenerator.addAndGet(2));//4
        print(mSequenceGenerator);//4
        print(mSequenceGenerator.incrementAndGet());//5
        print(mSequenceGenerator);//5
    }

    public static void print(String log) {
        System.out.println("-----log");
    }

    public static void print(AtomicInteger integer) {
        System.out.println("-----" + integer);
    }

    public static void print(int integer) {
        System.out.println("-----" + integer);
    }
}
