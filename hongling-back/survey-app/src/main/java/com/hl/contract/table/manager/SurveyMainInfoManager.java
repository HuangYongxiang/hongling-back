package com.hl.contract.table.manager;

import android.content.Context;

import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.table.dao.SurveyMainInfoDao;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.model.SurveyMainInfo;

import java.util.List;

/**
 * Created by liyu on 2017/1/22.
 */

public class SurveyMainInfoManager {
    private static SurveyMainInfoManager instance;
    private Context mContext;
    private SurveyMainInfoDao surveyMainInfoDao;
    private final DaoSession daoSession;

    public static SurveyMainInfoManager getInstance(){
        if(instance == null){
            synchronized (SurveyMainInfoManager.class){
                if(instance == null){
                    instance = new SurveyMainInfoManager(SurveyApplication.get());
                }
            }
        }
        return  instance;
    }

    public SurveyMainInfoManager(Context context){
        this.mContext = context;
        daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        surveyMainInfoDao = daoSession.getSurveyMainInfoDao();
    }

    public List<SurveyMainInfo> getSurveyMainInfoListByTaskNo(String taskNo){
        List<SurveyMainInfo> list = surveyMainInfoDao.queryBuilder().where(SurveyMainInfoDao.Properties.TaskNo.eq(taskNo)).list();
        return list;
    }

    public SurveyMainInfo getSurveyMainInfoByFlowId(String flowId){
        SurveyMainInfo surveyMainInfo = null;
        List<SurveyMainInfo> list = surveyMainInfoDao.queryBuilder().where(SurveyMainInfoDao.Properties.TaskNo.eq(flowId)).list();
        if(list != null && list.size() > 0){
            surveyMainInfo = list.get(0);
        }
        return surveyMainInfo;
    }
    public SurveyMainInfo getSurveyMainInfoByReportNoAndTaskNo(String reportNo, String taskNo){
        daoSession.clear();
        SurveyMainInfo surveyMainInfo = null;
        try {
            List<SurveyMainInfo> list = surveyMainInfoDao.queryBuilder()
                    .where(SurveyMainInfoDao.Properties.ReportNo.eq(reportNo))
                    .where(SurveyMainInfoDao.Properties.TaskNo.eq(taskNo)) .list();
            if(list != null && list.size() > 0){
                surveyMainInfo = list.get(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return surveyMainInfo;
    }

    public void saveSurveyMainInfo(SurveyMainInfo surveyMainInfo){
        String taskNo = surveyMainInfo.getTaskNo();
        List<SurveyMainInfo> surveyMainInfoByFlowId = getSurveyMainInfoListByTaskNo(taskNo);
        if(surveyMainInfoByFlowId != null && surveyMainInfoByFlowId.size() > 0){
            surveyMainInfoByFlowId.get(0).setTaskNo(taskNo);
            surveyMainInfoDao.update(surveyMainInfoByFlowId.get(0));
        }else {
            surveyMainInfoDao.insert(surveyMainInfo);
        }
    }

}
