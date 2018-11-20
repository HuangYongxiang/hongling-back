package com.hl.contract.table.manager;

import android.content.Context;

import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.table.dao.DictInfoDao;
import com.hl.contract.table.model.DictInfo;

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
            instance = new DictManager(SurveyApplication.get());
        }
        return instance;
    }

    public DictManager(Context context) {
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        dictInfoDao = daoSession.getDictInfoDao();
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

    public List<DictInfo> getAllDictInfosByKey(){
        List<DictInfo> list = dictInfoDao.queryBuilder().list();
        return list;
    }

    public List<DictInfo> getDictInfosByKey(String key){
        if(key == null){
            return null;
        }
        List<DictInfo> list = dictInfoDao.queryBuilder().where(DictInfoDao.Properties.Key.eq(key)).list();
        return list;
    }

}
