package com.hl.contract.table.manager;

import android.content.Context;

import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.SurveyAimCarDao;
import com.hl.contract.table.model.SurveyAimCar;

import java.util.List;

/**
 * Created by chenxuefei on 2017/2/5.
 */
public class SurveyAimCarManager {
    private static SurveyAimCarManager instance;
    private SurveyAimCarDao surveyAimCarDao;

    public static SurveyAimCarManager getInstance() {
        if (instance == null) {
            synchronized (SurveyAimCarManager.class) {
                if (instance == null) {
                    instance = new SurveyAimCarManager(SurveyApplication.get());
                }
            }
        }
        return instance;
    }

    public SurveyAimCarManager(Context context) {
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        surveyAimCarDao = daoSession.getSurveyAimCarDao();
    }

    public List<SurveyAimCar> getSurveyAimCarByReportNo(String reportNo){
        List<SurveyAimCar> surveyAimCarList = surveyAimCarDao.queryBuilder()
                .where(SurveyAimCarDao.Properties.ReportCode.eq(reportNo)).list();
        return surveyAimCarList;
    }

    /**
     * 获取承保车辆的信息
     */
    public SurveyAimCar getSurveyAimCar(String reportNo) {
        List<SurveyAimCar> list = getSurveyAimCarByReportNo(reportNo);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return new SurveyAimCar();
        }
    }

    public void insertSurveyAimCar(SurveyAimCar surveyAimCar){
        if(surveyAimCar != null){
            surveyAimCarDao.insert(surveyAimCar);
        }
    }

    public void updateSurveyAimCar(SurveyAimCar surveyAimCar){
        if(surveyAimCar != null){
            surveyAimCarDao.update(surveyAimCar);
        }
    }

    public void insertOrReplaceSurveyAimCar(SurveyAimCar surveyAimCar){
        if(surveyAimCar != null){
            surveyAimCarDao.insertOrReplace(surveyAimCar);
        }
    }

}