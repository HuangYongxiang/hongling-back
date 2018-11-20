package com.hl.contract.core;

import com.hl.core.lib.CoreApplication;
import com.hl.core.lib.plugin.PluginManager;
import com.hl.contract.core.plugin.IflyPlugin;
import com.hl.contract.core.plugin.SurveyTitleBarPlugin;

/**
 * @Describe: Application
 * @Package: com.hl.contract.core
 * @Author: liyu
 * @Date: 2018/3/13 15:48
 * @Copyright: hl
 */
public class SurveyApplication extends CoreApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    protected void registerPlugin(PluginManager pluginManager) {
        pluginManager.registerPlugin(new SurveyTitleBarPlugin())
                .registerPlugin(new IflyPlugin());
    }
}
