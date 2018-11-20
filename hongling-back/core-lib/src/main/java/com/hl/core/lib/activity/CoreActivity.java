package com.hl.core.lib.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;

import com.hl.core.lib.R;
import com.hl.core.lib.fragment.CoreFragment;
import com.hl.core.lib.fragment.LoadingDialogFragment;
import com.hl.core.lib.permission.CorePermission;
import com.hl.core.lib.permission.PermissionBean;
import com.hl.core.lib.permission.listener.CorePermissionListener;
import com.hl.core.lib.plugin.PluginManager;
import com.hl.core.lib.plugin.common.title.ITitleBar;
import com.hl.core.lib.util.UtilManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Describe: Activity核心基类
 * 1、交互管理
 * 2、Fragment托管
 * 3、延迟插件加载
 * 4、ProgressDialog 的控制
 * 5、添加权限控制回调
 * 6、加载框：基于Activity全局统一
 * 7、级联关闭：支持Activity -> Activity | Fragment -> Activity | Fragment -> Fragment
 * 8、入口拦截器
 * @Package: com.hl.core.lib.activity
 * @Author: liyu
 * @Date: 2018/1/3 9:37
 * @Copyright: hl
 */
public abstract class CoreActivity<T> extends FragmentActivity implements View.OnClickListener,CorePermissionListener,CoreHandler.CallBack {

    /**
     * 入口拦截器
     * @param intent 入参
     * @return 返回空即不拦截，其他则为拦截
     */
    protected abstract Object entryInterceptor(Intent intent);

    /**
     * 初始化标题栏
     */
    protected abstract void initTitle(T titleBar);

    protected final String TAG = getClass().getSimpleName();
    private List<CoreFragment> currentFragments;
    private Fragment currentFragment;
    protected T titleBar;
    //------------跟踪节点开始---------//
    private boolean isTrackNodeClose;
    private boolean isTrackNode;
    private boolean jumpTrackNode;
    private Class targetTrackNodeClass;
    final int TRACK_NODE_CLEAR_RESULT_CODE = 65533;
    final int TRACK_NODE_REQUEST_CODE = 65534;
    final int TRACK_NODE_RESULT_CODE = 65535;
    private final String TRACK_NODE_TAG = "track_node_tag";
    private final String TRACK_NODE_JUMP = "track_node_jump";
    private final String TRACK_NODE_IS_FRAGMENT = "track_node_is_fragment";
    private LinkedList<CoreFragment> backStackFragments = new LinkedList<>();
    //------------跟踪节点结束---------//

