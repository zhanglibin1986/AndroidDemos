package com.zlb.demos.androiddemos.commens.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import com.google.gson.Gson;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.commens.ILoadLayoutController;
import com.zlb.demos.androiddemos.commens.ILoadLayoutUi;
import com.zlb.demos.androiddemos.commens.LoadLayoutUi;
import com.zlb.demos.androiddemos.utils.Logger;
import com.zlb.demos.androiddemos.utils.Util;
import java.io.IOException;
import java.util.List;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * 通用列表。使用方法可参见demo：{@link com.zlb.demos.androiddemos.commens.list.sample.SampleActivity}
 * @author zhanglibin
 * @since 16/9/12 下午6:26
 */
public abstract class BaseCommenListFragment extends com.trello.rxlifecycle.components.RxFragment implements RecyclerViewMoreManager.MoreRecyclerCallback, SwipeRefreshLayout.OnRefreshListener, ILoadLayoutController, CommenListWrapAdapter.IBaseRecyclerAdapterController {
    public static final String JSON_STATUS = "status";
    public static final String JSON_DEFAULT_STATUS = "0";
    public static final String JSON_MESSAGE = "message";
    public static final String JSON_DATA = "data";

    private CommenRequestHelper requestHelper;
    private boolean isRequesting;// 是否正在请求数据
    protected CommenListRequest mRequest;
    private CommenListRequest originRequest;
    private CommenListWrapAdapter wrapAdapter;
    protected View rootView;
    protected SwipeRefreshLayout mRefreshLayout;
    private ViewStub mViewStubContent;
    private ViewGroup mContentView;

    protected RecyclerView mRecyclerView;
    private RecyclerViewMoreManager mRecyclerViewMoreManager;

    private CommentListResponse parseResponse;
    private CommenListWrapResponse wrapResponse;
    protected ILoadLayoutUi mLoadLayoutUi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = getContentView(inflater, container);
        mViewStubContent = (ViewStub) rootView.findViewById(R.id.stub_content);
        mViewStubContent.setLayoutResource(getContentLayoutId());
        mContentView = (ViewGroup) mViewStubContent.inflate();
        mRefreshLayout = (SwipeRefreshLayout) mContentView.findViewById(R.id.swipe_refresh_layout);

        wrapAdapter = new CommenListWrapAdapter(getActivity(), initAdapter(), this);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerViewMoreManager = new RecyclerViewMoreManager(getActivity(), mRecyclerView, initSpanCount(), this);
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

