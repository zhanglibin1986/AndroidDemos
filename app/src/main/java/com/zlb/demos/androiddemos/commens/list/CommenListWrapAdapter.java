package com.zlb.demos.androiddemos.commens.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zlb.demos.androiddemos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.commens
 * @date 16/8/13下午10:05
 * @Description
 */
public class CommenListWrapAdapter extends RecyclerView.Adapter implements BaseCommenListAdapter.DataChageListener {
    public static final int TYPE_LAST = 9999;//代表是列表中最后一条数据，我们称为末位标识的type
    public static final int TYPE_CUSTOM = 10000;//代表是列表中最后一条数据，我们称为末位标识的type

    private BaseCommenListAdapter delegate;
    private Context mContext;
    private int customLastLayoutId;//最后一条自定义布局
    private LastItemState mLastItemState;//末位标识的状态：加载更多，无加载更多，加载出错当前无网。
    private boolean hasMore;
    private IBaseRecyclerAdapterController mAdapterController;
    private CommentListResponse responseData;
//    private List<Object> allData = new ArrayList<>();

    public CommenListWrapAdapter(Context context, BaseCommenListAdapter delegate, IBaseRecyclerAdapterController controller) {
        this.delegate = delegate;
        this.mContext = context;
        this.customLastLayoutId = delegate.getCustomLastLayoutId();
        this.mAdapterController = controller;
    }


    private boolean hasCustomLast() {
        return customLastLayoutId != 0;
    }

    //重新初始化数据
    protected <T> void initData(CommentListResponse<T> data) {
        this.hasMore = data.isHasMore();
        this.responseData = data;
//        allData.clear();
        delegate.setData(data.getData());
//        allData.addAll(responseData.getData());
        mLastItemState = hasMore ? LastItemState.LOADING : LastItemState.NONE;
        notifyDataSetChanged();
    }

    protected <T> void addData(CommentListResponse<T> data) {
        delegate.addData(data.getData());
        syncData();
        notifyDataSetChanged();
    }

    /**
     * 从delete同步列表数据
     */
    private void syncData() {
        responseData.getData().clear();
        responseData.getData().addAll(delegate.getDatas());
    }

    /**
     * 告诉Adapter 请求失败了，会在有加载更多时，将此item 的ui换为点击重试等等
     */
    public void failRequest() {
        if(hasMore) {
            mLastItemState = LastItemState.ERROR;
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch(viewType) {
            case TYPE_LAST:
                holder = new HolderLastItem(LayoutInflater.from(mContext).inflate(R.layout.load_more_item, parent, false), mAdapterController);
                break;
            case TYPE_CUSTOM:
                holder = new HolderCustomLast(LayoutInflater.from(mContext).inflate(customLastLayoutId, parent, false));
                break;
            default:
                holder = delegate.onCreateViewHolder(parent, viewType);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type) {
            case TYPE_LAST:
                HolderLastItem lastItem = (HolderLastItem) holder;
                lastItem.show(mLastItemState);//根据最后一条数据的类型决定显示哪种ui
                Log.d("more", "onBindViewHolder mLastItemState = " + mLastItemState + " , position = " + position);
                break;
            case TYPE_CUSTOM:
                break;
            default:
                Log.e("error", "position = " + position);
                delegate.onBindViewHolder(holder, position);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(hasMore) {
            if(position == getItemCount() - 1) {
                return TYPE_LAST;
            } else {
                return delegate.getItemViewType(position);
            }
        } else if(hasCustomLast()) {
            if(position == getItemCount() - 1) {
                return TYPE_CUSTOM;
            } else {
                return delegate.getItemViewType(position);
            }
        } else {
            return delegate.getItemViewType(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return delegate.getItemId(position);
    }

    @Override
    public int getItemCount() {
        if(hasMore) {
            return delegate.getItemCount() + 1;
        } else {
            return delegate.getItemCount() + (hasCustomLast() ? 1 : 0);
        }
    }

    @Override
    public void onDataChanged() {
        syncData();
    }


    interface IBaseRecyclerAdapterController {
        /**
         * 点击重新加载
         */
        public void onReloadClicked();
    }

    /**
     * 末位标识的 ViewHolder
     */
    class HolderLastItem extends RecyclerView.ViewHolder {
        public View loading;
        public View error;

        public HolderLastItem(View itemView, final IBaseRecyclerAdapterController controller) {
            super(itemView);
            loading = itemView.findViewById(R.id.id_item_loading);
            error = itemView.findViewById(R.id.id_item_error);

            error.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    controller.onReloadClicked();
                }
            });
        }

        /**
         * 根据状态显示不同的ui
         * @param type
         */
        public void show(LastItemState type) {
            error.setVisibility(type == LastItemState.ERROR ? View.VISIBLE : View.GONE);
            loading.setVisibility(type == LastItemState.LOADING ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 最后一条数据的状态并刷新
     * @return
     */
    LastItemState getDataState() {
        return mLastItemState;
    }

    /**
     * 设置最后一条数据的状态并刷新
     * @param state
     */
    void setDataState(LastItemState state) {
        this.mLastItemState = state;
        notifyDataSetChanged();
    }

    class HolderCustomLast extends RecyclerView.ViewHolder {
        public HolderCustomLast(View itemView) {
            super(itemView);
        }
    }

    public void clear() {
        this.delegate.clear();
    }

    /**
     * 最后一条数据的类型
     */
    public enum LastItemState {
        ERROR,       //加载出错，无网，点击重试
        LOADING,    //加载中
        NONE        //没有更多数据
    }
}
