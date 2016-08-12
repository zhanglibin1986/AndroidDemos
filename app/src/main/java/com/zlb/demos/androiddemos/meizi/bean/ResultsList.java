package com.zlb.demos.androiddemos.meizi.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.meizi.bean
 * @date 16/8/12上午10:20
 * @Description
 */
public class ResultsList {
    private boolean error;
    private List<Meizi> results = new ArrayList<>();

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Meizi> getResults() {
        return results;
    }

    public void setResults(List<Meizi> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ResultsList{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
