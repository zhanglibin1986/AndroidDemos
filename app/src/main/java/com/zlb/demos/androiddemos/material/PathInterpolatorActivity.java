package com.zlb.demos.androiddemos.material;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.PathInterpolator;
import butterknife.BindView;
import butterknife.OnClick;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;
/**
 *  https://developer.android.com/training/material/animations.html
 *  看看这个就明白PathInterpolator了。https://github.com/kenaiX/snowball/tree/master/PathInterpolator%E7%AE%80%E4%BB%8B
 * @author zhanglibin
 * @since 16/9/6 下午3:29
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class PathInterpolatorActivity extends BaseActivity {
    @BindView(R.id.backgroundView) protected PathInterpolatorView bgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_interpolator);
    }

    @OnClick(R.id.button1)
    public void onButton1Clicked(View view) {
        anim(view);
    }


    private void anim(View view) {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.X,  view.getX() + 200);
//        ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.X,  View.Y, createPath(view));

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.X,  View.Y, createPath(view));
        animator.setDuration(1000);
        animator.setInterpolator(new PathInterpolator(0.60f, 0f, 0.4f, 1f));//修改这个值试试看，速度变化可以从http://cubic-bezier.com/#.17,.67,.83,.67 这个网址直观感受
        animator.start();
    }

    private Path createPath(View view) {
        int x = view.getLeft();
        int y = view.getTop();

        float controllX = x - 200;
        float controllY = y - 100;

        Path path = new Path();
        path.moveTo(x, y);
        path.quadTo(controllX, controllY, x - 300, y - 500);

        bgView.setPath(path);
        bgView.invalidate();

        return path;
    }
}
