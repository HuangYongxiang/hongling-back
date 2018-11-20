package com.hl.contract.table.manager;

import android.content.Context;
import android.text.TextUtils;

import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.table.dao.ReportCarDao;
import com.hl.contract.table.model.ReportCar;

import java.util.List;

/**
 * Created by chenxuefei on 2017/2/5.
 */
public class ReportCarManager {
    private static ReportCarManager instance;
    private ReportCarDao reportCarDao;

    public static ReportCarManager getInstance(){
        if(instance == null){
            synchronized (ReportCarManager.class){
                if(instance == null){
                    instance = new ReportCarManager(SurveyApplication.get());
                }
            }
        }
        return  instance;
    }

    private ReportCarManager(Context context) {
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        reportCarDao = daoSession.getReportCarDao();
    }

    public ReportCar getReportCar(String reportCode, String plateNo) {
        if (reportCode == null) {
            return null;
        }
        List<ReportCar> reportCarList = reportCarDao.queryBuilder()
                .where(ReportCarDao.Properties.ReportCode.eq(reportCode),
                        ReportCarDao.Properties.PlateNo.eq(plateNo)).list();

        if (reportCarList != null && reportCarList.size() > 0) {
            return reportCarList.get(0);
        }
        return null;
    }

    /**
     * 获取涉案车辆的最大序号(新增时序号递增)
     * @param reportCode
     * @return
     */
    public int getReportCarMaxSerialNo(String reportCode) {
        Integer maxSerialNo = 0;
        if (reportCode == null) {
            return maxSerialNo;
        }
        List<ReportCar> reportCarList = reportCarDao.queryBuilder()
                .where(ReportCarDao.Properties.ReportCode.eq(reportCode)).orderDesc(ReportCarDao.Properties.SerialNo).list();

        if (reportCarList != null && reportCarList.size() > 0) {
            maxSerialNo = reportCarList.get(0).getSerialNo();
            return maxSerialNo == null ? 0 : maxSerialNo;
        }
        return maxSerialNo;
    }

    /**
     * @param reportCode
     * @return 获取涉案车辆，用于查勘主页面。
     */
    public List<ReportCar> getSurveyReportCarList(String reportCode){
        if (reportCode == null) {
            return null;
        }
        List<ReportCar> reportCarList = reportCarDao.queryBuilder()
                .where(ReportCarDao.Properties.ReportCode.eq(reportCode)).list();
        if (reportCarList != null && reportCarList.size() > 0) {
            return reportCarList;
        }
        return null;
    }


    //涉案车辆

    /**
     * @param reportCode
     * @return ReportCar 获取所有的涉案车辆信息
     */
    public List<ReportCar> getAllSurveyCar(String reportCode) {
        if (reportCode == null) {
            return null;
        }
        try {
            SurveyGreenDaoHelper.getInstance().getDaoSession(null).clear();
        }catch (Exception e){
            e.printStackTrace();
        }
        List<ReportCar> reportCarList = reportCarDao.queryBuilder()
                .where(ReportCarDao.Properties.ReportCode.eq(reportCode)).list();

        if (reportCarList != null && reportCarList.size() > 0) {
            return reportCarList;
        }
        return null;
    }



    /**
     * @param reportCode
     * @return ReportCar 获取标的车辆信息
     */
    public ReportCar getSurveyCar(String reportCode) {
        if (reportCode == null) {
            return null;
        }
        List<ReportCar> reportCarList = reportCarDao.queryBuilder()
                .where(ReportCarDao.Properties.ReportCode.eq(reportCode),
                        ReportCarDao.Properties.CarType.eq("1")).list();

        if (reportCarList != null && reportCarList.size() > 0) {
            return reportCarList.get(0);
        }
        return null;
    }


    /**
     * @param reportCode
     * @return 获取三者车列表，用于查勘主页面。
     */
    public List<ReportCar> getSurveyThirdCarList(String reportCode){
        if (reportCode == null) {
            return null;
        }
        List<ReportCar> reportCarList = reportCarDao.queryBuilder()
                .where(ReportCarDao.Properties.ReportCode.eq(reportCode),
                        ReportCarDao.Properties.CarType.eq("2")).list();
        if (reportCarList != null && reportCarList.size() > 0) {
            return reportCarList;
        }
        return null;
    }

    /**
     * @param reportCode
     * @return 在报案详情页面显示的三者车列表
     */
    public List<ReportCar> getReportThirdCarList(String reportCode) {
        if (reportCode == null) {
            return null;
        }
        List<ReportCar> reportCarList = reportCarDao.queryBuilder()
                .where(ReportCarDao.Properties.ReportCode.eq(reportCode),
                        ReportCarDao.Properties.CarType.eq("2")).list();//这里暂时认为2是三者车
        if (reportCarList != null && reportCarList.size() > 0) {
            return reportCarList;
        }
        return null;
    }

