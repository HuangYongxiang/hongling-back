package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * Create data： 2017/5/12.
 * Author: Liyu
 * Function:查勘基本信息
 */

@Entity
public class SurveyMainInfo implements Serializable{
    @Transient
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String reportNo;//报案号
    private String taskNo;//任务号
    private String evalId;
    private String forcePolicyCode; //交强险保单号
    private String businessPolicyNo; //商业险保单号
    private String reportType; //报案类型
	private String reportTypeCode;//报案类型编码
    private String reportTime; //报案时间
    private String reportPersonName; //报案人姓名
    private String reportPersonContact; //报案人联系方式
    private String reportPhone; //报案电话

    private String insuredPersonName; //被保险人姓名
    private String insuredPersonCode; //被保险人代码
    private String insureCompanyCode; //承保机构代码
    private String insureCompanyName; //承保机构名称
    private String insureVehicleCode; //承保车型代码
    private String insureVehicleName; //承保车型名称
    private String companyCode; //理赔机构代码
    private String companyName; //理赔机构名称
    private String branchCompanyCode; //理赔中支机构代码
    private String branchCompanyName; //理赔中支机构名称

    private String plateNo; //车牌号码
    private String vehicleType; //号牌种类
	private String vehicleTypeCode;//号牌种类代码
    private String vehicleModel; //厂牌型号
    private String engineModel; //发动机号
    private String driverNo; //驾驶证号
    private String driverType; //准驾车型
    private String driverTypeCode; //准驾车型编码
    private String vin; //车架号

    private String accidentFrequency; //出险次数
    private String accidentTime; //出险时间
    private String accidentPlace; //出险地点
    private String accidentDescription; //出险经过
    private String accidentCause; //出险原因
    private String accidentCauseCode; //出险原因编码
    private String driverName; //出险驾驶员姓名
    private String driverLicenseType; //出险驾驶员证件类型
	private String driverLicenseTypeCode; //出险驾驶员证件类型编码
    private String driverLicenseNo; //出险驾驶员证号码
    private String driverContact; //出险驾驶员联系方式
    private String accidentDriverType; //出险驾驶员类型
    private String accidentArea; //出险区域
	private String accidentAreaCode;//出险区域编码
	private String accidentProvince;//出险地点（省）
	private String accidentCity;//出险地点（市）
    private String accidentPlaceStreet; //出险地点（街道）
    private String accidentPlaceZipCode; //出险地点（邮编）
    private String accidentWeather; //出险天气
    private String longitude; //出险地点经度
    private String latitude; //出险地点维度

    private String surveyStartTime; //车辆损失查勘调度开始时间
    private String surveyEndTime; //车辆损失查勘结束时间
    private String surveyTime;//查勘日期
    private String surveyPlace; //查勘地点
    private String surveyDes; //查勘结论
    private String surveyName; //查勘人名称
    private String surveyCode; //查勘人代码
    private String surveyName2; //查勘人名称2
    private String surveyCode2; //查勘人代码2
    private String surveyCeritCode; //查勘人身份证号
    private String surveyCeritCode2; //查勘人身份证号2
    private String surveyCompanyCode; //查勘机构代码
    private String surveyCompanyName; //查勘机构名称
    private String accidentType; //事故分类
    private String accidentTypeCode; //事故分类编码
    private String manageType; //事故处理方式
    private String manageTypeCode; //事故处理方式代码
    private String accidentDuty;//事故责任
    private String accidentDutyCode; //事故责任代码
    private String accidentReason;//事故原因
    private String accidentReasonCode;//事故原因编码
    private String roadAccidentCaseLevel;//道路交通事故案件严重程度
    private String roadAccidentCaseLevelCode;//道路交通事故案件严重程度编码
    private String dutyPercent; //事故责任系数;事故责任比例
    private String fieldType; //现场类别
    private String fieldTypeCode; //现场类别编码
    private String claimType; //理赔类型
    private String claimTypeCode; //理赔类型编码
    private String surveyType; //查勘类型
    private String surveyTypeCode; //查勘类型编码
    private String surveyNature; //查勘性质
    private String surveyNatureCode; //查勘性质编码
    private String isCurrentReport; //是否现场报案
    private String isFirstScene;//是否第一现场
    private String isSingleCarAccident;//是否单车事故
    private double estimateAmount; //估损金额
    private double estimateSurveyAmount; //预估查勘费用金额


