package com.hl.contract.table.manager;

import android.content.Context;

import com.hl.core.lib.util.UtilManager;
import com.hl.contract.business.main.service.dto.LpFlowDTO;
import com.hl.contract.business.main.service.dto.LpHistoricalReportInfoDTO;
import com.hl.contract.business.main.service.dto.LpHistoricalpaymentInfoDTO;
import com.hl.contract.business.main.service.dto.LpInsuranceCarDTO;
import com.hl.contract.business.main.service.dto.LpInsuranceTypeDTO;
import com.hl.contract.business.main.service.dto.LpPolicyDTO;
import com.hl.contract.business.main.service.dto.LpReportCarDTO;
import com.hl.contract.business.main.service.dto.LpReportDTO;
import com.hl.contract.business.main.service.dto.LpReportDriverDTO;
import com.hl.contract.business.main.service.dto.LpReportHumanTrackingDTO;
import com.hl.contract.business.main.service.dto.LpReportLossesDTO;
import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.table.dao.TaskInfoDao;
import com.hl.contract.table.model.DriverInfo;
import com.hl.contract.table.model.HistoricalReportInfo;
import com.hl.contract.table.model.HistoricalPaymentInfo;
import com.hl.contract.table.model.Insurance;
import com.hl.contract.table.model.PolicyInfo;
import com.hl.contract.table.model.ReportCar;
import com.hl.contract.table.model.ReportInfo;
import com.hl.contract.table.model.SurveyAimCar;
import com.hl.contract.table.model.SurveyInjury;
import com.hl.contract.table.model.SurveyProperty;
import com.hl.contract.table.model.TaskInfo;
import com.hl.contract.util.SurveyClaimUtil;

import java.util.ArrayList;
import java.util.List;

import static com.hl.core.lib.util.UtilManager.UUIDUtil;

/**
 * @Describe: 任务
 * @Package: com.hl.contract.table.manager
 * @Author: liyu
 * @Date: 2018/5/8/ 13:23
 * @Copyright: hl
 */

