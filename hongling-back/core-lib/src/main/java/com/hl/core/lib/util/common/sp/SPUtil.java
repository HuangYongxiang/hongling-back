package com.hl.core.lib.util.common.sp;

import android.content.Context;

import com.hl.core.lib.CoreApplication;

/**
 * @Describe: SP管理
 * @Package: com.hl.core.lib.util.common.sp
 * @Author: liyu
 * @Date: 2018/1/23 15:41
 * @Copyright: hl
 */
public class SPUtil {

    /**
     * 主题SP定制
     */
    public CoreSharePreference theme(){
        return new CoreSharePreference(CoreApplication.get().getSharedPreferences("sp_core_theme", Context.MODE_PRIVATE));
    }

    /**
     * 查勘模块SP定制
     */
    public CoreSharePreference survey(){
        return new CoreSharePreference(CoreApplication.get().getSharedPreferences("sp_core_survey", Context.MODE_PRIVATE));
    }

    /**
     * 定损模块SP定制
     */
    public CoreSharePreference eval(){
        return new CoreSharePreference(CoreApplication.get().getSharedPreferences("sp_core_eval", Context.MODE_PRIVATE));
    }

    /**
     * 缓存SP定制
     */
    public CoreSharePreference cache(){
        return new CoreSharePreference(CoreApplication.get().getSharedPreferences("sp_core_cache", Context.MODE_PRIVATE));
    }



}
