package com.hl.core.lib.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hl.core.lib.R;
import com.hl.core.lib.util.UtilManager;

/**
 * @Describe:
 * @Package: com.hl.core.lib.view
 * @Author: liyu
 * @Date: 2018/1/8 9:56
 * @Copyright: hl
 */
public class CustomProgressDialog extends ProgressDialog {
    protected TextView titleTv;
    private String title;

    public CustomProgressDialog(Context context) {
        super(context, 0);
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, R.style.core_common_dialog_style);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.core_custom_progress_dialog, null);// 得到加载view
        titleTv = (TextView)v.findViewById(R.id.title);
        titleTv.setText(title);

        DisplayMetrics metrics = UtilManager.System.getScreenSize(this.getContext());
        setContentView(v, new LinearLayout.LayoutParams((int) (metrics.widthPixels * 0.95),(int) (metrics.heightPixels * 0.15)));
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setTitle(int resId){
        this.title = getContext().getResources().getString(resId);
    }
}
