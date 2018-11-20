package com.hl.core.lib.plugin.theme;

import android.content.Context;

import com.hl.core.lib.R;
import com.hl.core.lib.plugin.CorePlugin;
import com.hl.core.lib.util.UtilManager;

/**
 * @Describe: 主题插件
 * @Package: com.hl.core.lib.plugin.theme
 * @Author: liyu
 * @Date: 2018/1/8 17:49
 * @Copyright: hl
 */
public class ThemePlugin extends CorePlugin {

    @Override
    public boolean initPlugin(Context context) {
        ThemeManager.get()
                .registerThemeDefault(R.style.core_ThemeDefault)//默认主题
                .registerTheme(ThemeConstant.THEME_NIGHT,R.style.core_ThemeNight);//夜色主题
        UtilManager.Log.d(TAG,"主题插件初始化成功");
        return true;
    }

}