    //通用句柄
    private CoreHandler mainHandler;
    //加载提示框
    private LoadingDialogFragment loadingDialog;
    private final int SHOW_LOADING_DIALOG = 101;
    private boolean mStateEnable;
    //请求者：即上个页面
    public String clientName = "";
    private final String CLIENT_TAG = "client_tag";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //入口拦截器
        entryInterceptor(getIntent());
        //加载延迟插件
        PluginManager.get().init(getApplicationContext(), true);
        parseIntent();
        //初始化加载框
        loadingDialog = new LoadingDialogFragment();
        loadingDialog.bindHandler(getMainHandler()).bindFragmentManager(getSupportFragmentManager());
    }

    //解析Intent
    private void parseIntent(){
        Intent intent = getIntent();
        if(intent != null){
            clientName = intent.getStringExtra(CLIENT_TAG);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mStateEnable = false;
        super.onSaveInstanceState(outState);
//        super.onStateNotSaved();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mStateEnable = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mStateEnable = true;
    }

    @Override
    protected void onStop() {
        mStateEnable = false;
        super.onStop();
    }

    /**
     * 建议使用：startActivity(Class cls) || startActivity(Class cls, Bundle bundle)
     */
    @Deprecated
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    protected void startActivityOriginal(Intent intent){
        super.startActivity(intent);
    }

    public void startActivity(Class<? extends CoreActivity> cls) {
        startActivityForResult(cls, TRACK_NODE_REQUEST_CODE,null);
    }

    public void startActivity(Class<? extends CoreActivity> cls, Bundle bundle) {
        startActivityForResult(cls, TRACK_NODE_REQUEST_CODE, bundle);
    }

    public void startActivityForResult(Class<? extends CoreActivity> cls, int requestCode) {
        this.startActivityForResult(cls, requestCode, null);
    }

    public void startActivityForResult(Class<? extends CoreActivity> cls, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (null != bundle)
            intent.putExtras(bundle);
        intent.putExtra(CLIENT_TAG,getClass().getName());
        if(dealEntryInterceptor(cls,intent)){
            return ;
        }
        super.startActivityForResult(intent, requestCode);
    }

    /**
     * 处理入口拦截器
     */
    private boolean dealEntryInterceptor(Class<? extends CoreActivity> cls,Intent intent){
        try {
            CoreActivity coreActivity = cls.newInstance();
            if(coreActivity != null){
                String msg = coreActivity.interceptor(this,intent);
                if(!TextUtils.isEmpty(msg)){
                    UtilManager.Toast.show(this,msg);
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    private String interceptor(Context context,Intent intent){
        Object result = entryInterceptor(intent);
        String msg = "";
        if(result != null){
            if(result instanceof String){
                msg = String.valueOf(result);
            }else if(result instanceof Integer){
                int resId = (Integer) result;
                msg = context.getResources().getString(resId);
            }else{
                return context.getResources().getString(R.string.core_interceptor_return_error);
            }
            if(!TextUtils.isEmpty(msg)){
                return msg;
            }
        }
        return null;
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
    public void trackNodeClose(Class targetTrackNode,boolean jumpTrackNode){
        Intent intent = getIntent();
        if(intent == null)
            intent = new Intent();
        if(targetTrackNode != null){
            intent.putExtra(TRACK_NODE_TAG,targetTrackNode.getSimpleName());
        }
        intent.putExtra(TRACK_NODE_JUMP,jumpTrackNode);
        //目标节点是否是Fragment
        intent.putExtra(TRACK_NODE_IS_FRAGMENT,targetIsFragment(targetTrackNode));
        setResult(TRACK_NODE_RESULT_CODE,intent);
        this.isTrackNodeClose = true;
        this.jumpTrackNode = jumpTrackNode;
        this.targetTrackNodeClass = targetTrackNode;
        onBackPressed();
    }

    private boolean targetIsFragment(Class trackNode){
        if(trackNode == null)
            return false;
        Object obj;
        try {
            obj = trackNode.newInstance();
            if(obj instanceof Fragment){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @CallSuper
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == TRACK_NODE_RESULT_CODE) {
            if(!this.isTrackNode || data.getBooleanExtra(TRACK_NODE_JUMP,false)){
                final String current = getClass().getSimpleName();
                final String target = data.getStringExtra(TRACK_NODE_TAG);
                final boolean isFragment = data.getBooleanExtra(TRACK_NODE_IS_FRAGMENT,false);
                //当前节点不等于目标节点且目标节点不是Fragment
                if(!current.equals(target) && !isFragment){
                    setResult(TRACK_NODE_RESULT_CODE,data);
                    onBackPressed();
                }
            }
        }
    }


    /**
     * 加载Fragment
     *
     * @param layoutId       绑定控件
     * @param fragment       目标Fragment
     * @param arguments      传值
     * @param addToBackStack 是否压栈
     */
    protected void addFragment(int layoutId, CoreFragment fragment, Bundle arguments, boolean addToBackStack) {
        if (currentFragments == null) {
            currentFragments = new ArrayList<>();
        }
        if (fragment.isAdded()) {
            UtilManager.Log.w(TAG, getString(R.string.core_fragment_added, fragment.getClass().getName()));
            return;
        }
        if (!currentFragments.contains(fragment)) {
            currentFragments.add(fragment);
        }
        if (arguments != null) {
            fragment.setArguments(arguments);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(layoutId, fragment, fragment.getClass().getName());
        if (addToBackStack) {
            transaction.addToBackStack(null);
            addToBackStack(fragment);
        }
        transaction.commitAllowingStateLoss();
    }

    protected void addFragment(int layoutId, CoreFragment fragment, Bundle arguments) {
        addFragment(layoutId, fragment, arguments, false);
    }

    protected void addFragment(int layoutId, CoreFragment fragment){
        addFragment(layoutId, fragment, null, false);
    }

    /**
     * 显示Fragment
     *
     * @param fragment 目标Fragment
     */
    @SuppressWarnings(value = "unchecked")
    protected void showFragment(CoreFragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (currentFragments != null && !currentFragments.isEmpty()) {
            for (Fragment f : currentFragments) {
                if (f == fragment) {
                    fragment.bindTitle(titleBar);
                    fragment.bindOwner(this);
                    transaction.show(fragment);
                } else {
                    transaction.hide(f);
                }
            }
        }
        transaction.commitAllowingStateLoss();
    }

    protected void replaceFragment(int layoutId, CoreFragment fragment) {
        replaceFragment(layoutId, fragment, null, false);
    }

    protected void replaceFragment(int layoutId, CoreFragment fragment, Bundle arguments) {
        replaceFragment(layoutId, fragment, arguments, false);
    }

    /**
     * 绑定Fragment
     *
     * @param layoutId       绑定控件
     * @param fragment       目标Fragment
     * @param arguments      传值
     * @param addToBackStack 是否压栈
     */
    @SuppressWarnings(value = "unchecked")
    protected void replaceFragment(int layoutId, CoreFragment fragment, Bundle arguments, boolean addToBackStack) {
        if (fragment == null || fragment == currentFragment) {
            return;
        }
        currentFragment = fragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (arguments != null) {
            fragment.setArguments(arguments);
        }
        if (addToBackStack) {
            transaction.addToBackStack(null);
            addToBackStack(fragment);
        }
        fragment.bindTitle(titleBar);
        fragment.bindOwner(this);
        transaction.replace(layoutId, fragment, fragment.getTag());
        transaction.commitAllowingStateLoss();
    }

    /**
     * Fragment压栈
     */
    public void addToBackStack(CoreFragment fragment){
        backStackFragments.add(fragment);
    }

    @CallSuper
    @Override
    public void onBackPressed() {
        //当目标节点不是Fragment或者Fragment栈内为空时则直接关闭当前Activity
        if ((targetTrackNodeClass != null && !targetIsFragment(this.targetTrackNodeClass))
                || getSupportFragmentManager().getBackStackEntryCount() <= 0) {
            finish();
        } else {
            popBackStack();
        }
        this.currentFragment = null;
    }

    /**
     * Fragment弹栈操作
     */
    private void popBackStack(){
        CoreFragment fragment = backStackFragments.getLast();
        if(backStackFragments.isEmpty()){
            UtilManager.Log.e(TAG,getString(R.string.core_fragment_back_stack));
            return ;
        }
        //Fragment节点跟踪属性赋值
        if(!this.jumpTrackNode){
            this.jumpTrackNode = fragment.jumpTrackNode;
        }
        if(this.targetTrackNodeClass == null){
            this.targetTrackNodeClass = fragment.trackNodeClass;
        }
        if(!this.isTrackNodeClose){
            this.isTrackNodeClose = fragment.isTrackNodeClose;
        }
        //直接进行弹栈操作
        getSupportFragmentManager().popBackStack();
        backStackFragments.removeLast();
        if(backStackFragments.isEmpty()){
            updateTitle();
            return ;
        }
        //若是跟踪节点关闭操作则继续进行弹栈操作
        if(this.isTrackNodeClose){
            int size = backStackFragments.size() - 1;
            for (int i = size;i >= 0;i--){
                CoreFragment frg = backStackFragments.get(i);
                boolean track = frg.isTrackNode;
                if(!track || this.jumpTrackNode){
                    if(this.targetTrackNodeClass == null
                            || !frg.getClass().getName().equals(this.targetTrackNodeClass.getName())){
                        if (!backStackFragments.isEmpty()){
                            getSupportFragmentManager().popBackStack();
                            backStackFragments.removeLast();
                        }
                    }
                }
            }
            //每次回退动作完成后重置节点跟踪属性
            this.isTrackNodeClose = false;
            this.jumpTrackNode = false;
            this.targetTrackNodeClass = null;
        }
        //当Fragment栈中为零时，则更新Activity标题栏
        if(backStackFragments.isEmpty()){
            updateTitle();
        }
        //目前是Fragment弹栈操作，清除Activity级联关闭回调
        setResult(TRACK_NODE_CLEAR_RESULT_CODE);
    }

    private void updateTitle(){
        if(titleBar != null){
            ((ITitleBar) titleBar).init();
            initTitle(titleBar);
            ((ITitleBar) titleBar).updateTitle();
        }
    }

    @CallSuper
    @Override
    public void onClick(View v) {
        if(currentFragments != null && !currentFragments.isEmpty()){
            for (CoreFragment fragment : currentFragments) {
                if(fragment != null
                        && fragment.isAdded()
                        && !fragment.isRemoving()
                        && !fragment.isDetached()
                        && !fragment.isHidden()){
                    fragment.onClick(v);
                }
            }
        }
    }

    /**
     * 请求权限
     *
     * @param bean
     */
    public void requestPermission(PermissionBean bean) {
        boolean necessary = bean.isNecessary();
        String[] permissions = (String[]) bean.getPermissions()
                .toArray(new String[bean.getPermissions().size()]);
        CorePermission.getInstance().with(this).isNecessary(necessary)
                .setMultiplePermission(permissions).requestPermission(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onSucceed(@NonNull List<String> grantPermissions) {
        grantPermissions.forEach(grantPermission -> UtilManager.Log.i(TAG, grantPermission));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onFailed(@NonNull List<String> deniedPermissions) {
        deniedPermissions.forEach(deniedPermission -> UtilManager.Log.i(TAG, deniedPermission));
    }

    protected void showDialog(String message) {
        showLoadingDialog();
    }

    protected void dismissDialog() {
        dismissLoadingDialog();
    }

    public void showLoadingDialog(String tip){
        Message msg = new Message();
        msg.what = SHOW_LOADING_DIALOG;
        msg.obj = tip;
        getMainHandler().sendMessageDelayed(msg,50);
    }

    //显示加载框
    public void showLoadingDialog(){
        getMainHandler().sendEmptyMessageDelayed(SHOW_LOADING_DIALOG,50);
    }

    //关闭加载框
    public void dismissLoadingDialog(){
        loadingDialog.dismissLoadingDialog();
    }

    //显示加载失败
    public void showLoadFailedDialog(){
        loadingDialog.showLoadFailedDialog();
    }

    //显示加载成功
    public void showLoadSucceedDialog(){
        loadingDialog.showLoadSucceedDialog();
    }

    /**
     * 获取通用句柄，自动释放
     */
    protected CoreHandler getMainHandler(){
        if(null == mainHandler){
            mainHandler = new CoreHandler(this, Looper.getMainLooper());
        }
        return mainHandler;
    }

    @CallSuper
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_LOADING_DIALOG://显示加载框
                if(mStateEnable){
                    String tip = "";
                    if(msg.obj != null){
                        tip = String.valueOf(msg.obj);
                    }
                    loadingDialog.showLoadingDialog(tip);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if(null != mainHandler)
            mainHandler.removeCallbacksAndMessages(null);
        if(titleBar != null)
            ((ITitleBar) titleBar).destroy();
        super.onDestroy();
    }

}
