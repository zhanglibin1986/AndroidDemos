package com.zlb.demos.androiddemos.material;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.OnClick;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;
/**
 * https://developer.android.com/reference/android/graphics/Path.html
 * @author zhanglibin
 * @since 16/9/6 上午11:36
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class CurvedMotionActivity extends BaseActivity {
    @BindView(R.id.button1) protected Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curved_motion);
    }

    @OnClick(R.id.button1)
    public void onButton1Clicked(View view) {
        anim(view);
    }

    private Path createPath() {
        Path path = new Path();

//        path.addCircle(0, 0, 300, Path.Direction.CW);
//        path.ad
        return path;
    }


    public void anim(View view) {
        ObjectAnimator mAnimator;
        mAnimator = ObjectAnimator.ofFloat(view, View.X, View.Y, createPath());
        mAnimator.setDuration(2000);
        mAnimator.start();
    }
    
}
