package com.hl.contract.table.manager;

import android.content.Context;

import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.table.dao.SurveyExtendInfoDao;
import com.hl.contract.table.model.SurveyExtendInfo;

import java.util.List;

/**
 * Created by chenxuefei on 2017/2/5.
 */
public class SurveyExtendInfoManager {
    private static SurveyExtendInfoManager instance;
    private SurveyExtendInfoDao surveyExtendInfoDao;
    private final DaoSession daoSession;

    public static SurveyExtendInfoManager getInstance(){
        if(instance == null){
            synchronized (SurveyExtendInfoManager.class){
                if(instance == null){
                    instance = new SurveyExtendInfoManager(SurveyApplication.get());
                }
            }
        }
        return  instance;
    }

    public SurveyExtendInfoManager(Context context) {
        daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        surveyExtendInfoDao = daoSession.getSurveyExtendInfoDao();
    }

    //查勘扩展
    /**
     * @param reportCode
     * @return List<SurveyExtendInfo> 获取查勘扩展信息
     */
    public List<SurveyExtendInfo> getSurveyExtendInfoList(String reportCode) {
        daoSession.clear();
        if (reportCode == null) {
            return null;
        }
        List<SurveyExtendInfo> surveyExtendInfoList = surveyExtendInfoDao.queryBuilder()
                .where(SurveyExtendInfoDao.Properties.ReportNo.eq(reportCode)).list();

        if (surveyExtendInfoList != null && surveyExtendInfoList.size() > 0) {
            return surveyExtendInfoList;
        }
        return null;
    }
    /**
     * @param reportCode requiredFlag
     * @return List<SurveyExtendInfo> 根据requiredFlag获取查勘扩展信息
     */
    public   List<SurveyExtendInfo> getSurveyExtendInfoListByRequiredFlag(String reportCode,String requiredFlag) {
        if (reportCode == null) {
            return null;
        }
         try {
             SurveyGreenDaoHelper.getInstance().getDaoSession(null).clear();//清除GreenDao缓存，避免返回缓存数据
         }catch (Exception e){
             e.printStackTrace();
          }
         List<SurveyExtendInfo> surveyExtendInfoList = surveyExtendInfoDao.queryBuilder()
                .where(SurveyExtendInfoDao.Properties.ReportNo.eq(reportCode),SurveyExtendInfoDao.Properties.RequiredFlag.eq(requiredFlag)).list();

        if (surveyExtendInfoList != null && surveyExtendInfoList.size() > 0) {
            return surveyExtendInfoList;
        }
        return null;
    }

    /**
     * @param reportCode
     * @return List<SurveyExtendInfo> 获取查勘扩展信息
     */
    public List<SurveyExtendInfo> getSurveyExtendInfoListBySurveyreportValue(String reportCode,String surveyreportValue) {
        if (reportCode == null) {
            return null;
        }
        List<SurveyExtendInfo> surveyExtendInfoList = surveyExtendInfoDao.queryBuilder()
                .where(SurveyExtendInfoDao.Properties.ReportNo.eq(reportCode),SurveyExtendInfoDao.Properties.SurveyreportValue.eq(surveyreportValue)).list();

        if (surveyExtendInfoList != null && surveyExtendInfoList.size() > 0) {
            return surveyExtendInfoList;
        }
        return null;
    }


    public void saveSurveyExtendInfo(SurveyExtendInfo surveyExtendInfo,String saveType) {
        if (saveType!=null&&saveType.equals("1")) {
            surveyExtendInfoDao.insert(surveyExtendInfo);

        } else if(saveType!=null&&saveType.equals("2")){
            surveyExtendInfoDao.update(surveyExtendInfo);
        }
    }


}
