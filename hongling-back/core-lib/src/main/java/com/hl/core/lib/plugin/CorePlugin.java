package com.hl.core.lib.plugin;

/**
 * @Describe: 插件基类
 * @Package: com.hl.core.lib.plugin
 * @Author: liyu
 * @Date: 2018/1/3 15:52
 * @Copyright: hl
 */
public abstract class CorePlugin implements IPlugin{

    public final String TAG = getClass().getSimpleName();

    public CorePlugin() {
    }

}
