package com.hl.core.lib;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.hl.core.lib.plugin.PluginManager;
import com.hl.core.lib.plugin.common.CatchLogPlugin;
import com.hl.core.lib.plugin.common.MonitorPlugin;
import com.hl.core.lib.plugin.common.NetworkPlugin;
import com.hl.core.lib.plugin.theme.ThemePlugin;
import com.hl.core.lib.plugin.title.TitleBarPlugin;
import com.hl.core.lib.util.UtilManager;

/**
 * @Describe: Application 核心类
 * 1、MultiDex
 * 2、插件管理
 * 3、全局上下文
 * @Package: com.hl.core.lib
 * @Author: liyu
 * @Date: 2018/1/2 11:03
 * @Copyright: hl
 */
public abstract class CoreApplication extends MultiDexApplication {

    static CoreApplication coreApplication;

    protected final String TAG = getClass().getSimpleName();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        coreApplication = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        UtilManager.Log.d(TAG,"Application已启动");
        //初始化插件管理
        initPluginManager().init(getApplicationContext(),false);
    }

    /**
     * 初始化插件管理
     */
    private PluginManager initPluginManager(){
        PluginManager pluginManager = PluginManager.get();
        //注册核心插件
        pluginManager.registerPlugin(new MonitorPlugin(),false)
                .registerPlugin(new TitleBarPlugin())
                .registerPlugin(new ThemePlugin())
                .registerPlugin(new NetworkPlugin(),true)
                .registerPlugin(new CatchLogPlugin(),true);
        //注册App插件
        registerPlugin(pluginManager);
        return pluginManager;
    }

    /**
     * 注册插件
     * @param pluginManager 插件管理
     */
    protected abstract void registerPlugin(PluginManager pluginManager);

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    public static CoreApplication get(){
        return coreApplication;
    }

}
