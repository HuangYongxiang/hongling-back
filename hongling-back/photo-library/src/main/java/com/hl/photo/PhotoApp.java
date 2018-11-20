package com.hl.photo;

import android.annotation.SuppressLint;
import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.hl.photo.table.PhotoGreenDaoHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Describe:
 * @Package: com.hl.photo
 * @Author: liyu
 * @Date: 2018/4/16/ 17:17
 * @Copyright: hl
 */


public class PhotoApp {
    private static final byte[] mLock = new byte[0];
    public static boolean isTakingPicture = false;

    private static PhotoApp mInstance;
    private static Application mApplication;

    private PhotoApp() {
    }

    public static PhotoApp instance() {
        if (mInstance == null) {
            synchronized (mLock) {
                if (mInstance == null) {
                    mInstance = new PhotoApp();
                }
            }
        }
        return mInstance;
    }
    public void init(){
        PhotoGreenDaoHelper.getInstance().getDaoMaster(get());
        Fresco.initialize(get());
    }

    public Application get() {
        if (mApplication == null) {
            try {
                Class activityThreadClazz = Class.forName("android.app.ActivityThread");
                Method method = activityThreadClazz.getMethod("currentActivityThread");
                Object activityThreadObj = method.invoke(activityThreadClazz, new Object[0]);
                Class activityThreadCls = activityThreadObj.getClass();
                Field field = activityThreadCls.getDeclaredField("mInitialApplication");
                field.setAccessible(true);
                mApplication = (Application) field.get(activityThreadObj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mApplication;
    }
}
