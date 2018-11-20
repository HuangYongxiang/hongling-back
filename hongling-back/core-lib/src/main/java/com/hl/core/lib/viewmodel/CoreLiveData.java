package com.hl.core.lib.viewmodel;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

/**
 * @Describe: LiveData核心类：一对多关系（一个LiveData可以对应多个Observer，为了保证数据回调一致以及方便理解，建议一对一）
 * @Package: com.hl.core.lib.viewmodel
 * @Author: liyu
 * @Date: 2018/3/19 12:31
 * @Copyright: hl
 */
public class CoreLiveData<T> extends android.arch.lifecycle.MutableLiveData<T> {

    /**
     * 注册观察者，仅注册一次
     * @param owner 生命周期
     * @param observer 观察者
     */
    public void observeOnce(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
        if(!hasActiveObservers()){
            observe(owner, observer);
        }
    }



}
