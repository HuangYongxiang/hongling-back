package com.hl.contract.table.manager;

import android.content.Context;

import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.SurveyInjuryDao;
import com.hl.contract.table.model.SurveyInjury;

import java.util.List;

/**
 * Created by chenxuefei on 2017/2/5.
 */
public class SurveyInjuryManager {
    private static SurveyInjuryManager instance;
    private SurveyInjuryDao surveyInjuryDao;

    public static SurveyInjuryManager getInstance(){
        if(instance == null){
            synchronized (SurveyInjuryManager.class){
                if(instance == null){
                    instance = new SurveyInjuryManager(SurveyApplication.get());
                }
            }
        }
        return  instance;
    }

    public SurveyInjuryManager(Context context) {
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        surveyInjuryDao = daoSession.getSurveyInjuryDao();
    }

    //人伤


    /**
     * @param reportCode
     * @param dataId
     * @return 根据id和报案号获取单条人伤信息
     */
    public SurveyInjury getSurveyInjury(String reportCode, String dataId) {
        if (reportCode == null || dataId == null) {
            return null;
        }

        List<SurveyInjury> surveyInjuryList = surveyInjuryDao.queryBuilder()
                .where(SurveyInjuryDao.Properties.ReportCode.eq(reportCode),
                        SurveyInjuryDao.Properties.Id.eq(dataId)).list();

        if (surveyInjuryList != null && surveyInjuryList.size() > 0) {
            return surveyInjuryList.get(0);
        }
        return null;
    }

    /**
     * @param reportCode
     * @return 根据报案号获取人伤信息列表
     */
    public List<SurveyInjury> getSurveyInjuryList(String reportCode) {
        if (reportCode == null) {
            return null;
        }

        List<SurveyInjury> surveyInjuryList = surveyInjuryDao.queryBuilder()
                .where(SurveyInjuryDao.Properties.ReportCode.eq(reportCode)).list();

        if (surveyInjuryList != null && surveyInjuryList.size() > 0) {
            return surveyInjuryList;
        }
        return null;
    }

    public void saveSurveyInjury(SurveyInjury surveyInjury){
        String reportCode = surveyInjury.getReportCode();
        String id = surveyInjury.getId();
        if(getSurveyInjury(reportCode,id) != null){
            surveyInjuryDao.update(surveyInjury);
        }else {
            surveyInjuryDao.insert(surveyInjury);
        }
    }

    /**
     * 获取人伤跟踪的最大序号(新增时序号递增)
     * @param reportCode
     * @return
     */
    public int getSurveyInjuryMaxSerialNo(String reportCode) {
        Integer maxSerialNo = 0;
        if (reportCode == null) {
            return maxSerialNo;
        }
        List<SurveyInjury> surveyInjuryList = surveyInjuryDao.queryBuilder()
                .where(SurveyInjuryDao.Properties.ReportCode.eq(reportCode)).orderDesc(SurveyInjuryDao.Properties.SerialNo).list();

        if (surveyInjuryList != null && surveyInjuryList.size() > 0) {
            maxSerialNo = surveyInjuryList.get(0).getSerialNo();
            return maxSerialNo == null ? 0 : maxSerialNo;
        }
        return maxSerialNo;
    }

    public void deleteSurveyInjury(SurveyInjury surveyInjury){
        surveyInjuryDao.delete(surveyInjury);
    }

    public void deleteSurveyInjuryById(String id){
        List<SurveyInjury> surveyInjuryList = surveyInjuryDao.queryBuilder()
                .where(SurveyInjuryDao.Properties.Id.eq(id)).list();
        if (surveyInjuryList != null && surveyInjuryList.size() > 0) {
            surveyInjuryDao.delete(surveyInjuryList.get(0));
        }
    }

    public void insertOrReplaceInTxSurveyInjurys(List<SurveyInjury> surveyInjuryList){
        if(surveyInjuryList != null && surveyInjuryList.size() > 0){
            surveyInjuryDao.insertOrReplaceInTx(surveyInjuryList);
        }
    }

}
