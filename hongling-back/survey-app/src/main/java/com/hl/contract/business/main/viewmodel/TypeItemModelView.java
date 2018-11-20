package com.hl.contract.business.main.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hl.core.lib.bean.TypeItem;
import com.hl.contract.business.main.adapter.IAdpaterBackCallListener;


/**
 * @Describe:
 * @Author: liyu
 * @Date: 2018/1/16 0016 下午 13:17
 * @Copyright: hl
 */

public class TypeItemModelView {
    private Context context;
    public TypeItem typeItem;
    public ObservableBoolean checked = new ObservableBoolean();
    public ObservableBoolean isLineShow = new ObservableBoolean();
    public ObservableField<Object> tag=new ObservableField<Object>();
    IAdpaterBackCallListener listener;
   // public String tag;

    public TypeItemModelView(Context context, TypeItem typeItem, IAdpaterBackCallListener listener) {
        this.context = context;
        this.typeItem = typeItem;
        this.listener = listener;
        this.isLineShow.set(true);
    }

    public void onClick(int position) {
        listener.callBack(position,this);
    }

    @BindingAdapter("android:layout_height")
    public static void setLayoutHeight(View view, float height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = (int) height;
        view.setLayoutParams(params);
    }
    @BindingAdapter("android:layout_width")
    public static void setLayoutWidth(View view, float width) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = (int) width;
        view.setLayoutParams(params);
    }
  @BindingAdapter("android:gravity")
    public static void setGravity(View view, int gravity) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//此处相当于布局文件中的Android:layout_gravity属性
        lp.gravity = gravity;
        view.setLayoutParams(lp);
    }
//   @BindingAdapter("android:layout_marginLeft")
//    public static void setLayoutMarginLeft(View view, int px) {
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
////此处相当于布局文件中的Android:layout_gravity属性
//        lp.setMargins(px,0,0,0);
//        view.setLayoutParams(lp);
//    }
   @BindingAdapter("android:paddingLeft")
    public static void setLayoutMarginLeft(View view, int px) {
     view.setPadding(px,0,0,0);
    }
}
