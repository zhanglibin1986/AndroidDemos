package com.zlb.demos.androiddemos.commens.keyboard;

/**
 * 软键盘弹出或收起的监听
 *
 * @author zhanglibin
 * @since 16/10/11 下午2:56
 */

public interface InputWindowListener {
    /**
     * 键盘弹出
     */
    void onSoftInputShow();

    /**
     * 键盘收起
     */
    void onSoftInputHide();
}
