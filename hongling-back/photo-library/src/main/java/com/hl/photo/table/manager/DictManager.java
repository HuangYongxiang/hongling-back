package com.hl.photo.table.manager;

import android.content.Context;
import android.text.TextUtils;


import com.hl.photo.PhotoApp;
import com.hl.photo.table.PhotoGreenDaoHelper;
import com.hl.photo.table.dao.DaoSession;
import com.hl.photo.table.dao.DictInfoDao;
import com.hl.photo.table.model.DictInfo;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by hl on 2017/8/30.
 */

public class DictManager {
    public static DictManager instance;
    private DictInfoDao dictInfoDao;
    private String errorEnum;

    public static DictManager getInstance(){
        if(instance == null){
            instance = new DictManager(PhotoApp.instance().get());
        }
        return instance;
    }

    public DictManager(Context context) {
        DaoSession daoSession = PhotoGreenDaoHelper.getInstance().getDaoSession(context);
        dictInfoDao = daoSession.getDictInfoDao();
        errorEnum = "DICT_INFO";//获取字典表
    }


    public void deleAllDictInfo(){
        dictInfoDao.deleteAll();
    }

    public void insertDictInfoList(List<DictInfo> dictInfoList){
        if(dictInfoList != null && dictInfoList.size() > 0){
            deleAllDictInfo();
            dictInfoDao.insertInTx(dictInfoList);
        }
    }

    public List<DictInfo> getDictInfosByKey(String key){
        if(key == null){
            return null;
        }
        List<DictInfo> list = dictInfoDao.queryBuilder().where(DictInfoDao.Properties.Key.eq(key)).list();
        return list;
    }

}
