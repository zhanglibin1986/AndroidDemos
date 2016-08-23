package com.zlb.demos.androiddemos.commens.list;

import java.io.IOException;

import okhttp3.Response;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.commens.list
 * @date 16/8/22上午10:44
 * @Description
 */
public interface OkRequestListener {
    void onOkFailure(CommenListRequest request, IOException e);

    void onOkResponse(CommenListRequest request, Response response);
}
