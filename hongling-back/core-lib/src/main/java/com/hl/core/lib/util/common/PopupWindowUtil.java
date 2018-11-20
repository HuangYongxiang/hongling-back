package com.hl.core.lib.util.common;

import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hl.core.lib.R;


/**
 * @Describe:自定义dialog
 * @Package: com.hl.core.lib.util.common
 * @Author: liyu
 * @Date: 2018/1/8 10:44
 * @Copyright: hl
 */

public class PopupWindowUtil {
    PopupWindow popupWindow;
    Window window;
    static PopupWindowUtil popupWindowUtinl;
    public static PopupWindowUtil getInitince(Window window){
        if(popupWindowUtinl==null){
            popupWindowUtinl=new PopupWindowUtil(window);
        }
        popupWindowUtinl.setWindow(window);
        return popupWindowUtinl;
    }

    public PopupWindowUtil(Window window) {
        this.window=window;
    }

    public void setWindow(Window window){
        this.window=window;
    }


    /***
     * wangliang
     *
     * @param view             popupwindow 展示的相对位置
     * @param popupWindow_view popuwindow的内部布局
     * @param height           屏幕高度
     * @param percent          展示屏幕的百分比
     */
    public PopupWindow initPopuptWindow(View view, View popupWindow_view, int width, int height, double percent, int cancleId) {
        // 获取自定义布局文件pop.xml的视图
        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, (int) (height * percent), true);
        popupWindow.setWidth(width - 20);
        // // 设置动画效果
        popupWindow.setAnimationStyle(R.style.core_mystyleanim);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        backgroundAlpha(0.5f);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(new poponDismissListener());
        Button cancleButton = (Button) popupWindow_view.findViewById(cancleId);
        cancleButton.setOnClickListener(view1 -> cancel());
        return popupWindow;
    }

    /***
     * wangliang
     *
     * @param view             popupwindow 展示的相对位置
     * @param popupWindow_view popuwindow的内部布局
     */
    public PopupWindow initPopuptWindowNoCancelBtn(View view, View popupWindow_view,int cancelId) {
        // 获取自定义布局文件pop.xml的视图
        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // // 设置动画效果
        popupWindow.setAnimationStyle(R.style.core_mystyleanim);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        backgroundAlpha(0.5f);
        popupWindow.setOnDismissListener(new poponDismissListener());
        TextView cancelTv = (TextView) popupWindow_view.findViewById(cancelId);
        cancelTv.setOnClickListener(view1 -> cancel());
        popupWindow_view.setOnTouchListener((view1, motionEvent) -> cancelTouch());
        return popupWindow;
    }

    public void dismiss(){
        if(popupWindow != null){
            backgroundAlpha(1f);
            popupWindow.dismiss();
            popupWindow=null;
        }
    }

    /***
     * wangliang
     *
     * @param view             popupwindow 展示的相对位置
     * @param popupWindow_view popuwindow的内部布局
     */
    public PopupWindow initPopuptWindowNoCancelBtn(View view, View popupWindow_view) {
        // 获取自定义布局文件pop.xml的视图
        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // // 设置动画效果
        popupWindow.setAnimationStyle(R.style.core_mystyleanim);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        backgroundAlpha(0.5f);
        popupWindow.setOnDismissListener(new poponDismissListener());
        popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(popupWindow != null){
                    backgroundAlpha(1f);
                    popupWindow.dismiss();
                    popupWindow=null;
                }
                return false;
            }
        });
        return popupWindow;
    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        if(window!=null){
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.alpha = bgAlpha; //0.0-1.0
//            lp.dimAmount = bgAlpha; //0.0-1.0
            if (bgAlpha == 1) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
            } else {
                window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//此行代码主要是解决在华为手机上半透明效果无效的bug
            }
            window.setAttributes(lp);
        }
    }

    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     *
     * @author cg
     */
    class poponDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }

    public PopupWindow initPopuptWindowWithNoAnim(View view, View popupWindow_view) {
        // 获取自定义布局文件pop.xml的视图
        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(view,0,0);
        backgroundAlpha(0.7f);
        popupWindow.setOnDismissListener(new poponDismissListener());
        popupWindow_view.setOnTouchListener((view1, motionEvent) -> cancelTouch());
        return popupWindow;
    }
    public PopupWindow initPopuptWindowWithNoAnimShowDown(View view, View popupWindow_view) {
        // 获取自定义布局文件pop.xml的视图
        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(view,0,0);
        backgroundAlpha(0.7f);
        popupWindow.setOnDismissListener(new poponDismissListener());
        popupWindow_view.setOnTouchListener((view1, motionEvent) -> cancelTouch());
        return popupWindow;
    }


    /** wxl
     *         通过计算目标View的下方是否能过容下我们的pop，如果不能，将显示在我们的目标控件的上方
     *
      * @param tagVIew
     * @param popupWindow_view
     * @param  isbelow
     * @return
     */

    public PopupWindow initPopupWindowAboveOrBelowTagView(View tagVIew, View popupWindow_view, boolean isbelow) {

        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        int[] location = new int[2];
        tagVIew.getLocationOnScreen(location);
         if (isbelow) {
            popupWindow.showAsDropDown(tagVIew, 0, 0);
        } else {
            popupWindow.showAtLocation(tagVIew, Gravity.NO_GRAVITY, location[0], location[1] - popupWindow.getContentView().getMeasuredHeight());
        }
        backgroundAlpha(0.7f);
        popupWindow.setOnDismissListener(new poponDismissListener());
        popupWindow_view.setOnTouchListener((view, motionEvent) -> cancelTouch());
        return popupWindow;
    }

    /***
     * wxl
     *     popwindow显示在屏幕任意位置
     *
     */
    public PopupWindow initPopuptWindowShowInAnyPlace(View view, View popupWindow_view, int gravity) {
        // 获取自定义布局文件pop.xml的视图
        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
         backgroundAlpha(0.7f);
        popupWindow.showAtLocation(view, gravity, 0, 0);
        popupWindow.setOnDismissListener(new poponDismissListener());
        return popupWindow;
    }

    private void cancel(){
        if(popupWindow != null){
            backgroundAlpha(1f);
            popupWindow.dismiss();
            popupWindow=null;
        }
    }

    private boolean cancelTouch(){
        if(popupWindow != null){
            backgroundAlpha(1f);
            popupWindow.dismiss();
            popupWindow=null;
        }
        return false;
    }


    /***
     * liyu
     *
     * @param view             popupwindow 展示的相对位置
     * @param popupWindow_view popuwindow的内部布局
     */
    public PopupWindow initPopuptWindowNoCancelBtn(View view, View popupWindow_view,int cancelId,Object flag) {
        view.setTag(flag);
        initPopuptWindowNoCancelBtn(view,popupWindow_view,cancelId);
        popupWindow_view.setOnClickListener(v -> {
            v.setTag(flag);
        });
        return popupWindow;
    }

}
