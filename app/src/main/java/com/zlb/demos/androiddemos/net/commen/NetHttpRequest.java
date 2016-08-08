package com.zlb.demos.androiddemos.net.commen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.net.commen
 * @date 16/7/29下午6:24
 * @Description
 */
public class NetHttpRequest {
    private String url;
    private String tag;
    private Map<String, String> getParams = new HashMap<>();//会拼在url中
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> postParams = new HashMap<>();//post 参数

    private NetHttpRequest() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Map<String, String> getGetParams() {
        return getParams;
    }

    public void setGetParams(Map<String, String> getParams) {
        this.getParams = getParams;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getPostParams() {
        return postParams;
    }

    public void setPostParams(Map<String, String> postParams) {
        this.postParams = postParams;
    }

    public static class Builder {
        private String url;
        private Map<String, String> getParams = new HashMap<>();//会拼在url中
        private Map<String, String> headers = new HashMap<>();
        private Map<String, String> postParams = new HashMap<>();//post 参数

        public Builder addParam(String key, String value) {
            this.getParams.put(key, value);
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public NetHttpRequest build() {
            NetHttpRequest request = new NetHttpRequest();
            request.setUrl(this.url);
            request.setGetParams(getParams);
            return request;
        }
    }
}
