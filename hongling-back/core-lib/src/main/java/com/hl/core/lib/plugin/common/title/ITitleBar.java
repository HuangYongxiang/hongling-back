package com.hl.core.lib.plugin.common.title;

import android.view.View;
import android.view.ViewGroup;

import com.hl.core.lib.activity.CoreActivity;

/**
 * @Describe: 标题栏接口
 * @Package: com.hl.core.lib.plugin.common.title
 * @Author: liyu
 * @Date: 2018/1/4 17:26
 * @Copyright: hl
 */
public interface ITitleBar {

    /**
     * 初始化
     */
    void init();

    /**
     * 绑定标题栏插件
     */
    Class bindPlugin();

    /**
     * 初始化标题栏视图
     * @param context 上下文
     * @param parentView 父类容器
     * @param onClickListener 点击监听
     */
    void initView(CoreActivity context, ViewGroup parentView, View.OnClickListener onClickListener);

    /**
     * 标题栏更新
     */
    void updateTitle();

    /**
     * 销毁并释放资源
     */
    void destroy();

}
