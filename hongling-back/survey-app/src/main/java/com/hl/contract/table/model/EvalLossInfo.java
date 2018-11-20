package com.hl.contract.table.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * @Describe:  封装进入定损的信息
 * @Package: com.hl.eval.table.model
 * @Author: liyu
 * @Date: 2018/5/10 17:53
 * @Copyright: hl
 */
@Entity
public class EvalLossInfo extends BaseObservable {
    @Id(autoincrement = true)
    private Long id;

    /**
     * test 传入使用
     */
    private String userCode;//用户名 固定：

    private String password;// 密码 固定
    private String requestType; //请求类型 固定001：进入定损  固定002：核损退回   固定003：查看定损
    private String requestUrlFlag;//请求地址类型    测试：TEST    生产：PROD
    private String appType;//要打开的APP类型     固定001:专业版     固定002：大众版
    private String isShieldingPrice;//是否屏蔽价格    0：否(默认)   1：是
    private String ifNewLossFlag;//是否包含报案信息   0：否   1：是
    private String auditLossFlag;//核损标记   0：正常定损    1：核损退回进定损

    private String requestSourceCode;//请求来源代码
    private String requestSourceName; //请求来源名称
    private String operatingTime; //操作时间
    private String returnURL; //返回URL
    private String refreshURL; //返回刷新URL

    private String dmgVhclId; //车损标的主键
    private String lossNo;//定损单号
    private String reportCode;//报案号
    private String insureVehicleName;//承保车型名称
    private String insureVehicleCode;//承保车型编码
    private String comCode;//定损员所属分公司代码
    private String company;//定损员所属分公司名称
    private String branchComCode;//所属中支代码 (如果没有则传分公司代码)
    private String branchComName;//定损员所属中支名称 (如果没有则传分公司名称)
    private String handlerCode;//定损员代码
    private String handlerName;//定损员姓名
    private String isSubjectVehicle;//是否标的车
    private String plateNo;//车牌号码
    private String vinNo;//vin码
    private String driverName;//驾驶员姓名
    private String enrolDate;//初登日期
    private String markColor;//牌照颜色
    private String evalTypeCode;//定损方式  01	修复定损 02	推定全损 03	实际全损 04	协议定损      中煤 01修复定损  02一次性协议定损  03推定全损  04法院判决
    private String remark;//定损整单备注
    private double salvageFee;//定损施救费用
    private String salvageFeeRemark;//定损施救费用备注
    private String engineNo;//发动机号
    private String repairFactoryID;//送修修理厂ID   呼叫中心报案时送修的修理厂信息id    安心，中华保险唯一修理厂标识
    private String repairFactoryCode;//送修修理厂编码    呼叫中心报案时送修的修理厂信息
    private String repairFactoryName;//送修修理厂名称	   呼叫中心报案时送修的修理厂信息
    private String repairFactoryType;//送修修理厂类型	   1是4s店   0是综合修理厂
    private String selfEstiFlag;//是否自核价 1=是 0=否 默认传0


    //安心增加：
    private String isLawsuit;//是否诉讼案件    1=是 0=否
    private String selfCompensation;//是否互碰自赔    1=是 0=否

    //阳光农业增加:
    private String lossItemCode;//序号

    @Transient
    private String vehCertainCode;//车型编码
    @Transient
    private String vehCertainName;//车型名称
    @Transient
    private String selfConfigFlag;//自定义车型标志
    @Transient
    private String vehFactoryName;//厂商名称
    @Transient
    private double remnantFee;//定损折扣残值
    @Transient
    private double manageRate;//定损管理费比率  管理费 = 定损合计  * 定损管理费比率
    @Transient
    private double manageFee;//定损管理费合计
    @Transient
    private double sumLossAmount; //定损合计  定损合计=换件+修理+辅料+管理费+施救-残值
    @Transient
    private double evalPartSum; //换件合计
    @Transient
    private double evalRepairSum;//工时合计
    @Transient
    private double evalMateSum;//辅料合计
    @Transient
    private double selfPaySum; //自付合计
    @Transient
    private double outerSum;//外修合计
    @Transient
    private double derogationSum;//减损合计
    @Transient
    private String priceType;//价格类型  1：4S价格 2：市场价格 99：其他
    @Transient
    private String repairFacID;//修理厂id
    @Transient
    private String repairFacCode;//修理厂编码
    @Transient
    private String repairFacName;//修理厂名称
    @Transient
    private String repairFacType;//修理厂类型 0综合修理厂 ; 1是4s店修理厂
    @Transient
    private String recycleType;//回收方式 0无回收 1 批量 2 单件
    @Transient
    private List<InsuranceItem> insuranceItemList; //险别列表

