package com.hl.core.lib.inter;

/**
 * @Describe:网络加载
 * @Package: com.hl.core.lib.inter
 * @Author: liyu
 * @Date: 2018/1/9 14:42
 * @Copyright: hl
 */

public interface BaseLoadListener<T> {
    /**
     * 加载数据成功
     * @param data
     */
    void loadSuccess(T data,String requestType);

    /**
     * 加载失败
     *
     * @param message
     */
    void loadFailure(String message);

    /**
     * 开始加载
     */
    void loadStart();

    /**
     * 加载结束
     */
    void loadComplete();
}
