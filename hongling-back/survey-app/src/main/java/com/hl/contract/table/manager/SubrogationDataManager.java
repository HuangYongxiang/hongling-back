package com.hl.contract.table.manager;

import android.content.Context;

import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.table.dao.SubrogationDataDao;
import com.hl.contract.table.model.SubrogationData;

import java.util.List;

/**
 * Created by chenxuefei on 2017/2/5.
 */
public class SubrogationDataManager {
    private static SubrogationDataManager instance;
    private SubrogationDataDao subrogationDataDao;

    public static SubrogationDataManager getInstance() {
        if (instance == null) {
            synchronized (SubrogationDataManager.class) {
                if (instance == null) {
                    instance = new SubrogationDataManager(SurveyApplication.get());
                }
            }
        }
        return instance;
    }

    public SubrogationDataManager(Context context) {
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        subrogationDataDao = daoSession.getSubrogationDataDao();
    }

    //对方责任信息

    public List<SubrogationData> getSubrogationDataList(String reportCode) {
        if (reportCode == null) {
            return null;
        }
        List<SubrogationData> subrogationDataList = subrogationDataDao.queryBuilder()
                .where(SubrogationDataDao.Properties.ReportCode.eq(reportCode)).list();
        if (subrogationDataList != null && subrogationDataList.size() > 0) {
            return subrogationDataList;
        }
        return null;
    }

    public SubrogationData getSubrogationData(String reportCode, String dataId) {
        if (reportCode == null || dataId == null) {
            return null;
        }
        List<SubrogationData> subrogationDataList = subrogationDataDao.queryBuilder()
                .where(SubrogationDataDao.Properties.ReportCode.eq(reportCode),
                        SubrogationDataDao.Properties.Id.eq(dataId)).list();

        if (subrogationDataList != null && subrogationDataList.size() > 0) {
            return subrogationDataList.get(0);
        }
        return null;
    }

    public void saveSubrogationData(SubrogationData subrogationData) {
        String reportCode = subrogationData.getReportCode();
        String id = subrogationData.getId();
        if (getSubrogationData(reportCode, id) != null) {
            subrogationDataDao.update(subrogationData);
        } else {
            subrogationDataDao.insert(subrogationData);
        }
    }

    public void deleteSubrogationDataById(String id) {
        List<SubrogationData> driverInfoList = subrogationDataDao.queryBuilder()
                .where(SubrogationDataDao.Properties.Id.eq(id)).list();
        if (driverInfoList != null && driverInfoList.size() > 0) {
            subrogationDataDao.delete(driverInfoList.get(0));
        }
    }

    public void deleteSubrogationData(SubrogationData subrogationData) {
        subrogationDataDao.delete(subrogationData);
    }

}
