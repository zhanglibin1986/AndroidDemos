package com.zlb.btshare;

import java.util.ArrayList;

/**
 * Created by zhanglibin on 2014/11/8.
 */
public class DetailPageSource {
    //获取首页源码HomeSource：http://www.btshare.net/sort-1-1.html
    //获取所有具体网页地址 DetailHtmls
    //获取具体网页内容DetailPageSource
    //解析title，img地址，下载地址 DetailPageBean

    /**
     * 网页源码
     */
    private String source;
    /**
     * 影片名称
     */
    private String title;
    /**
     * 影片图片
     */
    private ArrayList<String> picUrlList;

    /**
     * 下载地址
     */
    String downloadUrl;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getPicUrlList() {
        return picUrlList;
    }

    public void setPicUrlList(ArrayList<String> picUrlList) {
        this.picUrlList = picUrlList;
    }


}
