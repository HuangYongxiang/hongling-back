package com.hl.core.lib.plugin.common;

import android.content.Context;

import com.hl.core.lib.network.ApiManager;
import com.hl.core.lib.plugin.CorePlugin;
import com.hl.core.lib.util.UtilManager;

/**
 * @Describe: 网络插件
 * @Package: com.hl.core.lib.plugin.common
 * @Author: liyu
 * @Date: 2018/1/4 10:44
 * @Copyright: hl
 */
public class NetworkPlugin extends CorePlugin{

    @Override
    public boolean initPlugin(Context context) {
        /*网络初始化工具*/
        ApiManager.getInstance().init(context.getApplicationContext());
        UtilManager.Log.d(TAG,"网络插件初始化成功");
        return true;
    }


}
