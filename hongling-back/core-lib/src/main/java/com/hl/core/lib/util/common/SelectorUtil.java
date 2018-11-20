package com.hl.core.lib.util.common;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import com.hl.core.lib.R;

/**
 * @Describe: 选择器、获取主题颜色工具类
 * @Package: com.hl.core.lib.util.common
 * @Author: liyu
 * @Date: 2018/2/27 9:26
 * @Copyright: hl
 */
public class SelectorUtil {

    public int getThemeColor(Context context){
        int[] attribute = new int[] { R.attr.core_theme_color };
        TypedArray array = context.obtainStyledAttributes(null, attribute);
        int themeColor = array.getColor(0,-1);
        array.recycle();
        return themeColor;
    }

    public int getThemeColorPrimary(Context context){
        int[] attribute = new int[] { R.attr.core_theme_color_primary };
        TypedArray array = context.obtainStyledAttributes(null, attribute);
        int themeColorPrimary = array.getColor(0,-1);
        array.recycle();
        return themeColorPrimary;
    }

    public GradientDrawable getDrawable(float[] cornerRadii, int fillColor, int strokeWidth, int strokeColor) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadii(cornerRadii);
        gradientDrawable.setColor(fillColor);
        gradientDrawable.setStroke(strokeWidth, strokeColor);
        return gradientDrawable;
    }

    public StateListDrawable getSelector(Drawable checkedDraw, Drawable uncheckedDraw) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_checked}, checkedDraw);
        stateListDrawable.addState(new int[]{-android.R.attr.state_checked}, uncheckedDraw);
        return stateListDrawable;
    }

    public ColorStateList getColorStateList(int checked, int unchecked) {
        int[] colors = new int[] { checked, unchecked };
        int[][] states = new int[2][];
        states[0] = new int[] { android.R.attr.state_checked };
        states[1] = new int[] { -android.R.attr.state_checked };
        return new ColorStateList(states, colors);
    }



}
