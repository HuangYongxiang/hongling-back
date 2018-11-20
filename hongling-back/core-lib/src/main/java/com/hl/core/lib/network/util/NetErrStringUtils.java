package com.hl.core.lib.network.util;

import android.content.Context;

import com.hl.core.lib.R;


/**
 * @Describe:网络错误提示工具类
 * @Package: com.hl.core.lib.network.util
 * @Author: liyu
 * @Date: 2018/1/17 0017 上午 10:46
 * @Copyright: hl
 */
public class NetErrStringUtils {

    public static final int ERR_404 = 404;

    public static final int ERR_500 = 500;

    public static final int ERR_502 = 502;

    public static String getErrString(Context context, int code) {
        String result;
        switch (code) {
            case ERR_404:
                result = context.getString(R.string.core_err404);
                break;
            case ERR_500:
                result = context.getString(R.string.core_err500);
                break;
            case ERR_502:
                result = context.getString(R.string.core_err502);
                break;
            default:
                result = context.getString(R.string.core_errDefault);
                break;
        }
        return result;
    }

    public static String getErrString(Context context, Throwable t) {
        String result;
        if (t instanceof java.net.SocketTimeoutException) {
            result = context.getString(R.string.core_errTimeout);
        } else if (t != null && t.getMessage() != null && t.getMessage().equalsIgnoreCase("canceled")) {
            result = "请求已经取消";
        } else {
            result = context.getString(R.string.core_errDefault) + t.getMessage();
        }
        return result;
    }
}
