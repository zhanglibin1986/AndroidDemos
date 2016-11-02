package com.zlb.demos.androiddemos.html.mmjpg;

import android.app.Fragment;
import android.support.v7.widget.Toolbar;
import com.zlb.demos.androiddemos.base.BaseFragmentActivity;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/10/26 下午12:40
 */

public class MmActivity extends BaseFragmentActivity{


    @Override
    protected void setupToolbar(Toolbar toolbar) {
        toolbar.setTitle("Mmjpg");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    @Override
    protected Fragment createFragment() {
        return MmFragment.newInstance();
    }
}
