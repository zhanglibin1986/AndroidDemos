package com.zlb.demos.androiddemos.commens.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.commens
 * @date 16/8/13下午10:08
 * @Description
 */
public class CommenListRequest implements Cloneable {
    private String url;
    private Map<String, String> headers;
    private Map<String, String> params = new HashMap<>();//会拼在url中
    private Map<String, String> postParam = new HashMap<>();//post参数
    private List<String> paths = new ArrayList<>();
    private boolean isInit;//是否是初始化数据，即清除原来的数据，重新请求；注意：无网或请求数据失败的情况应该不清除数据
    private IResponseParser responseParser;
    private RequestType requestType;//请求类型
    private List<OkRequestListener> listeners = new ArrayList<>();
    private List<CommenRequestListener> commenRequestListeners = new ArrayList<>();

    private String nextStartKey;
    private String nextStartValue;
    /**
     * 参数中是否要加 nextStartKey，如果为false或者nextStartKey为空,则请求url中不含有nextStartKey和nextStartValue的键值对
     */
    private boolean hasMoreParam = true;
    private Class resultObject;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public void addParam(String key, String value) {
        this.params.put(key, value);
    }

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    public void addPath(String path) {
        this.paths.add(path);
    }

    public boolean isInit() {
        return isInit;
    }

    public void setInit(boolean init) {
        isInit = init;
    }

    public IResponseParser getResponseParser() {
        return responseParser;
    }

    public void setResponseParser(IResponseParser responseParser) {
        this.responseParser = responseParser;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public List<OkRequestListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<OkRequestListener> listeners) {
        this.listeners = listeners;
    }

    public void addListener(OkRequestListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(OkRequestListener listener) {
        if(this.listeners.contains(listener)) {
            this.listeners.remove(listener);
        }
    }

    public List<CommenRequestListener> getCommenRequestListeners() {
        return commenRequestListeners;
    }

    public void setCommenRequestListeners(List<CommenRequestListener> commenRequestListeners) {
        this.commenRequestListeners = commenRequestListeners;
    }

    public void addCommenRequestListener(CommenRequestListener listener) {
        this.commenRequestListeners.add(listener);
    }

    public void removeCommenListener(CommenRequestListener listener) {
        if(this.commenRequestListeners.contains(listener)) {
            this.commenRequestListeners.remove(listener);
        }
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public Map<String, String> getPostParam() {
        return postParam;
    }

    public void addPostParam(String key, String value) {
        this.postParam.put(key, value);
    }

    public void setPostParam(Map<String, String> postParam) {
        this.postParam = postParam;
    }

    public String getNextStartKey() {
        return nextStartKey;
    }

    public void setNextStartKey(String nextStartKey) {
        this.nextStartKey = nextStartKey;
    }

    public String getNextStartValue() {
        return nextStartValue;
    }

    public void setNextStartValue(String nextStartValue) {
        this.nextStartValue = nextStartValue;
    }

    public boolean isHasMoreParam() {
        return hasMoreParam;
    }

    public void setHasMoreParam(boolean hasMoreParam) {
        this.hasMoreParam = hasMoreParam;
    }

    public Class getResultObject() {
        return resultObject;
    }

    public void setResultObject(Class resultObject) {
        this.resultObject = resultObject;
    }

    public static class Builder {
        private String url;
        private Map<String, String> headers = new HashMap<>();
        private List<OkRequestListener> listeners = new ArrayList<>();
        private Map<String, String> params = new HashMap<>();//会拼在url中
        private Map<String, String> postParam = new HashMap<>();//post参数
        private List<String> paths = new ArrayList<>();
        private boolean isInit = true;//是否是初始化数据，即清除原来的数据，重新请求；注意：无网或请求数据失败的情况应该不清除数据
        private IResponseParser responseParser;
        private RequestType requestType;//请求类型
        private boolean isPost;//是否是get方法，目前只支持get和post

        private String nextStartKey;
        private String nextStartValue;
        private boolean hasMoreParam = true;//参数中是否要加 nextStartKey
        private List<CommenRequestListener> commenRequestListeners = new ArrayList<>();
        private Class resultObject;

        public static Builder newInstanse(String url, IResponseParser parser) {
            return new Builder(url, parser);
        }

        public Builder(String url, IResponseParser parser) {
            this.url = url;
            this.responseParser = parser;
        }

        /**
         * 设置param的参数类型
         * @param isPost
         * @return
         */
        public Builder method(boolean isPost) {
            this.isPost = isPost;
            return this;
        }

        public Builder addHeader(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        public Builder addListener(OkRequestListener listener) {
            this.listeners.add(listener);
            return this;
        }

        public Builder addCommenRequestListener(CommenRequestListener listener) {
            this.commenRequestListeners.add(listener);
            return this;
        }

        /**
         * 根据{@link #method(boolean)} 设置的param参数类型添加键值对。
         * @param key
         * @param value
         * @return
         */
        public Builder addParam(String key, String value) {
            if(isPost) {
                this.postParam.put(key, value);
            } else {
                this.params.put(key, value);
            }
            return this;
        }

        public Builder addPaths(List<String> paths) {
            this.paths.addAll(paths);
            return this;
        }

        public Builder addPath(String path) {
            this.paths.add(path);
            return this;
        }

        public Builder nextStartKey(String key) {
            this.nextStartKey = key;
            return this;
        }

        public Builder nextStartValue(String value) {
            this.nextStartValue = value;
            return this;
        }

        public Builder hasMoreParam(boolean hasMoreParam) {
            this.hasMoreParam = hasMoreParam;
            return this;
        }

        public Builder resultObject(Class<?> clz) {
            this.resultObject = clz;
            return this;
        }

        public CommenListRequest build() {
            CommenListRequest request = new CommenListRequest();
            request.url = this.url;
            request.params = this.params;
            request.setPostParam(this.postParam);
            request.paths = this.paths;
            request.isInit = this.isInit;
            request.responseParser = this.responseParser;
            request.requestType = this.requestType;
            request.setHeaders(this.headers);
            request.setListeners(this.listeners);
            request.setNextStartKey(this.nextStartKey);
            request.setNextStartValue(this.nextStartValue);
            request.setHasMoreParam(this.hasMoreParam);
            request.setCommenRequestListeners(this.commenRequestListeners);
            request.setResultObject(this.resultObject);
            return request;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        CommenListRequest request = null;
        try {
            request = (CommenListRequest) super.clone();
            request.postParam = getNewMap(postParam);
            request.params = getNewMap(params);
            request.headers = getNewMap(headers);
            request.paths = new ArrayList<>();
            for(int i = 0; i < paths.size(); i++) {
                request.paths.addAll(paths);
            }
        } catch (CloneNotSupportedException exception) {
            exception.printStackTrace();
        }

        return request;
    }

    private <T, D> Map<T, D> getNewMap(Map<T, D> map) {
        Map<T, D> result = new HashMap<>();
        Set<T> keys = map.keySet();
        for(T t : keys) {
            result.put(t, map.get(t));
        }
        return result;
    }

    public static enum RequestType {
        METHOD_GET, METHOD_POST
    }
}
