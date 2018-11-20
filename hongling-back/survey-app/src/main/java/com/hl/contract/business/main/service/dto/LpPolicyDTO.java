package com.hl.contract.business.main.service.dto;


import java.util.List;

/**
 * Created by hl on 2017/9/19.
 */

public class LpPolicyDTO {
    private String id;
    private String reportId;
    private String policyCode;
    private String companyCode;
    private String companyName;
    private String riskType;
    private Short riskYear;
    private String riskCode;
    private String riskName;
    private String customerTypeCode;
    private String customerTypeName;
    private String channelCode;
    private String channelName;
    private String billDate;
    private String policyHoldDate;
    private String insureBgnDate;
    private String insureEndDate;
    private String vehicleCode;
    private String vehicleName;
    private String modelName;
    private String carOwner;
    private String insuredPerson;
    private String identityNo;
    private String driverName;
    private String dealPerson;
    private String proxyInfo;
    private String specifyAssume;
    private String accidentNum;
    private String delFlag;
    private Double totalInsSum;
    private List<LpInsuranceTypeDTO> insuranceItemList;

    public String getAccidentNum() {
        return accidentNum;
    }

    public void setAccidentNum(String accidentNum) {
        this.accidentNum = accidentNum;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(String carOwner) {
        this.carOwner = carOwner;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCustomerTypeCode() {
        return customerTypeCode;
    }

    public void setCustomerTypeCode(String customerTypeCode) {
        this.customerTypeCode = customerTypeCode;
    }

    public String getCustomerTypeName() {
        return customerTypeName;
    }

    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName;
    }

    public String getDealPerson() {
        return dealPerson;
    }

    public void setDealPerson(String dealPerson) {
        this.dealPerson = dealPerson;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public List<LpInsuranceTypeDTO> getInsuranceItemList() {
        return insuranceItemList;
    }

    public void setInsuranceItemList(List<LpInsuranceTypeDTO> insuranceItemList) {
        this.insuranceItemList = insuranceItemList;
    }

    public String getInsureBgnDate() {
        return insureBgnDate;
    }

    public void setInsureBgnDate(String insureBgnDate) {
        this.insureBgnDate = insureBgnDate;
    }

    public String getInsuredPerson() {
        return insuredPerson;
    }

    public void setInsuredPerson(String insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public String getInsureEndDate() {
        return insureEndDate;
    }

    public void setInsureEndDate(String insureEndDate) {
        this.insureEndDate = insureEndDate;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getPolicyCode() {
        return policyCode;
    }

    public void setPolicyCode(String policyCode) {
        this.policyCode = policyCode;
    }

    public String getPolicyHoldDate() {
        return policyHoldDate;
    }

    public void setPolicyHoldDate(String policyHoldDate) {
        this.policyHoldDate = policyHoldDate;
    }

    public String getProxyInfo() {
        return proxyInfo;
    }

    public void setProxyInfo(String proxyInfo) {
        this.proxyInfo = proxyInfo;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getRiskCode() {
        return riskCode;
    }

    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public Short getRiskYear() {
        return riskYear;
    }

    public void setRiskYear(Short riskYear) {
        this.riskYear = riskYear;
    }

    public String getSpecifyAssume() {
        return specifyAssume;
    }

    public void setSpecifyAssume(String specifyAssume) {
        this.specifyAssume = specifyAssume;
    }

    public Double getTotalInsSum() {
        return totalInsSum;
    }

    public void setTotalInsSum(Double totalInsSum) {
        this.totalInsSum = totalInsSum;
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
}
