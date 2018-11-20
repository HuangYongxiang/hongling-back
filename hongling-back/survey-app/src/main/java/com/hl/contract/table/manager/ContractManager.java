package com.hl.contract.table.manager;

import android.content.Context;
import android.text.TextUtils;

import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.ContractDao;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.table.model.Contract;

import java.util.List;
public class ContractManager {
    private static ContractManager instance;
    private ContractDao contractDao;

    public static ContractManager getInstance(){
        if(instance == null){
            synchronized (ContractManager.class){
                if(instance == null){
                    instance = new ContractManager(SurveyApplication.get());
                }
            }
        }
        return  instance;
    }

    private ContractManager(Context context) {
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        contractDao = daoSession.getContractDao();
    }

    public Contract getContract(String reportCode, String plateNo) {
        if (reportCode == null) {
            return null;
        }
        List<Contract> reportCarList = contractDao.queryBuilder()
                .where(ContractDao.Properties.ReportCode.eq(reportCode),
                        ContractDao.Properties.PlateNo.eq(plateNo)).list();

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
    public int getContractMaxSerialNo(String reportCode) {
        Integer maxSerialNo = 0;
        if (reportCode == null) {
            return maxSerialNo;
        }
        List<Contract> reportCarList = contractDao.queryBuilder()
                .where(ContractDao.Properties.ReportCode.eq(reportCode)).orderDesc(ContractDao.Properties.SerialNo).list();

        if (reportCarList != null && reportCarList.size() > 0) {
            maxSerialNo = reportCarList.get(0).getSerialNo();
            return maxSerialNo == null ? 0 : maxSerialNo;
        }
        return maxSerialNo;
    }

    /**
     * @param userId
     * @return 获取本地合同列表
     */
    public List<Contract> getContractList(String userId){
        if (userId == null) {
            return null;
        }
        List<Contract> reportCarList = contractDao.queryBuilder()
                .where(ContractDao.Properties.UserId.eq(userId)).list();
        if (reportCarList != null && reportCarList.size() > 0) {
            return reportCarList;
        }
        return null;
    }

    /**
     * @param userId
     * @return 获取本地合同列表(审批)
     */
    public List<Contract> getContractListShenPi(String userId){
        if (userId == null) {
            return null;
        }
        List<Contract> reportCarList = contractDao.queryBuilder()
                .where(ContractDao.Properties.UserId.eq(userId),
                        ContractDao.Properties.ExemptFlag.eq("0")).list();
        if (reportCarList != null && reportCarList.size() > 0) {
            return reportCarList;
        }
        return null;
    }


    //涉案车辆

    /**
     * @param reportCode
     * @return Contract 获取所有的涉案车辆信息
     */
    public List<Contract> getAllSurveyCar(String reportCode) {
        if (reportCode == null) {
            return null;
        }
        try {
            SurveyGreenDaoHelper.getInstance().getDaoSession(null).clear();
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Contract> reportCarList = contractDao.queryBuilder()
                .where(ContractDao.Properties.ReportCode.eq(reportCode)).list();

        if (reportCarList != null && reportCarList.size() > 0) {
            return reportCarList;
        }
        return null;
    }



    /**
     * @param reportCode
     * @return Contract 获取标的车辆信息
     */
    public Contract getSurveyCar(String reportCode) {
        if (reportCode == null) {
            return null;
        }
        List<Contract> reportCarList = contractDao.queryBuilder()
                .where(ContractDao.Properties.ReportCode.eq(reportCode),
                        ContractDao.Properties.CarType.eq("1")).list();

        if (reportCarList != null && reportCarList.size() > 0) {
            return reportCarList.get(0);
        }
        return null;
    }


    /**
     * @param reportCode
     * @return 获取三者车列表，用于查勘主页面。
     */
    public List<Contract> getSurveyThirdCarList(String reportCode){
        if (reportCode == null) {
            return null;
        }
        List<Contract> reportCarList = contractDao.queryBuilder()
                .where(ContractDao.Properties.ReportCode.eq(reportCode),
                        ContractDao.Properties.CarType.eq("2")).list();
        if (reportCarList != null && reportCarList.size() > 0) {
            return reportCarList;
        }
        return null;
    }

    /**
     * @param reportCode
     * @return 在报案详情页面显示的三者车列表
     */
    public List<Contract> getReportThirdCarList(String reportCode) {
        if (reportCode == null) {
            return null;
        }
        List<Contract> reportCarList = contractDao.queryBuilder()
                .where(ContractDao.Properties.ReportCode.eq(reportCode),
                        ContractDao.Properties.CarType.eq("2")).list();//这里暂时认为2是三者车
        if (reportCarList != null && reportCarList.size() > 0) {
            return reportCarList;
        }
        return null;
    }

    /**
     * @param reportCode
     * @return Contract 获取车辆信息
     */
    public Contract getSurveyThirdCar(String reportCode) {
        if(reportCode == null){
            return null;
        }
        List<Contract> reportCarList = contractDao.queryBuilder()
                .where(ContractDao.Properties.ReportCode.eq(reportCode)).list();

        if (reportCarList != null && reportCarList.size() > 0) {
            return reportCarList.get(0);
        }

        return null;
    }

    /**
     * @param reportCode
     * @param plateNo
     * @return Contract 获取车辆信息
     */
    public Contract getSurveyThirdCarByPlateNo(String reportCode, String plateNo) {
        if(reportCode == null || plateNo ==null){
            return null;
        }
        List<Contract> reportCarList = contractDao.queryBuilder()
                .where(ContractDao.Properties.ReportCode.eq(reportCode),
                        ContractDao.Properties.PlateNo.eq(plateNo)).list();

        if (reportCarList != null && reportCarList.size() > 0) {
            return reportCarList.get(0);
        }

        return null;
    }

    /**
     * @param reportCode
     * @param serialNo
     * @return Contract 获取车辆信息
     */
    public Contract getSurveyThirdCarBySerialNo(String reportCode, Integer serialNo) {
        if(reportCode == null || serialNo == null){
            return null;
        }
        List<Contract> reportCarList = contractDao.queryBuilder()
                .where(ContractDao.Properties.ReportCode.eq(reportCode),
                        ContractDao.Properties.SerialNo.eq(serialNo)).list();

        if (reportCarList != null && reportCarList.size() > 0) {
            return reportCarList.get(0);
        }

        return null;
    }

    /**
     * @param plateNum
     * @param reportCode
     * @return Contract 根据车牌号和报案号获取车辆信息
     */
    public Contract getSurveyThirdCarByPlateNumAndReportNo(String plateNum, String reportCode) {
        if(reportCode == null || plateNum ==null){
            return null;
        }
        List<Contract> reportCarList = contractDao.queryBuilder()
                .where(ContractDao.Properties.PlateNo.eq(plateNum), ContractDao.Properties.ReportCode.eq(reportCode)).list();

        if (reportCarList != null && reportCarList.size() > 0) {
            return reportCarList.get(0);
        }

        return null;
    }

    public void saveSurveyCar(Contract reportCar) {
        String reportCode = reportCar.getReportCode();
        String dataId = reportCar.getId();
        if (getSurveyThirdCar(reportCode) != null) {
            contractDao.update(reportCar);
        } else {
            contractDao.insert(reportCar);
        }
    }

    public void deleteSurveyCar(Contract reportCar) {
        if (reportCar != null) {
            contractDao.delete(reportCar);
        }
    }

    public void deleteSurveyCarById(String id) {
        List<Contract> reportCarList = contractDao.queryBuilder()
                .where(ContractDao.Properties.ReportCode.eq(id)).list();
        if (reportCarList != null && reportCarList.size() > 0) {
            contractDao.delete(reportCarList.get(0));
        }
    }

    public void insertOrReplaceInTxContractList(List<Contract> reportCarList) {
        if (reportCarList != null && reportCarList.size() > 0) {
            contractDao.insertOrReplaceInTx(reportCarList);
        }
    }

    /**
     *  wxl
     *  校验涉案车辆的车牌号、车架号，不能相同
     */
    public String checkSurveyCar(String reportCode, Contract reportCar){
        String msg ="";
        if(reportCar == null|| TextUtils.isEmpty(reportCode)){
            return  msg;
        }
       if(getAllSurveyCar(reportCode)==null||getAllSurveyCar(reportCode).size()==0){
           return  msg;
       }else{
           List<Contract> reportCarList =  getAllSurveyCar(reportCode);
           for (Contract tempCar : reportCarList){

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
