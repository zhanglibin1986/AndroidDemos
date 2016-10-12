package com.design.proxy;

/**
 * Created by zhanglibin on 2014/8/22.
 */
public class PersonBean implements Person {
    private String name;
    private int score;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}


