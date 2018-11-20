package com.hl.core.lib.inter;

/**
 * @Describe:刷新 UI
 * @Package: com.hl.core.lib.inter
 * @Author: liyu
 * @Date: 2018/1/9 14:42
 * @Copyright: hl
 */

public interface IBaseViewListener<T> {
    /**
     * 开始加载
     *
     * @param loadType 加载的类型
     */
    void loadStart(int loadType);

    /**
     * 加载完成
     */
    void loadComplete();

    /**
     * 加载失败
     *
     * @param message
     */
    void loadFailure(String message);

    /**
     * 加载数据成功
     * @param data
     */
    void loadSuccess(T data,String requestType);

}
