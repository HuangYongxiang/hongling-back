package com.hl.chenzg.mylibrary;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;

/**
 * @Describe: 文件 绝对路径转provide路径
 * @Package: com.hl.core.lib.permission.file
 * @Author: liyu
 * @Date: 2018/1/10/010 17:00
 * @Copyright: hl
 */


public class CoreFileProvide {
    /**
     * @desc 如果需要给其他App共享，不能直接传file的路径，需要生成 uri
     * @param activity
     * @param file
     * @return uri
     */
    private static String TAG = "CoreFileProvide";
    public static Uri getUriForFile(Activity activity, File file){
        Uri uri = null;

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            String packageName = activity.getApplication().getPackageName();
            uri =  FileProvider.getUriForFile(activity,
                    packageName +".provider",file);;
        }else{
            uri = Uri.fromFile(file);
        }
        return uri;
    }




}
