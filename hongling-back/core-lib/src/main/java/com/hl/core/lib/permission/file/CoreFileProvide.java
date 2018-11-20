package com.hl.core.lib.permission.file;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.content.FileProvider;

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
    public static Uri getUriForFile(Activity activity, File file){
        return FileProvider.getUriForFile(activity,
                activity.getApplication().getPackageName()+".provider",file);
    }

}
