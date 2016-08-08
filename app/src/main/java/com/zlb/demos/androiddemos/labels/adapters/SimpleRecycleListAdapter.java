package com.zlb.demos.androiddemos.labels.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zlb.demos.androiddemos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos
 * @date 16/7/8上午11:22
 * @Description
 */
public class SimpleRecycleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<Object> datas = new ArrayList<>();
    protected Context context;

    public SimpleRecycleListAdapter(Context context) {
        this.context = context;
    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addDatas(List<Object> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.simple_recycler_list_item, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        TextView textView = (TextView) holder.itemView.findViewById(R.id.simple_list_item);
//        textView.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
