package com.zlb.demos.androiddemos.commens;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import com.zlb.demos.androiddemos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 请使用{@link Builder}来是获取LoadLayoutUi的实例
 *
 * @author zhanglibin
 * @Project: BreadTripAndroid
 * @Package com.breadtrip.view
 * @date 16/4/9下午5:25
 */
public class LoadLayoutUi implements ILoadLayoutUi {
    private View mRootView;
    private ILoadLayoutController mController;
    private View mLoadingView; //加载view

    private View mEmptyView; //空布局
    private View mErrorView; //错误布局
    private View mContentView; //内容布局
    private Button mRefreshButton;//刷新按钮
    private TextView mEmptyText; //空布局文案
    private TextView mErrorText; //错误布局文案
    private List<View> mViews; //能显示的View的集合
    private State mState = State.NORMAL;//当前显示状态
    private String mEmptyTextStr;
    private String mErrorTextStr;


    private Builder mBuilder;

    /**
     * 私有构造器，避免其他形式的实例化
     * @param builder
     */
    private LoadLayoutUi(Builder builder) {
        this.mBuilder = builder;
        mRootView = LayoutInflater.from(mBuilder.getContext()).inflate(R.layout.base_loading_fragment, mBuilder.getContainer());
        mController = mBuilder.getController();
        this.mContentView = mBuilder.getContentView();
        mViews = new ArrayList<>();
        initViewsList();
    }

    private void initViewsList() {
        mViews.clear();
        mViews.add(mContentView);
    }

