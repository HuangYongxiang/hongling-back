package com.hl.core.lib.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.hl.core.lib.R;
import com.hl.core.lib.inter.IUI;
import com.hl.core.lib.util.UtilManager;
import com.hl.core.lib.viewmodel.CoreMessage;
import com.hl.core.lib.viewmodel.CoreViewModel;
import com.hl.core.lib.viewmodel.CoreViewModelProvider;
import com.hl.core.lib.viewmodel.CoreViewModelProviders;
import com.hl.core.lib.viewmodel.ViewModel;

import java.lang.reflect.Field;

/**
 * @Describe: 标题栏Fragment
 * 1、标题管理
 * @Package: com.hl.core.lib.fragment
 * @Author: liyu
 * @Date: 2018/1/5 10:14
 * @Copyright: hl
 */
public abstract class TitleFragment<T> extends CoreFragment<T> implements IUI {

    protected View view;
    //DataBinding绑定视图
    protected View bindView;

    private CoreViewModelProvider viewModelProvider;

    /**
     * 初始化视图布局文件
     */
    protected abstract Object initLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    @CallSuper
    @Override
    protected void initTitle(T titleBar) {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAnnotation();
    }

    @SuppressWarnings(value = "unchecked")
    private void initAnnotation(){
        Field[] fields = getClass().getDeclaredFields();
        if(fields.length <= 0)
            return ;
        for (Field field : fields) {
            ViewModel viewModel = field.getAnnotation(ViewModel.class);
            if(viewModel != null){
                field.setAccessible(true);
                try {
                    Class c = Class.forName(field.getType().getName());
                    CoreViewModel coreViewModel = viewModelProvider().getVM(c);
                    field.set(this,coreViewModel);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = initContentView(inflater,container,savedInstanceState);
        initData();
        initViewProperty();
        return view;
    }

    //初始化页面视图
    private View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = null;
        Object obj = initLayout(inflater,container,savedInstanceState);
        if(obj instanceof View){
            view = (View) obj;
        }else if(obj instanceof Integer){
            view = inflater.inflate((Integer)obj,container,false);
        }else {
            UtilManager.Toast.show(getActivity(),getString(R.string.core_layout_failed));
            UtilManager.Log.e(TAG,getString(R.string.core_layout_failed));
        }
        return view;
    }

    //隐藏键盘
    public void hideSoftInput(){
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null && getView() != null){
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0); //强制隐藏键盘
        }
    }

    public void hideSoftInput(View view){
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null && view != null){
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    @MainThread
    public CoreViewModelProvider viewModelProvider(){
        if(viewModelProvider == null){
            viewModelProvider = CoreViewModelProviders.of(this);
            viewModelProvider.bindCallBack(msg -> {
                if(msg == null)
                    return ;
                //加载框显示
                if(msg.showLoadingDialog == 1){
                    showLoadingDialog();
                    return;
                }else if(msg.showLoadingDialog == 0){
                    dismissLoadingDialog();
                    return;
                }
                //提示消息
                if(CoreMessage.TIP == msg.msgCode && !TextUtils.isEmpty(msg.message)){
                    UtilManager.Toast.show(getActivity(),msg.message);
                    return;
                }
                msgCallBack(msg);
            });
            viewModelProvider.bindLifecycle(getLifecycle());
        }
        return viewModelProvider;
    }

    /**
     * 信息回调
     * @param msg 信息体
     */
    protected void msgCallBack(CoreMessage msg){

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(viewModelProvider != null){
            viewModelProvider.destroy();
        }
    }

    @Override
    public void initViewProperty() {

    }

    @Override
    public void initData() {

    }
}
