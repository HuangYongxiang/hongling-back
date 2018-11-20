package com.hl.core.lib.permission.checklibs;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.hl.core.lib.permission.utils.AudioRecordManager;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Describe:  1. 权限检查
 * @Package: com.hl.core.lib.permission.checklibs;
 * @Author: liyu
 * @Date: 2018/1/4 14:42
 * @Copyright: hl
 */
public class PermissionCheck {

    private static Fragment fragment;
    private static android.support.v4.app.Fragment supportFragmet;
    private static Activity activity;
    private static String TAG= "PermissionCheck";

    public static boolean isPermissionGranted(Object object, String permission) {
        boolean isPermissionGranted = false;
        checkObject(object);
//        Log.e(TAG, SystemClock.currentThreadTimeMillis()+"");
        try {
            switch (permission) {
                case Manifest.permission.CAMERA:
                    isPermissionGranted = checkCamera(activity);
//                    Log.e(TAG, "CAMERA "+SystemClock.currentThreadTimeMillis()+"");
                    break;
                case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                    isPermissionGranted = checkWriteStorage(activity);
//                    Log.e(TAG, "SD "+SystemClock.currentThreadTimeMillis()+"");
                    break;
                case Manifest.permission.RECORD_AUDIO:
                    isPermissionGranted = checkRecordAudio(activity);
//                    Log.e(TAG, "AUDIO "+SystemClock.currentThreadTimeMillis()+"");
                    break;
                case Manifest.permission.ACCESS_FINE_LOCATION:
                    isPermissionGranted = checkLocation(activity);
//                    Log.e(TAG, "LOCATION "+SystemClock.currentThreadTimeMillis()+"");
                    break;
                case Manifest.permission.READ_SMS:
                    isPermissionGranted = checkReadSms(activity);
//                    Log.e(TAG, "SMS "+SystemClock.currentThreadTimeMillis()+"");
                    break;
            }


        } catch (Exception e) {
            //  如果检查设备失败，打开设备失败
            try {
                Log.e(TAG,"e = "+e.getMessage());
                throw new Exception("PermissionCheck isPermissionGranted error :" + e.getMessage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } finally {
            return isPermissionGranted;
        }
    }

    /**
     * 如果不是 Activity Fragment v4Fragment
     * 抛出异常异常
     * @param object
     */
    private static void checkObject(Object object) {
        boolean isSuitability = false;
        if (object instanceof Fragment) {
            fragment = (Fragment) object;
            activity = fragment.getActivity();
            isSuitability = true;
        } else if (object instanceof android.support.v4.app.Fragment) {
            supportFragmet = (android.support.v4.app.Fragment) object;
            activity = supportFragmet.getActivity();
            isSuitability = true;
        } else if (object instanceof Activity) {
            activity = (Activity) object;
            isSuitability = true;
        }
        if (!isSuitability) {
            throw new IllegalArgumentException("Caller must be an Activity or a Fragment.");
        }
    }


    /**
     * 检查Camera权限
     * @param activity
     * @return 是否获得该权限
     */
    private static boolean checkCamera(Activity activity) {
        return true;
//        Camera camera = null;
//        try {
//            camera = Camera.open();
//            return true;
//        } catch (Exception e) {
//            return false;
//        } finally {
//            if (camera != null) {
//                camera.release();
//            }
//
//        }
    }

    /**
     * 检查写SD卡权限
     * @param activity
     * @return 是否获得该权限
     * @throws Exception
     */
    private static boolean checkWriteStorage(Activity activity) throws Exception {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath(), "permissions4m");
        if (!file.exists()) {
            try {
                boolean newFile = file.createNewFile();
                return newFile;
            } catch (IOException var4) {
                var4.printStackTrace();
                return false;
            }
        } else {
            return file.delete();
        }
    }

    /**
     * 检查录音权限
     * @param activity
     * @return 是否获得该权限
     * @throws Exception
     */
    private static boolean checkRecordAudio(Activity activity) throws Exception {
        AudioRecordManager recordManager = new AudioRecordManager();
        recordManager.startRecord(activity.getExternalFilesDir(Environment.DIRECTORY_RINGTONES) + "/" + "hongling" + ".env");
        recordManager.stopRecord();
        return recordManager.getSuccess();
    }

    /**
     * 检查短信
     * @param activity
     * @return 是否获得该权限
     * @throws Exception
     */
    private static boolean checkReadSms(Activity activity) throws Exception {
        Cursor cursor = activity.getContentResolver().query(Uri.parse("content://sms/"), (String[])null, (String)null, (String[])null, (String)null);
        if(cursor != null) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    /**
     * 检查位置
     * 此处忽略权限检查警告
     * @param activity
     * @return 是否获得该权限
     * @throws Exception
     */
    @SuppressLint("MissingPermission")
    private static boolean checkLocation(Activity activity) throws Exception {
        boolean hasPermission = false;
        final LocationManager locationManager = (LocationManager) activity.getSystemService(Context
                .LOCATION_SERVICE);
        List<String> list = locationManager.getProviders(true);
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (list.contains(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
            hasPermission =  true;
        } else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
            hasPermission =  true;
        }
        return hasPermission;
    }




}
