package com.zlb.demos.androiddemos.base;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import com.zlb.demos.androiddemos.R;

/**
 * 提供了Toolbar,内容为fragment的Activity
 *
 * @author zhanglibin
 * @since 16/9/24 下午9:43
 */

public abstract class BaseFragmentActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_fragment);
        setupToolbar(toolbar);

        Fragment commenListFragment =
                getFragmentManager().findFragmentById(
                        R.id.container_base_fragment);

        if (commenListFragment == null) {
            commenListFragment = createFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.container_base_fragment, commenListFragment);
            transaction.commit();
        }
    }

    /**
     * 模板方法,设置标题栏
     */
    protected abstract void setupToolbar(Toolbar toolbar);

    /**
     * 内容Fragment
     * @return
     */
    protected abstract Fragment createFragment();
}
