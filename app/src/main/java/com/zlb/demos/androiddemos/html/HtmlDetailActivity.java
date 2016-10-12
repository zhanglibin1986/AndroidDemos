package com.zlb.demos.androiddemos.html;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.zlb.demos.androiddemos.base.BaseFragmentActivity;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/9/25 下午1:29
 */

public class HtmlDetailActivity extends BaseFragmentActivity {

    public static void launch(Activity activity, String url) {
        Intent intent = new Intent(activity, HtmlDetailActivity.class);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }

    @Override
    protected void setupToolbar(Toolbar toolbar) {
        toolbar.setVisibility(View.GONE);
    }

    @Override
    protected Fragment createFragment() {
        return HtmlDetailFragment.newInstance(getIntent().getStringExtra("url"));
    }
}