    protected int initSpanCount() {
        return 1;
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

    private void request(final CommenListRequest request) {
        if(isRequesting) {
            return;
        }
        isRequesting = true;
//        request.addListener(this);
        requestHelper = new CommenRequestHelper(request);
//        requestHelper.execute();
        Observable<Response> observable = requestHelper.rxExecute();
        log("-------------------------------- request");
        observable.subscribeOn(Schedulers.io())
                .compose(this.<Response>bindUntilEvent(FragmentEvent.STOP))
                .map(new Func1<Response, String>() {
                    @Override
                    public String call(Response response) {
                        log("map1");
                        String json = null;
                        try {
                            json = response.body().string();
                        } catch (IOException e) {
                            // 解析数据出错
                            onOkFailure(request, e);
                        }

                        if(request.getListeners() != null && !request.getListeners().isEmpty()) {
                            for(OkRequestListener listener : request.getListeners()) {
                                listener.onOkResponse(request, response);
                            }
                        }
                        return json;
                    }
                })
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        if(s == null || TextUtils.isEmpty(s)) {
                            onOkFailure(request, new Exception());
                            return false;
                        } else {
                            return true;
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        if(request.getCommenRequestListeners() != null && !request.getCommenRequestListeners().isEmpty()) {
                            for(CommenRequestListener listener : request.getCommenRequestListeners()) {
                                listener.onSuccess(request, s);
                            }
                        }

                        log("doonNext okhttp success");
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<String, JSONObject>() {
                    @Override
                    public JSONObject call(String s) {
                        log("map String to JsonObject ");
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            parseResponse = new CommentListResponse();
                            wrapResponse = new CommenListWrapResponse();
                            wrapResponse.setStatus(jsonObject.has(JSON_STATUS) ? jsonObject.getString(JSON_STATUS) : JSON_DEFAULT_STATUS);
                            if(jsonObject.has(JSON_MESSAGE)) {
                                String message = jsonObject.getString(JSON_MESSAGE);
                                if(!TextUtils.isEmpty(message)) {
                                    wrapResponse.setMessage(message);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        return jsonObject;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<JSONObject, Boolean>() {
                    @Override
                    public Boolean call(JSONObject jsonObject) {
                        log("filter");
                        boolean statusCorrect = "0".equals(wrapResponse.getStatus());
                        if(!statusCorrect) {
                            if(!TextUtils.isEmpty(wrapResponse.getMessage())) {
                                Util.showToast(getActivity(), wrapResponse.getMessage());
                            } else {
                                Util.showToast(getActivity(), getString(R.string.http_error_netfailed));
                            }
                        }
                        return statusCorrect;
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<JSONObject, List>() {
                    @Override
                    public List call(JSONObject jsonObject) {
                        log("map JSONObject 2 List");
                        List list = null;
                        IResponseParser parser = request.getResponseParser();
                        JSONObject data = null;
                        try {
                            if(jsonObject.has(JSON_DATA)) {//json数据中含有data字段的情况
                                data = jsonObject.getJSONObject(JSON_DATA);
                            } else {
                                data = jsonObject;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if(data != null && request.getResultObject() != null) {
                            Object result =  new Gson().fromJson(data.toString(), request.getResultObject());
                            if(parser != null) {
                                parseResponse = request.getResponseParser().parse(result);
                            } else {
                                parseResponse = (CommentListResponse) result;
                            }
                            list = parseResponse.getData();
                            wrapResponse.setResponse(parseResponse);
                        }

                        return list;
                    }
                })

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Subscriber<List>() {
                            @Override
                            public void onCompleted() {
                                log("onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                log("onError");
                                onOkFailure(request, new Exception(e));
                            }

                            @Override
                            public void onNext(List list) {
                                log("onNext");
                                onOkResponse(request, list);
                            }
                        }
                );
    }


    public void onOkResponse(CommenListRequest request, final List listDatas) {
        if (getActivity() == null || getActivity().isFinishing() || !isAdded()) {
            return;
        }
        boolean isNeedTriggerScroll = false; // 是否需要触发手动滚动
        if (listDatas != null) {//解析出了数据，有可能为空
            mLoadLayoutUi.showData();
            request.setHasMoreParam(parseResponse.isHasMore());
            request.setNextStartValue(parseResponse.getNextStart());
            if (wrapAdapter.getItemCount() <= 0) {//列表中没有数据
                if (listDatas.isEmpty()) {//请求结果为空
                    showEmpty();
                } else {
                    wrapAdapter.initData(parseResponse);
                }
            } else {//列表中已有数据
                if (listDatas.isEmpty()) {//请求到的结果为空的
                    //提示没有加载到更多数据？
                    if (request.isInit()) {
                        showEmpty();
                    }
                } else {
                    if (request.isInit()) {//重置数据
                        mRecyclerView.stopScroll();
                        wrapAdapter.initData(parseResponse);
                        // 列表有数据，并且数据很少的时候，会显示出加载更多的loading，RecyclerView不会触发判断加载更多，需要手动触发。
                        isNeedTriggerScroll = true;
                        mRecyclerView.scrollToPosition(0);
                    } else {//加载更多
                        Log.d("loading", "wrapAdapter.addData");
                        wrapAdapter.addData(parseResponse);
                    }
                }
            }
            mRefreshLayout.setRefreshing(false);
            request.setInit(false);
        } else {//解析数据为null
            Log.d("commen", "解析数据为null");
            failed(request);
        }
        isRequesting = false;
        if (isNeedTriggerScroll) {
            // 手动判断加载更多
            mRecyclerViewMoreManager.scrolledToLoadMore(0);
        }
    }


    private void log(String log) {
        if(false) {
            Logger.d("common", log + " , thread name = " + new Thread().getName());
        }
    }

    public void onOkFailure(final CommenListRequest request, Exception e) {
        if(getActivity() == null || getActivity().isFinishing() || !isAdded()) {
            return;
        }
//        mRequest.removeListener(this);
        isRequesting = false;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                failed(request);
            }
        });
    }

    /**
     * 解析失败
     * @param mRequest
     */
    private void failed(CommenListRequest mRequest) {
        mRefreshLayout.setRefreshing(false);
        if(wrapAdapter.getItemCount() > 0){
            mLoadLayoutUi.showData();
            Util.showToast(getActivity().getApplicationContext(), getResources().getString(R.string.http_error_netfailed));
        } else if(mRequest.isInit()) {
            wrapAdapter.clear();
            showError();
        }
        if(mRequest.getCommenRequestListeners() != null && !mRequest.getCommenRequestListeners().isEmpty()) {
            for(CommenRequestListener listener : mRequest.getCommenRequestListeners()) {
                listener.onFailed(mRequest);
            }
        }
        wrapAdapter.failRequest();
    }

    /**
     * 显示空布局
     */
    protected void showEmpty() {
        mLoadLayoutUi.showEmpty();
    }

    /**
     * 显示错误布局
     */
    private void showError() {
        mLoadLayoutUi.showError();
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
            mRequest = request;
            originRequest = (CommenListRequest) request.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        if(wrapAdapter.getDelegateAdapter().getItemCount() <= 0) {
            mLoadLayoutUi.showLoading(false);//已有数据，则loading框显示在内容上面
        }
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

    @Override
    public void onReloadClicked() {
        wrapAdapter.setDataState(CommenListWrapAdapter.LastItemState.LOADING);
        request(mRequest);
    }
}
