package com.hl.core.lib.permission;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;

import com.hl.core.lib.permission.annotation.PermissionGranted;
import com.hl.core.lib.permission.checklibs.PermissionCheck;
import com.hl.core.lib.permission.handler.DynamicHandler;
import com.hl.core.lib.permission.listener.CorePermissionListener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Describe: 权限检查：1.with 2. permission 3. listener
 * @Package: com.hl.core.lib.permission
 * @Author: liyu
 * @Date: 2018/1/10/010 17:00
 * @Copyright: hl
 */

public class CorePermission {

    private String TAG = "CorePermission";
    private Activity activity;
    private ArrayList<String> allowedList;
    private ArrayList<String> deniedList;
    private ArrayList<String> requestList = new ArrayList<>();
    private boolean isNecessary = true;


    private CorePermission(){}
    public static CorePermission getInstance() {
        return Holder.INSTANCE;
    }
    private static class Holder {
        private static final CorePermission INSTANCE = new CorePermission();
    }

    /**
     * @desc 注解必须绑定一个activity 或者 fragment
     * @param object
     */
    public void bind(Object object){
        injectPremission(object);
        CorePermissionConfig.targetObject = object;
    }

    public CorePermission with(Object object){
        CorePermissionConfig.targetObject = object;
        checkObject(object);
        return getInstance();
    }

    /**
     * 添加读写SD卡权限
     * @return this
     */
    public CorePermission doSdcard(){
        requestList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return this;
    }

    /**
     * 拍照权限
     * @return this
     */
    public CorePermission doCamera(){
        requestList.add(Manifest.permission.CAMERA);
        return this;
    }

    /**
     * 录音权限
     * @return this
     */
    public CorePermission doAudio(){
        requestList.add(Manifest.permission.RECORD_AUDIO);
        return this;
    }

    /**
     * 定位权限
     * @return this
     */
    public CorePermission doLocation(){
        requestList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        return this;
    }


    /**
     *
     * @desc 传入需要请求的单个权限
     * @param permission
     * @return
     */
    @Deprecated
    public CorePermission setPermission(String permission){
        CorePermissionConfig.getInstance().setMultiplePermission(new String[]{permission});
        return CorePermission.getInstance();
    }

    /**
     * 传入需要请求的多个权限
     * @param permissions
     * @return this
     */
    public CorePermission setMultiplePermission(String[] permissions){
        CorePermissionConfig.getInstance().setMultiplePermission(permissions);
        return CorePermission.getInstance();
    }

    /**
     * 是否为必须条件
     * @param isNecessary
     * @return
     */
    public CorePermission isNecessary(boolean isNecessary){
        CorePermissionConfig.getInstance().isNecessary(isNecessary);
        return CorePermission.getInstance();
    }

    public void request(){
        isNecessary = CorePermissionConfig.getInstance().get(CorePermissionKeys.NECESSARY);

//        CorePermission.getInstance().isNecessary(isNecessary).setMultiplePermission(requestList).requestPermission(this);
    }


    /**
     * 实际请求方法：如果已经全部获取权限，则直接返回
     * 如果没有获取，打开一个新的activity 获取权限
     * @param listener
     */
    public void requestPermission(CorePermissionListener listener) {

        CorePermissionConfig config = CorePermissionConfig.getInstance();
        if (!checkConfigReady(config)){ // 未给activity 获取 没有添加需要申请权限
            return;
        }
        CorePermissionConfig.getInstance().put(CorePermissionKeys.LISTENER,listener);
        allowedList = new ArrayList<>();
        deniedList = new ArrayList<>();

        allowedList.clear();
        deniedList.clear();

        String[] permissions = config.get(CorePermissionKeys.PERMISSIONS);
        if (Build.VERSION.SDK_INT<23){
            listener.onFailed(deniedList);
            listener.onSucceed(Arrays.asList(permissions));
        }

        activity = CorePermissionConfig.getInstance()
                .get(CorePermissionKeys.ACTIVITY);

        for (String permission:permissions) {
            int result = ContextCompat.checkSelfPermission(activity, permission);

            if (result == PackageManager.PERMISSION_DENIED) {
                deniedList.add(permission);
            }else if (result == PackageManager.PERMISSION_GRANTED){
                boolean hasPermission = forceCheck(permission); // 是否拥有权限
                if (!hasPermission){
                    deniedList.add(permission);
                }else {
                    String op = AppOpsManagerCompat.permissionToOp(permission);
                    if (null == op && TextUtils.isEmpty(op)){
                        deniedList.add(permission);
                    }else{
                        result = AppOpsManagerCompat.noteProxyOp(activity, op, activity.getPackageName());
                        if (result == AppOpsManagerCompat.MODE_ALLOWED){
                            allowedList.add(permission);
                        }else{
                            deniedList.add(permission);
                        }
                    }
                }
            }
        }
            Intent intent = new Intent(activity, CorePermissionActivity.class);
            intent.putStringArrayListExtra("allowedList",allowedList);
            intent.putStringArrayListExtra("deniedList",deniedList);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
    }

