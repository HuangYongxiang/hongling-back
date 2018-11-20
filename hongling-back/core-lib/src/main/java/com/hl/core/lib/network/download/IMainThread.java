package com.hl.core.lib.network.download;

/**
 * @Describe: 转换线程
 * @Package: com.hl.core.lib.network.download
 * @Author: liyu
 * @Date: 2018/1/26 0026 下午 17:07
 * @Copyright: hl
 */

public interface IMainThread {
    void post(Runnable runnable);
}
