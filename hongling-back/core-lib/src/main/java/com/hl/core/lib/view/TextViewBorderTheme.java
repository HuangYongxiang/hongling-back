package com.hl.core.lib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.hl.core.lib.R;
import com.hl.core.lib.util.UtilManager;

/**
 * @Describe: 主题TextView：圆角边框，边框与文字颜色随着主题改变
 * @Package: com.hl.core.lib.view
 * @Author: liyu
 * @Date: 2018/3/2 17:50
 * @Copyright: hl
 */
@SuppressWarnings(value = "all")
public class TextViewBorderTheme extends TextView {

    public TextViewBorderTheme(Context context) {
        super(context);
        init(context, null);
    }

    public TextViewBorderTheme(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TextViewBorderTheme(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context,AttributeSet attrs){
        themeColor = UtilManager.selector.getThemeColor(context);
        if(attrs != null){
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.core_TextViewTextThemeStyle);
            boolean checked = a.getBoolean(R.styleable.core_TextViewTextThemeStyle_core_checked, false);
            a.recycle();
            setCore_checked(checked);
        }
        int themeColorPrimary = UtilManager.selector.getThemeColorPrimary(context);
        setTextColor(themeColor);
        final float cornerRad = UtilManager.Density.dip2px(context,3);
        int strokeWidth = UtilManager.Density.dip2px(context,1);
        float[] corner = new float[]{cornerRad,cornerRad,cornerRad,cornerRad,cornerRad,cornerRad,cornerRad,cornerRad};
        checked = UtilManager.selector.getDrawable(corner,themeColorPrimary,strokeWidth,themeColor);
        unCheckedTextColor = context.getResources().getColor(R.color.core_text_color_333333);
        core_line_color = context.getResources().getColor(R.color.core_line_color);
        unchecked = UtilManager.selector.getDrawable(corner,themeColorPrimary,strokeWidth,core_line_color);
        setClickable(true);
    }

    GradientDrawable checked,unchecked;
    int themeColor = -1;
    int core_line_color,unCheckedTextColor;

    public void setCore_checked(boolean core_checked) {
        if(core_checked){
            setTextColor(themeColor);
            setBackground(checked);
        }else {
            setTextColor(unCheckedTextColor);
            setBackground(unchecked);
        }
    }

}
