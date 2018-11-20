package com.hl.core.lib.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.hl.core.lib.util.UtilManager;

/**
 * @Describe: 主题TextView：矩形
 * @Package: com.hl.core.lib.view
 * @Author: liyu
 * @Date: 2018/2/26 17:50
 * @Copyright: hl
 */
@SuppressWarnings(value = "all")
public class TextViewRecTheme extends TextView {

    public TextViewRecTheme(Context context) {
        super(context);
        init(context);
    }

    public TextViewRecTheme(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TextViewRecTheme(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        int themeColor = UtilManager.selector.getThemeColor(context);
        int themeColorPrimary = UtilManager.selector.getThemeColorPrimary(context);
        setTextColor(themeColorPrimary);
        final float cornerRad = 0;
        int strokeWidth = UtilManager.Density.dip2px(context,1);
        float[] corner = new float[]{cornerRad,cornerRad,cornerRad,cornerRad,cornerRad,cornerRad,cornerRad,cornerRad};
        GradientDrawable drawable = UtilManager.selector.getDrawable(corner,themeColor,strokeWidth,themeColor);
        setBackground(drawable);
    }

}
