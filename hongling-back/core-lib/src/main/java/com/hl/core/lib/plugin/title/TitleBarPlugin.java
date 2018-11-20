package com.hl.core.lib.plugin.title;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hl.core.lib.R;
import com.hl.core.lib.activity.CoreActivity;
import com.hl.core.lib.plugin.CorePlugin;
import com.hl.core.lib.plugin.IPlugin;
import com.hl.core.lib.plugin.common.title.ITitleBar;
import com.hl.core.lib.plugin.common.title.ITitleBarPlugin;
import com.hl.core.lib.util.UtilManager;

/**
 * @Describe: 基础标题栏插件
 * @Package: com.hl.eval.core.plugin.title
 * @Author: liyu
 * @Date: 2018/1/3 12:49
 * @Copyright: hl
 */
public class TitleBarPlugin extends CorePlugin implements IPlugin,ITitleBarPlugin {

    private TitleBar titleBar;

    @Override
    public boolean initPlugin(Context context) {
        UtilManager.Log.d(TAG,"基础标题栏插件初始化成功");
        return true;
    }

    @Override
    public void bindTitleBar(ITitleBar titleBar) {
        this.titleBar = (TitleBar) titleBar;
    }

    @Override
    public boolean hasTitleBar() {
        return titleBar != null && titleBar.hasTitleBar;
    }

    @Override
    public ViewGroup initTitleView(CoreActivity context, View.OnClickListener onClickListener) {
        if(hasTitleBar()){
            LinearLayout parentLinearLayout = new LinearLayout(context);
            RelativeLayout.LayoutParams parentParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            parentLinearLayout.setOrientation(LinearLayout.VERTICAL);
            parentLinearLayout.setLayoutParams(parentParams);
            int[] attribute = new int[] { R.attr.core_background_color };
            TypedArray array = context.obtainStyledAttributes(null, attribute);
            parentLinearLayout.setBackgroundColor(array.getColor(0,-1));
            array.recycle();
            titleBar.initView(context,parentLinearLayout,onClickListener);
            return parentLinearLayout;
        }
        return null;
    }





}
