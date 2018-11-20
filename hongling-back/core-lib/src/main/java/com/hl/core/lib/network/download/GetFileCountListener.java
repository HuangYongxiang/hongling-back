package com.hl.core.lib.network.download;

/**
 * @Describe: 查询文件大小的回调
 * @Package: com.hl.core.lib.network.download
 * @Author: liyu
 * @Date: 2018/1/26 0026 下午 17:07
 * @Copyright: hl
 */
public interface GetFileCountListener {
    void success(boolean isSupportMulti, boolean isNew, String modified, Long fileSize);

    void failed(String message);
}
