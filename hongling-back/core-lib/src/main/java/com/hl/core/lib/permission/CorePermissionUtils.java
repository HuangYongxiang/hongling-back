package com.hl.core.lib.permission;

import java.util.HashMap;
import java.util.Map;

/**
 * @Describe: 权限检查帮助类，用于描述敏感权限
 * @Package: com.hl.core.lib.permission
 * @Author: liyu
 * @Date: 2018/1/10/010 17:00
 * @Copyright: hl
 */


public class CorePermissionUtils {
    static  HashMap<String,String> nameMap =  new HashMap<String,String>();
    static  HashMap<String,String> useMap =  new HashMap<String,String>();

    static {
        nameMap.put(android.Manifest.permission.CAMERA,"相机");
        nameMap.put(android.Manifest.permission.RECORD_AUDIO,"录音");
        nameMap.put(android.Manifest.permission.GET_ACCOUNTS,"获取账户");
        nameMap.put(android.Manifest.permission.CALL_PHONE,"拨打电话");
        nameMap.put(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,"存储");
        nameMap.put(android.Manifest.permission.ACCESS_FINE_LOCATION,"定位");
        nameMap.put(android.Manifest.permission.READ_SMS,"短信");
    }
    static {
        useMap.put(android.Manifest.permission.CAMERA,":无法使用证件识别");
        useMap.put(android.Manifest.permission.RECORD_AUDIO,":无法使用录音功能");
//        useMap.put(android.Manifest.permission.GET_ACCOUNTS,"获取账户");
        useMap.put(android.Manifest.permission.CALL_PHONE,":无法拨打电话");
        useMap.put(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,":无法保存案件信息");
        useMap.put(android.Manifest.permission.ACCESS_FINE_LOCATION,":定位失败");
        useMap.put(android.Manifest.permission.READ_SMS,"无法发送信息权限");
    }

    public static String getName(String key){
        for (Map.Entry<String,String> entry: nameMap.entrySet()) {
            if (entry.getKey().equals(key)){
                return entry.getValue();
            }
        }
        return "";
    }
    public static String getUserInfo(String key){
        for (Map.Entry<String,String> entry: useMap.entrySet()) {
            if (entry.getKey().equals(key)){
                return entry.getValue();
            }
        }
        return "";
    }
}
