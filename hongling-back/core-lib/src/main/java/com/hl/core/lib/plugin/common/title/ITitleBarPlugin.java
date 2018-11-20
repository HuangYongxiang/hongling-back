package com.hl.core.lib.plugin.common.title;

import android.view.View;
import android.view.ViewGroup;

import com.hl.core.lib.activity.CoreActivity;

/**
 * @Describe: 标题栏插件接口
 * @Package: com.hl.core.lib.plugin.common.title
 * @Author: liyu
 * @Date: 2018/1/3 12:59
 * @Copyright: hl
 */
public interface ITitleBarPlugin {

    /**
     * 绑定标题栏
     * @param titleBar 标题栏
     */
    void bindTitleBar(ITitleBar titleBar);

    /**
     * 初始化标题栏视图
     * @param context 上下文
     * @param onClickListener 点击监听
     */
    ViewGroup initTitleView(CoreActivity context, View.OnClickListener onClickListener);

    /**
     * 是否显示标题栏
     */
    boolean hasTitleBar();

}
