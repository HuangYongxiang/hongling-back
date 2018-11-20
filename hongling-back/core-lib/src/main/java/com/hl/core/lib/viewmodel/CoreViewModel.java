package com.hl.core.lib.viewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.ViewModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Describe: 核心VM
 * 1、生命周期管理
 * 2、自动注解
 * 3、异步执行
 * 4、加载框
 * 5、显示消息
 * 6、发送自定义消息
 * 7、当前网络请求数目
 * @Package: com.hl.core.lib.viewmodel
 * @Author: liyu
 * @Date: 2018/3/15 9:30
 * @Copyright: hl
 */
public class CoreViewModel extends ViewModel {

    protected final String TAG = getClass().getSimpleName();
    private static ScheduledExecutorService executorService;
    private List<LifecycleObserver> observers;
    private Lifecycle lifecycle;
    private MsgCallBack msgCallBack;

    public void bindCallBack(MsgCallBack msgCallBack){
        this.msgCallBack = msgCallBack;
    }

    public CoreViewModel() {
        if(null == executorService)
            executorService = new ScheduledThreadPoolExecutor(20);
        observers = new ArrayList<>();
        initAnnotation();
    }

    public void bindModel(Lifecycle lifecycle){
        this.lifecycle = lifecycle;
        for (LifecycleObserver observer: observers) {
            this.lifecycle.addObserver(observer);
            if(observer instanceof CoreModel){
                ((CoreModel) observer).bindCallBack(msgCallBack);
            }
        }
    }

    private void initAnnotation(){
        Field[] fields = getClass().getDeclaredFields();
        if(fields.length <= 0)
            return ;
        for (Field field : fields) {
            LiveData liveData = field.getAnnotation(LiveData.class);
            if (liveData != null) {
                field.setAccessible(true);
                try {
                    CoreLiveData coreLiveData = (CoreLiveData) field.getType().newInstance();
                    field.set(this,coreLiveData);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            Model model = field.getAnnotation(Model.class);
            if(model != null) {
                field.setAccessible(true);
                try {
                    CoreModel coreModel = (CoreModel) field.getType().newInstance();
                    field.set(this,coreModel);
                    observers.add(coreModel);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 异步执行
     * @param runnable 接口
     */
    protected void doAsync(Runnable runnable){
        executorService.execute(runnable);
    }

    /**
     * 异步延迟执行
     * @param runnable 接口
     * @param delay 延迟时间：秒
     */
    protected void doAsyncDelay(Runnable runnable,long delay){
        executorService.schedule(runnable,delay,TimeUnit.SECONDS);
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

    //当前网络请求数目：正在请求的数目
    public int currentNetworkCount(){
        int currentNetworkCount = 0;
        if(!observers.isEmpty()){
            for (LifecycleObserver observer: observers) {
                if(observer instanceof CoreModel){
                    currentNetworkCount += ((CoreModel) observer).currentNetworkCount();
                }
            }
        }
        return currentNetworkCount;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if(!observers.isEmpty()){
            for (LifecycleObserver observer: observers) {
                lifecycle.removeObserver(observer);
            }
        }
        observers.clear();
        observers = null;
        lifecycle = null;
        msgCallBack = null;
    }

}