    private String hugeType; //巨灾类型
    private String hugeCode; //巨灾编码
    private String bigCaseFlag; //重大赔案标志   1是  0否
    private String paySelfFlag; //互碰自赔标记   1是  0否
    private String isDesignateddriver;//是否指定驾驶员   1是  0否
    private String isNoFindThird;//是否找不到第三方   1是  0否
    private String isSubrogation;//是否代位求偿     1是  0否
    private String subrogationCaseType;//代位案件类型
    private String subrogationCaseTypeCode;//代位案件类型编码
    private String subrogationRequisitionFlag;//代位索赔申请书标志    1是  0否
    private double noLiabilityCompensation;//无责代赔费
    private String entrustedClaimFlag;//委托索赔标记   1是  0否
    private String dutyConfirmationType;//责任认定书类型
    private String dutyConfirmationTypeCode;//责任认定书类型编码
    private double carPayFromThird;//已从第三方获得车损赔偿金额
    private double salvageChargesFromThird;//已从第三方获得施救费赔偿金额
    private String handleOpinion; //接报案处理意见
    private String roadType; //道路信息
	private String roadTypeCode; //道路信息编码
    private String channelCode; //渠道号
    private String channelName; //渠道名称
    private String delFlag; //删除标记
    private String injureLossFlag; //是否人伤   1是  0否
    private String injureNum; //人伤数量
    private String carLossFlag; //是否车损
    private String thiefLossFlag; //是否盗抢
    private String cargoLossFlag; //是否物损
	
	
	/**退回*/
	private String surveyTimes;//查勘次数
	private String checkOpinion;//审核意见
	private String checkConclusion;//审核结论
	private String checkPersonCode;//审核人代码
	private String checkPersonName;//审核人姓名
	private String checkCompanyCode;//审核人机构代码
	private String checkCompanyName;//审核人机构名称
	private String checkTime;//审核时间
    @Transient
	private String riskCode;//险种


