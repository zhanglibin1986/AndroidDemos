package com.zlb.demos.androiddemos.commens.list;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.commens
 * @date 16/8/13下午10:22
 * @Description
 */
public interface IResponseParser<T> {
    /**
     *
     * @param t 数据解析结果
     * @return
     */
    CommentListResponse parse(T t);
}
