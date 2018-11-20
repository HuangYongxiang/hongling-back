package com.hl.core.lib.fragment;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hl.core.lib.R;
import com.hl.core.lib.activity.CoreActivity;
import com.hl.core.lib.permission.listener.CorePermissionListener;
import com.hl.core.lib.plugin.common.title.ITitleBar;
import com.hl.core.lib.util.UtilManager;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Describe: Fragment核心基类
 * 1、交互管理
 * 2、子Fragment托管
 * 3、ProgressDialog 的控制(liyu)
 * 4、添加权限控制回调
 * @Package: com.hl.core.lib.fragment
 * @Author: liyu
 * @Date: 2018/1/5 10:10
 * @Copyright: hl
 */
public abstract class CoreFragment<T> extends Fragment implements View.OnClickListener,CorePermissionListener {

    protected final String TAG = getClass().getSimpleName();
    protected T titleBar;
    protected CoreActivity owner;
    private ScheduledExecutorService scheduledExecutorService;
    //是否为跟踪节点
    public boolean isTrackNode;
    public Class trackNodeClass;
    public boolean jumpTrackNode;
    public boolean isTrackNodeClose;

    /**
     * 初始化标题栏
     */
    protected abstract void initTitle(T titleBar);

    /**
     * 绑定所属Activity标题
     * @param titleBar 标题栏
     */
    public void bindTitle(T titleBar){
        this.titleBar = titleBar;
        updateTitle();
    }

