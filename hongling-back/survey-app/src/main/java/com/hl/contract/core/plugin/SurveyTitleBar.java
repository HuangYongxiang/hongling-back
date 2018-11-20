package com.hl.contract.core.plugin;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hl.core.lib.activity.CoreActivity;
import com.hl.core.lib.plugin.common.title.ITitleBar;
import com.hl.contract.R;

/**
 * @Describe: 查勘标题栏
 * @Package: com.hl.contract.core.plugin
 * @Author: liyu
 * @Date: 2018/3/28 11:43
 * @Copyright: hl
 */
public class SurveyTitleBar implements ITitleBar {

    public boolean hasTitleBar;//是否有标题栏
    public boolean showTitle;//显示标题
    public boolean showRightBtn; //显示右侧文字按钮
    public boolean showLeftBtn; //显示左侧按钮
    private TextView title_tv;
    private ImageView title_left_img;
    public TextView title_right_btn;
    public String title;//标题
    public String rightBtnText;//右侧按钮文字

    @Override
    public void init() {
        hasTitleBar = true;
        showTitle = true;
        showLeftBtn = true;
        showRightBtn = false;
    }

    @Override
    public Class bindPlugin() {
        return SurveyTitleBarPlugin.class;
    }

    @Override
    public void initView(CoreActivity context, ViewGroup parentView, View.OnClickListener onClickListener) {
        LayoutInflater.from(context).inflate(R.layout.survey_toolbar_main, parentView, true);
        //标题
        title_tv = (TextView) parentView.findViewById(R.id.title_tv);
        if(!TextUtils.isEmpty(title)){
            title_tv.setText(title);
        }
        title_tv.setVisibility(showTitle?View.VISIBLE:View.INVISIBLE);
        //左侧图片
        title_left_img = (ImageView) parentView.findViewById(R.id.title_left_img);
        title_left_img.setVisibility(showLeftBtn?View.VISIBLE:View.INVISIBLE);
        if(onClickListener != null){
            title_left_img.setOnClickListener(onClickListener);
        }
        //右侧按钮
        title_right_btn = (TextView) parentView.findViewById(R.id.title_right_btn);
        title_right_btn.setVisibility(showRightBtn?View.VISIBLE:View.INVISIBLE);
        if(onClickListener != null){
            title_right_btn.setOnClickListener(onClickListener);
        }
        if(!TextUtils.isEmpty(rightBtnText)){
            title_right_btn.setVisibility(View.VISIBLE);
            title_right_btn.setText(rightBtnText);
        }else{
            title_right_btn.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void updateTitle() {
        title_tv.setText(title);
        if(!TextUtils.isEmpty(rightBtnText) && showRightBtn){
            title_right_btn.setText(rightBtnText);
            title_right_btn.setVisibility(View.VISIBLE);
        }else{
            title_right_btn.setVisibility(View.INVISIBLE);
        }
        title_left_img.setVisibility(showLeftBtn?View.VISIBLE:View.INVISIBLE);
    }

    @Override
    public void destroy() {
        title_tv = null;
        title_right_btn = null;
        title_left_img = null;
    }
}