    @Generated(hash = 785134825)
    public SurveyMainInfo(String id, String reportNo, String taskNo, String evalId,
            String forcePolicyCode, String businessPolicyNo, String reportType,
            String reportTypeCode, String reportTime, String reportPersonName,
            String reportPersonContact, String reportPhone,
            String insuredPersonName, String insuredPersonCode,
            String insureCompanyCode, String insureCompanyName,
            String insureVehicleCode, String insureVehicleName, String companyCode,
            String companyName, String branchCompanyCode, String branchCompanyName,
            String plateNo, String vehicleType, String vehicleTypeCode,
            String vehicleModel, String engineModel, String driverNo,
            String driverType, String driverTypeCode, String vin,
            String accidentFrequency, String accidentTime, String accidentPlace,
            String accidentDescription, String accidentCause,
            String accidentCauseCode, String driverName, String driverLicenseType,
            String driverLicenseTypeCode, String driverLicenseNo,
            String driverContact, String accidentDriverType, String accidentArea,
            String accidentAreaCode, String accidentProvince, String accidentCity,
            String accidentPlaceStreet, String accidentPlaceZipCode,
            String accidentWeather, String longitude, String latitude,
            String surveyStartTime, String surveyEndTime, String surveyTime,
            String surveyPlace, String surveyDes, String surveyName,
            String surveyCode, String surveyName2, String surveyCode2,
            String surveyCeritCode, String surveyCeritCode2,
            String surveyCompanyCode, String surveyCompanyName, String accidentType,
            String accidentTypeCode, String manageType, String manageTypeCode,
            String accidentDuty, String accidentDutyCode, String accidentReason,
            String accidentReasonCode, String roadAccidentCaseLevel,
            String roadAccidentCaseLevelCode, String dutyPercent, String fieldType,
            String fieldTypeCode, String claimType, String claimTypeCode,
            String surveyType, String surveyTypeCode, String surveyNature,
            String surveyNatureCode, String isCurrentReport, String isFirstScene,
            String isSingleCarAccident, double estimateAmount,
            double estimateSurveyAmount, String hugeType, String hugeCode,
            String bigCaseFlag, String paySelfFlag, String isDesignateddriver,
            String isNoFindThird, String isSubrogation, String subrogationCaseType,
            String subrogationCaseTypeCode, String subrogationRequisitionFlag,
            double noLiabilityCompensation, String entrustedClaimFlag,
            String dutyConfirmationType, String dutyConfirmationTypeCode,
            double carPayFromThird, double salvageChargesFromThird,
            String handleOpinion, String roadType, String roadTypeCode,
            String channelCode, String channelName, String delFlag,
            String injureLossFlag, String injureNum, String carLossFlag,
            String thiefLossFlag, String cargoLossFlag, String surveyTimes,
            String checkOpinion, String checkConclusion, String checkPersonCode,
            String checkPersonName, String checkCompanyCode,
            String checkCompanyName, String checkTime) {
        this.id = id;
        this.reportNo = reportNo;
        this.taskNo = taskNo;
        this.evalId = evalId;
        this.forcePolicyCode = forcePolicyCode;
        this.businessPolicyNo = businessPolicyNo;
        this.reportType = reportType;
        this.reportTypeCode = reportTypeCode;
        this.reportTime = reportTime;
        this.reportPersonName = reportPersonName;
        this.reportPersonContact = reportPersonContact;
        this.reportPhone = reportPhone;
        this.insuredPersonName = insuredPersonName;
        this.insuredPersonCode = insuredPersonCode;
        this.insureCompanyCode = insureCompanyCode;
        this.insureCompanyName = insureCompanyName;
        this.insureVehicleCode = insureVehicleCode;
        this.insureVehicleName = insureVehicleName;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.branchCompanyCode = branchCompanyCode;
        this.branchCompanyName = branchCompanyName;
        this.plateNo = plateNo;
        this.vehicleType = vehicleType;
        this.vehicleTypeCode = vehicleTypeCode;
        this.vehicleModel = vehicleModel;
        this.engineModel = engineModel;
        this.driverNo = driverNo;
        this.driverType = driverType;
        this.driverTypeCode = driverTypeCode;
        this.vin = vin;
        this.accidentFrequency = accidentFrequency;
        this.accidentTime = accidentTime;
        this.accidentPlace = accidentPlace;
        this.accidentDescription = accidentDescription;
        this.accidentCause = accidentCause;
        this.accidentCauseCode = accidentCauseCode;
        this.driverName = driverName;
        this.driverLicenseType = driverLicenseType;
        this.driverLicenseTypeCode = driverLicenseTypeCode;
        this.driverLicenseNo = driverLicenseNo;
        this.driverContact = driverContact;
        this.accidentDriverType = accidentDriverType;
        this.accidentArea = accidentArea;
        this.accidentAreaCode = accidentAreaCode;
        this.accidentProvince = accidentProvince;
        this.accidentCity = accidentCity;
        this.accidentPlaceStreet = accidentPlaceStreet;
        this.accidentPlaceZipCode = accidentPlaceZipCode;
        this.accidentWeather = accidentWeather;
        this.longitude = longitude;
        this.latitude = latitude;
        this.surveyStartTime = surveyStartTime;
        this.surveyEndTime = surveyEndTime;
        this.surveyTime = surveyTime;
        this.surveyPlace = surveyPlace;
        this.surveyDes = surveyDes;
        this.surveyName = surveyName;
        this.surveyCode = surveyCode;
        this.surveyName2 = surveyName2;
        this.surveyCode2 = surveyCode2;
        this.surveyCeritCode = surveyCeritCode;
        this.surveyCeritCode2 = surveyCeritCode2;
        this.surveyCompanyCode = surveyCompanyCode;
        this.surveyCompanyName = surveyCompanyName;
        this.accidentType = accidentType;
        this.accidentTypeCode = accidentTypeCode;
        this.manageType = manageType;
        this.manageTypeCode = manageTypeCode;
        this.accidentDuty = accidentDuty;
        this.accidentDutyCode = accidentDutyCode;
        this.accidentReason = accidentReason;
        this.accidentReasonCode = accidentReasonCode;
        this.roadAccidentCaseLevel = roadAccidentCaseLevel;
        this.roadAccidentCaseLevelCode = roadAccidentCaseLevelCode;
        this.dutyPercent = dutyPercent;
        this.fieldType = fieldType;
        this.fieldTypeCode = fieldTypeCode;
        this.claimType = claimType;
        this.claimTypeCode = claimTypeCode;
        this.surveyType = surveyType;
        this.surveyTypeCode = surveyTypeCode;
        this.surveyNature = surveyNature;
        this.surveyNatureCode = surveyNatureCode;
        this.isCurrentReport = isCurrentReport;
        this.isFirstScene = isFirstScene;
        this.isSingleCarAccident = isSingleCarAccident;
        this.estimateAmount = estimateAmount;
        this.estimateSurveyAmount = estimateSurveyAmount;
        this.hugeType = hugeType;
        this.hugeCode = hugeCode;
        this.bigCaseFlag = bigCaseFlag;
        this.paySelfFlag = paySelfFlag;
        this.isDesignateddriver = isDesignateddriver;
        this.isNoFindThird = isNoFindThird;
        this.isSubrogation = isSubrogation;
        this.subrogationCaseType = subrogationCaseType;
        this.subrogationCaseTypeCode = subrogationCaseTypeCode;
        this.subrogationRequisitionFlag = subrogationRequisitionFlag;
        this.noLiabilityCompensation = noLiabilityCompensation;
        this.entrustedClaimFlag = entrustedClaimFlag;
        this.dutyConfirmationType = dutyConfirmationType;
        this.dutyConfirmationTypeCode = dutyConfirmationTypeCode;
        this.carPayFromThird = carPayFromThird;
        this.salvageChargesFromThird = salvageChargesFromThird;
        this.handleOpinion = handleOpinion;
        this.roadType = roadType;
        this.roadTypeCode = roadTypeCode;
        this.channelCode = channelCode;
        this.channelName = channelName;
        this.delFlag = delFlag;
        this.injureLossFlag = injureLossFlag;
        this.injureNum = injureNum;
        this.carLossFlag = carLossFlag;
        this.thiefLossFlag = thiefLossFlag;
        this.cargoLossFlag = cargoLossFlag;
        this.surveyTimes = surveyTimes;
        this.checkOpinion = checkOpinion;
        this.checkConclusion = checkConclusion;
        this.checkPersonCode = checkPersonCode;
        this.checkPersonName = checkPersonName;
        this.checkCompanyCode = checkCompanyCode;
        this.checkCompanyName = checkCompanyName;
        this.checkTime = checkTime;
    }
    @Generated(hash = 248948887)
    public SurveyMainInfo() {
    }
	

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getReportNo() {
        return this.reportNo;
    }
    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }
    public String getTaskNo() {
        return this.taskNo;
    }
    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }
    public String getEvalId() {
        return this.evalId;
    }
    public void setEvalId(String evalId) {
        this.evalId = evalId;
    }
    public String getForcePolicyCode() {
        return this.forcePolicyCode;
    }
    public void setForcePolicyCode(String forcePolicyCode) {
        this.forcePolicyCode = forcePolicyCode;
    }
    public String getBusinessPolicyNo() {
        return this.businessPolicyNo;
    }
    public void setBusinessPolicyNo(String businessPolicyNo) {
        this.businessPolicyNo = businessPolicyNo;
    }
    public String getReportType() {
        return this.reportType;
    }
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
    public String getReportTime() {
        return this.reportTime;
    }
    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }
    public String getReportPersonName() {
        return this.reportPersonName;
    }
    public void setReportPersonName(String reportPersonName) {
        this.reportPersonName = reportPersonName;
    }
    public String getReportPersonContact() {
        return this.reportPersonContact;
    }
    public void setReportPersonContact(String reportPersonContact) {
        this.reportPersonContact = reportPersonContact;
    }
    public String getReportPhone() {
        return this.reportPhone;
    }
    public void setReportPhone(String reportPhone) {
        this.reportPhone = reportPhone;
    }
    public String getInsuredPersonName() {
        return this.insuredPersonName;
    }
    public void setInsuredPersonName(String insuredPersonName) {
        this.insuredPersonName = insuredPersonName;
    }
    public String getInsuredPersonCode() {
        return this.insuredPersonCode;
    }
    public void setInsuredPersonCode(String insuredPersonCode) {
        this.insuredPersonCode = insuredPersonCode;
    }
    public String getInsureCompanyCode() {
        return this.insureCompanyCode;
    }
    public void setInsureCompanyCode(String insureCompanyCode) {
        this.insureCompanyCode = insureCompanyCode;
    }
    public String getInsureCompanyName() {
        return this.insureCompanyName;
    }
    public void setInsureCompanyName(String insureCompanyName) {
        this.insureCompanyName = insureCompanyName;
    }
    public String getInsureVehicleCode() {
        return this.insureVehicleCode;
    }
    public void setInsureVehicleCode(String insureVehicleCode) {
        this.insureVehicleCode = insureVehicleCode;
    }
    public String getInsureVehicleName() {
        return this.insureVehicleName;
    }
    public void setInsureVehicleName(String insureVehicleName) {
        this.insureVehicleName = insureVehicleName;
    }
    public String getCompanyCode() {
        return this.companyCode;
    }
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    public String getCompanyName() {
        return this.companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getBranchCompanyCode() {
        return this.branchCompanyCode;
    }
    public void setBranchCompanyCode(String branchCompanyCode) {
        this.branchCompanyCode = branchCompanyCode;
    }
    public String getBranchCompanyName() {
        return this.branchCompanyName;
    }
    public void setBranchCompanyName(String branchCompanyName) {
        this.branchCompanyName = branchCompanyName;
    }
    public String getPlateNo() {
        return this.plateNo;
    }
    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }
    public String getVehicleType() {
        return this.vehicleType;
    }
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    public String getVehicleModel() {
        return this.vehicleModel;
    }
    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }
    public String getEngineModel() {
        return this.engineModel;
    }
    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel;
    }
    public String getDriverNo() {
        return this.driverNo;
    }
    public void setDriverNo(String driverNo) {
        this.driverNo = driverNo;
    }
    public String getDriverType() {
        return this.driverType;
    }
    public void setDriverType(String driverType) {
        this.driverType = driverType;
    }
    public String getDriverTypeCode() {
        return this.driverTypeCode;
    }
    public void setDriverTypeCode(String driverTypeCode) {
        this.driverTypeCode = driverTypeCode;
    }
    public String getVin() {
        return this.vin;
    }
    public void setVin(String vin) {
        this.vin = vin;
    }
    public String getAccidentTime() {
        return this.accidentTime;
    }
    public void setAccidentTime(String accidentTime) {
        this.accidentTime = accidentTime;
    }
    public String getAccidentPlace() {
        return this.accidentPlace;
    }
    public void setAccidentPlace(String accidentPlace) {
        this.accidentPlace = accidentPlace;
    }
    public String getAccidentDescription() {
        return this.accidentDescription;
    }
    public void setAccidentDescription(String accidentDescription) {
        this.accidentDescription = accidentDescription;
    }
    public String getAccidentCause() {
        return this.accidentCause;
    }
    public void setAccidentCause(String accidentCause) {
        this.accidentCause = accidentCause;
    }
    public String getAccidentCauseCode() {
        return this.accidentCauseCode;
    }
    public void setAccidentCauseCode(String accidentCauseCode) {
        this.accidentCauseCode = accidentCauseCode;
    }
    public String getDriverName() {
        return this.driverName;
    }
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    public String getDriverLicenseType() {
        return this.driverLicenseType;
    }
    public void setDriverLicenseType(String driverLicenseType) {
        this.driverLicenseType = driverLicenseType;
    }
    public String getDriverLicenseNo() {
        return this.driverLicenseNo;
    }
    public void setDriverLicenseNo(String driverLicenseNo) {
        this.driverLicenseNo = driverLicenseNo;
    }
    public String getDriverContact() {
        return this.driverContact;
    }
    public void setDriverContact(String driverContact) {
        this.driverContact = driverContact;
    }
    public String getAccidentDriverType() {
        return this.accidentDriverType;
    }
    public void setAccidentDriverType(String accidentDriverType) {
        this.accidentDriverType = accidentDriverType;
    }
    public String getAccidentArea() {
        return this.accidentArea;
    }
    public void setAccidentArea(String accidentArea) {
        this.accidentArea = accidentArea;
    }
    public String getAccidentPlaceStreet() {
        return this.accidentPlaceStreet;
    }
    public void setAccidentPlaceStreet(String accidentPlaceStreet) {
        this.accidentPlaceStreet = accidentPlaceStreet;
    }
    public String getAccidentPlaceZipCode() {
        return this.accidentPlaceZipCode;
    }
    public void setAccidentPlaceZipCode(String accidentPlaceZipCode) {
        this.accidentPlaceZipCode = accidentPlaceZipCode;
    }
    public String getAccidentWeather() {
        return this.accidentWeather;
    }
    public void setAccidentWeather(String accidentWeather) {
        this.accidentWeather = accidentWeather;
    }
    public String getLongitude() {
        return this.longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getLatitude() {
        return this.latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getSurveyStartTime() {
        return this.surveyStartTime;
    }
    public void setSurveyStartTime(String surveyStartTime) {
        this.surveyStartTime = surveyStartTime;
    }
    public String getSurveyEndTime() {
        return this.surveyEndTime;
    }
    public void setSurveyEndTime(String surveyEndTime) {
        this.surveyEndTime = surveyEndTime;
    }
    public String getSurveyTime() {
        return this.surveyTime;
    }
    public void setSurveyTime(String surveyTime) {
        this.surveyTime = surveyTime;
    }
    public String getSurveyPlace() {
        return this.surveyPlace;
    }
    public void setSurveyPlace(String surveyPlace) {
        this.surveyPlace = surveyPlace;
    }
    public String getSurveyDes() {
        return this.surveyDes;
    }
    public void setSurveyDes(String surveyDes) {
        this.surveyDes = surveyDes;
    }
    public String getSurveyName() {
        return this.surveyName;
    }
    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }
    public String getSurveyCode() {
        return this.surveyCode;
    }
    public void setSurveyCode(String surveyCode) {
        this.surveyCode = surveyCode;
    }
    public String getSurveyName2() {
        return this.surveyName2;
    }
    public void setSurveyName2(String surveyName2) {
        this.surveyName2 = surveyName2;
    }
    public String getSurveyCode2() {
        return this.surveyCode2;
    }
    public void setSurveyCode2(String surveyCode2) {
        this.surveyCode2 = surveyCode2;
    }
    public String getSurveyCeritCode() {
        return this.surveyCeritCode;
    }
    public void setSurveyCeritCode(String surveyCeritCode) {
        this.surveyCeritCode = surveyCeritCode;
    }
    public String getSurveyCeritCode2() {
        return this.surveyCeritCode2;
    }
    public void setSurveyCeritCode2(String surveyCeritCode2) {
        this.surveyCeritCode2 = surveyCeritCode2;
    }
    public String getSurveyCompanyCode() {
        return this.surveyCompanyCode;
    }
    public void setSurveyCompanyCode(String surveyCompanyCode) {
        this.surveyCompanyCode = surveyCompanyCode;
    }
    public String getSurveyCompanyName() {
        return this.surveyCompanyName;
    }
    public void setSurveyCompanyName(String surveyCompanyName) {
        this.surveyCompanyName = surveyCompanyName;
    }
    public String getAccidentType() {
        return this.accidentType;
    }
    public void setAccidentType(String accidentType) {
        this.accidentType = accidentType;
    }
    public String getAccidentTypeCode() {
        return this.accidentTypeCode;
    }
    public void setAccidentTypeCode(String accidentTypeCode) {
        this.accidentTypeCode = accidentTypeCode;
    }
    public String getManageType() {
        return this.manageType;
    }
    public void setManageType(String manageType) {
        this.manageType = manageType;
    }
    public String getManageTypeCode() {
        return this.manageTypeCode;
    }
    public void setManageTypeCode(String manageTypeCode) {
        this.manageTypeCode = manageTypeCode;
    }
    public String getAccidentDuty() {
        return this.accidentDuty;
    }
    public void setAccidentDuty(String accidentDuty) {
        this.accidentDuty = accidentDuty;
    }
    public String getAccidentDutyCode() {
        return this.accidentDutyCode;
    }
    public void setAccidentDutyCode(String accidentDutyCode) {
        this.accidentDutyCode = accidentDutyCode;
    }
    public String getAccidentReason() {
        return this.accidentReason;
    }
    public void setAccidentReason(String accidentReason) {
        this.accidentReason = accidentReason;
    }
    public String getAccidentReasonCode() {
        return this.accidentReasonCode;
    }
    public void setAccidentReasonCode(String accidentReasonCode) {
        this.accidentReasonCode = accidentReasonCode;
    }
    public String getRoadAccidentCaseLevel() {
        return this.roadAccidentCaseLevel;
    }
    public void setRoadAccidentCaseLevel(String roadAccidentCaseLevel) {
        this.roadAccidentCaseLevel = roadAccidentCaseLevel;
    }
    public String getRoadAccidentCaseLevelCode() {
        return this.roadAccidentCaseLevelCode;
    }
    public void setRoadAccidentCaseLevelCode(String roadAccidentCaseLevelCode) {
        this.roadAccidentCaseLevelCode = roadAccidentCaseLevelCode;
    }
    public String getDutyPercent() {
        return this.dutyPercent;
    }
    public void setDutyPercent(String dutyPercent) {
        this.dutyPercent = dutyPercent;
    }
    public String getFieldType() {
        return this.fieldType;
    }
    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
    public String getFieldTypeCode() {
        return this.fieldTypeCode;
    }
    public void setFieldTypeCode(String fieldTypeCode) {
        this.fieldTypeCode = fieldTypeCode;
    }
    public String getClaimType() {
        return this.claimType;
    }
    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }
    public String getClaimTypeCode() {
        return this.claimTypeCode;
    }
    public void setClaimTypeCode(String claimTypeCode) {
        this.claimTypeCode = claimTypeCode;
    }
    public String getSurveyType() {
        return this.surveyType;
    }
    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }
    public String getSurveyTypeCode() {
        return this.surveyTypeCode;
    }
    public void setSurveyTypeCode(String surveyTypeCode) {
        this.surveyTypeCode = surveyTypeCode;
    }
    public String getSurveyNature() {
        return this.surveyNature;
    }
    public void setSurveyNature(String surveyNature) {
        this.surveyNature = surveyNature;
    }
    public String getSurveyNatureCode() {
        return this.surveyNatureCode;
    }
    public void setSurveyNatureCode(String surveyNatureCode) {
        this.surveyNatureCode = surveyNatureCode;
    }
    public String getIsCurrentReport() {
        return this.isCurrentReport;
    }
    public void setIsCurrentReport(String isCurrentReport) {
        this.isCurrentReport = isCurrentReport;
    }
    public String getIsFirstScene() {
        return this.isFirstScene;
    }
    public void setIsFirstScene(String isFirstScene) {
        this.isFirstScene = isFirstScene;
    }
    public String getIsSingleCarAccident() {
        return this.isSingleCarAccident;
    }
    public void setIsSingleCarAccident(String isSingleCarAccident) {
        this.isSingleCarAccident = isSingleCarAccident;
    }
    public double getEstimateAmount() {
        return this.estimateAmount;
    }
    public void setEstimateAmount(double estimateAmount) {
        this.estimateAmount = estimateAmount;
    }
    public double getEstimateSurveyAmount() {
        return this.estimateSurveyAmount;
    }
    public void setEstimateSurveyAmount(double estimateSurveyAmount) {
        this.estimateSurveyAmount = estimateSurveyAmount;
    }
    public String getHugeType() {
        return this.hugeType;
    }
    public void setHugeType(String hugeType) {
        this.hugeType = hugeType;
    }
    public String getHugeCode() {
        return this.hugeCode;
    }
    public void setHugeCode(String hugeCode) {
        this.hugeCode = hugeCode;
    }
    public String getBigCaseFlag() {
        return this.bigCaseFlag;
    }
    public void setBigCaseFlag(String bigCaseFlag) {
        this.bigCaseFlag = bigCaseFlag;
    }
    public String getPaySelfFlag() {
        return this.paySelfFlag;
    }
    public void setPaySelfFlag(String paySelfFlag) {
        this.paySelfFlag = paySelfFlag;
    }
    public String getIsDesignateddriver() {
        return this.isDesignateddriver;
    }
    public void setIsDesignateddriver(String isDesignateddriver) {
        this.isDesignateddriver = isDesignateddriver;
    }
    public String getIsNoFindThird() {
        return this.isNoFindThird;
    }
    public void setIsNoFindThird(String isNoFindThird) {
        this.isNoFindThird = isNoFindThird;
    }
    public String getIsSubrogation() {
        return this.isSubrogation;
    }
    public void setIsSubrogation(String isSubrogation) {
        this.isSubrogation = isSubrogation;
    }
    public String getSubrogationCaseType() {
        return this.subrogationCaseType;
    }
    public void setSubrogationCaseType(String subrogationCaseType) {
        this.subrogationCaseType = subrogationCaseType;
    }
    public String getSubrogationCaseTypeCode() {
        return this.subrogationCaseTypeCode;
    }
    public void setSubrogationCaseTypeCode(String subrogationCaseTypeCode) {
        this.subrogationCaseTypeCode = subrogationCaseTypeCode;
    }
    public String getSubrogationRequisitionFlag() {
        return this.subrogationRequisitionFlag;
    }
    public void setSubrogationRequisitionFlag(String subrogationRequisitionFlag) {
        this.subrogationRequisitionFlag = subrogationRequisitionFlag;
    }
    public double getNoLiabilityCompensation() {
        return this.noLiabilityCompensation;
    }
    public void setNoLiabilityCompensation(double noLiabilityCompensation) {
        this.noLiabilityCompensation = noLiabilityCompensation;
    }
    public String getEntrustedClaimFlag() {
        return this.entrustedClaimFlag;
    }
    public void setEntrustedClaimFlag(String entrustedClaimFlag) {
        this.entrustedClaimFlag = entrustedClaimFlag;
    }
    public String getDutyConfirmationType() {
        return this.dutyConfirmationType;
    }
    public void setDutyConfirmationType(String dutyConfirmationType) {
        this.dutyConfirmationType = dutyConfirmationType;
    }
    public String getDutyConfirmationTypeCode() {
        return this.dutyConfirmationTypeCode;
    }
    public void setDutyConfirmationTypeCode(String dutyConfirmationTypeCode) {
        this.dutyConfirmationTypeCode = dutyConfirmationTypeCode;
    }
    public double getCarPayFromThird() {
        return this.carPayFromThird;
    }
    public void setCarPayFromThird(double carPayFromThird) {
        this.carPayFromThird = carPayFromThird;
    }
    public double getSalvageChargesFromThird() {
        return this.salvageChargesFromThird;
    }
    public void setSalvageChargesFromThird(double salvageChargesFromThird) {
        this.salvageChargesFromThird = salvageChargesFromThird;
    }
    public String getHandleOpinion() {
        return this.handleOpinion;
    }
    public void setHandleOpinion(String handleOpinion) {
        this.handleOpinion = handleOpinion;
    }
    public String getRoadType() {
        return this.roadType;
    }
    public void setRoadType(String roadType) {
        this.roadType = roadType;
    }
    public String getChannelCode() {
        return this.channelCode;
    }
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }
    public String getChannelName() {
        return this.channelName;
    }
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    public String getDelFlag() {
        return this.delFlag;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
    public String getInjureLossFlag() {
        return this.injureLossFlag;
    }
    public void setInjureLossFlag(String injureLossFlag) {
        this.injureLossFlag = injureLossFlag;
    }
    public String getInjureNum() {
        return this.injureNum;
    }
    public void setInjureNum(String injureNum) {
        this.injureNum = injureNum;
    }
    public String getCarLossFlag() {
        return this.carLossFlag;
    }
    public void setCarLossFlag(String carLossFlag) {
        this.carLossFlag = carLossFlag;
    }
    public String getThiefLossFlag() {
        return this.thiefLossFlag;
    }
    public void setThiefLossFlag(String thiefLossFlag) {
        this.thiefLossFlag = thiefLossFlag;
    }
    public String getCargoLossFlag() {
        return this.cargoLossFlag;
    }
    public void setCargoLossFlag(String cargoLossFlag) {
        this.cargoLossFlag = cargoLossFlag;
    }
    public String getAccidentFrequency() {
        return this.accidentFrequency;
    }
    public void setAccidentFrequency(String accidentFrequency) {
        this.accidentFrequency = accidentFrequency;
    }
    public String getReportTypeCode() {
        return this.reportTypeCode;
    }
    public void setReportTypeCode(String reportTypeCode) {
        this.reportTypeCode = reportTypeCode;
    }
    public String getVehicleTypeCode() {
        return this.vehicleTypeCode;
    }
    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }
    public String getDriverLicenseTypeCode() {
        return this.driverLicenseTypeCode;
    }
    public void setDriverLicenseTypeCode(String driverLicenseTypeCode) {
        this.driverLicenseTypeCode = driverLicenseTypeCode;
    }
    public String getAccidentAreaCode() {
        return this.accidentAreaCode;
    }
    public void setAccidentAreaCode(String accidentAreaCode) {
        this.accidentAreaCode = accidentAreaCode;
    }
    public String getAccidentProvince() {
        return this.accidentProvince;
    }
    public void setAccidentProvince(String accidentProvince) {
        this.accidentProvince = accidentProvince;
    }
    public String getAccidentCity() {
        return this.accidentCity;
    }
    public void setAccidentCity(String accidentCity) {
        this.accidentCity = accidentCity;
    }
    public String getRoadTypeCode() {
        return this.roadTypeCode;
    }
    public void setRoadTypeCode(String roadTypeCode) {
        this.roadTypeCode = roadTypeCode;
    }
    public String getSurveyTimes() {
        return this.surveyTimes;
    }
    public void setSurveyTimes(String surveyTimes) {
        this.surveyTimes = surveyTimes;
    }
    public String getCheckOpinion() {
        return this.checkOpinion;
    }
    public void setCheckOpinion(String checkOpinion) {
        this.checkOpinion = checkOpinion;
    }
    public String getCheckConclusion() {
        return this.checkConclusion;
    }
    public void setCheckConclusion(String checkConclusion) {
        this.checkConclusion = checkConclusion;
    }
    public String getCheckPersonCode() {
        return this.checkPersonCode;
    }
    public void setCheckPersonCode(String checkPersonCode) {
        this.checkPersonCode = checkPersonCode;
    }
    public String getCheckPersonName() {
        return this.checkPersonName;
    }
    public void setCheckPersonName(String checkPersonName) {
        this.checkPersonName = checkPersonName;
    }
    public String getCheckCompanyCode() {
        return this.checkCompanyCode;
    }
    public void setCheckCompanyCode(String checkCompanyCode) {
        this.checkCompanyCode = checkCompanyCode;
    }
    public String getCheckCompanyName() {
        return this.checkCompanyName;
    }
    public void setCheckCompanyName(String checkCompanyName) {
        this.checkCompanyName = checkCompanyName;
    }
    public String getCheckTime() {
        return this.checkTime;
    }
    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getRiskCode() {
        return riskCode;
    }

    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }
}
