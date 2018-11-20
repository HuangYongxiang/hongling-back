package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @Describe:  进入定损传报案、保单的数据的情况使用：保单信息
 * @Author: liyu
 * @Date: 2018/1/13 13:06
 * @Copyright: hl
 */
@Entity
public class PolicyInfo {
    @Id
    private String id;
    private String policyCode;//保单号
    private String reportId;//报案id
    private String reportCode;//报案号
    private String insureBgnDate;//保险起期
    private String insureEndDate;//保险止期
    private String vehicleCode;//车型代码
    private String vehicleName;//车型名称
    private String insuredPerson;//被保险人姓名
    private String companyCode;//承保机构代码
    private String companyName;//承保机构名称
    private Double totalInsSum;//总保额
    private String riskType;//险种类别
    private String riskCode;//险种代码
    private String riskName;//险种名称
    private String customerTypeCode;//客户类型代码
    private String customerTypeName;//客户类型名称
    private String specifyAssume;//特别约定
    private String channelCode;//业务渠道代码
    private String channelName;//业务渠道名称
    private String billDate;//出单日期
    private String policyHoldDate;//投保日期
    private String modelName;//厂牌型号
    private String carOwner;//车辆所有人
    private String identityNo;//身份证号
    private String driverName;//驾驶员姓名
    private String proxyInfo;//代理点明细
    private String accidentNum;//历史出险次数
    private String dealPerson;//保单经办人
    private String insuranceBranchCompanyCode;//承保中支机构编码
    private String insuranceBranchCompanyName;//承保中支机构名称
    @Generated(hash = 1933823549)
    public PolicyInfo(String id, String policyCode, String reportId,
                      String reportCode, String insureBgnDate, String insureEndDate,
                      String vehicleCode, String vehicleName, String insuredPerson,
                      String companyCode, String companyName, Double totalInsSum,
                      String riskType, String riskCode, String riskName,
                      String customerTypeCode, String customerTypeName, String specifyAssume,
                      String channelCode, String channelName, String billDate,
                      String policyHoldDate, String modelName, String carOwner,
                      String identityNo, String driverName, String proxyInfo,
                      String accidentNum, String dealPerson,
                      String insuranceBranchCompanyCode, String insuranceBranchCompanyName) {
        this.id = id;
        this.policyCode = policyCode;
        this.reportId = reportId;
        this.reportCode = reportCode;
        this.insureBgnDate = insureBgnDate;
        this.insureEndDate = insureEndDate;
        this.vehicleCode = vehicleCode;
        this.vehicleName = vehicleName;
        this.insuredPerson = insuredPerson;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.totalInsSum = totalInsSum;
        this.riskType = riskType;
        this.riskCode = riskCode;
        this.riskName = riskName;
        this.customerTypeCode = customerTypeCode;
        this.customerTypeName = customerTypeName;
        this.specifyAssume = specifyAssume;
        this.channelCode = channelCode;
        this.channelName = channelName;
        this.billDate = billDate;
        this.policyHoldDate = policyHoldDate;
        this.modelName = modelName;
        this.carOwner = carOwner;
        this.identityNo = identityNo;
        this.driverName = driverName;
        this.proxyInfo = proxyInfo;
        this.accidentNum = accidentNum;
        this.dealPerson = dealPerson;
        this.insuranceBranchCompanyCode = insuranceBranchCompanyCode;
        this.insuranceBranchCompanyName = insuranceBranchCompanyName;
    }
    @Generated(hash = 117423779)
    public PolicyInfo() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPolicyCode() {
        return this.policyCode;
    }
    public void setPolicyCode(String policyCode) {
        this.policyCode = policyCode;
    }
    public String getReportId() {
        return this.reportId;
    }
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
    public String getReportCode() {
        return this.reportCode;
    }
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }
    public String getInsureBgnDate() {
        return this.insureBgnDate;
    }
    public void setInsureBgnDate(String insureBgnDate) {
        this.insureBgnDate = insureBgnDate;
    }
    public String getInsureEndDate() {
        return this.insureEndDate;
    }
    public void setInsureEndDate(String insureEndDate) {
        this.insureEndDate = insureEndDate;
    }
    public String getVehicleCode() {
        return this.vehicleCode;
    }
    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }
    public String getVehicleName() {
        return this.vehicleName;
    }
    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
    public String getInsuredPerson() {
        return this.insuredPerson;
    }
    public void setInsuredPerson(String insuredPerson) {
        this.insuredPerson = insuredPerson;
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
    public Double getTotalInsSum() {
        return this.totalInsSum;
    }
    public void setTotalInsSum(Double totalInsSum) {
        this.totalInsSum = totalInsSum;
    }
    public String getRiskType() {
        return this.riskType;
    }
    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }
    public String getRiskCode() {
        return this.riskCode;
    }
    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }
    public String getRiskName() {
        return this.riskName;
    }
    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }
    public String getCustomerTypeCode() {
        return this.customerTypeCode;
    }
    public void setCustomerTypeCode(String customerTypeCode) {
        this.customerTypeCode = customerTypeCode;
    }
    public String getCustomerTypeName() {
        return this.customerTypeName;
    }
    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName;
    }
    public String getSpecifyAssume() {
        return this.specifyAssume;
    }
    public void setSpecifyAssume(String specifyAssume) {
        this.specifyAssume = specifyAssume;
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
    public String getBillDate() {
        return this.billDate;
    }
    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }
    public String getPolicyHoldDate() {
        return this.policyHoldDate;
    }
    public void setPolicyHoldDate(String policyHoldDate) {
        this.policyHoldDate = policyHoldDate;
    }
    public String getModelName() {
        return this.modelName;
    }
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    public String getCarOwner() {
        return this.carOwner;
    }
    public void setCarOwner(String carOwner) {
        this.carOwner = carOwner;
    }
    public String getIdentityNo() {
        return this.identityNo;
    }
    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }
    public String getDriverName() {
        return this.driverName;
    }
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    public String getProxyInfo() {
        return this.proxyInfo;
    }
    public void setProxyInfo(String proxyInfo) {
        this.proxyInfo = proxyInfo;
    }
    public String getAccidentNum() {
        return this.accidentNum;
    }
    public void setAccidentNum(String accidentNum) {
        this.accidentNum = accidentNum;
    }
    public String getDealPerson() {
        return this.dealPerson;
    }
    public void setDealPerson(String dealPerson) {
        this.dealPerson = dealPerson;
    }
    public String getInsuranceBranchCompanyCode() {
        return this.insuranceBranchCompanyCode;
    }
    public void setInsuranceBranchCompanyCode(String insuranceBranchCompanyCode) {
        this.insuranceBranchCompanyCode = insuranceBranchCompanyCode;
    }
    public String getInsuranceBranchCompanyName() {
        return this.insuranceBranchCompanyName;
    }
    public void setInsuranceBranchCompanyName(String insuranceBranchCompanyName) {
        this.insuranceBranchCompanyName = insuranceBranchCompanyName;
    }
}
