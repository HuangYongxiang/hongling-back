package com.hl.core.lib.network.download;


import android.os.Handler;
import android.os.Looper;

/**
 * @Describe: hander发送线程间的消息
 * @Package: com.hl.core.lib.network.download
 * @Author: liyu
 * @Date: 2018/1/26 0026 下午 17:07
 * @Copyright: hl
 */


public class MainThreadImpl implements IMainThread {

    private MainThreadImpl() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    private Handler mHandler;
    private static MainThreadImpl sMMainThreadImpl = new MainThreadImpl();

    public static MainThreadImpl getMainThread() {
        return sMMainThreadImpl;
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }
}