    /**
     * 绑定所属Activity
     * @param coreActivity 归属
     */
    public void bindOwner(CoreActivity coreActivity){
        owner = coreActivity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void updateTitle(){
        if(titleBar != null){
            //若有标题栏则更新标题栏
            if(fetchTitleBar() != null){
                ((ITitleBar) titleBar).init();
                initTitle(titleBar);
                ((ITitleBar) titleBar).updateTitle();
            }
        }
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
            UtilManager.Log.i(TAG,"has no TitleBar!");
        }
        return titleBar;
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    protected void startActivity(Class<?> cls){
        if(hasOwner()){
            owner.startActivity(cls);
        }
    }

    protected void startActivity(Class<?> cls,Bundle bundle){
        if(hasOwner()){
            owner.startActivity(cls,bundle);
        }
    }

    protected void startActivityForResult(Class<?> cls, int requestCode){
        if(hasOwner()){
            owner.startActivityForResult(cls,requestCode);
        }
    }

    protected void startActivityForResult(Class<?> cls, int requestCode,Bundle bundle){
        if(hasOwner()){
            owner.startActivityForResult(cls,requestCode,bundle);
        }
    }

    /**
     * 加载Fragment
     * @param layoutId 绑定控件
     * @param fragment 目标Fragment
     * @param arguments 传值
     * @param addToBackStack 是否压栈
     */
    protected void addFragment(int layoutId, CoreFragment fragment, Bundle arguments,boolean addToBackStack) {
        if(fragment.isAdded()){
            UtilManager.Log.w(TAG,getString(R.string.core_fragment_added,fragment.getClass().getName()));
            return ;
        }
        if(arguments != null){
            fragment.setArguments(arguments);
        }
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(layoutId, fragment);
        if(hasOwner()){
            fragment.bindOwner(owner);
        }
        if(addToBackStack){
            transaction.addToBackStack(null);
            if(hasOwner()){
                owner.addToBackStack(fragment);
            }
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 显示Fragment
     * @param fragment 目标Fragment
     */
    protected void showFragment(CoreFragment fragment){
        FragmentManager manager = getChildFragmentManager();
        List<Fragment> list =  manager.getFragments();
        FragmentTransaction transaction = manager.beginTransaction();
        for(Fragment f : list){
            if(f == fragment){
                transaction.show(fragment);
            }else{
                transaction.hide(f);
            }
        }
        transaction.commitAllowingStateLoss();
    }

    protected void addFragment(int layoutId, CoreFragment fragment){
        addFragment(layoutId,fragment,null,false);
    }

    protected void addFragment(int layoutId, CoreFragment fragment,Bundle arguments){
        addFragment(layoutId,fragment,arguments,false);
    }

    protected void replaceFragment(int layoutId, CoreFragment fragment, Bundle arguments){
        replaceFragment(layoutId,fragment,arguments,false);
    }

    /**
     * 绑定Fragment
     * @param layoutId 绑定控件
     * @param fragment 目标Fragment
     * @param arguments 传值
     * @param addToBackStack 是否压栈
     */
    protected void replaceFragment(int layoutId, CoreFragment fragment, Bundle arguments,boolean addToBackStack) {
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if(arguments != null){
            fragment.setArguments(arguments);
        }
        transaction.replace(layoutId, fragment);
        if(hasOwner()){
            fragment.bindOwner(owner);
        }
        if(addToBackStack){
            transaction.addToBackStack(null);
            if(hasOwner()){
                owner.addToBackStack(fragment);
            }
        }
        transaction.commitAllowingStateLoss();
    }

    private boolean hasOwner(){
        if(owner != null){
            return true;
        }else{
            UtilManager.Log.w(TAG,getString(R.string.core_fragment_has_no_owner));
            return false;
        }
    }

    protected void showDialog(String message) {
        showLoadingDialog();
    }

    protected void dismissDialog() {
        dismissLoadingDialog();
    }

    @Override
    public void onSucceed(@NonNull List<String> grantPermissions) {

    }

    @Override
    public void onFailed(@NonNull List<String> deniedPermissions) {

    }

    //显示加载框
    protected void showLoadingDialog(){
        if(hasOwner()){
            owner.showLoadingDialog();
        }
    }

    //关闭加载框
    protected void dismissLoadingDialog(){
        if(hasOwner()){
            owner.dismissLoadingDialog();
        }
    }

    //显示加载失败
    protected void showLoadFailedDialog(){
        if(hasOwner()){
            owner.showLoadFailedDialog();
        }
    }

    //显示加载成功
    protected void showLoadSucceedDialog(){
        if(hasOwner()){
            owner.showLoadSucceedDialog();
        }
    }

    /**
     * 打开跟踪节点：终止级联关闭，建议在首页或需要拦截的节点打开
     */
    protected void trackNodeOpen() {
        isTrackNode = true;
    }

    /**
     * 关闭跟踪节点：自动退栈至跟踪节点位置
     * @param targetTrackNode 目标节点
     */
    public void trackNodeClose(Class targetTrackNode) {
        trackNodeClose(targetTrackNode,false);
    }

    /**
     * 关闭跟踪节点：自动退栈至跟踪节点位置
     * @param targetTrackNode 目标节点
     * @param jumpTrackNode 强制跳过跟踪节点拦截器
     */
    public void trackNodeClose(Class targetTrackNode, boolean jumpTrackNode){
        this.trackNodeClass = targetTrackNode;
        this.jumpTrackNode = jumpTrackNode;
        this.isTrackNodeClose = true;
        if(hasOwner()){
            owner.trackNodeClose(targetTrackNode,jumpTrackNode);
        }
    }

    /**
     * 定时任务
     * @param command 执行命令接口
     * @param initialDelay 第一次执行的延迟时间
     * @param delay 下一次执行的延迟时间
     * @param unit 时间单位
     */
    protected void scheduleWithFixedDelay(Runnable command,
                           long initialDelay,
                           long delay,
                           TimeUnit unit){
        if(scheduledExecutorService == null){
            scheduledExecutorService = Executors.newScheduledThreadPool(4);
        }
        scheduledExecutorService.scheduleWithFixedDelay(command,initialDelay,delay,unit);
    }

    @CallSuper
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        if(scheduledExecutorService != null){
            scheduledExecutorService.shutdownNow();
            scheduledExecutorService = null;
        }
        super.onDestroy();
    }

}
