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
    protected List<T> datas = new ArrayList<>();
    protected int customLastLayoutId;//自定义最后一条布局
    private DataChageListener dataChageListener;//数据发生变化后会通知
    public BaseCommenListAdapter() {

    }

    /**
     *
     * @param customLastLayoutId 自定义最后一条布局
     */
    public BaseCommenListAdapter(int customLastLayoutId) {
        this.customLastLayoutId = customLastLayoutId;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 初始化设置数据
     * @param data
     */
    public void setData(List<T> data) {
        this.datas.clear();
        this.datas.addAll(data);
        dataChanged();
    }

    /**
     * 添加数据
     * @param data
     */
    public void addData(List<T> data) {
        this.datas.addAll(data);
        dataChanged();
    }

    /**
     * 获取列表数据
     * @return
     */
    public List<T> getDatas() {
        return datas;
    }

    public int getCustomLastLayoutId() {
        return customLastLayoutId;
    }

    public void setCustomLastLayoutId(int customLastLayoutId) {
        this.customLastLayoutId = customLastLayoutId;
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

    public void setDataChangeListener(DataChageListener listener) {
        this.dataChageListener = listener;
    }

    /**
     * 通知数据发生变化
     */
    private void dataChanged() {
        if(dataChageListener != null) {
            dataChageListener.onDataChanged();
        }
    }

    public interface DataChageListener {
        void onDataChanged();
    }

}
