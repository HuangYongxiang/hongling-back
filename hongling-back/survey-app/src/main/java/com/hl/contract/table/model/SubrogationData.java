package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * Create data： 2017/6/21.
 * Author: liyu
 * Function:责任对方信息
 */
@Entity
public class SubrogationData implements Serializable{
    @Transient
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String reportCode;//报案号
    private String flowId;//任务流水号
    private int subrogationSerialNo;//序号
    private String subrogationLinkerName;//责任对方姓名
    private String subrogationCarType;//责任方类型
    private String subrogationCarTypeCode;//责任方类型编码
    private String subrogationEngineNo;//责任方车辆发动机号
    private String subrogationLicensePlateNo;//责任方车辆号码
    private String subrogationLicensePlateType;//责任方车辆号牌种类
    private String subrogationLicensePlateTypeCode;//责任方车辆号牌种类编码
    private String subrogationVIN;//责任方车辆VIN码
    private String subrogationCaInsurerCodeSY;//责任方商业险承保公司代码
    private String subrogationCaInsurerCodeSYCompaName;//责任方商业险承保公司名称
    private String subrogationCaInsurerCodeSYDQ;//责任方商业险承保地区代码
    private String subrogationCaInsurerCodeSYDQArea;//责任方商业险承保地区名称
    private String subrogationCaInsurerCodeJQ;//责任方交强险承保公司代码
    private String subrogationCaInsurerCodeJQCompaName;//责任方交强险承保公司名称
    private String subrogationCaInsurerCodeJQDQ;//责任方交强险承保地区代码
    private String subrogationCaInsurerCodeJQDQArea;//责任方交强险承保地区名称
    private String subrogationCaInsurerUnit;//责任对方单位名称
    private String subrogationCaInsurerPhone;//责任对方联系电话
    private String subrogationCaInsurerDriver;//责任对方驾驶员姓名
    private String subrogationDriverPhone;//驾驶员联系方式
    private String subrogationUnitPhone;//单位联系人电话
    private double subrogationAmount;//责任对方三者险限额
    private String subrogationFlag;//责任对方是否投保不计免赔特约险
    private String subrogationCaInsurerLinkerName;//责任对方联系人
    private String subrogationAdress;//家庭或者单位详细地址
    private String subrogationPostcode;//邮政编码
    private String subrogationCertificateId;//身份证号码
    private String subrogationCaInsurerStartDate;//责任对方交强险保险起期
    private String subrogationCaInsurerEndDate;//责任对方交强险保险止期
    private String subrogationBusCaInsurerStartDate;//责任对方商业保险起期
    private String subrogationBusCaInsurerEndDate;//责任对方商业保险止期
    private String subrogationLegalRepresentative;//法定代表人姓名
    private String subrogationInsurance;//投保保险情况
    @Generated(hash = 1851574274)
    public SubrogationData(String id, String reportCode, String flowId,
                           int subrogationSerialNo, String subrogationLinkerName, String subrogationCarType,
                           String subrogationCarTypeCode, String subrogationEngineNo,
                           String subrogationLicensePlateNo, String subrogationLicensePlateType,
                           String subrogationLicensePlateTypeCode, String subrogationVIN,
                           String subrogationCaInsurerCodeSY, String subrogationCaInsurerCodeSYCompaName,
                           String subrogationCaInsurerCodeSYDQ, String subrogationCaInsurerCodeSYDQArea,
                           String subrogationCaInsurerCodeJQ, String subrogationCaInsurerCodeJQCompaName,
                           String subrogationCaInsurerCodeJQDQ, String subrogationCaInsurerCodeJQDQArea,
                           String subrogationCaInsurerUnit, String subrogationCaInsurerPhone,
                           String subrogationCaInsurerDriver, String subrogationDriverPhone,
                           String subrogationUnitPhone, double subrogationAmount, String subrogationFlag,
                           String subrogationCaInsurerLinkerName, String subrogationAdress,
                           String subrogationPostcode, String subrogationCertificateId,
                           String subrogationCaInsurerStartDate, String subrogationCaInsurerEndDate,
                           String subrogationBusCaInsurerStartDate, String subrogationBusCaInsurerEndDate,
                           String subrogationLegalRepresentative, String subrogationInsurance) {
        this.id = id;
        this.reportCode = reportCode;
        this.flowId = flowId;
        this.subrogationSerialNo = subrogationSerialNo;
        this.subrogationLinkerName = subrogationLinkerName;
        this.subrogationCarType = subrogationCarType;
        this.subrogationCarTypeCode = subrogationCarTypeCode;
        this.subrogationEngineNo = subrogationEngineNo;
        this.subrogationLicensePlateNo = subrogationLicensePlateNo;
        this.subrogationLicensePlateType = subrogationLicensePlateType;
        this.subrogationLicensePlateTypeCode = subrogationLicensePlateTypeCode;
        this.subrogationVIN = subrogationVIN;
        this.subrogationCaInsurerCodeSY = subrogationCaInsurerCodeSY;
        this.subrogationCaInsurerCodeSYCompaName = subrogationCaInsurerCodeSYCompaName;
        this.subrogationCaInsurerCodeSYDQ = subrogationCaInsurerCodeSYDQ;
        this.subrogationCaInsurerCodeSYDQArea = subrogationCaInsurerCodeSYDQArea;
        this.subrogationCaInsurerCodeJQ = subrogationCaInsurerCodeJQ;
        this.subrogationCaInsurerCodeJQCompaName = subrogationCaInsurerCodeJQCompaName;
        this.subrogationCaInsurerCodeJQDQ = subrogationCaInsurerCodeJQDQ;
        this.subrogationCaInsurerCodeJQDQArea = subrogationCaInsurerCodeJQDQArea;
        this.subrogationCaInsurerUnit = subrogationCaInsurerUnit;
        this.subrogationCaInsurerPhone = subrogationCaInsurerPhone;
        this.subrogationCaInsurerDriver = subrogationCaInsurerDriver;
        this.subrogationDriverPhone = subrogationDriverPhone;
        this.subrogationUnitPhone = subrogationUnitPhone;
        this.subrogationAmount = subrogationAmount;
        this.subrogationFlag = subrogationFlag;
        this.subrogationCaInsurerLinkerName = subrogationCaInsurerLinkerName;
        this.subrogationAdress = subrogationAdress;
        this.subrogationPostcode = subrogationPostcode;
        this.subrogationCertificateId = subrogationCertificateId;
        this.subrogationCaInsurerStartDate = subrogationCaInsurerStartDate;
        this.subrogationCaInsurerEndDate = subrogationCaInsurerEndDate;
        this.subrogationBusCaInsurerStartDate = subrogationBusCaInsurerStartDate;
        this.subrogationBusCaInsurerEndDate = subrogationBusCaInsurerEndDate;
        this.subrogationLegalRepresentative = subrogationLegalRepresentative;
        this.subrogationInsurance = subrogationInsurance;
    }
    @Generated(hash = 773272340)
    public SubrogationData() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getReportCode() {
        return this.reportCode;
    }
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }
    public int getSubrogationSerialNo() {
        return this.subrogationSerialNo;
    }
    public void setSubrogationSerialNo(int subrogationSerialNo) {
        this.subrogationSerialNo = subrogationSerialNo;
    }
    public String getSubrogationLinkerName() {
        return this.subrogationLinkerName;
    }
    public void setSubrogationLinkerName(String subrogationLinkerName) {
        this.subrogationLinkerName = subrogationLinkerName;
    }
    public String getSubrogationCarType() {
        return this.subrogationCarType;
    }
    public void setSubrogationCarType(String subrogationCarType) {
        this.subrogationCarType = subrogationCarType;
    }
    public String getSubrogationCarTypeCode() {
        return this.subrogationCarTypeCode;
    }
    public void setSubrogationCarTypeCode(String subrogationCarTypeCode) {
        this.subrogationCarTypeCode = subrogationCarTypeCode;
    }
    public String getSubrogationEngineNo() {
        return this.subrogationEngineNo;
    }
    public void setSubrogationEngineNo(String subrogationEngineNo) {
        this.subrogationEngineNo = subrogationEngineNo;
    }
    public String getSubrogationLicensePlateNo() {
        return this.subrogationLicensePlateNo;
    }
    public void setSubrogationLicensePlateNo(String subrogationLicensePlateNo) {
        this.subrogationLicensePlateNo = subrogationLicensePlateNo;
    }
    public String getSubrogationLicensePlateType() {
        return this.subrogationLicensePlateType;
    }
    public void setSubrogationLicensePlateType(String subrogationLicensePlateType) {
        this.subrogationLicensePlateType = subrogationLicensePlateType;
    }
    public String getSubrogationLicensePlateTypeCode() {
        return this.subrogationLicensePlateTypeCode;
    }
    public void setSubrogationLicensePlateTypeCode(
            String subrogationLicensePlateTypeCode) {
        this.subrogationLicensePlateTypeCode = subrogationLicensePlateTypeCode;
    }
    public String getSubrogationVIN() {
        return this.subrogationVIN;
    }
    public void setSubrogationVIN(String subrogationVIN) {
        this.subrogationVIN = subrogationVIN;
    }
    public String getSubrogationCaInsurerCodeSY() {
        return this.subrogationCaInsurerCodeSY;
    }
    public void setSubrogationCaInsurerCodeSY(String subrogationCaInsurerCodeSY) {
        this.subrogationCaInsurerCodeSY = subrogationCaInsurerCodeSY;
    }
    public String getSubrogationCaInsurerCodeSYCompaName() {
        return this.subrogationCaInsurerCodeSYCompaName;
    }
    public void setSubrogationCaInsurerCodeSYCompaName(
            String subrogationCaInsurerCodeSYCompaName) {
        this.subrogationCaInsurerCodeSYCompaName = subrogationCaInsurerCodeSYCompaName;
    }
    public String getSubrogationCaInsurerCodeSYDQ() {
        return this.subrogationCaInsurerCodeSYDQ;
    }
    public void setSubrogationCaInsurerCodeSYDQ(
            String subrogationCaInsurerCodeSYDQ) {
        this.subrogationCaInsurerCodeSYDQ = subrogationCaInsurerCodeSYDQ;
    }
    public String getSubrogationCaInsurerCodeSYDQArea() {
        return this.subrogationCaInsurerCodeSYDQArea;
    }
    public void setSubrogationCaInsurerCodeSYDQArea(
            String subrogationCaInsurerCodeSYDQArea) {
        this.subrogationCaInsurerCodeSYDQArea = subrogationCaInsurerCodeSYDQArea;
    }
    public String getSubrogationCaInsurerCodeJQ() {
        return this.subrogationCaInsurerCodeJQ;
    }
    public void setSubrogationCaInsurerCodeJQ(String subrogationCaInsurerCodeJQ) {
        this.subrogationCaInsurerCodeJQ = subrogationCaInsurerCodeJQ;
    }
    public String getSubrogationCaInsurerCodeJQCompaName() {
        return this.subrogationCaInsurerCodeJQCompaName;
    }
    public void setSubrogationCaInsurerCodeJQCompaName(
            String subrogationCaInsurerCodeJQCompaName) {
        this.subrogationCaInsurerCodeJQCompaName = subrogationCaInsurerCodeJQCompaName;
    }
    public String getSubrogationCaInsurerCodeJQDQ() {
        return this.subrogationCaInsurerCodeJQDQ;
    }
    public void setSubrogationCaInsurerCodeJQDQ(
            String subrogationCaInsurerCodeJQDQ) {
        this.subrogationCaInsurerCodeJQDQ = subrogationCaInsurerCodeJQDQ;
    }
    public String getSubrogationCaInsurerCodeJQDQArea() {
        return this.subrogationCaInsurerCodeJQDQArea;
    }
    public void setSubrogationCaInsurerCodeJQDQArea(
            String subrogationCaInsurerCodeJQDQArea) {
        this.subrogationCaInsurerCodeJQDQArea = subrogationCaInsurerCodeJQDQArea;
    }
    public String getSubrogationCaInsurerUnit() {
        return this.subrogationCaInsurerUnit;
    }
    public void setSubrogationCaInsurerUnit(String subrogationCaInsurerUnit) {
        this.subrogationCaInsurerUnit = subrogationCaInsurerUnit;
    }
    public String getSubrogationCaInsurerPhone() {
        return this.subrogationCaInsurerPhone;
    }
    public void setSubrogationCaInsurerPhone(String subrogationCaInsurerPhone) {
        this.subrogationCaInsurerPhone = subrogationCaInsurerPhone;
    }
    public String getSubrogationCaInsurerDriver() {
        return this.subrogationCaInsurerDriver;
    }
    public void setSubrogationCaInsurerDriver(String subrogationCaInsurerDriver) {
        this.subrogationCaInsurerDriver = subrogationCaInsurerDriver;
    }
    public String getSubrogationDriverPhone() {
        return this.subrogationDriverPhone;
    }
    public void setSubrogationDriverPhone(String subrogationDriverPhone) {
        this.subrogationDriverPhone = subrogationDriverPhone;
    }
    public String getSubrogationUnitPhone() {
        return this.subrogationUnitPhone;
    }
    public void setSubrogationUnitPhone(String subrogationUnitPhone) {
        this.subrogationUnitPhone = subrogationUnitPhone;
    }
    public double getSubrogationAmount() {
        return this.subrogationAmount;
    }
    public void setSubrogationAmount(double subrogationAmount) {
        this.subrogationAmount = subrogationAmount;
    }
    public String getSubrogationFlag() {
        return this.subrogationFlag;
    }
    public void setSubrogationFlag(String subrogationFlag) {
        this.subrogationFlag = subrogationFlag;
    }
    public String getSubrogationCaInsurerLinkerName() {
        return this.subrogationCaInsurerLinkerName;
    }
    public void setSubrogationCaInsurerLinkerName(
            String subrogationCaInsurerLinkerName) {
        this.subrogationCaInsurerLinkerName = subrogationCaInsurerLinkerName;
    }
    public String getSubrogationAdress() {
        return this.subrogationAdress;
    }
    public void setSubrogationAdress(String subrogationAdress) {
        this.subrogationAdress = subrogationAdress;
    }
    public String getSubrogationPostcode() {
        return this.subrogationPostcode;
    }
    public void setSubrogationPostcode(String subrogationPostcode) {
        this.subrogationPostcode = subrogationPostcode;
    }
    public String getSubrogationCertificateId() {
        return this.subrogationCertificateId;
    }
    public void setSubrogationCertificateId(String subrogationCertificateId) {
        this.subrogationCertificateId = subrogationCertificateId;
    }
    public String getSubrogationCaInsurerStartDate() {
        return this.subrogationCaInsurerStartDate;
    }
    public void setSubrogationCaInsurerStartDate(
            String subrogationCaInsurerStartDate) {
        this.subrogationCaInsurerStartDate = subrogationCaInsurerStartDate;
    }
    public String getSubrogationCaInsurerEndDate() {
        return this.subrogationCaInsurerEndDate;
    }
    public void setSubrogationCaInsurerEndDate(String subrogationCaInsurerEndDate) {
        this.subrogationCaInsurerEndDate = subrogationCaInsurerEndDate;
    }
    public String getSubrogationBusCaInsurerStartDate() {
        return this.subrogationBusCaInsurerStartDate;
    }
    public void setSubrogationBusCaInsurerStartDate(
            String subrogationBusCaInsurerStartDate) {
        this.subrogationBusCaInsurerStartDate = subrogationBusCaInsurerStartDate;
    }
    public String getSubrogationBusCaInsurerEndDate() {
        return this.subrogationBusCaInsurerEndDate;
    }
    public void setSubrogationBusCaInsurerEndDate(
            String subrogationBusCaInsurerEndDate) {
        this.subrogationBusCaInsurerEndDate = subrogationBusCaInsurerEndDate;
    }
    public String getSubrogationLegalRepresentative() {
        return this.subrogationLegalRepresentative;
    }
    public void setSubrogationLegalRepresentative(
            String subrogationLegalRepresentative) {
        this.subrogationLegalRepresentative = subrogationLegalRepresentative;
    }
    public String getSubrogationInsurance() {
        return this.subrogationInsurance;
    }
    public void setSubrogationInsurance(String subrogationInsurance) {
        this.subrogationInsurance = subrogationInsurance;
    }
    public String getFlowId() {
        return this.flowId;
    }
    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }


}
