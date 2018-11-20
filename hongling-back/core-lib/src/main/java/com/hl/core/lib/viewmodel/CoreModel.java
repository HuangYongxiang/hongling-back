package com.hl.core.lib.viewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.SparseArray;

import com.hl.core.lib.bean.Response;
import com.hl.core.lib.network.retrofit.NetWorkRequest;
import com.hl.core.lib.network.retrofit.NetworkResponse;

import retrofit2.Call;

/**
 * @Describe: 核心model
 * 1、生命周期管理
 * 2、加载框
 * 3、显示消息
 * 4、发送自定义消息
 * 5、当前网络请求数目
 * 6、UI销毁时自动取消所有网络请求
 * @Package: com.hl.core.lib.viewmodel
 * @Author: liyu
 * @Date: 2018/3/15 11:37
 * @Copyright: hl
 */
public class CoreModel implements LifecycleObserver {

    protected final String TAG = getClass().getSimpleName();
    private MsgCallBack msgCallBack;
    private int currentNetworkCount = 0;
    private SparseArray<Call> calls;

    public CoreModel() {
        calls = new SparseArray<>();
    }

    public void bindCallBack(MsgCallBack msgCallBack){
        this.msgCallBack = msgCallBack;
    }

    /**
     * 显示加载框
     */
    protected void showLoadingDialog(){
        if(msgCallBack != null)
            msgCallBack.callback(new CoreMessage(1));
    }

    /**
     * 关闭加载框
     */
    protected void dismissLoadingDialog(){
        if(msgCallBack != null)
            msgCallBack.callback(new CoreMessage(0));
    }

    /**
     * 显示消息
     * @param msg 消息
     */
    protected void showMessage(String msg){
        if(msgCallBack != null)
            msgCallBack.callback(new CoreMessage(CoreMessage.TIP,msg));

    }

    /**
     * 向UI发送消息
     * @param coreMessage 消息体
     */
    protected void sendMessage(CoreMessage coreMessage){
        if(msgCallBack != null){
            msgCallBack.callback(coreMessage);
        }
    }

    /**
     * 异步请求网络
     */
    public <T>  void asyncNetWork(boolean showLoadingDialog,String TAG, int requestCode, Call<Response<T>> call, NetworkResponse<Response<T>> responseListener){
        ++currentNetworkCount;
        if(showLoadingDialog){
            showLoadingDialog();
        }
        calls.put(call.hashCode(),call);
        NetWorkRequest.getInstance().asyncNetWork(TAG, requestCode, call, new NetworkResponse<Response<T>>() {
            @Override
            public void onDataReady(Response<T> response) {
                if(showLoadingDialog){
                    dismissLoadingDialog();
                }
                --currentNetworkCount;
                responseListener.onDataReady(response);
            }

            @Override
            public void onDataError(int requestCode, int responseCode, String message) {
                if(showLoadingDialog){
                    dismissLoadingDialog();
                }
                --currentNetworkCount;
                responseListener.onDataError(requestCode,responseCode,message);
            }
        });
    }

    /**
     * 异步请求网络：显示加载框
     */
    public <T>  void asyncNetWork(String TAG, int requestCode, Call<Response<T>> call, NetworkResponse<Response<T>> responseListener){
        asyncNetWork(true,TAG,requestCode,call,responseListener);
    }

    //当前网络请求数目：正在请求的数目
    public int currentNetworkCount(){
        return currentNetworkCount;
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected void onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected void onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected void onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected void onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy() {
        msgCallBack = null;
        if(calls == null)
            return ;
        for(int i=0;i<calls.size();i++){
            Call call = calls.valueAt(i);
            if(call != null && !call.isCanceled()){
                call.cancel();
            }
        }
        calls.clear();
        calls = null;
    }

}
