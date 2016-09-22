package com.zlb.demos.androiddemos.apidemos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;


/**
 * https://segmentfault.com/a/1190000000721127
 * @author zhanglibin
 * @since 16/9/6 下午2:14
 */
public class PathActivity extends BaseActivity {
    @BindView(R.id.path_view) protected PathView pathView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        pathView.invalidate();
    }


}
