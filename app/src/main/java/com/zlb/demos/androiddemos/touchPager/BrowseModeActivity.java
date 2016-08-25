package com.zlb.demos.androiddemos.touchPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;

import butterknife.Bind;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.touchPager
 * @date 16/8/25下午3:50
 * @Description
 */
public class BrowseModeActivity extends BaseActivity {
    @Bind(R.id.image_pager) protected ViewPager navigationGallery;
    @Bind(R.id.btnBack) protected ImageButton btnBack;
    @Bind(R.id.btnPlay) protected ImageButton btnPlay;
    @Bind(R.id.tvTitle) protected TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_mode_activity);

    }

    private void initViews() {
        navigationGallery.setPageMargin(getResources().getDimensionPixelSize(R.dimen.pagesMargin));

    }


}
