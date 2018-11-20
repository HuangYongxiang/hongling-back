package com.hl.contract.util;

import android.text.TextUtils;
import android.widget.TextView;

import java.math.BigDecimal;

/**
 * @Describe: Text控件
 * @Package: com.hl.contract.util
 * @Author: liyu
 * @Date: 2018/3/26 14:53
 * @Copyright: hl
 */
public class TextUtil {



    public static String safeText(TextView textView){
        if(textView == null)
            return "";
        if(textView.getText()!=null){
            return textView.getText().toString();
        }
        return "";
    }

    public static String doubleToString(Double val){
        if(val == null)
            return "";
        return String.valueOf(val);
    }

    public static double safeRate(String val){
        double doubleVal = safeDouble(val);
        return doubleVal/100.0;
    }

    public static double safeDouble(String val){
        double result = 0;
        if(TextUtils.isEmpty(val))
            return result;
        try {
            result = Double.parseDouble(val);
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return result;
    }

    public static String safeString(String val){
        if(TextUtils.isEmpty(val)){
            return "0";
        }
        return val;
    }

    /**
     * 四舍五入并保留小数点
     * @param num 数值
     * @param scale 保留小数位数
     */
    public static double formatDouble(Double num, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = null == num ? new BigDecimal("0.0") : new BigDecimal(Double.toString(num));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }



}
