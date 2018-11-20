package com.hl.core.lib.plugin.common;

import android.content.Context;

import com.hl.core.lib.plugin.CorePlugin;
import com.hl.core.lib.util.common.CrashHandler;


/**
 * @Describe:
 * @Package: com.hl.core.lib.plugin.common
 * @Author: Administrator
 * @Date: 2018/1/15 0015 上午 9:36
 * @Copyright: hl
 */

public class CatchLogPlugin extends CorePlugin {
    @Override
    public boolean initPlugin(Context context) {
        //非生产模式则关闭异常处理
//        if(!BuildConfig.DEBUG) {
            CrashHandler crashHandler = CrashHandler.getInstance();
            crashHandler.init(context);
//        }
        return true;
    }
}
