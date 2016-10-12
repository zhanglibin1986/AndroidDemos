package android.zlb.java;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhanglibin on 2014/11/3.
 */
public class Aimp{
    public static void main(String[] args) {
        new Aimp().test();
    }
    private void test() {
        int i = 0;
        System.out.println("i = " + i);
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis() - start < 1000) {
            i++;
        }
        System.out.println("------------ result = " + i);
    }
}
