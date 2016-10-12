package com.zlb.btshare;

import com.zlb.util.FileUtil;

import java.io.*;

/**
 * Created by zhanglibin on 2014/11/8.
 */
public class HomeSourceFactory {
    private volatile static HomeSourceFactory homeSourceFactory;
    private HomeSourceFactory() {

    }

    public static HomeSourceFactory getInstance() {
        if(homeSourceFactory == null) {
            synchronized (HomeSourceFactory.class) {
                if(homeSourceFactory == null) {
                    homeSourceFactory = new HomeSourceFactory();
                }
            }
        }
        return homeSourceFactory;
    }

    public HomeSource generateHomeSource(String localHtml) {
        File file = new File(localHtml);
        String content = FileUtil.readFile(file);

//        HomeSource source = new HomeSource();
        return null;
    }


    private HomeSource parseSource(String source) {
        return null;
    }
}
