package com.hl.contract.table.manager;

import android.content.Context;

import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.table.dao.HistoricalPaymentInfoDao;
import com.hl.contract.table.model.HistoricalPaymentInfo;

import java.util.List;

/**
 * @Describe: 历史赔付
 * @Package: com.hl.contract.table.manager
 * @Author: liyu
 * @Date: 2018/4/9 10:27
 * @Copyright: hl
 */
public class HistoricalPaymentManager {
    private static HistoricalPaymentManager instance;
    private HistoricalPaymentInfoDao historicalPaymentInfoDao;

    public static HistoricalPaymentManager getInstance(){
        if(instance == null){
            synchronized (HistoricalPaymentManager.class){
                if(instance == null){
                    instance = new HistoricalPaymentManager(SurveyApplication.get());
                }
            }
        }
        return  instance;
    }

    private HistoricalPaymentManager(Context context) {
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        historicalPaymentInfoDao = daoSession.getHistoricalPaymentInfoDao();
    }

    //历史 报案赔付信息

    public List<HistoricalPaymentInfo> getHistoricalPaymentInfoList(String reportCode){
        if (reportCode == null) {
            return null;
        }
        List<HistoricalPaymentInfo> historicalPaymentInfoList =  historicalPaymentInfoDao.queryBuilder()
                .where(HistoricalPaymentInfoDao.Properties.ReportCode.eq(reportCode)).list();
        if (historicalPaymentInfoList != null && historicalPaymentInfoList.size() > 0) {
            return historicalPaymentInfoList;
        }
        return null;
    }

    /**
     * 获取历史赔付信息
     */
    public HistoricalPaymentInfo getHistoricalPaymentInfo(String reportNo) {
        List<HistoricalPaymentInfo> list = historicalPaymentInfoDao.queryBuilder().where(HistoricalPaymentInfoDao.Properties.ReportCode.eq(reportNo)).list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return new HistoricalPaymentInfo();
        }
    }

    public HistoricalPaymentInfo getHistoricalPaymentInfoList(String reportCode, String dataId) {
        if (reportCode == null || dataId == null) {
            return null;
        }
        List<HistoricalPaymentInfo> historicalPaymentInfoList = historicalPaymentInfoDao.queryBuilder()
                .where(HistoricalPaymentInfoDao.Properties.ReportCode.eq(reportCode),
                        HistoricalPaymentInfoDao.Properties.Id.eq(dataId)).list();

        if (historicalPaymentInfoList != null && historicalPaymentInfoList.size() > 0) {
            return historicalPaymentInfoList.get(0);
        }
        return null;
    }


    public void saveHistoricalPaymentInfo(HistoricalPaymentInfo historicalPaymentInfo) {
        String reportCode = historicalPaymentInfo.getReportCode();
        String id = historicalPaymentInfo.getId();
        if (getHistoricalPaymentInfoList(reportCode, id) != null) {
            historicalPaymentInfoDao.update(historicalPaymentInfo);
        } else {
            historicalPaymentInfoDao.insert(historicalPaymentInfo);
        }
    }



    public void deleteHistoricalPaymentInfo(HistoricalPaymentInfo historicalPaymentInfo) {
        if (historicalPaymentInfo != null) {
            historicalPaymentInfoDao.delete(historicalPaymentInfo);
        }
    }

    public void insertHistoricalPaymentInfoList(List<HistoricalPaymentInfo> historicalReportInfoList) {
        if (historicalReportInfoList != null && historicalReportInfoList.size() > 0) {
            historicalPaymentInfoDao.insertOrReplaceInTx(historicalReportInfoList);
        }
    }

}
