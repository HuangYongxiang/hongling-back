package com.hl.contract.util;

/**
 * @Describe: Databinding工具类
 * @Package: com.hl.contract.util
 * @Author: liyu
 * @Date: 2018/3/22 9:20
 * @Copyright: hl
 */
public class BindUtil {


    /**
     * 1返回true，0返回false
     */
    public static boolean checkedYes(String tag){
        return "1".equals(tag);
    }

    /**
     * 0返回true，1返回false
     */
    public static boolean checkedNo(String tag){
        return "0".equals(tag);
    }


    /**
     * 转成字符串
     */
    public static String toString(Object obj){
        if(obj != null){
            return String.valueOf(obj);
        }
        return "";
    }



}
