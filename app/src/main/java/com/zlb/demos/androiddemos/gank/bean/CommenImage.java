package com.zlb.demos.androiddemos.gank.bean;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/9/19 上午10:31
 */
public class CommenImage {
    private String name;

    private String id;

    private String url;

    private String localUrl;

    private String description;

    private boolean isStar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStar() {
        return isStar;
    }

    public void setStar(boolean star) {
        isStar = star;
    }

    @Override
    public String toString() {
        return "CommenImage{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", localUrl='" + localUrl + '\'' +
                ", description='" + description + '\'' +
                ", isStar=" + isStar +
                '}';
    }
}
