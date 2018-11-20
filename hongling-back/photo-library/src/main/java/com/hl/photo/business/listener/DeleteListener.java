package com.hl.photo.business.listener;

/**
 * Created by chenxuefei on 2017/2/14.
 */

public interface DeleteListener {
    /**
     * @param data 要删除的数据
     * @param dataType 表示删除的数据的类型  1驾驶员信息 2三者车信息 3人伤信息 4物损信息
     * @param <T>
     */
    public <T> void deleteData(T data, int dataType);
}