    /**
     * 判断有没有传入 activity 和 需要请求的权限
     * @param config
     * @return
     */
    private boolean checkConfigReady(CorePermissionConfig config) {
        Activity activity = config.get(CorePermissionKeys.ACTIVITY);
        String[] permissions = config.get(CorePermissionKeys.PERMISSIONS);
        boolean isReady = false;
        if (!(activity instanceof Activity)){
            throw new RuntimeException("need with a activity or fragment");

        }else if (permissions == null || permissions.length <1){
            throw new RuntimeException("need to request at least one permission");
        }else{
            isReady = true;
        }
        return  isReady;

    }

    /**
     * 已经获取返回 True
     * @param permission
     * @return
     */
    private boolean forceCheck(String permission) {
        boolean granted = PermissionCheck.isPermissionGranted(CorePermissionConfig.getInstance()
                .get(CorePermissionKeys.ACTIVITY), permission);
        return granted;
    }



    /*********----------添加注解（未完成）------------***********************/

    /**
     *
     * @param targetObject
     */
    public void injectPremission(Object targetObject){
        checkObject(targetObject);
        Class<?> targetObjectClass = targetObject.getClass();

        // 类上注解
//        injectTypePermission(targetObject,targetObjectClass);
        // 方法上注解
        injectMethodPermission(targetObject,targetObjectClass);

    }

    /**
     * 处理方法上的注解
     * @param object
     * @param clazz
     */
    private void injectMethodPermission(Object object, Class<?> clazz) {
        Method[] allMethods = clazz.getMethods(); // 获取当前对象的所有注解
        for (Method method : allMethods){
            Annotation[] annotations = method.getAnnotations(); // 获取注解

            PermissionGranted permissionObtain = method.getAnnotation(PermissionGranted.class);
            if (permissionObtain != null){
                Log.e(TAG,"get permissionObtain"+permissionObtain);


            for (Annotation annotation : annotations){
//                checkPermissionType(annotation);
                if (annotation instanceof PermissionGranted){
                    Class<? extends Annotation> annotationType = annotation.annotationType();
                    String listenerSetter = "addPermissionListener";
                    Class<CorePermissionListener> listenerType = CorePermissionListener.class;
                    String methodSuccessName = "onSucceed"; // 成功
                    String methodFailedName = "onFailed"; // 失败

                    try {
                        Method declaredMethod = annotationType.getDeclaredMethod("value"); // 找到注解的方法
                        String[] permissions = (String[]) declaredMethod.invoke(annotation, new Object[]{}); // 取到注解上权限
                        for (String permission: permissions) {
                            DynamicHandler handler = new DynamicHandler(activity);
                            handler.addMethod(methodSuccessName,method);
                            handler.addMethod(methodFailedName,method);
                            Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(),
                                    new Class<?>[]{listenerType}, handler);
                            setPermission(permission);
                            Method addPermissionListenerMeathod = CorePermission.class.getMethod(listenerSetter, listenerType);
                            addPermissionListenerMeathod.invoke(CorePermission.class.getInterfaces(),listener);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            }
        }
    }


    private void checkObject(Object object) {
        if (object instanceof  Activity){
            activity = (Activity) object;
        }else if (object instanceof Fragment){
            activity =((Fragment) object).getActivity();
        }else if (object instanceof android.support.v4.app.Fragment){
            activity =((Fragment) object).getActivity();
        }else{
            throw new RuntimeException("need with a activity or fragment");
        }
        CorePermissionConfig.getInstance().with(activity);
    }

}
