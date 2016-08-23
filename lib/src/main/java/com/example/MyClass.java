package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rx.Observable;
import rx.functions.Action1;

public class MyClass {
    public static void main(String[] args) {
        System.out.println("hello java lib");
        Request request1 = new Request();
        request1.setInit(true);
        request1.setUrl("url1");
        request1.setPaths(createPath("path0", "path00"));
        request1.getPostParam().put("map0", "map00");
        request1.requestType = RequestType.METHOD_GET;
        Request request2 = null;
        try {
            request2 = (Request) request1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(request2);

        request1.setInit(false);
        request1.setUrl("url2");
        request1.setPaths(createPath("path2", "path22"));
        request1.getPostParam().clear();
        request1.getPostParam().put("map2", "map22");
        request1.requestType = RequestType.METHOD_POST;

        System.out.println(request2);

        try {
            request2 = (Request) request1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(request2);


//        request1.set

    }

    static List<String> createPath(String... path) {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < path.length; i++) {
            list.add(path[i]);
        }
        return list;
    }


    static class Request implements Cloneable {
        private String url;
        private Map<String, String> postParam = new HashMap<>();//post参数
        private List<String> paths = new ArrayList<>();
        private boolean isInit;//是否是初始化数据，即清除原来的数据，重新请求；注意：无网或请求数据失败的情况应该不清除数据
//        private IResponseParser responseParser;
        private RequestType requestType;//请求类型
//        private List<OkRequestListener> listeners = new ArrayList<>();


        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Map<String, String> getPostParam() {
            return postParam;
        }

        public void setPostParam(Map<String, String> postParam) {
            this.postParam = postParam;
        }

        public List<String> getPaths() {
            return paths;
        }

        public void setPaths(List<String> paths) {
            this.paths = paths;
        }

        public boolean isInit() {
            return isInit;
        }

        public void setInit(boolean init) {
            isInit = init;
        }

        public RequestType getRequestType() {
            return requestType;
        }

        public void setRequestType(RequestType requestType) {
            this.requestType = requestType;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            Request r = null;
            try {
                r = (Request) super.clone();
                r.postParam = new HashMap<>();
                Set<String> keys = postParam.keySet();
                for(String key : keys) {
                    r.postParam.put(key, postParam.get(key));
                }

            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            return r;
        }

        @Override
        public String toString() {
            return "Request{" +
                    "url='" + url + '\'' +
                    ", postParam=" + postParam +
                    ", paths=" + paths +
                    ", isInit=" + isInit +
                    ", requestType=" + requestType +
                    '}';
        }
    }

    public static enum RequestType {
        METHOD_GET, METHOD_POST
    }
}
