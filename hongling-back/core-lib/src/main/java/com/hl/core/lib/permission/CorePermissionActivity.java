package com.hl.core.lib.permission;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.AppOpsManagerCompat;
import android.text.TextUtils;
import android.util.Log;

import com.hl.core.lib.permission.annotation.PermissionGranted;
import com.hl.core.lib.permission.checklibs.PermissionCheck;
import com.hl.core.lib.permission.listener.CorePermissionListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Describe: 权限检查：实际获取回调的activity
 * @Package: com.hl.core.lib.permission
 * @Author: liyu
 * @Date: 2018/1/10/010 17:03
 * @Copyright: hl
 */

public class CorePermissionActivity extends Activity {

    private CorePermissionListener listener;
    private String TAG = this.getClass().getSimpleName();
    private List<String> deniedList;
    private List<String> allowedList;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getIntent().getSerializableExtra("listener");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getWindow().getStatusBarColor());
        }
        deniedList = getIntent().getStringArrayListExtra("deniedList");
        allowedList = getIntent().getStringArrayListExtra("allowedList");
        request();

    }


    /**
     * 请求权限
     */
    private void request() {

        String[] permissions = new String[deniedList.size()];
        permissions = deniedList.toArray(permissions);

        if (permissions != null && permissions.length > 0){
            requestPermission(100,permissions);
        }else{
            setResultListener(deniedList, allowedList);
        }
    }


    /**
     * 申请指定的权限.
     */
    public void requestPermission(final int requestCode, final String... permissions) {
//        Log.e("tine",""+ SystemClock.currentThreadTimeMillis());

        ActivityCompat.requestPermissions(this, permissions, requestCode);

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        List<String> resultDeniedList = new ArrayList<>();
        List<String> resultAllowList = new ArrayList<>();
        List<String> resultRationaleList = new ArrayList<>(); // 拒绝过
        resultDeniedList.clear();
        resultAllowList.clear();
        resultAllowList.addAll(allowedList);
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults.length > 0
                    && grantResults[i] == PackageManager.PERMISSION_GRANTED){ // 已经获取了
                String op = AppOpsManagerCompat.permissionToOp(permissions[i]);

                if (null == op && TextUtils.isEmpty(op)) {
                    resultDeniedList.add(permissions[i]);
                    continue;
                }else{
                    int result = AppOpsManagerCompat.noteProxyOp(this, op, getPackageName());
                    if (result ==  AppOpsManagerCompat.MODE_ALLOWED && PermissionCheck.isPermissionGranted(this,permissions[i])){
                        if (!resultAllowList.contains(permissions[i])){
                            resultAllowList.add(permissions[i]);
                        }

                        for (Iterator it = resultDeniedList.iterator(); it.hasNext();) {
                            String str = (String)it.next();
                            if (str.equals(permissions[i])){
                                    it.remove();
                                }
                            }
                    }else {
                        ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]); // true 没有完全禁止弹出
                    }
                }
            }else if (grantResults.length > 0
                    && grantResults[i] == PackageManager.PERMISSION_DENIED){
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this,permissions[i])){
                    resultRationaleList.add(permissions[i]);
                }else {
                    resultDeniedList.add(permissions[i]);
                }

            }
        }
        if (resultRationaleList != null && resultRationaleList.size() > 0){
            showDialog(resultDeniedList, resultAllowList,resultRationaleList);
        }else {
            setResultListener(resultDeniedList, resultAllowList);
        }

    }

    private void setResultListener(List<String> resultDeniedList, List<String> resultAllowList) {
        setResultListener(resultDeniedList,resultAllowList,null);
    }
    private void setResultListener(List<String> resultDeniedList, List<String> resultAllowList,List<String> resultRationaleList ) {
        if (resultRationaleList != null&& resultRationaleList.size() > 0){
            resultDeniedList.addAll(resultRationaleList);
        }
        if (resultDeniedList.size() == 0 && CorePermissionConfig.targetObject != null){
            // 全部获取走注解方法
            getPermission();
            this.finish();
        }else {
            if (CorePermissionConfig.getInstance().get(CorePermissionKeys.NECESSARY)){
                showDialog(resultDeniedList,resultAllowList,new ArrayList<>());
            }else{
                CorePermissionConfig.permissionListener.onSucceed(resultAllowList);
                CorePermissionConfig.permissionListener.onFailed(resultDeniedList);
                this.finish();
            }
        }
    }

    /**
     * 申请的所有权限都成功之后，走注解 PermissionGranted 方法
     * @PermissionGranted
     */
    private void getPermission() {
        Class<?> clazz = CorePermissionConfig.targetObject.getClass();

        while (clazz != null) {
            for (Method method : clazz.getDeclaredMethods()) {
                PermissionGranted ann = method.getAnnotation(PermissionGranted.class);
                if (ann != null) {
                        if (method.getParameterTypes().length > 0) {  // @PermissionGranted 的方法不能传入参数
                            throw new RuntimeException(
                                    "Cannot execute method " + method.getName() + " because it is non-void method and/or has input parameters.");
                        }
                        try {
                            if (!method.isAccessible()) { // 如果注解的方法不是public
                                method.setAccessible(true);
                            }
                            method.invoke( CorePermissionConfig.targetObject);
                        } catch (IllegalAccessException e) {
                            Log.e(TAG, "runDefaultMethod:IllegalAccessException", e);
                        } catch (InvocationTargetException e) {
                            Log.e(TAG, "runDefaultMethod:InvocationTargetException", e);
                        }
                }
            }

            clazz = clazz.getSuperclass();
        }
    }


    /**
     * 展示所有被手动拒绝的权限
     * @param resultRationaleList
     */
    private void showDialog(final List<String> resultDeniedList, final List<String> resultAllowList,
                            final List<String> resultRationaleList) {
        StringBuilder msg = new StringBuilder();
        msg.append("您已经拒绝使用以下权限：\n");
        if (resultDeniedList != null && resultDeniedList.size()>0){
            resultRationaleList.addAll(resultDeniedList);
        }
        for (String permission:resultRationaleList) {
            msg.append("\n"+CorePermissionUtils.getName(permission));
        }
        String tipMessage = msg.toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("权限申请")
                .setMessage(tipMessage
                        +"\n\n"
                        +"请进入设置手动赋予")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (CorePermissionConfig.getInstance().get(CorePermissionKeys.NECESSARY)){
                            Activity activity = CorePermissionConfig.getInstance()
                                    .get(CorePermissionKeys.ACTIVITY);
                            activity.onBackPressed();
                            CorePermissionActivity.this.finish();
                        }else{
                            setResultListener(resultDeniedList,resultAllowList,resultRationaleList);
                        }
                    }
                })
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", CorePermissionActivity.this.getPackageName(), null);
                        intent.setData(uri);
                        CorePermissionActivity.this.startActivityForResult(intent,0);

                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }



    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String[] permission = CorePermissionConfig.getInstance().get(CorePermissionKeys.PERMISSIONS);
        if (listener != null){
            CorePermission.getInstance().with(this).setMultiplePermission(permission).requestPermission(listener);
        }else {
            listener = CorePermissionConfig.getInstance().get(CorePermissionKeys.LISTENER);
            if (listener != null){
                this.finish();
                Boolean isNecessary = CorePermissionConfig.getInstance().get(CorePermissionKeys.NECESSARY);
                CorePermission.getInstance().with(this).setMultiplePermission(permission)
                        .isNecessary(isNecessary).requestPermission(listener);

            }
        }
    }
}
