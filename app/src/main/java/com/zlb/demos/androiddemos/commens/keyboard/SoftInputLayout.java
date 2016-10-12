package com.zlb.demos.androiddemos.commens.keyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * 监听键盘弹出或收起
 *
 * @author zhanglibin
 * @since 16/10/11 下午2:52
 */

public class SoftInputLayout extends RelativeLayout {
    private InputWindowListener listener;

    public SoftInputLayout(Context context) {
        super(context);
    }

    public SoftInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(oldh > h) {

            if(listener != null) {
                listener.onSoftInputShow();
            }
        } else {
            if(listener != null) {
                listener.onSoftInputHide();
            }
        }
    }

    public void setSoftInputListener(InputWindowListener listener) {
        this.listener = listener;
    }

}
