package com.zlb.demos.androiddemos.commens.list;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.commens.list
 * @date 16/8/23下午7:11
 * @Description
 */
interface CommenRequestListener {
    void onSuccess(CommenListRequest request, String json);
    void onFailed(CommenListRequest request);
}
