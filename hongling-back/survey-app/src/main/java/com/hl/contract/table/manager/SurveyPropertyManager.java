package com.hl.contract.table.manager;

import android.content.Context;

import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.SurveyPropertyDao;
import com.hl.contract.table.model.SurveyProperty;

import java.util.List;

/**
 * Created by chenxuefei on 2017/2/5.
 */
public class SurveyPropertyManager {
    private static SurveyPropertyManager instance;
    private SurveyPropertyDao surveyPropertyDao;

    public static SurveyPropertyManager getInstance(){
        if(instance == null){
            synchronized (SurveyPropertyManager.class){
                if(instance == null){
                    instance = new SurveyPropertyManager(SurveyApplication.get());
                }
            }
        }
        return  instance;
    }

    public SurveyPropertyManager(Context context) {
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        surveyPropertyDao = daoSession.getSurveyPropertyDao();
    }

    //财损

    /**
     * @param reportCode
     * @param dataId
     * @return 根据id和报案号获取单条物损信息
     */
    public SurveyProperty getSurveyProperty(String reportCode, String dataId) {
        if (reportCode == null || dataId == null) {
            return null;
        }
        List<SurveyProperty> surveyPropertyList = surveyPropertyDao.queryBuilder()
                .where(SurveyPropertyDao.Properties.ReportCode.eq(reportCode),
                        SurveyPropertyDao.Properties.Id.eq(dataId)).list();

        if (surveyPropertyList != null && surveyPropertyList.size() > 0) {
            return surveyPropertyList.get(0);
        }
        return null;
    }

    /**
     * @param reportCode
     * @return 获取物损列表，用于查勘主页面。
     */
    public List<SurveyProperty> getSurveyPropertyList(String reportCode) {
        if (reportCode == null) {
            return null;
        }
        List<SurveyProperty> surveyPropertyList = surveyPropertyDao.queryBuilder()
                .where(SurveyPropertyDao.Properties.ReportCode.eq(reportCode)).list();
        if (surveyPropertyList != null && surveyPropertyList.size() > 0) {
            return surveyPropertyList;
        }
        return null;
    }

    public void saveSurveyProperty(SurveyProperty surveyProperty) {
        String reportCode = surveyProperty.getReportCode();
        String id = surveyProperty.getId();
        if (getSurveyProperty(reportCode, id) != null) {
            surveyPropertyDao.update(surveyProperty);
        } else {
            surveyPropertyDao.insert(surveyProperty);
        }
    }

    /**
     * 获取财产损失的最大序号(新增时序号递增)
     * @param reportCode
     * @return
     */
    public int getSurveyPropertyMaxSerialNo(String reportCode) {
        Integer maxSerialNo = 0;
        if (reportCode == null) {
            return maxSerialNo;
        }
        List<SurveyProperty> surveyPropertyList = surveyPropertyDao.queryBuilder()
                .where(SurveyPropertyDao.Properties.ReportCode.eq(reportCode)).orderDesc(SurveyPropertyDao.Properties.SerialNo).list();

        if (surveyPropertyList != null && surveyPropertyList.size() > 0) {
            maxSerialNo = surveyPropertyList.get(0).getSerialNo();
            return maxSerialNo == null ? 0 : maxSerialNo;
        }
        return maxSerialNo;
    }

    public void deleteSurveyProperty(SurveyProperty surveyProperty){
        surveyPropertyDao.delete(surveyProperty);
    }

    public void deleteSurveyPropertyById(String id){
        List<SurveyProperty> surveyPropertyList = surveyPropertyDao.queryBuilder()
                .where(SurveyPropertyDao.Properties.Id.eq(id)).list();
        if (surveyPropertyList != null && surveyPropertyList.size() > 0) {
            surveyPropertyDao.delete(surveyPropertyList.get(0));
        }
    }

    public void insertOrReplaceInTxSurveyPropertyList(List<SurveyProperty> surveyPropertyList){
        surveyPropertyDao.insertOrReplaceInTx(surveyPropertyList);
    }

}
