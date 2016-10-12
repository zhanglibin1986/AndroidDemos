package com.zlb.java.lang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by zhanglibin on 2014/11/6.
 */
public class TestProcess {
    public static void main(String[] args) {
        new TestProcess().runCmd("ping www.baidu.com");
    }

    public void runCmd(String cmd) {
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            /**
             * 读数据的流
             */
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
            String line;
            StringBuilder builder = new StringBuilder();
            while((line = reader.readLine()) != null) {
//                builder.append(line).append(System.lineSeparator());
            }
            System.out.println(new String(builder.toString().getBytes("GBK"), "GB2312"));

            InputStream errorStream = process.getErrorStream();
            StringBuilder errorBuiler = new StringBuilder();
            BufferedReader error = new BufferedReader(new InputStreamReader(errorStream));
            while((line = error.readLine()) != null)  {
//                errorBuiler.append(line).append(System.lineSeparator());
            }

            System.out.println("error ------------ " + errorBuiler.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
