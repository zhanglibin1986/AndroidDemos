package com.zlb.demos.androiddemos.commens;

/**
 * @author zhanglibin
 * @Project: BreadTripAndroid
 * @Package com.breadtrip.view
 * @date 16/4/9下午5:13
 * @Description
 */
public interface ILoadLayoutUi {
    /**
     * 显示隐藏loading框
     * @param isShowContent 显示loading框的时候，是否显示下面的内容
     */
    public void showLoading(boolean isShowContent);

    /**
     *  显示错误布局
     */
    public void showError();

    /**
     * 显示错误布局文本
     * @param text 文本
     */
    public void showError(String text);
    /**
     *  显示空布局
     */
    public void showEmpty();

    /**
     * 显示空布局自定义文本
     * @param text 文本
     */
    public void showEmpty(String text);

    /**
     * 隐藏所有loading布局
     */
    public void showData();

    public void setEmptyText(String text);

    public void setErrorText(String text);

    public void showAllEmpty();

    public enum State {
        NORMAL, LOADING, EMPTY, ERROR
    }
}
