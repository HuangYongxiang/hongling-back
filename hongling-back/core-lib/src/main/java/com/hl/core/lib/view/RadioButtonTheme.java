package com.hl.core.lib.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.hl.core.lib.util.UtilManager;

/**
 * @Describe: 主题RadioButton
 * @Package: com.hl.core.lib.view
 * @Author: liyu
 * @Date: 2018/2/26 18:30
 * @Copyright: hl
 */
@SuppressWarnings(value = "all")
public class RadioButtonTheme extends RadioButton {

    public RadioButtonTheme(Context context) {
        super(context);
        init(context);
    }

    public RadioButtonTheme(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RadioButtonTheme(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        int themeColor = UtilManager.selector.getThemeColor(context);
        int themeColorPrimary = UtilManager.selector.getThemeColorPrimary(context);
        int strokeWidth = UtilManager.Density.dip2px(context,1);
        float[] corner = new float[]{0,0,0,0,0,0,0,0};
        GradientDrawable themeDrawable = UtilManager.selector.getDrawable(corner,themeColor,strokeWidth,themeColor);
        GradientDrawable primaryDrawable = UtilManager.selector.getDrawable(corner,themeColorPrimary,strokeWidth,themeColorPrimary);
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{themeDrawable,primaryDrawable});
        layerDrawable.setLayerInset(1,-1, 0,-1,UtilManager.Density.dip2px(context,2));
        GradientDrawable unchecked = UtilManager.selector.getDrawable(corner,themeColorPrimary,strokeWidth,themeColorPrimary);
        StateListDrawable selector = UtilManager.selector.getSelector(layerDrawable, unchecked);
        ViewCompat.setBackground(this,selector);
    }


}
