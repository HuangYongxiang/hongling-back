package com.hl.core.lib.permission;

import android.app.Activity;

import com.hl.core.lib.permission.listener.CorePermissionListener;

import java.util.HashMap;

/**
 * Created by liyu on 2018/1/4/004.
 */

public class CorePermissionConfig {



    private static final HashMap<CorePermissionKeys, Object> PERMISSION_CONFIGS = new HashMap<>();

    public static CorePermissionListener permissionListener = null;
    public static Activity activity;
    public static Object targetObject;

    private static String TAG = "CorePermissionConfig";


    static CorePermissionConfig getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CorePermissionConfig INSTANCE = new CorePermissionConfig();
    }


    public final CorePermissionConfig with(Activity activity) {
        PERMISSION_CONFIGS.put(CorePermissionKeys.ACTIVITY, activity);
        return this;
    }

    /**
     * 未使用，直接 通过 with(T) setMultiplePermission(T ...)设置
     * @param key
     * @param object
     */
    public void put(CorePermissionKeys key, Object object){
        PERMISSION_CONFIGS.put(key,object);
    }


    public static final void configure() {
        PERMISSION_CONFIGS.put(CorePermissionKeys.READY, true);

    }

    /**
     * 1. 开始设计时候，需要config，现在在申请时候，检查一下activity对象和permissions对象
     * 2. 现在废弃使用
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) PERMISSION_CONFIGS.get(CorePermissionKeys.READY);
        if (!isReady) {
            throw new RuntimeException("Configurator: Configuration is not ready,call configure");
        }
    }

    public CorePermissionConfig setMultiplePermission(String[] permissions) {
        PERMISSION_CONFIGS.put(CorePermissionKeys.PERMISSIONS,permissions);
        return this;
    }

    public CorePermissionConfig isNecessary(boolean isNecessary){
        PERMISSION_CONFIGS.put(CorePermissionKeys.NECESSARY,isNecessary);
        return this;
    }

    final <T> T get(CorePermissionKeys key) {
        final Object value = PERMISSION_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) PERMISSION_CONFIGS.get(key);
    }
}
