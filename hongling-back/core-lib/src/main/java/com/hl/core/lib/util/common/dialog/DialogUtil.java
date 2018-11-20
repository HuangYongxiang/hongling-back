package com.hl.core.lib.util.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hl.core.lib.R;

/**
 * @Describe:自定义dialog
 * @Package: com.hl.core.lib.util.common.dialog
 * @Author: liyu
 * @Date: 2018/1/3 10:44
 * @Copyright: hl
 */
public class DialogUtil {

    public interface DialogOnClickListener{
        void onClickListener(Dialog dialog);
    }

    /**
     * 关闭 添加 对话框
     * @param contentView
     * @param title
     * @param listener
     */
    public static void dialogCloseOrAdd(Context context, View contentView, String title, final DialogOnClickListener listener) {
        final Dialog dialog = new Dialog(context, R.style.core_dialog_style);
        View baseView = LayoutInflater.from(context).inflate(R.layout.core_dialog_close_add_layout, null, false);
        TextView titleName_tv = (TextView) baseView.findViewById(R.id.title_name_tv);
        ImageView cancle_img = (ImageView) baseView.findViewById(R.id.cancle_img);
        LinearLayout groupView = (LinearLayout) baseView.findViewById(R.id.parent_view);
        TextView close_tv = (TextView) baseView.findViewById(R.id.close_tv);
        RelativeLayout add_layout = (RelativeLayout) baseView.findViewById(R.id.add_rl);

        titleName_tv.setText(title);
        cancle_img.setOnClickListener(view -> dialog.dismiss());
        close_tv.setOnClickListener(view -> dialog.dismiss());
        add_layout.setOnClickListener(view -> listener.onClickListener(dialog));

        groupView.addView(contentView);
        dialog.setContentView(baseView);
        dialog.setCancelable(false);
        dialog.show();
    }
    /**
     * 关闭 添加 对话框
     * @param contentView
     * @param title
     * @param listener
     */
    public static void dialogCloseOrAdd(Context context, View contentView, String title, final DialogOnClickListener listener,String addFlag) {
        final Dialog dialog = new Dialog(context, R.style.core_dialog_style);
        View baseView = LayoutInflater.from(context).inflate(R.layout.core_dialog_close_add_layout, null, false);
        TextView titleName_tv = (TextView) baseView.findViewById(R.id.title_name_tv);
        ImageView cancle_img = (ImageView) baseView.findViewById(R.id.cancle_img);
        LinearLayout groupView = (LinearLayout) baseView.findViewById(R.id.parent_view);
        TextView close_tv = (TextView) baseView.findViewById(R.id.close_tv);
        RelativeLayout add_layout = (RelativeLayout) baseView.findViewById(R.id.add_rl);
        TextView add_tv = (TextView) baseView.findViewById(R.id.add_tv);

        titleName_tv.setText(title);
        cancle_img.setOnClickListener(view -> dialog.dismiss());
        close_tv.setOnClickListener(view -> dialog.dismiss());
        add_layout.setOnClickListener(view -> listener.onClickListener(dialog));
       // @drawable/core_edit_text_bg
        add_tv.setTextColor("1".equals(addFlag)?context.getResources().getColor(R.color.core_text_color_999999):context.getResources().getColor(R.color.core_theme_color));//
        add_layout.setBackground("1".equals(addFlag)?context.getResources().getDrawable(R.drawable.core_edit_text_bg):context.getResources().getDrawable(R.drawable.core_edit_text_bg));
        groupView.addView(contentView);
        dialog.setContentView(baseView);
        dialog.setCancelable(false);
        dialog.show();
    }

