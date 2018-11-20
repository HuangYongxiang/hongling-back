package com.hl.contract.business.main.adapter;

/**
 * @Describe:
 * @Author: Administrator
 * @Date: 2018/1/16 0016 下午 15:10
 * @Copyright: hl
 */

public interface IAdpaterBackCallListener<T> {
    void callBack(int position, T t);
}
