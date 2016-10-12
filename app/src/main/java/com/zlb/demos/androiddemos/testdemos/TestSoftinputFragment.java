package com.zlb.demos.androiddemos.testdemos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseFragment;
import com.zlb.demos.androiddemos.commens.keyboard.InputWindowListener;
import com.zlb.demos.androiddemos.commens.keyboard.SoftInputLayout;
import com.zlb.demos.androiddemos.utils.Util;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/10/11 下午4:36
 */

public class TestSoftinputFragment extends BaseFragment implements InputWindowListener {

    @BindView(R.id.fragment_test_soft_input) protected SoftInputLayout rootView;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_softinput, container, false);
    }

    @Override
    protected void initView() {
        rootView.setSoftInputListener(this);
    }

    @Override
    public void onSoftInputShow() {
        Util.showToast(getActivity(), "fragment onSoftInputShow");
    }

    @Override
    public void onSoftInputHide() {
        Util.showToast(getActivity(), "fragment onSoftInputHide");
    }
}
