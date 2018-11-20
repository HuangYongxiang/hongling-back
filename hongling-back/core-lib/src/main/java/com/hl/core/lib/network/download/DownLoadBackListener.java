package com.hl.core.lib.network.download;


import com.hl.core.lib.network.download.db.DownLoadEntity;
/**
 * @Describe:
 * @Package: com.hl.core.lib.network.download
 * @Author: liyu
 * @Date: 2018/1/26 0026 下午 17:07
 * @Copyright: hl
 */
public interface DownLoadBackListener {
    void onPreDeal();

    void onDealCompleted();

    void onDealError(String message);

    void onStart(double percent);

    void onCancel();

    void onDownLoading(double percent);

    void onCompleted();

    void onError(DownLoadEntity downLoadEntity, Throwable throwable);
}
