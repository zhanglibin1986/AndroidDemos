package com.zlb.demos.androiddemos.html;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.Window;
import com.zlb.demos.androiddemos.base.BaseFragmentActivity;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/9/24 下午9:56
 */

public class HtmlActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition( new Explode() );
            getWindow().setExitTransition( new Explode() );
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupToolbar(Toolbar toolbar) {
        toolbar.setTitle("Html content");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    @Override
    protected Fragment createFragment() {
        return HtmlFragment.newInstance();
    }

}