    @Transient
    private List<PolicyInfo> policyList;//保单列表

    @Transient
    private InsuranceCar insuranceCar;//承保标的车

    @Transient
    private List<Insurance> insuranceList;//承保险别列表

    @Transient
    private ReportInfo reportInfo;//报案信息

    @Generated(hash = 635622542)
    public EvalLossInfo(Long id, String userCode, String password, String requestType, String requestUrlFlag,
                        String appType, String isShieldingPrice, String ifNewLossFlag, String auditLossFlag,
                        String requestSourceCode, String requestSourceName, String operatingTime, String returnURL,
                        String refreshURL, String dmgVhclId, String lossNo, String reportCode, String insureVehicleName,
                        String insureVehicleCode, String comCode, String company, String branchComCode, String branchComName,
                        String handlerCode, String handlerName, String isSubjectVehicle, String plateNo, String vinNo,
                        String driverName, String enrolDate, String markColor, String evalTypeCode, String remark,
                        double salvageFee, String salvageFeeRemark, String engineNo, String repairFactoryID,
                        String repairFactoryCode, String repairFactoryName, String repairFactoryType, String selfEstiFlag,
                        String isLawsuit, String selfCompensation, String lossItemCode) {
        this.id = id;
        this.userCode = userCode;
        this.password = password;
        this.requestType = requestType;
        this.requestUrlFlag = requestUrlFlag;
        this.appType = appType;
        this.isShieldingPrice = isShieldingPrice;
        this.ifNewLossFlag = ifNewLossFlag;
        this.auditLossFlag = auditLossFlag;
        this.requestSourceCode = requestSourceCode;
        this.requestSourceName = requestSourceName;
        this.operatingTime = operatingTime;
        this.returnURL = returnURL;
        this.refreshURL = refreshURL;
        this.dmgVhclId = dmgVhclId;
        this.lossNo = lossNo;
        this.reportCode = reportCode;
        this.insureVehicleName = insureVehicleName;
        this.insureVehicleCode = insureVehicleCode;
        this.comCode = comCode;
        this.company = company;
        this.branchComCode = branchComCode;
        this.branchComName = branchComName;
        this.handlerCode = handlerCode;
        this.handlerName = handlerName;
        this.isSubjectVehicle = isSubjectVehicle;
        this.plateNo = plateNo;
        this.vinNo = vinNo;
        this.driverName = driverName;
        this.enrolDate = enrolDate;
        this.markColor = markColor;
        this.evalTypeCode = evalTypeCode;
        this.remark = remark;
        this.salvageFee = salvageFee;
        this.salvageFeeRemark = salvageFeeRemark;
        this.engineNo = engineNo;
        this.repairFactoryID = repairFactoryID;
        this.repairFactoryCode = repairFactoryCode;
        this.repairFactoryName = repairFactoryName;
        this.repairFactoryType = repairFactoryType;
        this.selfEstiFlag = selfEstiFlag;
        this.isLawsuit = isLawsuit;
        this.selfCompensation = selfCompensation;
        this.lossItemCode = lossItemCode;
    }

    @Generated(hash = 673006071)
    public EvalLossInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestUrlFlag() {
        return this.requestUrlFlag;
    }

    public void setRequestUrlFlag(String requestUrlFlag) {
        this.requestUrlFlag = requestUrlFlag;
    }

