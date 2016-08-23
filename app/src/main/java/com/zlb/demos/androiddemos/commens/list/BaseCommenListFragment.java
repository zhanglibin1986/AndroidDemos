package com.zlb.demos.androiddemos.commens.list;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.commens.ILoadLayoutController;
import com.zlb.demos.androiddemos.commens.ILoadLayoutUi;
import com.zlb.demos.androiddemos.commens.LoadLayoutUi;
import com.zlb.demos.androiddemos.commens.RxFragment;

import java.io.IOException;

import okhttp3.Response;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.commens
 * @date 16/8/13下午10:04
 * @Description
 */
public abstract class BaseCommenListFragment extends RxFragment implements OkRequestListener, RecyclerViewMoreManager.MoreRecyclerCallback, SwipeRefreshLayout.OnRefreshListener, ILoadLayoutController {
    private CommenRequestHelper requestHelper;
    private boolean isRequesting;// 是否正在请求数据
    private CommenListRequest mRequest;
    private CommenListRequest originRequest;
    private CommenListWrapAdapter wrapAdapter;
    protected View rootView;
    protected SwipeRefreshLayout mRefreshLayout;
    private ViewStub mViewStubContent;
    private ViewGroup mContentView;

    protected RecyclerView mRecyclerView;
    private RecyclerViewMoreManager mRecyclerViewMoreManager;

    private CommentListResponse parseResponse;
    protected ILoadLayoutUi mLoadLayoutUi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        rootView = getContentView(inflater, container);
        mViewStubContent = (ViewStub) rootView.findViewById(R.id.stub_content);
        mViewStubContent.setLayoutResource(getContentLayoutId());
        mContentView = (ViewGroup) mViewStubContent.inflate();
        mRefreshLayout = (SwipeRefreshLayout) mContentView.findViewById(R.id.swipe_refresh_layout);

        wrapAdapter = new CommenListWrapAdapter(getActivity(), initAdapter());
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerViewMoreManager = new RecyclerViewMoreManager(getActivity(), mRecyclerView, this);
        mRecyclerView.setAdapter(wrapAdapter);

        LoadLayoutUi.Builder builder = LoadLayoutUi.Builder.newInstance(getActivity(), this, (ViewGroup) rootView.findViewById(R.id.root_container), mContentView);
        if(getCustomEmptyLayoutId() != -1) {
            if(getCustomEmptyTextId() == -1) {
                throw new RuntimeException("must overide the getCustomEmptyTextId()");
            }
            builder.setCustomEmptyLayoutId(getCustomEmptyLayoutId(), getCustomEmptyTextId());
        }
        mLoadLayoutUi = builder.build();

        mRefreshLayout.setOnRefreshListener(this);
        return rootView;
    }

    protected int getCustomEmptyLayoutId() {
        return -1;
    }

    protected int getCustomEmptyTextId() {
        return -1;
    }

    protected abstract BaseCommenListAdapter initAdapter();

    /**
     * 覆盖该方法的话，必须包含base_recycler_default_list.xml中的内容，且id不能变
     * @return
     */
    protected int getContentLayoutId() {
        return R.layout.base_commen_list_refresh_list;
    }

    private void request(CommenListRequest request) {
        if(isRequesting) {
            return;
        }
        isRequesting = true;
        request.addListener(this);
        requestHelper = new CommenRequestHelper(request);
        requestHelper.execute();
    }

    @Override
    public void onOkFailure(CommenListRequest request, IOException e) {

    }

    @Override
    public void onOkResponse(CommenListRequest request, Response response) {

    }

    /**
     * 获取Fragment根View，自定义的layout文件中也应该包含base_recycler_fragment中的内容
     * @param inflater
     * @param container
     * @return
     */
    protected View getContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_commen_list, container, false);
    }

    @Override
    public boolean hasMore() {
        if(parseResponse != null) {
            return parseResponse.isHasMore();
        } else {
            return false;
        }
    }

    @Override
    public void loadMore() {
        request(mRequest);
    }

    @Override
    public boolean isRequesting() {
        return isRequesting;
    }

    public void requestData(CommenListRequest request) {
        if(isRequesting) {
            return;
        }
        try {
            originRequest = (CommenListRequest) request.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        mLoadLayoutUi.showLoading(wrapAdapter.getItemCount() <= 0);//已有数据，则loading框显示在内容上面
        request(request);
    }

    @Override
    public boolean isLastItemLoading() {
        return wrapAdapter.getDataState() == CommenListWrapAdapter.LastItemState.LOADING;
    }

    @Override
    public void onRefresh() {
        requestData(originRequest);
    }

    @Override
    public void refresh() {
        mLoadLayoutUi.showLoading(false);
        if(isRequesting) {
            return;
        }
        mRequest = originRequest;
        try {
            originRequest = (CommenListRequest) mRequest.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        request(mRequest);
    }
}
