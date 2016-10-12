package com.zlb.java.lang;
import static java.lang.System.out;

/**
 * Created by zhanglibin on 2014/9/9.
 */
public class TestEnum {
    public static void main(String[] args) {
        TestEnum t1 = new TestEnum();
        TestEnum t2 = new TestEnum();
        TestEnum t3 = new TestEnum();

        t1.printTypeOrdinal(DialogType.TYPE_FORCE_ALERT_OPEN);
        t1.printTypeOrdinal(DialogType.TYPE_FORCE_ALERT_LOOK);

        t2.printTypeOrdinal(DialogType.TYPE_FORCE_ALERT_OPEN);
        t2.printTypeOrdinal(DialogType.TYPE_FORCE_ALERT_LOOK);

        t3.printTypeOrdinal(DialogType.TYPE_FORCE_ALERT_OPEN);
        t3.printTypeOrdinal(DialogType.TYPE_FORCE_ALERT_LOOK);
    }

    public void printTypeOrdinal(DialogType type) {
        out.println(type.ordinal());
    }

    public enum DialogType {
        /**
         * 本地唤醒
         */
        TYPE_FORCE_ALERT_OPEN,
        /**
         * 强唤醒推送，桌面类型。
         */
        TYPE_FORCE_ALERT_LOOK
    }
}