    @Override
    public void showLoading(boolean isShowContent) {
        if (mLoadingView == null) {
            mLoadingView = getViewFromStub(R.id.stub_loading, mBuilder.getLoadingLayoutId());
            mViews.add(mLoadingView);
            mLoadingView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ;
                }
            });
        }
        if (isShowContent) {
            showView(mViews, mLoadingView, mContentView);
        } else {
            showView(mViews, mLoadingView);
        }
        mState = State.LOADING;
    }

    @Override
    public void showError() {
        if (mErrorView == null) {
            mErrorView = getViewFromStub(R.id.stub_error, mBuilder.getLoadingErrorLayoutId());
            mViews.add(mErrorView);
            int btnId = mBuilder.getCustomRetryBtnId();
            mRefreshButton = (Button) mErrorView.findViewById(btnId == 0 ? R.id.btn_refresh : btnId);
            int errorTextId = mBuilder.getCustomErrorText();
            mErrorText = (TextView) mErrorView.findViewById(errorTextId == 0 ? R.id.loading_error_text : errorTextId);
            if(mRefreshButton != null) {
                mRefreshButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mController.refresh();
                    }
                });
            }
        }
        showView(mViews, mErrorView);
        if(!TextUtils.isEmpty(mErrorTextStr)) {
            mErrorText.setText(mErrorTextStr);
        }
        mState = State.ERROR;
    }

    @Override
    public void showError(String text) {
        showError();
        if(!TextUtils.isEmpty(text)) {
            mErrorText.setText(text);
        }
    }

    @Override
    public void showEmpty() {
        if (mEmptyView == null) {
            mEmptyView = getViewFromStub(R.id.stub_empty, mBuilder.getLoadingEmptyLayoutId());
            int emptyTextId = mBuilder.getCustomEmptyText();
            mEmptyText = (TextView) mEmptyView.findViewById(emptyTextId == 0 ? R.id.loading_empty_text : emptyTextId);
            mViews.add(mEmptyView);
        }
        showView(mViews, mEmptyView);
        if(!TextUtils.isEmpty(mEmptyTextStr)) {
            mEmptyText.setText(mEmptyTextStr);
        }
        mState = State.EMPTY;
    }

    @Override
    public void showEmpty(String text) {
        showEmpty();
        if(!TextUtils.isEmpty(text)) {
            mEmptyText.setText(text);
        }
    }

    @Override
    public void showData() {
        showView(mViews, mContentView);
        mState = State.NORMAL;
    }

    @Override
    public void setEmptyText(String text) {
        mEmptyTextStr = text;
    }

    @Override
    public void setErrorText(String text) {
        mErrorTextStr = text;
    }

    @Override
    public void showAllEmpty() {
        showView(mViews, null);
        mState = State.EMPTY;
    }

    public State getState() {
        return mState;
    }

    private View getViewFromStub(int stubId, int customLayoutId) {
        ViewStub viewStub = (ViewStub) mRootView.findViewById(stubId);
        if (customLayoutId != 0) {
            viewStub.setLayoutResource(customLayoutId);
        }
        if (viewStub == null) {
            throw new RuntimeException("Cann't find the load layout ViewStub id = " + (customLayoutId != 0 ? customLayoutId : stubId));
        }
        return viewStub.inflate();
    }

    /**
     * 显示其中的一个View，隐藏其他（如果不为空）
     *
     * @param views
     * @param view
     */
    @NonNull
    private void showView(List<View> views, View view) {
        for (int i = 0; i < views.size(); i++) {
            View v = views.get(i);
            if(v != null && v != view) {
                v.setVisibility(View.GONE);
            }
        }
        if(view != null) {
            view.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 显示其中的2个View，隐藏其他（如果不为空）
     *
     * @param views
     * @param view1
     * @param view2
     */
    @NonNull
    private void showView(List<View> views, View view1, View view2) {
        for (int i = 0; i < views.size(); i++) {
            View v = views.get(i);
            if (v == view1 || v == view2) {
                v.setVisibility(View.VISIBLE);
            } else if (v != null) {
                v.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 请使用{@link Builder#newInstance(Context, ILoadLayoutController, ViewGroup, View)} 来获取实例。
     */
    public static class Builder {
        private Context mContext;
        private ViewGroup container;
        private ILoadLayoutController mController;
        private View mContentView;

        private int mLoadingLayoutId;      //自定义加载中布局id
        private int mLoadErrorLayoutId; //自定义错误布局id
        private int mLoadEmptyLayoutId; //自定义为空布局id
        private int mCustomRetryBtnId;     //自定义错误布局重试按钮view id
        private int mCustomEmptyText; //自定义空布局文案提示view id
        private int mCustomErrorText; //自定义错误布局文案提示view id

        /**
         *
         * @param context
         * @param controller UI的回调接口
         * @param container 承载加载布局的container，就是说要在哪个区域显示这三层加载布局
         * @param contentView 数据view，显示这三层加载布局时会隐藏
         */
        private Builder(Context context, ILoadLayoutController controller, ViewGroup container, View contentView) {
            mContext = context;
            this.container = container;
            mController = controller;
            mContentView = contentView;
        }
        /**
         *
         * @param context
         * @param controller UI的回调接口
         * @param container 承载加载布局的container，就是说要在哪个区域显示这三层加载布局
         * @param contentView 数据view，显示这三层加载布局时会隐藏
         */
        public static Builder newInstance(Context context, ILoadLayoutController controller, ViewGroup container, View contentView) {
            return new Builder(context, controller, container, contentView);
        }

        /**
         * 更换加载中的布局
         *
         * @param layoutId 自定义loading布局layout id
         */
        public Builder setCustomLoadingLayoutId(int layoutId) {
            this.mLoadingLayoutId = layoutId;
            return this;
        }

        /**
         * 更换加载的错误布局
         *
         * @param layoutId   布局id 自定义错误布局layout id
         * @param textViewId 显示的文案view id
         * @param retryBtnId 刷新重试按钮的view的id
         */
        public Builder setCustomErrorLayoutId(int layoutId, int textViewId, int retryBtnId) {
            this.mLoadErrorLayoutId = layoutId;
            this.mCustomRetryBtnId = retryBtnId;
            this.mCustomErrorText = textViewId;
            return this;
        }

        /**
         * 更换加载空的布局
         *
         * @param layoutId   自定义空布局layout id
         * @param textViewId 显示的文案view id
         */
        public Builder setCustomEmptyLayoutId(int layoutId, int textViewId) {
            this.mLoadEmptyLayoutId = layoutId;
            this.mCustomEmptyText = textViewId;
            return this;
        }

        public int getLoadingLayoutId() {
            return mLoadingLayoutId;
        }

        public int getLoadingErrorLayoutId() {
            return mLoadErrorLayoutId;
        }

        public int getLoadingEmptyLayoutId() {
            return mLoadEmptyLayoutId;
        }

        public int getCustomRetryBtnId() {
            return mCustomRetryBtnId;
        }

        public int getCustomEmptyText() {
            return mCustomEmptyText;
        }

        public View getContentView() {
            return mContentView;
        }

        public ILoadLayoutController getController() {
            return mController;
        }

        public ViewGroup getContainer() {
            return container;
        }

        public int getCustomErrorText() {
            return mCustomErrorText;
        }

        public Context getContext() {
            return mContext;
        }

        public LoadLayoutUi build() {
            return new LoadLayoutUi(this);
        }
    }
}

