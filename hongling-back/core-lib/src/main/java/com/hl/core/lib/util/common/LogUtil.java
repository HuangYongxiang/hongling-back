package com.hl.core.lib.util.common;

import android.util.Log;

import com.hl.core.lib.BuildConfig;


/**
 * @Describe: 日志打印
 * @Package: com.hl.core.lib.util
 * @Author: liyu
 * @Date: 2018/1/2 11:50
 * @Copyright: hl
 */
public class LogUtil {

    /** 是否开启debug模式 */
    private static boolean isDebug = BuildConfig.LOG_DEBUG;

    public LogUtil() {
    }

    /**
     * 错误
     */
    public void e(Class<?> clazz, String msg){
        if(isDebug){
            Log.e(clazz.getSimpleName(), msg);
        }
    }

    public void e(String clazzName, String msg){
        if(isDebug){
            Log.e(clazzName, msg);
        }
    }

    /**
     *  信息
     */
    public void i(Class<?> clazz, String msg){
        if(isDebug){
            Log.i(clazz.getSimpleName(), msg);
        }
    }

    public void i(String clazzName, String msg){
        if(isDebug){
            Log.i(clazzName, msg);
        }
    }

    /**
     * 警告
     */
    public void w(Class<?> clazz, String msg){
        if(isDebug){
            Log.w(clazz.getSimpleName(), msg);
        }
    }

    public void w(String clazzName, String msg){
        if(isDebug){
            Log.w(clazzName, msg);
        }
    }

    /**
     * 测试
     */
    public void d(Class<?> clazz, String msg){
        if(isDebug){
            Log.d(clazz.getSimpleName(), msg);
        }
    }

    public void d(String clazzName, String msg){
        if(isDebug){
            Log.d(clazzName, msg);
        }
    }
}
