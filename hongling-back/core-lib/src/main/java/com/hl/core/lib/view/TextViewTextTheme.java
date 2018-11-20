package com.hl.core.lib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.hl.core.lib.R;
import com.hl.core.lib.util.UtilManager;

/**
 * @Describe: 主题TextView：只有文字颜色跟随主题颜色
 * @Package: com.hl.core.lib.view
 * @Author: liyu
 * @Date: 2018/3/2 17:50
 * @Copyright: hl
 */
@SuppressWarnings(value = "all")
public class TextViewTextTheme extends TextView {

    public TextViewTextTheme(Context context) {
        super(context);
        init(context,null);
    }

    public TextViewTextTheme(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public TextViewTextTheme(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs){
        themeColor = UtilManager.selector.getThemeColor(context);
        if(attrs != null){
            TypedArray a = getContext().obtainStyledAttributes(attrs,R.styleable.core_TextViewTextThemeStyle);
            boolean checked = a.getBoolean(R.styleable.core_TextViewTextThemeStyle_core_checked, false);
            a.recycle();
            setCore_checked(checked);
        }
        unCheckedColor = context.getResources().getColor(R.color.core_text_color_333333);
        checkedBackgroundColor = context.getResources().getColor(R.color.core_bg_color);
    }

    int themeColor = -1;
    int unCheckedColor;
    int checkedBackgroundColor;

    public void setCore_checked(boolean core_checked) {
        if(core_checked){
            setTextColor(themeColor);
            setBackgroundColor(checkedBackgroundColor);
        }else {
            setTextColor(unCheckedColor);
            setBackgroundColor(Color.WHITE);
        }
    }

}
