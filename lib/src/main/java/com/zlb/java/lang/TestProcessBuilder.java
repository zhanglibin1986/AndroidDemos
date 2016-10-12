package com.zlb.java.lang;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by zhanglibin on 2014/11/6.
 */
public class TestProcessBuilder {
    public static void main(String[] args) {
//        ProcessBuilder pb = new ProcessBuilder("ping", , arg2);
    }

    public static void execCmd(String cmd, String arg1, String arg2) {
        ProcessBuilder pb = new ProcessBuilder(cmd, arg1, arg2);
//        pb.
        Map<String, String> env = pb.environment();
        env.put("VAR1", "myValue");
        env.remove("OTHERVAR");
        env.put("VAR2", env.get("VAR1") + "suffix");
        pb.directory(new File("myDir"));
        try {
            Process p = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
