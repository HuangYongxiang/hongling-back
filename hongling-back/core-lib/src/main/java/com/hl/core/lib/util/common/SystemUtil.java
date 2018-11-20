package com.hl.core.lib.util.common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.File;

/**
 * @Describe:
 * @Package: com.hl.core.lib.util.common
 * @Author: liyu
 * @Date: 2018/1/8 10:44
 * @Copyright: hl
 */
public class SystemUtil {

    private static DisplayMetrics mDm;

    // 获取包的版本名称
    public static String getPackageVersion(Context mContext) {
        String verName = "";
        try {
            verName = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verName;
    }
    // 获取包的版本名称
    public static int getPackageVersionCode(Context mContext) {
        int verCode = -1;
        try {
            verCode = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verCode;
    }

    // 获取系统简略信息
    public String getSimpleSystemInfo() {
        String sdk_ver;
        sdk_ver = android.os.Build.VERSION.SDK;
        sdk_ver += "_";
        sdk_ver += android.os.Build.MANUFACTURER;
        sdk_ver += "_";
        sdk_ver += android.os.Build.MODEL;
        return sdk_ver;
    }

    // 获取IMEI号
    public String getIMEI(Context mContext) {
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        if (null == imei) {
            imei = "000000000000000";
        }
        return imei;
    }

    public static DisplayMetrics getScreenSize(Context mContext) {
        if (null == mDm) {
            mDm = new DisplayMetrics();
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            if(wm != null){
                wm.getDefaultDisplay().getMetrics(mDm);
            }
        }
        return mDm;
    }

    // 获取包源代码路径
    public static String getPackageSourceDir(Context mContext) {
        try {
            return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).applicationInfo.sourceDir;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param context
     * 安装apk，并关闭登录页面
     * */
    public static void install(Context context, String filePath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(filePath)),
                "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    /**
     * 获取应用名称
     * @param context 上下文
     */
    public String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }



}
