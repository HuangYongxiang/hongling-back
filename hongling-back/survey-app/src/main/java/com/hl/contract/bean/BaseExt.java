package com.hl.contract.bean;

import android.text.TextUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Describe: 扩展类基类：用于Databinding页面扩展使用
 * @Package: com.hl.contract.bean
 * @Author: liyu
 * @Date: 2018/3/23 11:22
 * @Copyright: hl
 */
public class BaseExt<T> {

    protected T model;

    //获取model
    public T model(){
        if(model == null){
            model = fetchModel();
        }
        return model;
    }

    //model赋值
    public void setModel(T model){
        if(model != null){
            this.model = model;
        }
    }

    //boolean to string
    protected String bolToStr(boolean bol){
        return bol ? "1" : "0";
    }

    //string to boolean
    protected boolean strToBol(String str){
        return "1".equals(str);
    }

    //object to string
    protected String objToStr(Object obj){
        if(obj != null){
            return String.valueOf(obj);
        }
        return "";
    }

    //string to double
    protected double strToDouble(String str){
        double db = 0.0;
        if(TextUtils.isEmpty(str))
            return db;
        db = Double.parseDouble(str);
        return db;
    }


    @SuppressWarnings("unchecked")
    private Class<T> initClazz(){
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if(params.length > 0){
            if(params[0] instanceof Class){
                return (Class<T>) params[0];
            }
        }
        return null;
    }

    private T fetchModel() {
        T model = null;
        try {
            Class<T> cls = initClazz();
            if(cls != null){
                model = cls.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }


}