    /**
     * 删除 确认 对话框
     * @param contentView
     * @param title
     * @param deleteListener
     * @param sureListener
     */
    public static Dialog dialogDeleteOrSure(Context context, View contentView, String title, final DialogOnClickListener deleteListener, final DialogOnClickListener sureListener) {
        final Dialog dialog = new Dialog(context, R.style.core_dialog_style);
        View baseView = LayoutInflater.from(context).inflate(R.layout.core_dialog_delete_sure_layout, null, false);
        TextView titleName_tv = (TextView) baseView.findViewById(R.id.title_name_tv);
        ImageView cancle_img = (ImageView) baseView.findViewById(R.id.cancle_img);
        LinearLayout groupView = (LinearLayout) baseView.findViewById(R.id.parent_view);
        TextView delete_tv = (TextView) baseView.findViewById(R.id.delete_tv);
        TextView sure_tv = (TextView) baseView.findViewById(R.id.sure_tv);

        titleName_tv.setText(title);
        cancle_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        delete_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteListener.onClickListener(dialog);
            }
        });
        sure_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sureListener.onClickListener(dialog);
            }
        });

        groupView.addView(contentView);
        dialog.setContentView(baseView);
        dialog.setCancelable(false);
        dialog.show();
        return dialog;
    }
    /**
     * 删除(取消) 确认 对话框
     * @param contentView
     * @param title
     * @param cancle
     * @param sure
     * @param deleteListener
     * @param sureListener
     */
    public static Dialog dialogDeleteOrSure(Context context, View contentView, String title, int cancle,int sure,final DialogOnClickListener deleteListener, final DialogOnClickListener sureListener) {
        final Dialog dialog = new Dialog(context, R.style.core_dialog_style);
        View baseView = LayoutInflater.from(context).inflate(R.layout.core_dialog_delete_sure_layout, null, false);
        TextView titleName_tv = (TextView) baseView.findViewById(R.id.title_name_tv);
        ImageView cancle_img = (ImageView) baseView.findViewById(R.id.cancle_img);
        LinearLayout groupView = (LinearLayout) baseView.findViewById(R.id.parent_view);
        TextView delete_tv = (TextView) baseView.findViewById(R.id.delete_tv);
        TextView sure_tv = (TextView) baseView.findViewById(R.id.sure_tv);

        if(cancle != -1){
            delete_tv.setText(cancle);
        }
        if(sure != -1){
            sure_tv.setText(sure);
        }
        titleName_tv.setText(title);
        cancle_img.setOnClickListener(view -> dialog.dismiss());
        delete_tv.setOnClickListener(view -> deleteListener.onClickListener(dialog));
        sure_tv.setOnClickListener(view -> sureListener.onClickListener(dialog));

        groupView.addView(contentView);
        dialog.setContentView(baseView);
        dialog.setCancelable(false);
        dialog.show();
        return dialog;
    }


    /**
     * 确认 对话框
     * @param context
     * @param titleName
     * @param tipContent
     * @param isVisibily    默认显示右上角 X , -1
     * @param sureListener
     */
    public static void dialogSure(Context context,String titleName, String tipContent, int isVisibily ,DialogOnClickListener sureListener) {
        final Dialog dialog = new Dialog(context, R.style.core_dialog_style);
        View baseView = LayoutInflater.from(context).inflate(R.layout.core_dialog_sure_layout, null, false);
        TextView titleName_tv = (TextView) baseView.findViewById(R.id.title_name_tv);
        ImageView cancle_img = (ImageView) baseView.findViewById(R.id.cancle_img);
        TextView tipContent_tv = (TextView) baseView.findViewById(R.id.tip_tv);
        TextView sure_tv = (TextView) baseView.findViewById(R.id.close_tv);

        titleName_tv.setText(titleName);
        tipContent_tv.setText(tipContent);
        if(isVisibily != -1){
            cancle_img.setVisibility(isVisibily);
        }
        cancle_img.setOnClickListener(view -> dialog.dismiss());
        sure_tv.setOnClickListener(view -> sureListener.onClickListener(dialog));

        dialog.setContentView(baseView);
        dialog.setCancelable(false);
        dialog.show();
    }

    /**
     * 取消 确认 对话框
     * @param contentView
     * @param title
     * @param deleteListener
     * @param sureListener
     */
    public static void dialogCancleOrSure(Context context, View contentView, String title, final DialogOnClickListener deleteListener, final DialogOnClickListener sureListener) {
        final Dialog dialog = new Dialog(context, R.style.core_dialog_style);
        View baseView = LayoutInflater.from(context).inflate(R.layout.core_dialog_delete_sure_layout, null, false);
        TextView titleName_tv = (TextView) baseView.findViewById(R.id.title_name_tv);
        ImageView cancle_img = (ImageView) baseView.findViewById(R.id.cancle_img);
        LinearLayout groupView = (LinearLayout) baseView.findViewById(R.id.parent_view);
        TextView delete_tv = (TextView) baseView.findViewById(R.id.delete_tv);
        delete_tv.setText("取消");
        TextView sure_tv = (TextView) baseView.findViewById(R.id.sure_tv);

        titleName_tv.setText(title);
        cancle_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        delete_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteListener.onClickListener(dialog);
            }
        });
        sure_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sureListener.onClickListener(dialog);
            }
        });

        groupView.addView(contentView);
        dialog.setContentView(baseView);
        dialog.setCancelable(false);
        dialog.show();
    }

    /**
     * 确认 对话框
     * @param tipContent
     * @param sureListener
     */
    public static void dialogSure(Context context,String tipContent, final DialogOnClickListener sureListener) {
        final Dialog dialog = new Dialog(context, R.style.core_dialog_style);
        View baseView = LayoutInflater.from(context).inflate(R.layout.core_dialog_sure_layout, null, false);
        TextView titleName_tv = (TextView) baseView.findViewById(R.id.title_name_tv);
        ImageView cancle_img = (ImageView) baseView.findViewById(R.id.cancle_img);
        TextView tipContent_tv = (TextView) baseView.findViewById(R.id.tip_tv);
        TextView sure_tv = (TextView) baseView.findViewById(R.id.close_tv);

        titleName_tv.setText(context.getResources().getString(R.string.core_dialog_tip));
        tipContent_tv.setText(tipContent);
        cancle_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        sure_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sureListener.onClickListener(dialog);
            }
        });

        dialog.setContentView(baseView);
        dialog.setCancelable(false);
        dialog.show();
    }


    /**
     * 确认编辑对话框
     * @param context
     * @param contentView
     * @param titleName
     * @param sureListener
     */
    public static void dialogSureEdit(Context context,View contentView,String titleName, final DialogOnClickListener sureListener) {
        final Dialog dialog = new Dialog(context, R.style.core_dialog_style);
        View baseView = LayoutInflater.from(context).inflate(R.layout.core_dialog_sure_edit_layout, null, false);
        LinearLayout groupView = (LinearLayout) baseView.findViewById(R.id.parent_view);
        TextView titleName_tv = (TextView) baseView.findViewById(R.id.title_name_tv);
        ImageView cancle_img = (ImageView) baseView.findViewById(R.id.cancle_img);
        TextView sure_tv = (TextView) baseView.findViewById(R.id.close_tv);

        titleName_tv.setText(titleName);
        cancle_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        sure_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sureListener.onClickListener(dialog);
            }
        });
        groupView.addView(contentView);
        dialog.setContentView(baseView);
        dialog.setCancelable(false);
        dialog.show();
    }

    /**
     * 显示损失比例对话框
     * @param context
     * @param contentView
     * @param titleName
     */
    public static void dialogNoFooter(Context context,View contentView,String titleName) {
        final Dialog dialog = new Dialog(context, R.style.core_dialog_style);
        View baseView = LayoutInflater.from(context).inflate(R.layout.core_dialog_no_footer_layout, null, false);
        LinearLayout groupView = (LinearLayout) baseView.findViewById(R.id.parent_view);
        TextView titleName_tv = (TextView) baseView.findViewById(R.id.title_name_tv);
        ImageView cancle_img = (ImageView) baseView.findViewById(R.id.cancle_img);

        titleName_tv.setText(titleName);
        cancle_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        groupView.addView(contentView);
        dialog.setContentView(baseView);
        dialog.setCancelable(false);
        dialog.show();
    }

}
