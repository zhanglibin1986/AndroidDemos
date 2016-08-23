package com.zlb.demos.androiddemos.commens.list;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.commens.list
 * @date 16/8/22下午6:17
 * @Description
 */
public abstract class BaseCommenListAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<T> datas = new ArrayList<>();

    public void setData(List<T> data) {
        this.datas.clear();
        this.datas.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        this.datas.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 获取列表数据
     * @return
     */
    public List<T> getDatas() {
        return datas;
    }

    /**
     * 清空列表数据
     */
    public void clear() {
        synchronized (BaseCommenListAdapter.class) {
            if (datas != null) {
                datas.clear();
                notifyDataSetChanged();
            }
        }
    }

}
