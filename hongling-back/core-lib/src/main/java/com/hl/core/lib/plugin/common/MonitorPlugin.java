package com.hl.core.lib.plugin.common;

import android.content.Context;
import android.os.StrictMode;

import com.hl.core.lib.BuildConfig;
import com.hl.core.lib.CoreApplication;
import com.hl.core.lib.plugin.CorePlugin;
import com.hl.core.lib.util.UtilManager;
import com.squareup.leakcanary.LeakCanary;

/**
 * @Describe: 应用监控组件：严格模式+内存泄露分析
 * @Package: com.hl.core.lib.plugin.common
 * @Author: liyu
 * @Date: 2018/3/6 16:55
 * @Copyright: hl
 */
public class MonitorPlugin extends CorePlugin{

    @Override
    public boolean initPlugin(Context context) {
        UtilManager.Log.d(TAG,"应用监控组件成功");
        if(BuildConfig.DEBUG){
            //由于目前数据库操作都是UI线程所以先屏蔽detectDiskReads与detectDiskWrites
            //后期需要对数据库操作优化
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                    .detectDiskReads()
//                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyDialog()
                    .penaltyLog()
                    .build());
            UtilManager.Log.d(TAG,"严格模式开启");
            //开启LeakCanary需要将CatchLogPlugin关闭
            LeakCanary.install(CoreApplication.get());
        }
        return true;
    }


}