    public String getAppType() {
        return this.appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getIsShieldingPrice() {
        return this.isShieldingPrice;
    }

    public void setIsShieldingPrice(String isShieldingPrice) {
        this.isShieldingPrice = isShieldingPrice;
    }

    public String getIfNewLossFlag() {
        return this.ifNewLossFlag;
    }

    public void setIfNewLossFlag(String ifNewLossFlag) {
        this.ifNewLossFlag = ifNewLossFlag;
    }

    public String getAuditLossFlag() {
        return this.auditLossFlag;
    }

    public void setAuditLossFlag(String auditLossFlag) {
        this.auditLossFlag = auditLossFlag;
    }

    public String getRequestSourceCode() {
        return this.requestSourceCode;
    }

    public void setRequestSourceCode(String requestSourceCode) {
        this.requestSourceCode = requestSourceCode;
    }

    public String getRequestSourceName() {
        return this.requestSourceName;
    }

    public void setRequestSourceName(String requestSourceName) {
        this.requestSourceName = requestSourceName;
    }

    public String getOperatingTime() {
        return this.operatingTime;
    }

    public void setOperatingTime(String operatingTime) {
        this.operatingTime = operatingTime;
    }

    public String getReturnURL() {
        return this.returnURL;
    }

    public void setReturnURL(String returnURL) {
        this.returnURL = returnURL;
    }

    public String getRefreshURL() {
        return this.refreshURL;
    }

    public void setRefreshURL(String refreshURL) {
        this.refreshURL = refreshURL;
    }

    public String getDmgVhclId() {
        return this.dmgVhclId;
    }

    public void setDmgVhclId(String dmgVhclId) {
        this.dmgVhclId = dmgVhclId;
    }

    public String getLossNo() {
        return this.lossNo;
    }

    public void setLossNo(String lossNo) {
        this.lossNo = lossNo;
    }

    public String getReportCode() {
        return this.reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }
    @Bindable
    public String getInsureVehicleName() {
        return this.insureVehicleName;
    }

    public void setInsureVehicleName(String insureVehicleName) {
        this.insureVehicleName = insureVehicleName;
    }

    public String getInsureVehicleCode() {
        return this.insureVehicleCode;
    }

    public void setInsureVehicleCode(String insureVehicleCode) {
        this.insureVehicleCode = insureVehicleCode;
    }

    public String getComCode() {
        return this.comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBranchComCode() {
        return this.branchComCode;
    }

    public void setBranchComCode(String branchComCode) {
        this.branchComCode = branchComCode;
    }

    public String getBranchComName() {
        return this.branchComName;
    }

    public void setBranchComName(String branchComName) {
        this.branchComName = branchComName;
    }

    public String getHandlerCode() {
        return this.handlerCode;
    }

    public void setHandlerCode(String handlerCode) {
        this.handlerCode = handlerCode;
    }

    public String getHandlerName() {
        return this.handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public String getIsSubjectVehicle() {
        return this.isSubjectVehicle;
    }

    public void setIsSubjectVehicle(String isSubjectVehicle) {
        this.isSubjectVehicle = isSubjectVehicle;
    }
    @Bindable
    public String getPlateNo() {
        return this.plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }
    @Bindable
    public String getVinNo() {
        return this.vinNo;
    }

    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    public String getDriverName() {
        return this.driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    @Bindable
    public String getEnrolDate() {
        return this.enrolDate;
    }

    public void setEnrolDate(String enrolDate) {
        this.enrolDate = enrolDate;
    }

    public String getMarkColor() {
        return this.markColor;
    }

    public void setMarkColor(String markColor) {
        this.markColor = markColor;
    }

    public String getEvalTypeCode() {
        return this.evalTypeCode;
    }

    public void setEvalTypeCode(String evalTypeCode) {
        this.evalTypeCode = evalTypeCode;
    }
    @Bindable
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getSalvageFee() {
        return this.salvageFee;
    }

    public void setSalvageFee(double salvageFee) {
        this.salvageFee = salvageFee;
    }

    public String getSalvageFeeRemark() {
        return this.salvageFeeRemark;
    }

    public void setSalvageFeeRemark(String salvageFeeRemark) {
        this.salvageFeeRemark = salvageFeeRemark;
    }
    @Bindable
    public String getEngineNo() {
        return this.engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getRepairFactoryID() {
        return this.repairFactoryID;
    }

    public void setRepairFactoryID(String repairFactoryID) {
        this.repairFactoryID = repairFactoryID;
    }

    public String getRepairFactoryCode() {
        return this.repairFactoryCode;
    }

    public void setRepairFactoryCode(String repairFactoryCode) {
        this.repairFactoryCode = repairFactoryCode;
    }

    public String getRepairFactoryName() {
        return this.repairFactoryName;
    }

    public void setRepairFactoryName(String repairFactoryName) {
        this.repairFactoryName = repairFactoryName;
    }

    public String getRepairFactoryType() {
        return this.repairFactoryType;
    }

    public void setRepairFactoryType(String repairFactoryType) {
        this.repairFactoryType = repairFactoryType;
    }

    public String getSelfEstiFlag() {
        return this.selfEstiFlag;
    }

    public void setSelfEstiFlag(String selfEstiFlag) {
        this.selfEstiFlag = selfEstiFlag;
    }

    public String getIsLawsuit() {
        return this.isLawsuit;
    }

    public void setIsLawsuit(String isLawsuit) {
        this.isLawsuit = isLawsuit;
    }

    public String getSelfCompensation() {
        return this.selfCompensation;
    }

    public void setSelfCompensation(String selfCompensation) {
        this.selfCompensation = selfCompensation;
    }

    public String getLossItemCode() {
        return this.lossItemCode;
    }

    public void setLossItemCode(String lossItemCode) {
        this.lossItemCode = lossItemCode;
    }

    public String getVehCertainCode() {
        return vehCertainCode;
    }

    public void setVehCertainCode(String vehCertainCode) {
        this.vehCertainCode = vehCertainCode;
    }

    public String getVehCertainName() {
        return vehCertainName;
    }

    public void setVehCertainName(String vehCertainName) {
        this.vehCertainName = vehCertainName;
    }

    public String getSelfConfigFlag() {
        return selfConfigFlag;
    }

    public void setSelfConfigFlag(String selfConfigFlag) {
        this.selfConfigFlag = selfConfigFlag;
    }

    public String getVehFactoryName() {
        return vehFactoryName;
    }

    public void setVehFactoryName(String vehFactoryName) {
        this.vehFactoryName = vehFactoryName;
    }

    public double getRemnantFee() {
        return remnantFee;
    }

    public void setRemnantFee(double remnantFee) {
        this.remnantFee = remnantFee;
    }

    public double getManageRate() {
        return manageRate;
    }

    public void setManageRate(double manageRate) {
        this.manageRate = manageRate;
    }

    public double getManageFee() {
        return manageFee;
    }

    public void setManageFee(double manageFee) {
        this.manageFee = manageFee;
    }

    public double getSumLossAmount() {
        return sumLossAmount;
    }

    public void setSumLossAmount(double sumLossAmount) {
        this.sumLossAmount = sumLossAmount;
    }

    public double getEvalPartSum() {
        return evalPartSum;
    }

    public void setEvalPartSum(double evalPartSum) {
        this.evalPartSum = evalPartSum;
    }

    public double getEvalRepairSum() {
        return evalRepairSum;
    }

    public void setEvalRepairSum(double evalRepairSum) {
        this.evalRepairSum = evalRepairSum;
    }

    public double getEvalMateSum() {
        return evalMateSum;
    }

    public void setEvalMateSum(double evalMateSum) {
        this.evalMateSum = evalMateSum;
    }

    public double getSelfPaySum() {
        return selfPaySum;
    }

    public void setSelfPaySum(double selfPaySum) {
        this.selfPaySum = selfPaySum;
    }

    public double getOuterSum() {
        return outerSum;
    }

    public void setOuterSum(double outerSum) {
        this.outerSum = outerSum;
    }

    public double getDerogationSum() {
        return derogationSum;
    }

    public void setDerogationSum(double derogationSum) {
        this.derogationSum = derogationSum;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getRepairFacID() {
        return repairFacID;
    }

    public void setRepairFacID(String repairFacID) {
        this.repairFacID = repairFacID;
    }

    public String getRepairFacCode() {
        return repairFacCode;
    }

    public void setRepairFacCode(String repairFacCode) {
        this.repairFacCode = repairFacCode;
    }

    public String getRepairFacName() {
        return repairFacName;
    }

    public void setRepairFacName(String repairFacName) {
        this.repairFacName = repairFacName;
    }

    public String getRepairFacType() {
        return repairFacType;
    }

    public void setRepairFacType(String repairFacType) {
        this.repairFacType = repairFacType;
    }

    public String getRecycleType() {
        return recycleType;
    }

    public void setRecycleType(String recycleType) {
        this.recycleType = recycleType;
    }

    public List<InsuranceItem> getInsuranceItemList() {
        return insuranceItemList;
    }

    public void setInsuranceItemList(List<InsuranceItem> insuranceItemList) {
        this.insuranceItemList = insuranceItemList;
    }

    public List<PolicyInfo> getPolicyList() {
        return policyList;
    }

    public void setPolicyList(List<PolicyInfo> policyList) {
        this.policyList = policyList;
    }

    public InsuranceCar getInsuranceCar() {
        return insuranceCar;
    }

    public void setInsuranceCar(InsuranceCar insuranceCar) {
        this.insuranceCar = insuranceCar;
    }

    public List<Insurance> getInsuranceList() {
        return insuranceList;
    }

    public void setInsuranceList(List<Insurance> insuranceList) {
        this.insuranceList = insuranceList;
    }

    public ReportInfo getReportInfo() {
        return reportInfo;
    }

    public void setReportInfo(ReportInfo reportInfo) {
        this.reportInfo = reportInfo;
    }
}
