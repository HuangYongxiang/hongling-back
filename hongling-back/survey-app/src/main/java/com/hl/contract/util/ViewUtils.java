package com.hl.contract.util;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.text.method.ReplacementTransformationMethod;
import android.util.Log;
import android.widget.TextView;

import com.hl.contract.R;


/**
 * created by liyu at 2017/7/27.
 * desc: 检查控件
 */

public class ViewUtils {

   private static ViewUtils viewUtils = null;

    private ViewUtils(){

    }

   public static ViewUtils getViewUtils(){

       if(viewUtils==null){
          synchronized (ViewUtils.class){
              if(viewUtils==null){
                  viewUtils = new ViewUtils();
              }
          }
       }

       return viewUtils;
   }



    @SuppressWarnings("deprecation")
    public  static  boolean checkAllTextView(Context context,TextView... views) {
        boolean flag = true;
        for (TextView v:views){
            if (TextUtils.isEmpty(valueOfView(v))){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    v.setHintTextColor(context.getColor(R.color.survey_check_edittext));
                }else{
                    v.setHintTextColor(context.getResources().getColor(R.color.survey_check_edittext));
                }
                flag = false;
            }
        }
        return  flag;
    }

    /**
     * 获取TextView
     * @param textView
     * @return
     */
    public static String valueOfView(TextView textView){
        return  textView.getText().toString().trim();
    }

    /**
     * 检查TextView 内容是否为空
     * @param view
     * @return
     */
    public static boolean isViewValueNotNuLL(TextView view){
        if (TextUtils.isEmpty(view.getText().toString().trim())){
            return false;
        }else{
            return  true;
        }
    }

    /**
     * view.getText()
     */
    public static String viewValue(TextView view){
        return view.getText().toString().trim();
    }


    /**
     * String 转 Double 时，当输入时 "" 时，
     * ValueOf 会出错，直接返回 0
     */
    public static double valueOf(String str){
        try{
        if (str.equals("") || TextUtils.isEmpty(str)){
            return 0;
        }else{
            return Double.valueOf(str);
        }
        }catch (Exception e){
            Log.e("ViewUtils",e.getMessage());
            return 0;
        }
    }

    /**
     * 设置值到View上
     */
    public void setTextViewValue(){}


    /**
     * 将输入的小写字母转换成大写
     */
    public    ReplacementTransformationMethod getTransformationMethod(){

      return   inputLowerToUpper;

    }


    public ReplacementTransformationMethod inputLowerToUpper=new ReplacementTransformationMethod(){
        @Override
        protected char[] getOriginal() {
            char[] lower = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z' };
            return lower;
        }
        @Override
               protected char[] getReplacement() {
            char[] upper = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z' };
            return upper;
        }
    };


}
