package com.hl.core.lib.network.download;


import com.hl.core.lib.network.download.db.DownLoadEntity;


/**
 * @Describe: 转换回调，用户转换线程间消息传递
 * @Package: com.hl.core.lib.network.download
 * @Author: liyu
 * @Date: 2018/1/26 0026 下午 17:07
 * @Copyright: hl
 */
public class DownCallBackListener implements DownLoadTaskListener {
    private MainThreadImpl mMainThread = MainThreadImpl.getMainThread();


    private DownLoadBackListener mBackListener;
    long mTotalSize;
    long mHasDownSize;

    private boolean isReturnStart;
    private boolean isReturnErr;
    private boolean isReturnCancel;

    public DownCallBackListener(DownLoadBackListener backListener, long totalSize, long hasDownSize) {
        mBackListener = backListener;
        mTotalSize = totalSize;
        mHasDownSize = hasDownSize;
    }

    @Override
    public synchronized void onStart() {
        if (!isReturnStart) {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mBackListener.onStart((double) mHasDownSize / mTotalSize);
                }
            });
        }
        isReturnStart = true;
    }

    @Override
    public synchronized void onCancel(DownLoadEntity downLoadEntity) {
        if (!isReturnCancel) {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mBackListener.onCancel();
                }
            });
        }
        isReturnCancel = true;
    }

    @Override
    public synchronized void onDownLoading(long downSize) {
        mHasDownSize += downSize;
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mBackListener.onDownLoading((double) mHasDownSize / mTotalSize);
            }
        });
    }

    @Override
    public synchronized void onCompleted(DownLoadEntity downLoadEntity) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mBackListener.onCompleted();
            }
        });
    }

    @Override
    public synchronized void onError(final DownLoadEntity downLoadEntity, final Throwable throwable) {
        if (!isReturnErr) {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mBackListener.onError(downLoadEntity,throwable);
                }
            });
        }
        isReturnErr = true;
    }
}
