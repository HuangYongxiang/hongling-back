package com.hl.core.lib.util.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.hl.core.lib.view.CustomProgressDialog;

/**
 * @Describe:
 * @Package: com.hl.core.lib.util.common
 * @Author: liyu
 * @Date: 2018/1/8 10:28
 * @Copyright: hl
 */
public class ProgressDialogUtil {
    public static ProgressDialog createProgressDialog(Context context, String title, String msg,
                                                      DialogInterface.OnCancelListener cancelListener) {
        CustomProgressDialog mProgressDialog = new CustomProgressDialog(context);
        if (!TextUtils.isEmpty(title))
            mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(msg);
        if (null != cancelListener)
            mProgressDialog.setOnCancelListener(cancelListener);
        mProgressDialog.setCanceledOnTouchOutside(true);
        mProgressDialog.setCancelable(true);
        return mProgressDialog;
    }

    public static ProgressDialog createProgressDialogNoCancleable(Context context, String title, String msg,
                                                                  DialogInterface.OnCancelListener cancelListener) {
        CustomProgressDialog mProgressDialog = new CustomProgressDialog(context);
        if (!TextUtils.isEmpty(title))
            mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(msg);
        if (null != cancelListener)
            mProgressDialog.setOnCancelListener(cancelListener);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        return mProgressDialog;
    }
}
