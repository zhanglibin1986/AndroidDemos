package com.zlb.demos.androiddemos.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.trello.rxlifecycle.components.RxFragment;

/**
 * Fragment基类
 *
 * @author jiwei
 * @since 16/9/9 下午4:18
 */
public abstract class BaseFragment extends RxFragment {
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = createView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    /**
     * 相当于{@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}, 不能再覆盖{@link
     * #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     */
    protected abstract View createView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState);

    /**
     * 初始化View
     */
    protected abstract void initView();

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
