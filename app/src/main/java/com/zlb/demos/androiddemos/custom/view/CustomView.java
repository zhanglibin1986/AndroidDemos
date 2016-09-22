package com.zlb.demos.androiddemos.custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/9/10 上午9:01
 */
public class CustomView extends ViewGroup {

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }
}

