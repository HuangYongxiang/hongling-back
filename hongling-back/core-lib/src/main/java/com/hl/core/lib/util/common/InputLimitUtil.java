package com.hl.core.lib.util.common;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * @Describe:限制输入的小数位数
 * @Package: com.hl.eval.bean
 * @Author: liyu
 * @Date: 2018/1/12 16:41
 * @Copyright: hl
 */

public class InputLimitUtil {

    /**
     * 设置小数位数控制(两位)
     */
    public static InputFilter lengthfilter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            // 删除等特殊字符，直接返回
            if ("".equals(source.toString())) {
                return null;
            }
            String dValue = dest.toString();
            String[] splitArray = dValue.split("\\.");
            if (splitArray.length > 1) {
                String dotValue = splitArray[1];
                int diff = dotValue.length() + 1 - 2;
                if (diff > 0) {
                    return source.subSequence(start, end - diff);
                }
            }
            return null;
        }
    };

    /**
     * 设置小数位数控制(一位)
     */
    public static InputFilter lengthfilterRetainADecimalFraction = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            // 删除等特殊字符，直接返回
            if ("".equals(source.toString())) {
                return null;
            }
            String dValue = dest.toString();
            String[] splitArray = dValue.split("\\.");
            if (splitArray.length > 1) {
                String dotValue = splitArray[1];
                int diff = dotValue.length() + 1 - 1;
                if (diff > 0) {
                    return source.subSequence(start, end - diff);
                }
            }
            return null;
        }
    };

    public static InputFilter[] inputFilters(){
        return new InputFilter[]{lengthfilter};
    }

    //输入一位小数限制
    public static InputFilter[] inputFiltersRetainADecimalFraction(){
        return new InputFilter[]{lengthfilterRetainADecimalFraction};
    }
}
