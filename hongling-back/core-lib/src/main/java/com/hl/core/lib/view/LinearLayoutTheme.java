package com.hl.core.lib.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.hl.core.lib.util.UtilManager;

/**
 * @Describe: 主题LinearLayout：背景色随着主题变化
 * @Package: com.hl.core.lib.view
 * @Author: liyu
 * @Date: 2018/3/8 17:50
 * @Copyright: hl
 */
@SuppressWarnings(value = "all")
public class LinearLayoutTheme extends LinearLayout {

    public LinearLayoutTheme(Context context) {
        super(context);
        init(context);
    }

    public LinearLayoutTheme(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LinearLayoutTheme(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        int themeColor = UtilManager.selector.getThemeColor(context);
        final float cornerRad = 0;
        int strokeWidth = UtilManager.Density.dip2px(context,1);
        float[] corner = new float[]{cornerRad,cornerRad,cornerRad,cornerRad,cornerRad,cornerRad,cornerRad,cornerRad};
        GradientDrawable drawable = UtilManager.selector.getDrawable(corner,themeColor,strokeWidth,themeColor);
        setBackground(drawable);
    }

}
