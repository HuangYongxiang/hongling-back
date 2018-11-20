package com.hl.core.lib.network;

import android.util.SparseArray;
import android.util.SparseBooleanArray;

import com.google.gson.Gson;
import com.hl.core.lib.util.UtilManager;

import java.lang.reflect.Type;

/**
 * @Describe: 缓存数据仓库：暂时采用GSon+SP进行缓存存储
 * @Package: com.hl.eval.core
 * @Author: liyu
 * @Date: 2018/3/8 14:24
 * @Copyright: hl
 */
public class CacheRepository {

    /**---------缓存编码开始----------*/
    private static final int CACHE_CODE = 12345;
    public static int CODE = CACHE_CODE;
    public static int CACHE_CODE_REPAIR_GROUP = ++CODE;//工时工种
    public static int CACHE_CODE_REPAIR_GROUP_CHILD = ++CODE;//根据工时工种查询子集
    /**---------缓存编码结束----------*/

    public static final long TIME_ONE_YEAR = 60*24*365;//一年
    public static final long TIME_HALF_YEAR = 60*24*183;//半年
    public static final long TIME_THREE_MONTH = 60*24*91;//三个月
    public static final long TIME_TWO_MONTH = 60*24*91;//两个月
    public static final long TIME_ONE_MONTH = 60*24*30;//一个月
    public static final long TIME_HALF_MONTH = 60*24*15;//半个月
    public static final long TIME_ONE_WEEK = 60*24*7;//一周
    public static final long TIME_ONE_DAY = 60*24;//一天

    //缓存全局开关
    private boolean CACHE_ENABLE = false;

    private final String TAG = getClass().getSimpleName();
    private static CacheRepository singleton;
    private Gson gson;
    private SparseArray<Type> types;
    private SparseBooleanArray cacheEnables;

    public static CacheRepository get() {
        if (null == singleton) {
            synchronized (CacheRepository.class) {
                if (null == singleton) {
                    singleton = new CacheRepository();
                }
            }
        }
        return singleton;
    }

    private CacheRepository() {
        gson = new Gson();
        types = new SparseArray<>();
        cacheEnables = new SparseBooleanArray();
    }


    /**
     * 注册缓存
     * @param cacheCode 缓存业务码
     * @param validTime 缓存有效时间（分）
     * @param type 缓存类型映射
     */
    public CacheRepository registerCacheBusiness(int cacheCode,long validTime,Type type){
        return registerCacheBusiness(true,cacheCode,validTime,type);
    }

    /**
     * 注册缓存
     * @param cacheEnable 是否启用缓存
     * @param cacheCode 缓存业务码
     * @param validTime 缓存有效时间（分）
     * @param type 缓存类型映射
     */
    public CacheRepository registerCacheBusiness(boolean cacheEnable,int cacheCode,long validTime,Type type){
        types.put(cacheCode,type);
        cacheEnables.put(cacheCode,cacheEnable);
        UtilManager.SP.cache().put(getValidTimeName(cacheCode),validTime);
        return this;
    }

    //缓存全局开关：false关闭全局缓存 | true打开全局缓存
    public void cacheEnable(boolean cacheEnable){
        this.CACHE_ENABLE = cacheEnable;
    }

    //写缓存
    public <T> void write(int cacheCode,T obj){
        if(!CACHE_ENABLE)
            return;
        if(!cacheEnables.get(cacheCode)){
            return ;
        }
        if(cacheCode > CACHE_CODE){
            try {
                String str = gson.toJson(obj);
                UtilManager.SP.cache().put(String.valueOf(cacheCode),str);
                long current = System.currentTimeMillis();
                UtilManager.SP.cache().put(getAddCacheTimeName(cacheCode),current);
                UtilManager.Log.i(TAG,"保存缓存成功("+cacheCode+")");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //读缓存
    public <T> T read(int cacheCode){
        if(!CACHE_ENABLE)
            return null;
        if(!cacheEnables.get(cacheCode)){
            return null;
        }
        long current = System.currentTimeMillis();
        T obj = null;
        Type type = types.get(cacheCode);
        if(cacheCode > CACHE_CODE && type != null){
            //判断缓存有效时间
            long addCacheTime = getAddCacheTime(cacheCode);
            if(addCacheTime == -1)
                return null;
            long def = current - addCacheTime;
            long validTime = getValidTime(cacheCode) * 60 * 1000;
            if(validTime < 0)
                return null;
            if(def  < validTime){
                String str = UtilManager.SP.cache().getString(String.valueOf(cacheCode),"");
                try {
                    obj = gson.fromJson(str,type);
                    long end = System.currentTimeMillis();
                    double coast = (end - current)/1000.0;
                    UtilManager.Log.i(TAG,"读取缓存成功("+cacheCode+")：有效时间剩余 = "
                            +(validTime-def)/1000+"秒 | 耗时 = "+coast+"秒");
                } catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                UtilManager.Log.i(TAG,"读取缓存("+cacheCode+")：缓存已过期，开始请求网络");
            }

        }
        return obj;
    }

    private String getValidTimeName(int cacheCode){
        return String.valueOf(cacheCode).concat("_valid_time");
    }

    private String getAddCacheTimeName(int cacheCode){
        return String.valueOf(cacheCode).concat("_add_time");
    }
    private long getValidTime(int cacheCode){
        return UtilManager.SP.cache().getLong(getValidTimeName(cacheCode),-1);
    }

    private long getAddCacheTime(int cacheCode){
        return UtilManager.SP.cache().getLong(getAddCacheTimeName(cacheCode),-1);
    }

    //删除所有缓存
    public void deleteAllCache(){
        UtilManager.SP.cache().clear();
        UtilManager.Log.i(TAG,"清除所有缓存成功");
    }



}
