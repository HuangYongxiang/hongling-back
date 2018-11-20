package com.hl.core.lib.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.hl.core.lib.util.UtilManager;

/**
 * @Describe: 主题ImageView：颜色随着主题改变
 * @Package: com.hl.core.lib.view
 * @Author: liyu
 * @Date: 2018/3/6 14:40
 * @Copyright: hl
 */
@SuppressWarnings(value = "all")
public class ImageViewTheme extends ImageView {

    public ImageViewTheme(Context context) {
        super(context);
        init(context);
    }

    public ImageViewTheme(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ImageViewTheme(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        int themeColor = UtilManager.selector.getThemeColor(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getDrawable().setTint(themeColor);
        }
    }


}
