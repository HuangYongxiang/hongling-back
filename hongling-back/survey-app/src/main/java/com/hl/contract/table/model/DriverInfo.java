package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * Create data： 2017/05/25.
 * Author: liyu
 * Function:驾驶员信息：列表（多条）
 */
@Entity
public class DriverInfo implements Serializable {
    @Transient
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String reportCode;//报案号
    private String driverName;//姓名
    private String contactNumber;//联系电话
    private String certificateType;//证件类型
    private String certificateTypeCode;//证件类型编码
    private String certificateNo;//证件号码

    private String sex;//驾驶员性别
    private int age;//驾驶员年龄
    private String licenseNumber;//驾驶证号码
    private String driveType; //准驾车型
    private String driveTypeCode; //准驾车型编码
    private String plateNo; //车牌号码
    private String driverTerritorial; //驾驶员属地
    private String certificationAuthority;//颁证机关
    private String degreeOfEducation;//文化程度
    private String profession; //行业
    private String issueDate; //初次领证日期
    private String unitOrAddress;// 单位或地址
    private Integer serialNo;//序号，理赔同步过来
    @Generated(hash = 702443451)
    public DriverInfo(String id, String reportCode, String driverName,
                      String contactNumber, String certificateType,
                      String certificateTypeCode, String certificateNo, String sex, int age,
                      String licenseNumber, String driveType, String driveTypeCode,
                      String plateNo, String driverTerritorial, String certificationAuthority,
                      String degreeOfEducation, String profession, String issueDate,
                      String unitOrAddress, Integer serialNo) {
        this.id = id;
        this.reportCode = reportCode;
        this.driverName = driverName;
        this.contactNumber = contactNumber;
        this.certificateType = certificateType;
        this.certificateTypeCode = certificateTypeCode;
        this.certificateNo = certificateNo;
        this.sex = sex;
        this.age = age;
        this.licenseNumber = licenseNumber;
        this.driveType = driveType;
        this.driveTypeCode = driveTypeCode;
        this.plateNo = plateNo;
        this.driverTerritorial = driverTerritorial;
        this.certificationAuthority = certificationAuthority;
        this.degreeOfEducation = degreeOfEducation;
        this.profession = profession;
        this.issueDate = issueDate;
        this.unitOrAddress = unitOrAddress;
        this.serialNo = serialNo;
    }
    @Generated(hash = 2077275369)
    public DriverInfo() {
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
    public String getDriverName() {
        return this.driverName;
    }
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    public String getContactNumber() {
        return this.contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public String getCertificateType() {
        return this.certificateType;
    }
    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }
    public String getCertificateTypeCode() {
        return this.certificateTypeCode;
    }
    public void setCertificateTypeCode(String certificateTypeCode) {
        this.certificateTypeCode = certificateTypeCode;
    }
    public String getCertificateNo() {
        return this.certificateNo;
    }
    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getLicenseNumber() {
        return this.licenseNumber;
    }
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
    public String getDriveType() {
        return this.driveType;
    }
    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }
    public String getDriveTypeCode() {
        return this.driveTypeCode;
    }
    public void setDriveTypeCode(String driveTypeCode) {
        this.driveTypeCode = driveTypeCode;
    }
    public String getPlateNo() {
        return this.plateNo;
    }
    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }
    public String getDriverTerritorial() {
        return this.driverTerritorial;
    }
    public void setDriverTerritorial(String driverTerritorial) {
        this.driverTerritorial = driverTerritorial;
    }
    public String getCertificationAuthority() {
        return this.certificationAuthority;
    }
    public void setCertificationAuthority(String certificationAuthority) {
        this.certificationAuthority = certificationAuthority;
    }
    public String getDegreeOfEducation() {
        return this.degreeOfEducation;
    }
    public void setDegreeOfEducation(String degreeOfEducation) {
        this.degreeOfEducation = degreeOfEducation;
    }
    public String getProfession() {
        return this.profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }
    public String getIssueDate() {
        return this.issueDate;
    }
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
    public String getUnitOrAddress() {
        return this.unitOrAddress;
    }
    public void setUnitOrAddress(String unitOrAddress) {
        this.unitOrAddress = unitOrAddress;
    }
    public Integer getSerialNo() {
        return this.serialNo;
    }
    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }
}