    /**
     * @param reportCode
     * @param dataId
     * @return ReportCar 获取车辆信息
     */
    public ReportCar getSurveyThirdCar(String reportCode, String dataId) {
        if(reportCode == null || dataId ==null){
            return null;
        }
        List<ReportCar> reportCarList = reportCarDao.queryBuilder()
                .where(ReportCarDao.Properties.ReportCode.eq(reportCode),
                        ReportCarDao.Properties.Id.eq(dataId)).list();

        if (reportCarList != null && reportCarList.size() > 0) {
            return reportCarList.get(0);
        }

        return null;
    }

    /**
     * @param reportCode
     * @param plateNo
     * @return ReportCar 获取车辆信息
     */
    public ReportCar getSurveyThirdCarByPlateNo(String reportCode, String plateNo) {
        if(reportCode == null || plateNo ==null){
            return null;
        }
        List<ReportCar> reportCarList = reportCarDao.queryBuilder()
                .where(ReportCarDao.Properties.ReportCode.eq(reportCode),
                        ReportCarDao.Properties.PlateNo.eq(plateNo)).list();

        if (reportCarList != null && reportCarList.size() > 0) {
            return reportCarList.get(0);
        }

        return null;
    }

    /**
     * @param reportCode
     * @param serialNo
     * @return ReportCar 获取车辆信息
     */
    public ReportCar getSurveyThirdCarBySerialNo(String reportCode, Integer serialNo) {
        if(reportCode == null || serialNo == null){
            return null;
        }
        List<ReportCar> reportCarList = reportCarDao.queryBuilder()
                .where(ReportCarDao.Properties.ReportCode.eq(reportCode),
                        ReportCarDao.Properties.SerialNo.eq(serialNo)).list();

        if (reportCarList != null && reportCarList.size() > 0) {
            return reportCarList.get(0);
        }

        return null;
    }

    /**
     * @param plateNum
     * @param reportCode
     * @return ReportCar 根据车牌号和报案号获取车辆信息
     */
    public ReportCar getSurveyThirdCarByPlateNumAndReportNo(String plateNum, String reportCode) {
        if(reportCode == null || plateNum ==null){
            return null;
        }
        List<ReportCar> reportCarList = reportCarDao.queryBuilder()
                .where(ReportCarDao.Properties.PlateNo.eq(plateNum), ReportCarDao.Properties.ReportCode.eq(reportCode)).list();

        if (reportCarList != null && reportCarList.size() > 0) {
            return reportCarList.get(0);
        }

        return null;
    }

    public void saveSurveyCar(ReportCar reportCar) {
        String reportCode = reportCar.getReportCode();
        String dataId = reportCar.getId();
        if (getSurveyThirdCar(reportCode, dataId) != null) {
            reportCarDao.update(reportCar);
        } else {
            reportCarDao.insert(reportCar);
        }
    }

    public void deleteSurveyCar(ReportCar reportCar) {
        if (reportCar != null) {
            reportCarDao.delete(reportCar);
        }
    }

    public void deleteSurveyCarById(String id) {
        List<ReportCar> reportCarList = reportCarDao.queryBuilder()
                .where(ReportCarDao.Properties.Id.eq(id)).list();
        if (reportCarList != null && reportCarList.size() > 0) {
            reportCarDao.delete(reportCarList.get(0));
        }
    }

    public void insertOrReplaceInTxReportCarList(List<ReportCar> reportCarList) {
        if (reportCarList != null && reportCarList.size() > 0) {
            reportCarDao.insertOrReplaceInTx(reportCarList);
        }
    }

    /**
     *  wxl
     *  校验涉案车辆的车牌号、车架号，不能相同
     */
    public String checkSurveyCar(String reportCode, ReportCar reportCar){
        String msg ="";
        if(reportCar == null|| TextUtils.isEmpty(reportCode)){
            return  msg;
        }
       if(getAllSurveyCar(reportCode)==null||getAllSurveyCar(reportCode).size()==0){
           return  msg;
       }else{
           List<ReportCar> reportCarList =  getAllSurveyCar(reportCode);
           for (ReportCar tempCar : reportCarList){

               if(!reportCar.getId().equals(tempCar.getId())) {
                   if(reportCar.getPlateNo().equals(tempCar.getPlateNo())){
                       msg = "车牌号"+reportCar.getPlateNo()+"在涉案车辆中已存在";
                       return  msg;
                   }else if(reportCar.getVinNo().equals(tempCar.getVinNo())) {
                       msg = "VIN码"+reportCar.getVinNo()+"在涉案车辆中已存在";
                       return  msg;
                   }
                }
            }
       }

        return  msg;
    }

}
