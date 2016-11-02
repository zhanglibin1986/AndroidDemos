package com.zlb.demos.androiddemos.commens.list;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.commens.list
 * @date 16/8/22上午11:55
 * @Description
 */
public class RecyclerViewMoreManager {
    private RecyclerView mRecyclerView;
    private GridLayoutManager layoutManager;
    private MoreRecyclerCallback callback;
    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            scrolledToLoadMore(dy);
        }
    };

    /**
     * 滑动判断是否触发加载更多
     * @param dy y轴偏移
     */
    public void scrolledToLoadMore(int dy) {
        int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
        int totalItemCount = layoutManager.getItemCount();
        if (lastVisibleItem >= totalItemCount - 1 && dy >= 0) {
            if (callback.hasMore() && !callback.isRequesting() && callback.isLastItemLoading()) {
                callback.loadMore();
            }
        }
    }



    public RecyclerViewMoreManager(Activity activity, @NonNull RecyclerView recyclerView, int spanCount, MoreRecyclerCallback callback) {
        mRecyclerView = recyclerView;
        layoutManager = new GridLayoutManager(activity, spanCount);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = mRecyclerView.getAdapter().getItemViewType(position);
                if(type == CommenListWrapAdapter.TYPE_CUSTOM || type == CommenListWrapAdapter.TYPE_LAST) {
                    return spanCount;
                } else {
                    return 1;
                }
            }
        });

        mRecyclerView.setLayoutManager(layoutManager);
        this.callback = callback;
        mRecyclerView.addOnScrollListener(scrollListener);
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public interface MoreRecyclerCallback {
        /**
         * 是否正在加载数据
         *
         * @return
         */
        boolean isRequesting();

        /**
         * 是否还有更多数据加载
         *
         * @return
         */
        boolean hasMore();

        /**
         * 加载更多
         */
        void loadMore();

        /**
         * 最后一条是否是加载更多的布局
         * @return
         */
        boolean isLastItemLoading();
    }
}
