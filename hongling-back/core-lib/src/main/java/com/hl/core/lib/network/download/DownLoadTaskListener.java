package com.hl.core.lib.network.download;


import com.hl.core.lib.network.download.db.DownLoadEntity;

/**
 * @Describe: 下载任务的回调
 * @Package: com.hl.core.lib.network.download
 * @Author: liyu
 * @Date: 2018/1/26 0026 下午 17:07
 * @Copyright: hl
 */
public interface DownLoadTaskListener {
    void onStart();

    void onCancel(DownLoadEntity downLoadEntity);

    void onDownLoading(long downSize);

    void onCompleted(DownLoadEntity downLoadEntity);

    void onError(DownLoadEntity downLoadEntity, Throwable throwable);
}
