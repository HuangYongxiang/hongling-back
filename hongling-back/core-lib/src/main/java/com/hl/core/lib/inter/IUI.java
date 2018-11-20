package com.hl.core.lib.inter;

/**
 * @Describe:
 * @Package: com.hl.core.lib.inter
 * @Author: liyu
 * @Date: 2018/1/15 14:39
 * @Copyright: hl
 */
public interface IUI {
    /**
     * 初始化view属性，在此之前已通过ButterKnife初始化view
     * @Title: initViewProperty
     * @return void
     * @date Apr 18, 2014 11:00:56 AM
     */
    void initViewProperty();

    /**
     * 初始化数据请求相关
     * @Title: initData
     * @return void
     * @date Apr 24, 2014 10:38:59 AM
     */
    void initData();
}
