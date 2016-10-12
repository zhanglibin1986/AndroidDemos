package com.zlb.util;

import java.io.*;

/**
 * Created by zhanglibin on 2014/11/8.
 */
public class FileUtil {
    public static  String readFile(File file) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        StringBuffer buffer = new StringBuffer();
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if(bufferedReader != null) {
                    bufferedReader.close();
                }
                if(fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }
}
