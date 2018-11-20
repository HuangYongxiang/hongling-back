package com.hl.contract.table.manager;

import android.content.Context;

import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.HistoricalReportInfoDao;
import com.hl.contract.table.model.HistoricalReportInfo;

import java.util.List;

/**
 * @Describe: 历史报案
 * @Package: com.hl.contract.table.manager
 * @Author: liyu
 * @Date: 2018/4/9 10:27
 * @Copyright: hl
 */
public class HistoricalReportInfoManager {
    private static HistoricalReportInfoManager instance;
    private HistoricalReportInfoDao historicalReportInfoDao;

    public static HistoricalReportInfoManager getInstance(){
        if(instance == null){
            synchronized (HistoricalReportInfoManager.class){
                if(instance == null){
                    instance = new HistoricalReportInfoManager(SurveyApplication.get());
                }
            }
        }
        return  instance;
    }

    public HistoricalReportInfoManager(Context context) {
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        historicalReportInfoDao = daoSession.getHistoricalReportInfoDao();
    }

    //历史 报案信息

    public List<HistoricalReportInfo> getHistoricalReportList(String reportCode){
        if (reportCode == null) {
            return null;
        }
        List<HistoricalReportInfo> historicalReportInfoList =  historicalReportInfoDao.queryBuilder()
                .where(HistoricalReportInfoDao.Properties.ReportCode.eq(reportCode)).list();
        if (historicalReportInfoList != null && historicalReportInfoList.size() > 0) {
            return historicalReportInfoList;
        }
        return null;
    }

    /**
     * 获取历史报案信息
     */
    public HistoricalReportInfo getHistoricalReportInfo(String reportNo) {
        List<HistoricalReportInfo> list = historicalReportInfoDao.queryBuilder().where(HistoricalReportInfoDao.Properties.ReportCode.eq(reportNo)).list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return new HistoricalReportInfo();
        }
    }

    public HistoricalReportInfo getHistoricalReportList(String reportCode, String dataId) {
        if (reportCode == null || dataId == null) {
            return null;
        }
        List<HistoricalReportInfo> historicalReportInfoList = historicalReportInfoDao.queryBuilder()
                .where(HistoricalReportInfoDao.Properties.ReportCode.eq(reportCode),
                        HistoricalReportInfoDao.Properties.Id.eq(dataId)).list();

        if (historicalReportInfoList != null && historicalReportInfoList.size() > 0) {
            return historicalReportInfoList.get(0);
        }
        return null;
    }


    public void saveHistoricalReportInfo(HistoricalReportInfo historicalReportInfo) {
        String reportCode = historicalReportInfo.getReportCode();
        String id = historicalReportInfo.getId();
        if (getHistoricalReportList(reportCode, id) != null) {
            historicalReportInfoDao.update(historicalReportInfo);
        } else {
            historicalReportInfoDao.insert(historicalReportInfo);
        }
    }


    public void deleteHistoricalReportInfoById(String id){
        List<HistoricalReportInfo> historicalReportInfoList = historicalReportInfoDao.queryBuilder()
                .where(HistoricalReportInfoDao.Properties.Id.eq(id)).list();
        if (historicalReportInfoList != null && historicalReportInfoList.size() > 0) {
            historicalReportInfoDao.delete(historicalReportInfoList.get(0));
        }
    }

    public void deleteHistoricalReportInfo(HistoricalReportInfo historicalReportInfo) {
        if (historicalReportInfo != null) {
            historicalReportInfoDao.delete(historicalReportInfo);
        }
    }

    public void insertHistoricalReportInfoList(List<HistoricalReportInfo> historicalReportInfoList) {
        if (historicalReportInfoList != null && historicalReportInfoList.size() > 0) {
            historicalReportInfoDao.insertOrReplaceInTx(historicalReportInfoList);
        }
    }

}