public class TaskManager{
    public final static String TAG = "TaskManager";
    public static TaskManager instance;
    private Context mContext;
    private TaskInfoDao taskInfoDao;


    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager(SurveyApplication.get());
        }
        return instance;
    }

    private TaskManager(Context context) {
        mContext = context;
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        taskInfoDao = daoSession.getTaskInfoDao();

    }

    public void updateTaskInfo(TaskInfo taskInfo) {
        if (taskInfo != null) {
            taskInfoDao.update(taskInfo);
        }
    }
    /**
     * @param lpReportDTO
     * @param plateNo
     * @return 是否需要在数据库中插入此条报案信息。
     */

    public boolean saveLpReportData(LpReportDTO lpReportDTO, String plateNo) {
        if (lpReportDTO == null) {
            return false;
        }
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setId(lpReportDTO.getId() == null ? UUIDUtil.getUUID() : lpReportDTO.getId());
        reportInfo.setUserId(UtilManager.SP.survey().getString(SurveyClaimUtil.USER_ID, ""));
        reportInfo.setReportCode(lpReportDTO.getReportCode());
        reportInfo.setAccidentCauseCode(lpReportDTO.getAccidentCauseCode());
        reportInfo.setAccidentCauseName(lpReportDTO.getAccidentCauseName());
        reportInfo.setAccidentDutyCode(lpReportDTO.getAccidentDutyCode());
        reportInfo.setAccidentDutyName(lpReportDTO.getAccidentDutyName());
        reportInfo.setReportPersonName(lpReportDTO.getReportPersonName());
        reportInfo.setReportMoblePhone(lpReportDTO.getReportPhone());
        reportInfo.setReportTime(lpReportDTO.getReportTime());
        reportInfo.setAccidentTime(lpReportDTO.getAccidentTime());
        reportInfo.setIsCurrentReport(lpReportDTO.getIsCurrentReport() == null ? "1" : lpReportDTO.getIsCurrentReport());
        reportInfo.setManageType(lpReportDTO.getManageType());
        reportInfo.setAccidentReasonCode(lpReportDTO.getAccidentReasonCode());
        reportInfo.setAccidentReasonName("");//TODO 没有
        reportInfo.setHugeType(lpReportDTO.getHugeType());
        reportInfo.setHugeTypeName("");//TODO
        reportInfo.setHugeCode(lpReportDTO.getHugeCode());
        reportInfo.setHugeName("");//TODO
        reportInfo.setInsuranceCompanyCode(lpReportDTO.getInsuranceCompanyCode());
        reportInfo.setInsuranceCompanyName(lpReportDTO.getInsuranceCompanyName());
        reportInfo.setReportType(lpReportDTO.getReportType());
        reportInfo.setAccidentPlace(lpReportDTO.getAccidentPlace());
        reportInfo.setLongitude("");
        reportInfo.setLatitude("");
        reportInfo.setAccidentCourse(lpReportDTO.getAccidentCourse());
        reportInfo.setCarLossFlag(lpReportDTO.getCarLossFlag());
        reportInfo.setInjureLossFlag(lpReportDTO.getInjureLossFlag());
        reportInfo.setCargoLossFlag(lpReportDTO.getCargoLossFlag());
        reportInfo.setThiefLossFlag(lpReportDTO.getThiefLossFlag());
        reportInfo.setInjureNum(lpReportDTO.getInjureNum());
        reportInfo.setAccidentArea(lpReportDTO.getAccidentArea());
        reportInfo.setAccidentWeather(lpReportDTO.getAccidentWeather());
        reportInfo.setChannelCode(lpReportDTO.getChannelCode());
        reportInfo.setChannelName(lpReportDTO.getChannelName());
        reportInfo.setCompleteDegree(0.0);
        reportInfo.setCompleteFlag("0");
        reportInfo.setSurveyFlag("-1");
        reportInfo.setAimCarFlag("-1");
        reportInfo.setDamageFlag("-1");
        reportInfo.setThirdCarFlag("-1");
        reportInfo.setPlateNum(plateNo);

        String reportCode = lpReportDTO.getReportCode();
        ReportInfoManager reportInfoManager = ReportInfoManager.getInstance();
        List<ReportInfo> reportInfoList = reportInfoManager.getReportInfoListByReportCode(reportCode);
        if (reportInfoList != null && reportInfoList.size() > 0) {
            reportInfo.setId(reportInfoList.get(0).getId());
            return false;
        } else {
            reportInfoManager.insertReportInfo(reportInfo);
            return true;
        }
    }


    /**
     * @param lpInsuranceCars 承保车辆信息（标的车）
     */
    public void saveInsuranceCarData(LpInsuranceCarDTO lpInsuranceCars) {
        if (lpInsuranceCars == null) {
            return;
        }
        SurveyAimCar surveyAimCar = new SurveyAimCar();
        surveyAimCar.setId(lpInsuranceCars.getId() == null ? UUIDUtil.getUUID() : lpInsuranceCars.getId());
        surveyAimCar.setReportCode(lpInsuranceCars.getReportCode());
        surveyAimCar.setRealPrice(lpInsuranceCars.getRealPrice());
        surveyAimCar.setVehiclePrice(lpInsuranceCars.getVehiclePrice());
        surveyAimCar.setIsImport(lpInsuranceCars.getIsImport());
        surveyAimCar.setVehicleType(lpInsuranceCars.getVehicleType());
        surveyAimCar.setVehicleTypeName(lpInsuranceCars.getVehicleType());   //TODO
        surveyAimCar.setCarColor(lpInsuranceCars.getCarColor());
        surveyAimCar.setCarColorName(lpInsuranceCars.getCarColor()); //TODO
        surveyAimCar.setEnrolDate(lpInsuranceCars.getEnrolDate());
        surveyAimCar.setUseProperty(lpInsuranceCars.getUseProperty());
        surveyAimCar.setDriverArea(lpInsuranceCars.getDriverArea());
        surveyAimCar.setSeat(lpInsuranceCars.getSeat());
        surveyAimCar.setVinNo(lpInsuranceCars.getVinno());
        surveyAimCar.setEngineNo(lpInsuranceCars.getEngineNo());
        surveyAimCar.setVehicleModel(lpInsuranceCars.getVehicleModel());
        surveyAimCar.setPlateNum(lpInsuranceCars.getPlateNum());
        surveyAimCar.setPower(lpInsuranceCars.getPower());
        surveyAimCar.setDisplacement(lpInsuranceCars.getDisplacement());
        surveyAimCar.setTonnage(lpInsuranceCars.getTonnage());
        surveyAimCar.setPlateColor(lpInsuranceCars.getPlateColor());
        surveyAimCar.setCreateTime(lpInsuranceCars.getCreateTime());
        surveyAimCar.setMakeDate(lpInsuranceCars.getMakeDate());
        surveyAimCar.setGuardAlarm(lpInsuranceCars.getGuardAlarm());
        surveyAimCar.setExemptFlag(lpInsuranceCars.getExemptFlag());
        surveyAimCar.setBelongProperty(lpInsuranceCars.getBelongProperty());

        String reportCode = lpInsuranceCars.getReportCode();
        SurveyAimCarManager surveyAimCarManager = SurveyAimCarManager.getInstance();
        List<SurveyAimCar> surveyAimCarList = surveyAimCarManager.getSurveyAimCarByReportNo(reportCode);
        if (surveyAimCarList != null && surveyAimCarList.size() > 0) {
            surveyAimCar.setId(surveyAimCarList.get(0).getId());
            surveyAimCarManager.updateSurveyAimCar(surveyAimCar);
        } else {
            surveyAimCarManager.insertOrReplaceSurveyAimCar(surveyAimCar);
        }
    }

    public void saveTaskListData(List<LpFlowDTO> lpFlowDTOs, String accidentPlace,
                                 String reportTime, String reportPersonName, String plateNo) {
        if (lpFlowDTOs == null || lpFlowDTOs.size() == 0) {
            return;
        }
        List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();
        TaskInfo taskInfo = null;
        for (LpFlowDTO lpFlowDTO : lpFlowDTOs) {
            String mainVehFlag = "1";
            String completeFlag = "0";//by liyu   中银演示要用，拉取任务查勘未完成，其他完成，当查勘完成时同时修改其他任务为完成
            String taskTypeCode = lpFlowDTO.getTaskTypeCode();
            if (SurveyClaimUtil.TASK_TYPE_SURVEY.equals(taskTypeCode)) {
                completeFlag = "0";
            }

            if (SurveyClaimUtil.TASK_TYPE_SURVEY.equals(taskTypeCode)) {
                mainVehFlag = "0";
            }
            taskInfo = new TaskInfo();
            taskInfo.setId(lpFlowDTO.getId() == null ? UUIDUtil.getUUID() : lpFlowDTO.getId());
            taskInfo.setReportCode(lpFlowDTO.getReportCode());//报案号
            taskInfo.setFlowId(lpFlowDTO.getId() == null ? "null" : lpFlowDTO.getId());//案件主键
            taskInfo.setDispatchType(lpFlowDTO.getDispatchType());//调度类型 1正常调度   2新增标的
            taskInfo.setTaskNo(lpFlowDTO.getTaskNo());//任务号
            taskInfo.setTaskCode(lpFlowDTO.getTaskTypeCode());//任务类型代码（0101查勘 0201 本车 0202 三者  0301 物损  0401人伤）
            taskInfo.setFlowCode(lpFlowDTO.getFlowCode());//流程平台代码(05定损 06核价 07核损 )
            taskInfo.setMainVeFlag(mainVehFlag);//主车标志 1主车    2	三者车
            taskInfo.setLossName(lpFlowDTO.getLossName());
            taskInfo.setDispatchPersonId(lpFlowDTO.getDispatchPersonId());
            taskInfo.setDispatchPersonName(lpFlowDTO.getDispatchPersonName());
            taskInfo.setDispatchTime(lpFlowDTO.getDispatchDate());
            taskInfo.setRemark(lpFlowDTO.getDispatchDescrip());
            taskInfo.setEvaluationPersonId(lpFlowDTO.getEvaluationPersonId());
            taskInfo.setEvaluationPersonName(lpFlowDTO.getEvaluationPersonName());
            taskInfo.setDealCompCode(lpFlowDTO.getDealOrgId());
            taskInfo.setDealCompName(lpFlowDTO.getDealOrgName());
            taskInfo.setStatusCode(lpFlowDTO.getStateCode());
            taskInfo.setSendPhoneFlag(lpFlowDTO.getSendPhoneFlag());
            taskInfo.setAccidentPlace(accidentPlace);
            taskInfo.setReportTime(reportTime);
            taskInfo.setReportPersonName(reportPersonName);
            taskInfo.setPlateNo(plateNo);
            taskInfo.setCompleteDegree(0);
            taskInfo.setCompleteFlag(completeFlag);
            taskInfo.setChildStateCode(lpFlowDTO.getChildStateCode());

            taskInfos.add(taskInfo);
        }
        insertOrReplaceInTxTaskInfos(taskInfos);
        updateReportInfo(lpFlowDTOs.get(0).getReportCode());
    }

    /**
     * 用于更新reportInfo表中的surveyFlag，aimCarFlag，damageFlag，thirdCarFlag，根据taskInfos表中的taskCode和completeFlag来确定reportInfo表中4个flag的值
     */
    public void updateReportInfo(String reportCode) {
        List<TaskInfo> taskInfoList = getTaskListByReportNo(reportCode);
        if (taskInfoList == null || taskInfoList.size() == 0) {
            return;
        }

        List<ReportInfo> reportInfoList = ReportInfoManager.getInstance().getReportInfoListByReportCode(reportCode);
        ReportInfo reportInfo = null;
        if (reportInfoList != null && reportInfoList.size() > 0) {
//            reportInfoEvent.setReportInfo(reportInfoList.get(0));
//            EventBus.post(reportInfoEvent);
            reportInfo = reportInfoList.get(0);
        } else {
            return;
        }
        String surveyFlag = "-1";//是否完成查勘标记
        String aimCarFlag = "-1";//是否完成标的标记
        String damageFlag = "-1";//是否完成物损标记
        String thirdCarFlag = "-1";//是否完成三者标记
        String injuryFlag = "-1";//是否完成人伤标记

        for (TaskInfo taskInfo : taskInfoList) {//by liyu   中银演示要用，拉取任务查勘未完成，其他完成，当查勘完成时同时修改其他任务为完成
            if (SurveyClaimUtil.TASK_TYPE_SURVEY.equals(taskInfo.getTaskCode())) {//任务类型代码（0101查勘 0201 本车 0202 三者 0301 物损  0401人伤）
                surveyFlag = taskInfo.getCompleteFlag() == null ? "0" : taskInfo.getCompleteFlag();
            } else if (SurveyClaimUtil.TASK_TYPE_EVAL_BD.equals(taskInfo.getTaskCode())) {
                aimCarFlag = taskInfo.getCompleteFlag() == null ? "1" : taskInfo.getCompleteFlag();
            } else if (SurveyClaimUtil.TASK_TYPE_EVAL_SZ.equals(taskInfo.getTaskCode())) {
                thirdCarFlag = taskInfo.getCompleteFlag() == null ? "0" : taskInfo.getCompleteFlag();
            } else if (SurveyClaimUtil.TASK_TYPE_POP.equals(taskInfo.getTaskCode())) {
                damageFlag = taskInfo.getCompleteFlag() == null ? "0" : taskInfo.getCompleteFlag();
            } else if (SurveyClaimUtil.TASK_TYPE_INJURY.equals(taskInfo.getTaskCode())) {
                injuryFlag = taskInfo.getCompleteFlag() == null ? "0" : taskInfo.getCompleteFlag();
            }
        }

        reportInfo.setSurveyFlag(surveyFlag);
        reportInfo.setAimCarFlag(aimCarFlag);
        reportInfo.setDamageFlag(damageFlag);
        reportInfo.setThirdCarFlag(thirdCarFlag);
        reportInfo.setInjureLossFlag(injuryFlag);

        ReportInfoManager.getInstance().updateReportInfo(reportInfo);
    }

    public void savePolicyData(List<LpPolicyDTO> policyList, String reportCode) {
        if (policyList == null || policyList.size() == 0) {
            return;
        }
        List<PolicyInfo> policyInfos = new ArrayList<PolicyInfo>();
        for (LpPolicyDTO policy : policyList) {
            PolicyInfo policyInfo = new PolicyInfo();
            policyInfo.setId(policy.getId() == null ? UUIDUtil.getUUID() : policy.getId());
            policyInfo.setPolicyCode(policy.getPolicyCode());
            policyInfo.setReportId(policy.getReportId());
            policyInfo.setReportCode(reportCode);
            policyInfo.setInsureBgnDate(policy.getInsureBgnDate());
            policyInfo.setInsureEndDate(policy.getInsureEndDate());
            policyInfo.setVehicleCode(policy.getVehicleCode());
            policyInfo.setVehicleName(policy.getVehicleName());
            policyInfo.setInsuredPerson(policy.getInsuredPerson());
            policyInfo.setCompanyCode(policy.getCompanyCode());
            policyInfo.setCompanyName(policy.getCompanyName());
            policyInfo.setTotalInsSum(policy.getTotalInsSum());
            policyInfo.setRiskType(policy.getRiskType());
            policyInfo.setRiskCode(policy.getRiskCode());
            policyInfo.setRiskName(policy.getRiskName());
            policyInfo.setCustomerTypeCode(policy.getCustomerTypeCode());
            policyInfo.setCustomerTypeName(policy.getCustomerTypeName());
            policyInfo.setSpecifyAssume(policy.getSpecifyAssume());
            policyInfo.setChannelCode(policy.getChannelCode());
            policyInfo.setChannelName(policy.getChannelName());
            policyInfo.setBillDate(policy.getBillDate());
            policyInfo.setPolicyHoldDate(policy.getPolicyHoldDate());
            policyInfo.setModelName(policy.getModelName());
            policyInfo.setCarOwner(policy.getCarOwner());
            policyInfo.setIdentityNo(policy.getIdentityNo());
            policyInfo.setDriverName(policy.getDriverName());
            policyInfo.setDealPerson(policy.getDealPerson());
            policyInfo.setProxyInfo(policy.getProxyInfo());
            policyInfo.setAccidentNum(policy.getAccidentNum());
            policyInfo.setInsuranceBranchCompanyCode("");    //TODO
            policyInfo.setInsuranceBranchCompanyName("");//TODO

            policyInfos.add(policyInfo);
            List<LpInsuranceTypeDTO> LpInsuranceTypeList = policy.getInsuranceItemList();
            saveInsuranceTypeData(LpInsuranceTypeList);
        }

        PolicyInfoManager.getInstance().insertOrReplaceInTxPolicyInfos(policyInfos);
    }

    /**
     * @param LpInsuranceTypeList 保存承保险别
     */
    public void saveInsuranceTypeData(List<LpInsuranceTypeDTO> LpInsuranceTypeList) {
        if (LpInsuranceTypeList == null || LpInsuranceTypeList.size() == 0) {
            return;
        }

        List<Insurance> insuranceList = new ArrayList<Insurance>();
        for (LpInsuranceTypeDTO LpInsuranceType : LpInsuranceTypeList) {
            Insurance insurance = new Insurance();
            insurance.setId(LpInsuranceType.getId() == null ? UUIDUtil.getUUID() : LpInsuranceType.getId());
            insurance.setReportCode(LpInsuranceType.getReportCode());
            insurance.setPolicyId(LpInsuranceType.getPolicyId());
            insurance.setRiskCode(LpInsuranceType.getRiskCode());
            insurance.setRiskName(LpInsuranceType.getRiskName());
            insurance.setItemCode(LpInsuranceType.getItemCode());
            insurance.setItemName(LpInsuranceType.getItemName());
            insurance.setInsuranceSuitYear(LpInsuranceType.getInsuranceSuitYear());
            insurance.setTotalInsSum(LpInsuranceType.getTotalInsSum());
            insurance.setTotalInsFee(LpInsuranceType.getTotalInsFee());
            insurance.setInsuranceProperty(LpInsuranceType.getInsuranceProperty());
            insurance.setItemAttribute(LpInsuranceType.getItemAttribute());
            insurance.setNopayFlag(LpInsuranceType.getNopayFlag());
            insuranceList.add(insurance);
        }
        InsuranceManager.getInstance().insertOrReplaceInTxInsuranceList(insuranceList);

/*        List<Insurance> insuranceList1 = insuranceDao.loadAll();
        for(Insurance insurance1:insuranceList1) {
            insurance1.toString();
        }*/
    }

    public void saveReportCarData(List<LpReportCarDTO> lpReportCarList) {
        if (lpReportCarList == null || lpReportCarList.size() == 0) {
            return;
        }
        List<ReportCar> reportCarList = new ArrayList<ReportCar>();
        for (LpReportCarDTO lpReportCar : lpReportCarList) {
            ReportCar reportCar = new ReportCar();
            reportCar.setId(lpReportCar.getId() == null ? UUIDUtil.getUUID() : lpReportCar.getId());
            reportCar.setReportCode(lpReportCar.getReportCode());//报案号
            Integer serialNo = lpReportCar.getSerialNo();//序号
            if(serialNo != null){
                if(serialNo == 1){
                    //车辆信息中SerialNo序号为“1”的是标的车，从“2”开始之后的均为三者车
                    reportCar.setCarType("标的");//车辆类型编码 1代表本车 2 代表三者车
                    reportCar.setCarTypeCode("1");//车辆类型编码 1代表本车 2 代表三者车 //TODO  没有
                }else if(serialNo >= 2){
                    reportCar.setCarType("三者");//车辆类型编码 1代表本车 2 代表三者车
                    reportCar.setCarTypeCode("2");//车辆类型编码 1代表本车 2 代表三者车 //TODO  没有
                }
            }
            reportCar.setVehicleModel(lpReportCar.getBrandModel());//厂牌型号
            reportCar.setEngineNo(lpReportCar.getEngineNo());//发动机号
            reportCar.setVinNo(lpReportCar.getVin());//VIN/车架号
            reportCar.setPlateNo(lpReportCar.getPlateNo());//号牌号码
            reportCar.setPlateType("");//号牌种类
            reportCar.setPlateTypeCode(lpReportCar.getPlateType()); //号牌种类编码 //TODO  没有
            reportCar.setAmountDamages(lpReportCar.getAmountDamages().doubleValue());//损失金额
            reportCar.setDutyRatio(lpReportCar.getDutyRatio());//责任比例
            reportCar.setInsurancePolicyNo(lpReportCar.getInsurancePolicyNo());//交强险保单号
            reportCar.setInsuranceCompany(lpReportCar.getInsuranceCompany());//交强险承保公司
            reportCar.setCommercialPolicyNo(lpReportCar.getCommercialPolicyNo());//商业险保单号
            reportCar.setCommercialCompany(lpReportCar.getCommercialCompany());//商业险承保公司
            reportCar.setVehicleType(null); //车辆种类 //TODO  没有
            reportCar.setUseProperty(null);//使用性质（购买、租用、自用）//TODO  没有
            reportCar.setVehiclePrice(0.0);//新车购置价//TODO  没有
            reportCar.setRealPrice(0.0);//实际价值//TODO  没有
            reportCar.setIsImport(null);//国产/进口//TODO  没有
            reportCar.setMakeDate(null);//制造年月（出厂年份）//TODO  没有
            reportCar.setEnrolDate(null);//初次登记年月//TODO  没有
            reportCar.setSeat(0);//座位数量//TODO  没有
            reportCar.setPower(null);//功率//TODO  没有
            reportCar.setDisplacement(null);//排量//TODO  没有
            reportCar.setTonnage(null);//吨位（核定载质量）//TODO  没有
            reportCar.setCarColor(null);//车身颜色//TODO  没有
            reportCar.setPlateColor(null);//车牌颜色//TODO  没有
            reportCar.setGuardAlarm(null);//防盗装置//TODO  没有
            reportCar.setExemptFlag(null);//免验标志//TODO  没有
            reportCar.setDriverArea(null);//行驶区域//TODO  没有
            reportCar.setSerialNo(serialNo);

            reportCarList.add(reportCar);
        }
        ReportCarManager.getInstance().insertOrReplaceInTxReportCarList(reportCarList);
/*        List<ReportCar> reportCarss = reportCarDao.loadAll();
        for(ReportCar reportCar1:reportCarss) {
            reportCar1.toString();
        }*/
    }

    /**
     * @param lpReportDriverList 获取驾驶员信息保存到数据库
     */
    public void saveDriverInfoData(List<LpReportDriverDTO> lpReportDriverList) {
        if (lpReportDriverList == null || lpReportDriverList.size() == 0) {
            return;
        }
        List<DriverInfo> driverInfoList = new ArrayList<DriverInfo>();
        for (LpReportDriverDTO lpReportDriver : lpReportDriverList) {
            DriverInfo driverInfo = new DriverInfo();
            driverInfo.setId(lpReportDriver.getId() == null ? UUIDUtil.getUUID() : lpReportDriver.getId());
            driverInfo.setReportCode(lpReportDriver.getReportCode());//报案号
            driverInfo.setDriverName(lpReportDriver.getName());//姓名
            driverInfo.setContactNumber(lpReportDriver.getContactNumber());//联系电话
            driverInfo.setCertificateType(lpReportDriver.getCertificateType());//证件类型
            driverInfo.setCertificateTypeCode(null);//证件类型编码 //TODO 没有
            driverInfo.setCertificateNo(lpReportDriver.getCertificateNo());//证件号码
            driverInfo.setSex(lpReportDriver.getSex());//驾驶员性别
            driverInfo.setAge(0);//驾驶员年龄    //TODO 没有
            driverInfo.setLicenseNumber(lpReportDriver.getLicenseNumber());//驾驶证号码
            driverInfo.setDriveType(null);//准驾车型 //TODO 没有
            driverInfo.setDriveTypeCode(null);//准驾车型编码 //TODO 没有
            driverInfo.setPlateNo(null);//车牌号码 //TODO 没有
            driverInfo.setDriverTerritorial(null);//驾驶员属地 //TODO 没有
            driverInfo.setCertificationAuthority(null);//颁证机关 //TODO 没有
            driverInfo.setDegreeOfEducation(null);//文化程度 //TODO 没有
            driverInfo.setProfession(null);//行业 //TODO 没有
            driverInfo.setIssueDate(null);//初次领证日期 //TODO 没有
            driverInfo.setUnitOrAddress(null);// 单位或地址 //TODO 没有
            driverInfo.setSerialNo(lpReportDriver.getSerialNo());

            driverInfoList.add(driverInfo);
        }
        DriverInfoManager.getInstance().insertOrReplaceInTxDriverInfoList(driverInfoList);
/*        List<DriverInfo> driverInfos = driverInfoDao.loadAll();
        for(DriverInfo driverInfo1:driverInfos) {
            driverInfo1.toString();
        }*/
    }

    /**
     * @param LpReportLossesList 获取物损信息保存到数据库
     */
    public void saveSurveyDamageData(List<LpReportLossesDTO> LpReportLossesList) {
        //当没有物损的时候直接返回
        if (LpReportLossesList == null || LpReportLossesList.size() < 1) {
            return;
        }
        List<SurveyProperty> surveyPropertyList = new ArrayList<SurveyProperty>();
        for (LpReportLossesDTO lpReportLossesDTO : LpReportLossesList) {
            SurveyProperty surveyProperty = new SurveyProperty();
            surveyProperty.setId(lpReportLossesDTO.getId() == null ? UUIDUtil.getUUID() : lpReportLossesDTO.getId());
            surveyProperty.setReportCode(lpReportLossesDTO.getReportCode());//报案号
            surveyProperty.setAscriptionType(lpReportLossesDTO.getaScription());//归属（类别）
            surveyProperty.setAscriptionTypeCode(null);//归属（类别）编码 //TODO 没有
            surveyProperty.setLossName(lpReportLossesDTO.getLossName());//损失名称
            surveyProperty.setRiskCode(null);//险别编码//TODO 没有
            surveyProperty.setRiskName(null);//险别//TODO 没有
            surveyProperty.setExpensesName(null);//费用名称//TODO 没有
            surveyProperty.setLossPrice(0.0);//损失金额 //TODO 没有
            surveyProperty.setLossDegreeDescription(lpReportLossesDTO.getLossDegreeDescription());//损失程度描述
            surveyProperty.setSerialNo(lpReportLossesDTO.getSerialNo());

            surveyPropertyList.add(surveyProperty);
        }
        SurveyPropertyManager.getInstance().insertOrReplaceInTxSurveyPropertyList(surveyPropertyList);
/*        List<SurveyDamage> surveyDamageLists = surveyDamageDao.loadAll();
        for (SurveyDamage surveyDamage1 : surveyDamageLists) {
            surveyDamage1.toString();
        }*/
    }

    /**
     * @param lpHistoricalReportInfoDTOList 获取历史案件保存到数据库
     */
    public void saveHistoricalReportInfo(List<LpHistoricalReportInfoDTO> lpHistoricalReportInfoDTOList) {
        if (lpHistoricalReportInfoDTOList == null || lpHistoricalReportInfoDTOList.size() < 1) {
            return;
        }
        List<HistoricalReportInfo> historicalReportInfos = new ArrayList<HistoricalReportInfo>();
        for (LpHistoricalReportInfoDTO lpHistoricalReportInfoDTO : lpHistoricalReportInfoDTOList) {
            HistoricalReportInfo historicalReportInfo = new HistoricalReportInfo();
            historicalReportInfo.setId(UUIDUtil.getUUID());
            historicalReportInfo.setReportCode(lpHistoricalReportInfoDTO.getReportCode());   /**本案报案号*/
            historicalReportInfo.setHisReportCode(lpHistoricalReportInfoDTO.getHisReportCode()); /**历史报案号*/
            historicalReportInfo.setHisReporter(lpHistoricalReportInfoDTO.getHisReporter());/**历史报案人*/
            historicalReportInfo.setHisReportTime(lpHistoricalReportInfoDTO.getHisReportTime()); /**历史报案时间*/
            historicalReportInfo.setHisAccidentTime(lpHistoricalReportInfoDTO.getHisAccidentTime());  /**历史出险时间*/
            historicalReportInfo.setHisAccidentPlace(lpHistoricalReportInfoDTO.getHisAccidentPlace()); /**历史出险地点*/
            historicalReportInfo.setHisAccidentCause(lpHistoricalReportInfoDTO.getHisAccidentCause());      /**历史出险原因*/
            historicalReportInfo.setHisReportMoblephone(lpHistoricalReportInfoDTO.getHisReportMoblephone()); /**历史报案人手机*/
            historicalReportInfo.setHisManageType(lpHistoricalReportInfoDTO.getHisManageType());/**历史处理方式*/

            historicalReportInfos.add(historicalReportInfo);
        }
        HistoricalReportInfoManager.getInstance().insertHistoricalReportInfoList(historicalReportInfos);
    }

    /**
     * @param historicalpaymentInfoDTOList 获取历史赔付案件保存到数据库
     */
    public void saveHistoricalPayMentInfo(List<LpHistoricalpaymentInfoDTO> historicalpaymentInfoDTOList) {
        if (historicalpaymentInfoDTOList == null || historicalpaymentInfoDTOList.size() < 1) {
            return;
        }
        List<HistoricalPaymentInfo> historicalpayMentInfos = new ArrayList<HistoricalPaymentInfo>();
        for (LpHistoricalpaymentInfoDTO lpHistoricalpaymentInfoDTO : historicalpaymentInfoDTOList) {
            HistoricalPaymentInfo historicalpayMentInfo = new HistoricalPaymentInfo();
            historicalpayMentInfo.setId(UUIDUtil.getUUID());
            historicalpayMentInfo.setReportCode(lpHistoricalpaymentInfoDTO.getReportCode());   /**本案报案号*/
            historicalpayMentInfo.setHisReportCode(lpHistoricalpaymentInfoDTO.getHisReportCode());  /**历史报案号*/
            historicalpayMentInfo.setHisPolicyCode(lpHistoricalpaymentInfoDTO.getHisPolicyCode());  /**历史保单号*/
            historicalpayMentInfo.setHisPaycaseCode(lpHistoricalpaymentInfoDTO.getHisPaycaseCode()); /**历史赔案号*/
            historicalpayMentInfo.setHisPaySum(lpHistoricalpaymentInfoDTO.getHisPaySum()); /**历史赔付金额*/
            historicalpayMentInfo.setHisPayNum(lpHistoricalpaymentInfoDTO.getHisPayNum());/**历史赔付次数*/
            historicalpayMentInfo.setHisClaimsExaminers(lpHistoricalpaymentInfoDTO.getHisClaimsExaminers()); /**历史核赔人*/
            historicalpayMentInfo.setHisNuclearDate(lpHistoricalpaymentInfoDTO.getHisNuclearDate()); /**历史核赔时间*/
            historicalpayMentInfo.setHisClaimState(lpHistoricalpaymentInfoDTO.getHisClaimState()); /**本案当前状态*/
            historicalpayMentInfo.setHisPayWay(lpHistoricalpaymentInfoDTO.getHisPayWay());/**历史赔付方式*/
            historicalpayMentInfo.setHisPayclearTime(lpHistoricalpaymentInfoDTO.getHisPayclearTime());/**历史赔付结算时间*/

            historicalpayMentInfos.add(historicalpayMentInfo);
        }
        HistoricalPaymentManager.getInstance().insertHistoricalPaymentInfoList(historicalpayMentInfos);
    }
    /**
     * @param lpReportHumanTrackingList 获取人伤信息保存到数据库
     */
    public void saveSurveyInjuryData(List<LpReportHumanTrackingDTO> lpReportHumanTrackingList) {
        //当没有人伤的时候直接返回
        if (lpReportHumanTrackingList == null || lpReportHumanTrackingList.size() < 1) {
            return;
        }
        List<SurveyInjury> surveyInjuryList = new ArrayList<SurveyInjury>();
        for (LpReportHumanTrackingDTO lpReportHumanTrackingDTO : lpReportHumanTrackingList) {
            SurveyInjury surveyInjury = new SurveyInjury();
            surveyInjury.setId(lpReportHumanTrackingDTO.getId() == null ? UUIDUtil.getUUID() : lpReportHumanTrackingDTO.getId());
            surveyInjury.setReportCode(lpReportHumanTrackingDTO.getReportCode());//报案号
            surveyInjury.setInjuryName(lpReportHumanTrackingDTO.getName());//伤者姓名
            surveyInjury.setInjuryType(null);//人伤类型 //TODO 没有
            surveyInjury.setInjuryTypeCode(lpReportHumanTrackingDTO.getInjuryType());//伤亡类型编码
            surveyInjury.setCasualtyType(null);//伤亡类型 //TODO 没有
            surveyInjury.setCasualtyTypeCode(lpReportHumanTrackingDTO.getCasualtyType());//伤亡类型编码
            surveyInjury.setCertificateType(null);//证件类型 //TODO 没有
            surveyInjury.setCertificateTypeCode(lpReportHumanTrackingDTO.getCertificateType());//证件类型编码
            surveyInjury.setCertificateNo(lpReportHumanTrackingDTO.getCertificateNo());//证件号码
            surveyInjury.setInjuryDescription(lpReportHumanTrackingDTO.getInjuryDescription());//伤情描述
            surveyInjury.setHospital(lpReportHumanTrackingDTO.getHospital());//就诊医院
            surveyInjury.setSex(null);//受伤人性别
            surveyInjury.setAge(0);//受伤人年龄
            surveyInjury.setHouseholdRegister(null);//户籍
            surveyInjury.setProfession(null);//行业
            surveyInjury.setPlateNo(null); //车牌号码
            surveyInjury.setPeopleType(null);//人员类型
            surveyInjury.setPeopleTypeCode(null);//人员类型编码
            surveyInjury.setInjuryGrade(null);//伤情级别
            surveyInjury.setInjuryGradeCode(null);//伤情级别编码
            surveyInjury.setIsSelfMedical(null);//是否自行就医
            surveyInjury.setDisabledGrade(null);//伤残等级
            surveyInjury.setDisabledGradeCode(null);//伤残等级编码
            surveyInjury.setSerialNo(lpReportHumanTrackingDTO.getSerialNo());

            surveyInjuryList.add(surveyInjury);
        }
        SurveyInjuryManager.getInstance().insertOrReplaceInTxSurveyInjurys(surveyInjuryList);
/*        List<SurveyInjury> surveyInjurys = surveyInjuryDao.loadAll();
        for(SurveyInjury SurveyInjury1:surveyInjurys) {
            SurveyInjury1.toString();
        }*/
    }





    /**
     * @return 返回未完成任务个数
     */
    public int getTaskNumber() {
        List<TaskInfo> list = taskInfoDao.queryBuilder().where(TaskInfoDao.Properties.CompleteFlag.notEq("1"),
                TaskInfoDao.Properties.EvaluationPersonId.eq(UtilManager.SP.survey().getString(SurveyClaimUtil.USER_ID, ""))).list();
        if (list == null) {
            return 0;
        } else
            return list.size();
    }

    /**
     * 获取该报案号下的查勘任务
     *
     * @param reportNo
     * @return
     */
    public TaskInfo getSurveyTaskByReportNo(String reportNo) {
        List<TaskInfo> surveyTaskList = getSurveyTaskList(reportNo);
        TaskInfo surveyTask = null;
        if (surveyTaskList != null && surveyTaskList.size() > 0) {
            surveyTask = surveyTaskList.get(0);
        }
        return surveyTask;
    }

    /**
     * @return 返回查勘任务列表
     */
    public List<TaskInfo> getSurveyTaskList(String reportNo) {
        List<TaskInfo> list = taskInfoDao.queryBuilder().where(TaskInfoDao.Properties.TaskCode.eq(SurveyClaimUtil.TASK_TYPE_SURVEY), TaskInfoDao.Properties.ReportCode.eq(reportNo),
                TaskInfoDao.Properties.EvaluationPersonId.eq(UtilManager.SP.survey().getString(SurveyClaimUtil.USER_ID, ""))).list();
        return list;
    }

    /**
     * @return 返回标的定损任务列表
     */
    public List<TaskInfo> getEvalTaskList(String reportNo) {
        List<TaskInfo> list = taskInfoDao.queryBuilder().where(TaskInfoDao.Properties.TaskCode.eq(SurveyClaimUtil.TASK_TYPE_EVAL_BD), TaskInfoDao.Properties.ReportCode.eq(reportNo),
                TaskInfoDao.Properties.EvaluationPersonId.eq(UtilManager.SP.survey().getString(SurveyClaimUtil.USER_ID, ""))).list();
        return list;
    }

    /**
     * @return 返回三者定损任务列表
     */
    public List<TaskInfo> getThirdTaskList(String reportNo) {
        List<TaskInfo> list = taskInfoDao.queryBuilder().where(TaskInfoDao.Properties.TaskCode.eq(SurveyClaimUtil.TASK_TYPE_EVAL_SZ), TaskInfoDao.Properties.ReportCode.eq(reportNo),
                TaskInfoDao.Properties.EvaluationPersonId.eq(UtilManager.SP.survey().getString(SurveyClaimUtil.USER_ID, ""))).list();
        return list;
    }


    /**
     * 通过FlowId查找任务列表
     *
     * @param flowId
     * @return
     */
    public TaskInfo getTaskListByFlowId(String flowId) {
        List<TaskInfo> list = taskInfoDao.queryBuilder().where(TaskInfoDao.Properties.FlowId.eq(flowId)).list();
        if (list == null) {
            return null;
        } else
            return list.get(0);
    }

    /**
     * 通过报案号查找任务列表
     *
     * @param reportNo
     * @return
     */
    public List<TaskInfo> getTaskListByReportNo(String reportNo) {
        List<TaskInfo> list = taskInfoDao.queryBuilder().where(TaskInfoDao.Properties.ReportCode.eq(reportNo)).orderAsc(TaskInfoDao.Properties.TaskCode).list();
        return list;
    }

    /**
     * 通过报案号查找未完成的任务列表
     *
     * @param reportNo
     * @return
     */
    public List<TaskInfo> getNotCompleteTaskListByReportNo(String reportNo) {
        List<TaskInfo> list = taskInfoDao.queryBuilder().where(
                TaskInfoDao.Properties.ReportCode.eq(reportNo), TaskInfoDao.Properties.CompleteFlag.eq("0")
                , TaskInfoDao.Properties.EvaluationPersonId.eq(UtilManager.SP.survey().getString(SurveyClaimUtil.USER_ID, ""))).orderAsc(TaskInfoDao.Properties.TaskCode).list();
        return list;
    }

    /**
     * 通过报案号查找未完成的任务列表
     *
     * @param reportNo
     * @return
     */
    public List<TaskInfo> getAllTaskListByReportNo(String reportNo) {
        List<TaskInfo> list = taskInfoDao.queryBuilder().where(TaskInfoDao.Properties.ReportCode.eq(reportNo)
                , TaskInfoDao.Properties.EvaluationPersonId.eq(UtilManager.SP.survey().getString(SurveyClaimUtil.USER_ID, ""))).orderAsc(TaskInfoDao.Properties.TaskCode).list();
        return list;
    }

    /**
     * 通过报案号查找任务
     *
     * @param reportNo
     * @return
     */
    public TaskInfo getTaskByReportNo(String reportNo) {
        List<TaskInfo> list = getTaskListByReportNo(reportNo);
        TaskInfo taskInfo = null;
        if (list != null && list.size() > 0) {
            taskInfo = list.get(0);
        }
        return taskInfo;
    }

    /**
     * 通过报案号 FlowId 查找任务列表
     *
     * @param reportNo
     * @return
     */
    public List<TaskInfo> getTaskListByReportNoAndFlowId(String reportNo, String flowId) {
        List<TaskInfo> list = taskInfoDao.queryBuilder().where(
                TaskInfoDao.Properties.ReportCode.eq(reportNo),
                TaskInfoDao.Properties.FlowId.eq(flowId)).list();
        return list;
    }

    /**
     * 通过报案号 任务号 查找任务列表
     *
     * @param reportNo
     * @return
     */
    public List<TaskInfo> getTaskListByReportNoAndTaskNo(String reportNo, String taskNo) {
        List<TaskInfo> list = taskInfoDao.queryBuilder().where(
                TaskInfoDao.Properties.ReportCode.eq(reportNo),
                TaskInfoDao.Properties.TaskNo.eq(taskNo)).list();
        return list;
    }

    /**
     * 通过报案号 FlowId 查找任务
     *
     * @param reportNo
     * @return
     */
    public TaskInfo getTaskByReportNoAndFlowId(String reportNo, String flowId) {
        List<TaskInfo> list = getTaskListByReportNoAndFlowId(reportNo, flowId);
        TaskInfo taskInfo = null;
        if (list != null && list.size() > 0) {
            taskInfo = list.get(0);
        }
        return taskInfo;
    }

    /**
     * 通过报案号 任务号 查找任务
     *
     * @param reportNo
     * @return
     */
    public TaskInfo getTaskByReportNoAndTaskNo(String reportNo, String taskNo) {
        List<TaskInfo> list = getTaskListByReportNoAndTaskNo(reportNo, taskNo);
        TaskInfo taskInfo = null;
        if (list != null && list.size() > 0) {
            taskInfo = list.get(0);
        }
        return taskInfo;
    }

    public void insertOrReplaceInTxTaskInfos(List<TaskInfo> taskInfos){
        if(taskInfos != null && taskInfos.size() > 0){
            taskInfoDao.insertOrReplaceInTx(taskInfos);
        }
    }

}
