package com.zlb.btshare;

import com.zlb.gethtml.GetHtmlUtil;
import com.zlb.util.PatternUtil;

import java.util.List;

/**
 * Created by zhanglibin on 2014/11/8.
 */
public class BtShare {
    public static final String TEST_URL_1 = "http://dyall.2121921.net";
    public static final String TEST_URL_2 = "http://www.btshare.net/sort-1-1.html";


    public static void main(String[] args) {
        String mainContent = GetHtmlUtil.getHtmlContent(TEST_URL_2, "utf-8");//主页中的内容
        System.out.println(mainContent);
        List<String> htmsNoHead = GetHtmlUtil.getPatternLists(mainContent, PatternUtil.PATTERN_HTM);
    }


    //获取首页源码HomeSource：http://www.btshare.net/sort-1-1.html
    //获取所有具体网页地址 DetailHtmls
    //获取具体网页内容DetailPageSource
    //解析title，img地址，下载地址 DetailPageBean
}
