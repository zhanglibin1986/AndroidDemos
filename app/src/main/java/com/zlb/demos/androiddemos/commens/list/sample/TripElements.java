package com.zlb.demos.androiddemos.commens.list.sample;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.commens.list.sample
 * @date 16/8/25上午10:18
 * @Description
 */
public class TripElements {
    private List<TripData> elements = new ArrayList<>();
    private String next_start;

    public List<TripData> getElements() {
        return elements;
    }

    public void setElements(List<TripData> elements) {
        this.elements = elements;
    }

    public String getNext_start() {
        return next_start;
    }

    public void setNext_start(String next_start) {
        this.next_start = next_start;
    }

    @Override
    public String toString() {
        return "TripElements{" +
                "elements=" + elements +
                ", next_start='" + next_start + '\'' +
                '}';
    }

    public static class TripData {
        private String type;
        private List<TripItem> data = new ArrayList<>();
        private String desc;

        public List<TripItem> getData() {
            return data;
        }

        public void setData(List<TripItem> data) {
            this.data = data;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "TripData{" +
                    "type='" + type + '\'' +
                    ", data=" + data +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }

    public static class TripItem {
        @JSONField(name = "cover_image")
        private String cover_image_default;
        private String name;
        private String last_day;

        public String getCover_image_default() {
            return cover_image_default;
        }

        public void setCover_image_default(String cover_image_default) {
            this.cover_image_default = cover_image_default;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLast_day() {
            return last_day;
        }

        public void setLast_day(String last_day) {
            this.last_day = last_day;
        }

        @Override
        public String toString() {
            return "TripItem{" +
                    "cover_image_default='" + cover_image_default + '\'' +
                    ", name='" + name + '\'' +
                    ", last_day='" + last_day + '\'' +
                    '}';
        }
    }
}

