package com.zlb.demos.androiddemos.testdemos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;
import butterknife.BindView;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;
import com.zlb.demos.androiddemos.commens.keyboard.InputWindowListener;
import com.zlb.demos.androiddemos.commens.keyboard.SoftInputLayout;
import com.zlb.demos.androiddemos.utils.Util;

public class TestSoftInputActivity extends BaseActivity implements InputWindowListener {

    @BindView(R.id.sign_edit_phone) protected EditText editText;
    @BindView(R.id.activity_test_soft_input) protected SoftInputLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_soft_input);

        rootView.setSoftInputListener(this);
    }

    @Override
    public void onSoftInputShow() {
        Util.showToast(this, "onSoftInputShow");
    }

    @Override
    public void onSoftInputHide() {
        Util.showToast(this, "onSoftInputHide");
    }
}
