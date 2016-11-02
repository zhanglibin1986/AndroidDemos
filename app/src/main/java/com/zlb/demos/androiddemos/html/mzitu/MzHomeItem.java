package com.zlb.demos.androiddemos.html.mzitu;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/9/24 下午11:32
 */

public class MzHomeItem {
    private String detailUrl;
    private String title;
    private String picUrl;
    private boolean isStar;

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public boolean isStar() {
        return isStar;
    }

    public void setStar(boolean star) {
        isStar = star;
    }

    @Override
    public String toString() {
        return "MzHomeItem{" +
                "detailUrl='" + detailUrl + '\'' +
                ", title='" + title + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", isStar=" + isStar +
                '}';
    }
}
