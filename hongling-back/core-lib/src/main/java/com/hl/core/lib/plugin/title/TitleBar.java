package com.hl.core.lib.plugin.title;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hl.core.lib.R;
import com.hl.core.lib.activity.CoreActivity;
import com.hl.core.lib.plugin.common.title.ITitleBar;

/**
 * @Describe: 标题栏
 * @Package: com.hl.eval.core.plugin.title
 * @Author: liyu
 * @Date: 2018/1/4 15:41
 * @Copyright: hl
 */
public class TitleBar implements ITitleBar {

    public boolean hasTitleBar;//是否有标题栏
    public boolean showBack;//显示返回按钮
    public boolean showTitle;//显示标题
    public boolean showCloseBtn;//显示左侧关闭按钮
    public boolean showRightBtn; //显示右侧文字按钮`
    public boolean jumpTrackNode;//强制跳过跟踪节点拦截器
    public Class targetTrackNode;//目标节点
    private TextView title_tv;
    private ImageView title_back_btn;
    private ImageView title_left_img;
    public int title_left_img_resId;//左侧图片按钮
    public TextView title_right_btn;
    private TextView title_left_close_btn;
    public String title;//标题
    public String rightBtnText;//右侧按钮文字

    @Override
    public void init() {
        hasTitleBar = true;
        showTitle = true;
        showBack = true;
        showCloseBtn = false;
        showRightBtn = false;
        title_left_img_resId = -1;
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
        title_left_close_btn.setVisibility(showCloseBtn?View.VISIBLE:View.INVISIBLE);
        title_back_btn.setVisibility(showBack?View.VISIBLE:View.INVISIBLE);
        if(title_left_img_resId == -1){
            title_left_img.setVisibility(View.INVISIBLE);
        }else{
            title_left_img.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Class bindPlugin() {
        return TitleBarPlugin.class;
    }

    @Override
    public void initView(CoreActivity context, ViewGroup parentView, View.OnClickListener onClickListener) {
        LayoutInflater.from(context).inflate(R.layout.core_toolbar_main, parentView, true);
        //标题
        title_tv = (TextView) parentView.findViewById(R.id.title_tv);
        if(!TextUtils.isEmpty(title)){
            title_tv.setText(title);
        }
        title_tv.setVisibility(showTitle?View.VISIBLE:View.INVISIBLE);
        //返回按钮
        title_back_btn = (ImageView) parentView.findViewById(R.id.title_back_btn);
        title_back_btn.setVisibility(showBack?View.VISIBLE:View.INVISIBLE);
        if(showBack){
            title_back_btn.setOnClickListener(view -> context.onBackPressed());
        }
        //左侧图片
        title_left_img = (ImageView) parentView.findViewById(R.id.title_left_img);
        if(title_left_img_resId == -1){
            title_left_img.setVisibility(View.INVISIBLE);
        }else{
            title_left_img.setVisibility(View.VISIBLE);
            title_left_img.setImageDrawable(context.getResources().getDrawable(title_left_img_resId));
        }
        if(onClickListener != null){
            title_left_img.setOnClickListener(onClickListener);
        }
        //左侧关闭按钮
        title_left_close_btn = (TextView) parentView.findViewById(R.id.title_left_close_btn);
        title_left_close_btn.setVisibility(showCloseBtn?View.VISIBLE:View.INVISIBLE);
        title_left_close_btn.setOnClickListener(view -> context.trackNodeClose(targetTrackNode, jumpTrackNode));
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
    public void destroy() {
        title_tv = null;
        title_back_btn = null;
        title_right_btn = null;
        title_left_close_btn = null;
        title_left_img = null;
    }
}
