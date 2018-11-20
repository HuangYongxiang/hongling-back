package com.hl.core.lib.activity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
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
import com.hl.core.lib.event.EventBus;
import com.hl.core.lib.inter.IUI;
import com.hl.core.lib.plugin.PluginManager;
import com.hl.core.lib.plugin.common.title.ITitleBar;
import com.hl.core.lib.plugin.common.title.ITitleBarPlugin;
import com.hl.core.lib.plugin.theme.ThemeEvent;
import com.hl.core.lib.plugin.theme.ThemeManager;
import com.hl.core.lib.util.UtilManager;
import com.hl.core.lib.viewmodel.CoreMessage;
import com.hl.core.lib.viewmodel.CoreViewModel;
import com.hl.core.lib.viewmodel.CoreViewModelProvider;
import com.hl.core.lib.viewmodel.CoreViewModelProviders;
import com.hl.core.lib.viewmodel.ViewModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @Describe: 标题栏Activity
 * 1、Activity周期管理
 * 2、标题栏插件动态加载
 * 3、主题管理
 * 4、支持DataBinding视图绑定
 * 5、隐藏软键盘
 * @Package: com.hl.core.lib.activity
 * @Author: liyu
 * @Date: 2018/1/2 18:21
 * @Copyright: hl
 */
public abstract class TitleActivity<T> extends CoreActivity<T> implements IUI {

    //标题栏插件
    protected ITitleBarPlugin titleBarPlugin;

    //页面内容
    private ViewGroup contentView;

    //DataBinding绑定视图
    protected View bindView;

    private CoreViewModelProvider viewModelProvider;

    @Override
    public void setTheme(int resId) {
        super.setTheme(ThemeManager.get().getCurrentTheme());
    }

    @CallSuper
    @Override
    protected Object entryInterceptor(Intent intent) {
        return null;
    }

    /**
     * 初始化视图布局文件
     */
    protected abstract Object initLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //竖屏，禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //初始化标题栏
        initTitleBar();
        //初始化页面布局
        initContentView(initLayout());
        EventBus.register(this);
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

    @Override
    protected void onStart() {
        super.onStart();
        initData();
        initViewProperty();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 初始化标题栏
     */
    private void initTitleBar(){
        titleBar = fetchTitleBar();
        if(titleBar == null)
            return ;
        ITitleBar iTitleBar = (ITitleBar) titleBar;
        iTitleBar.init();
        //初始化标题栏属性
        titleBarPlugin = PluginManager.get().getPluginInterface(iTitleBar.bindPlugin());
        if(titleBarPlugin == null){
            UtilManager.Log.e(TAG,getString(R.string.core_plugin_not_init, titleBar.getClass().getSimpleName()));
            return ;
        }
        initTitle(titleBar);
        //绑定标题栏
        titleBarPlugin.bindTitleBar(iTitleBar);
        //初始化标题栏视图
        initTitleView(titleBarPlugin);
    }

    /**
     * 初始化标题栏视图
     * @param titleBarPlugin 主题插件
     */
    private void initTitleView(ITitleBarPlugin titleBarPlugin){
        if(!titleBarPlugin.hasTitleBar())
            return ;
        try {
            contentView = titleBarPlugin.initTitleView(this,this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 初始化标题栏视图
     */
    private void initContentView(Object view){
        if(view == null){
            view = 0;
        }
        if(view instanceof Integer){
            setContentView((Integer) view);
        } else if(view instanceof View){
            bindView = (View) view;
            setContentView(0);
        } else {
            UtilManager.Toast.show(this,getString(R.string.core_layout_failed));
            UtilManager.Log.e(TAG,getString(R.string.core_layout_failed));
        }
    }

    @Override
    public void setContentView(View view) {
        //当绑定了DataBinding视图则阻止手动设置视图
        if(bindView == null){
            super.setContentView(view);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        if(loadBindView(viewGroup))
            return ;
        if(contentView != null){
            View view = LayoutInflater.from(this).inflate(layoutResID, contentView, true);
            viewGroup.addView(view);
        }else{
            if(layoutResID != 0){
                super.setContentView(layoutResID);
            }
        }
    }

    //加载DataBinding视图
    private boolean loadBindView(ViewGroup viewGroup){
        if(bindView != null){
            //加载DataBinding视图
            if(contentView != null){
                //有标题栏
                contentView.addView(bindView);
                viewGroup.addView(contentView);
            }else{
                //无标题栏
                viewGroup.addView(bindView);
            }
            return true;
        }
        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateTheme(ThemeEvent event){
        if(!getClass().getName().equals(event.className)){
            recreate();
        }
    }

    /**
     * 是否前台显示
     * android 5.0 之后getRunningTask（）方法被废弃，失效了
     * android.permission.GET_TASKS 权限被禁止
     * https://blog.csdn.net/hyhyl1990/article/details/45700447
     * @return
     */
    protected boolean isForeground() {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if(am == null)
            return false;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                //前台程序
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(getPackageName())) {
                            return true;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            String name = getClass().getName();
            String className = componentInfo.getClassName();
            if (name.equals(className)){
                return true;
            }
        }

        return false;
    }

    @SuppressWarnings(value = "unchecked")
    private Class<T> initClazz(){
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if(params.length > 0){
            if(params[0] instanceof Class){
                return (Class<T>) params[0];
            }
        }
        return null;
    }

    /**
     * 获取标题实例
     */
    private T fetchTitleBar(){
        T titleBar = null;
        try {
            Class<T> title = initClazz();
            if(title != null){
                titleBar = title.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return titleBar;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initViewProperty() {

    }

    //隐藏键盘
    public void hideSoftInput(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null){
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0); //强制隐藏键盘
        }
    }

    public void hideSoftInput(View view){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
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
                    UtilManager.Toast.show(TitleActivity.this,msg.message);
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
    protected void onDestroy() {
        super.onDestroy();
        EventBus.unregister(this);
        if(viewModelProvider != null){
            viewModelProvider.destroy();
        }
    }


}
