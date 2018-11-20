package com.hl.core.lib.network.download;


import com.hl.core.lib.network.download.db.DownLoadDatabase;
import com.hl.core.lib.network.download.db.DownLoadEntity;
import com.hl.core.lib.network.retrofit.NetWorkRequest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Describe: 下载管理类，暴漏对外接口
 * @Package: com.hl.core.lib.network.download
 * @Author: liyu
 * @Date: 2018/1/26 0026 下午 17:07
 * @Copyright: hl
 */

public class DownLoadManager {

    private DownLoadDatabase mDownLoadDatabase = new DownLoadDatabase(NetWorkRequest.getInstance().mContext);

    private ExecutorService mExecutorService = Executors.newCachedThreadPool();

    //多线程下载文件最低大小10mb
    private final long MULTI_LINE = 10 * 1024 * 1024;

    //所有下载Task
    private Map<String, DownLoadRequest> mDownLoadRequestMap = new ConcurrentHashMap<>();

    private DownLoadManager() {
    }

    private static class DownLoadManagerHolder {
        private static final DownLoadManager INSTANCE = new DownLoadManager();
    }

    public static final DownLoadManager getInstance() {
        return DownLoadManagerHolder.INSTANCE;
    }

    //默认支持多线程下载
    public void downLoad(final List<DownLoadEntity> list, final String tag, final DownLoadBackListener downLoadTaskListener) {
        downLoad(list, tag, downLoadTaskListener, MULTI_LINE);
    }


    public void downLoad(final List<DownLoadEntity> list, final String tag, final  DownLoadBackListener downLoadTaskListener, final long multiLine) {
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                DownLoadRequest downLoadRequest = new DownLoadRequest(mDownLoadDatabase, downLoadTaskListener, list, multiLine);
                downLoadRequest.start();
                mDownLoadRequestMap.put(tag, downLoadRequest);
            }
        });
    }

    //默认支持多线程下载
    public void downLoad(final DownLoadEntity downLoadEntity, final String tag, final DownLoadBackListener downLoadTaskListener) {
        List<DownLoadEntity> list = new ArrayList<>();
        list.add(downLoadEntity);
        downLoad(list, tag, downLoadTaskListener, MULTI_LINE);
    }


    public void downLoad(String url, String saveName, final String tag, final DownLoadBackListener downLoadTaskListener){
        DownLoadEntity downLoadEntity = new DownLoadEntity();
        downLoadEntity.url=url;
        downLoadEntity.saveName=saveName;
        List<DownLoadEntity> list = new ArrayList<>();
        list.add(downLoadEntity);
        downLoad(list, tag, downLoadTaskListener, MULTI_LINE);
    }


    public void downLoad(final DownLoadEntity downLoadEntity, final String tag, final DownLoadBackListener downLoadTaskListener, final long multiLine) {
        List<DownLoadEntity> list = new ArrayList<>();
        list.add(downLoadEntity);
        downLoad(list, tag, downLoadTaskListener, multiLine);
    }
    public void downLoad(String url,String saveName, final String tag, final DownLoadBackListener downLoadTaskListener, final long multiLine) {
        List<DownLoadEntity> list = new ArrayList<>();
        DownLoadEntity downLoadEntity = new DownLoadEntity();
        list.add(downLoadEntity);
        downLoad(list, tag, downLoadTaskListener, multiLine);
    }

    //取消所有任务
    public void cancel() {
        if (!mDownLoadRequestMap.isEmpty()) {
            Iterator iterator = mDownLoadRequestMap.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                cancel(key);
            }
        }
    }

    //取消当前tag所有任务
    public void cancel(String tag) {
        if (!mDownLoadRequestMap.isEmpty()) {
            if (mDownLoadRequestMap.containsKey(tag)) {
                mDownLoadRequestMap.get(tag).stop();
                mDownLoadRequestMap.remove(tag);
            }
        }
    }
}
