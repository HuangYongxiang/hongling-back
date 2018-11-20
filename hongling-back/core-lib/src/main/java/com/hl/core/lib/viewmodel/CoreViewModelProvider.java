package com.hl.core.lib.viewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelStore;
import android.arch.lifecycle.ViewModelStoreOwner;
import android.support.annotation.NonNull;

/**
 * @Describe: VM提供者
 * @Author: liyu
 * @Date: 2018/3/15 11:43
 * @Copyright: hl
 */
public class CoreViewModelProvider extends ViewModelProvider{

    private Lifecycle lifecycle;

    private MsgCallBack msgCallBack;

    public void bindCallBack(MsgCallBack msgCallBack){
        this.msgCallBack = msgCallBack;
    }

    public void bindLifecycle(Lifecycle lifecycle){
        this.lifecycle = lifecycle;
    }

    public CoreViewModelProvider(@NonNull ViewModelStoreOwner owner, @NonNull Factory factory) {
        super(owner, factory);
    }

    public CoreViewModelProvider(@NonNull ViewModelStore store, @NonNull Factory factory) {
        super(store, factory);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T get(@NonNull Class<T> modelClass) {
        return super.get(modelClass);
    }

    public <T extends CoreViewModel> T getVM(@NonNull Class<T> modelClass){
        T viewModel =  get(modelClass);
        viewModel.bindCallBack(msgCallBack);
        viewModel.bindModel(lifecycle);
        return viewModel;
    }

    public void destroy(){
        msgCallBack = null;
    }

}
