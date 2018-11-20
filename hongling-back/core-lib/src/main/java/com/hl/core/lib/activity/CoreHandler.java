package com.hl.core.lib.activity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @Describe: 通用句柄
 * @Package: com.hl.core.lib.activity
 * @Author: liyu
 * @Date: 2018/2/5 13:19
 * @Copyright: hl
 */
public class CoreHandler extends Handler {

    private List<CallBack> callBacks = new ArrayList<>();

    public interface CallBack{
        void handleMessage(Message msg);
    }

    public CoreHandler(CallBack callBack, Looper looper){
        super(looper);
        if(callBack != null)
            callBacks.add(callBack);
    }

    public void addCallBack(CallBack callBack){
        if(!callBacks.contains(callBack)){
            callBacks.add(callBack);
        }
    }

    @Override
    public void handleMessage(Message msg) {
        if(!callBacks.isEmpty()){
            for (CallBack call : callBacks){
                call.handleMessage(msg);
            }
        }
    }

}
