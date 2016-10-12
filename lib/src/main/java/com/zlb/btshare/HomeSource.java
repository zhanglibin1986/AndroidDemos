package com.zlb.btshare;

import java.util.ArrayList;

/**
 * Created by zhanglibin on 2014/11/8.
 */
public class HomeSource {
    //获取首页源码HomeSource：http://www.btshare.net/sort-1-1.html
    //获取所有具体网页地址 DetailHtmls
    //获取具体网页内容DetailPageSource
    //解析title，img地址，下载地址 DetailPageBean

    /**
     * 网页源码
     */
    private String content;
    /**
     * 网页中的详情页链接
     */
    private ArrayList<PageInfo> detailHtmlList;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<PageInfo> getDetailHtmlList() {
        return detailHtmlList;
    }

    public void setDetailHtmlList(ArrayList<PageInfo> detailHtmlList) {
        this.detailHtmlList = detailHtmlList;
    }
}
