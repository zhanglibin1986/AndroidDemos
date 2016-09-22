package com.zlb.demos.androiddemos.material;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;
import com.zlb.demos.androiddemos.utils.Util;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * https://developer.android.com/training/material/animations.html#ViewState
 *
 * @author zhanglibin
 * @since 16/9/6 上午10:28
 */
public class ViewStateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_state);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.text1)
    public void onTextView1Clicked(View view) {
        Util.showToast(this, "textview 1 is clicked");
    }
}
