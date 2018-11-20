package com.hl.contract.table.manager;

import android.content.Context;

import com.hl.contract.table.dao.DriverInfoDao;
import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.table.model.DriverInfo;

import java.util.List;

/**
 * Created by chenxuefei on 2017/2/5.
 */
public class DriverInfoManager {
    private static DriverInfoManager instance;
    private DriverInfoDao driverInfoDao;

    public static DriverInfoManager getInstance(){
        if(instance == null){
            synchronized (DriverInfoManager.class){
                if(instance == null){
                    instance = new DriverInfoManager(SurveyApplication.get());
                }
            }
        }
        return  instance;
    }

    private DriverInfoManager(Context context) {
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        driverInfoDao = daoSession.getDriverInfoDao();
    }

    //驾驶员信息

    public List<DriverInfo> getDriverInfoList(String reportCode){
        if (reportCode == null) {
            return null;
        }
        List<DriverInfo> driverInfoList =  driverInfoDao.queryBuilder()
                .where(DriverInfoDao.Properties.ReportCode.eq(reportCode)).list();
        if (driverInfoList != null && driverInfoList.size() > 0) {
            return driverInfoList;
        }
        return null;
    }

    /**
     * 获取驾驶员信息
     */
    public DriverInfo getDriverInfo(String reportNo) {
        List<DriverInfo> list = driverInfoDao.queryBuilder().where(DriverInfoDao.Properties.ReportCode.eq(reportNo)).list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return new DriverInfo();
        }
    }

    public DriverInfo getDriverInfo(String reportCode, String dataId) {
        if (reportCode == null || dataId == null) {
            return null;
        }
        List<DriverInfo> driverInfoList = driverInfoDao.queryBuilder()
                .where(DriverInfoDao.Properties.ReportCode.eq(reportCode),
                        DriverInfoDao.Properties.Id.eq(dataId)).list();

        if (driverInfoList != null && driverInfoList.size() > 0) {
            return driverInfoList.get(0);
        }
        return null;
    }

    /**
     * 根据报案号 和 序号查询驾驶员信息
     * @param reportCode
     * @param serialNo
     * @return
     */
    public DriverInfo getDriverInfoBySerialNo(String reportCode, Integer serialNo) {
        if (reportCode == null || serialNo == null) {
            return null;
        }
        List<DriverInfo> driverInfoList = driverInfoDao.queryBuilder()
                .where(DriverInfoDao.Properties.ReportCode.eq(reportCode),
                        DriverInfoDao.Properties.SerialNo.eq(serialNo)).list();

        if (driverInfoList != null && driverInfoList.size() > 0) {
            return driverInfoList.get(0);
        }
        return null;
    }

    public void saveDriverInfo(DriverInfo driverInfo) {
        String reportCode = driverInfo.getReportCode();
        String id = driverInfo.getId();
        if (getDriverInfo(reportCode, id) != null) {
            driverInfoDao.update(driverInfo);
        } else {
            driverInfoDao.insert(driverInfo);
        }
    }

    /**
     * 获取财产损失的最大序号(新增时序号递增)
     * @param reportCode
     * @return
     */
    public int getDriverInfoMaxSerialNo(String reportCode) {
        Integer maxSerialNo = 0;
        if (reportCode == null) {
            return maxSerialNo;
        }
        List<DriverInfo> driverInfoList = driverInfoDao.queryBuilder()
                .where(DriverInfoDao.Properties.ReportCode.eq(reportCode)).orderDesc(DriverInfoDao.Properties.SerialNo).list();

        if (driverInfoList != null && driverInfoList.size() > 0) {
            maxSerialNo = driverInfoList.get(0).getSerialNo();
            return maxSerialNo == null ? 0 : maxSerialNo;
        }
        return maxSerialNo;
    }

    public void deleteDriverInfoById(String id){
        List<DriverInfo> driverInfoList = driverInfoDao.queryBuilder()
                .where(DriverInfoDao.Properties.Id.eq(id)).list();
        if (driverInfoList != null && driverInfoList.size() > 0) {
            driverInfoDao.delete(driverInfoList.get(0));
        }
    }

    public void deleteDriverInfo(DriverInfo driverInfo) {
        if (driverInfo != null) {
            driverInfoDao.delete(driverInfo);
        }
    }

    public void insertOrReplaceInTxDriverInfoList(List<DriverInfo> driverInfoList){
        if(driverInfoList != null && driverInfoList.size() > 0){
            driverInfoDao.insertOrReplaceInTx(driverInfoList);
        }
    }

}
