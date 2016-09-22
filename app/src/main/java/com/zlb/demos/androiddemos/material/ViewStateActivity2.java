package com.zlb.demos.androiddemos.material;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;
import com.zlb.demos.androiddemos.utils.Util;

import butterknife.OnClick;

public class ViewStateActivity2 extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_state);
    }

    @OnClick(R.id.text1)
    public void onTextView1Clicked(View view) {
        Util.showToast(this, "textview 1 is clicked");
    }
}
