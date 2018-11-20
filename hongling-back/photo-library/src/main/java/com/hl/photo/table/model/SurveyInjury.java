package com.hl.photo.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * Create data： 2017/6/22.
 * Author: liyu
 * Function:人伤跟踪信息：列表（多条）
 */
@Entity
public class SurveyInjury implements Serializable {
    @Transient
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String reportCode;//报案号
    private String injuryName;//伤者姓名
    private String injuryType;//人伤类型
    private String injuryTypeCode;//人伤类型编码
    private String casualtyType;//伤亡类型
    private String casualtyTypeCode;//伤亡类型编码
    private String certificateType;//证件类型
    private String certificateTypeCode;//证件类型编码
    private String certificateNo;//证件号码
    private String injuryDescription;//伤情描述
    private String hospital;//就诊医院

    private String sex;//受伤人性别
    private int age;//受伤人年龄
    private String householdRegister;//户籍
    private String profession;//行业
    private String plateNo; //车牌号码
    private String peopleType;//人员类型
    private String peopleTypeCode;//人员类型编码
    private String injuryGrade;//伤情级别
    private String injuryGradeCode;//伤情级别编码
    private String isSelfMedical;//是否自行就医
    private String disabledGrade;//伤残等级
    private String disabledGradeCode;//伤残等级编码
    private Integer serialNo;//序号，理赔同步过来
    private String additionalFlag;//新增标记
    @Generated(hash = 708815154)
    public SurveyInjury(String id, String reportCode, String injuryName,
                        String injuryType, String injuryTypeCode, String casualtyType,
                        String casualtyTypeCode, String certificateType,
                        String certificateTypeCode, String certificateNo,
                        String injuryDescription, String hospital, String sex, int age,
                        String householdRegister, String profession, String plateNo,
                        String peopleType, String peopleTypeCode, String injuryGrade,
                        String injuryGradeCode, String isSelfMedical, String disabledGrade,
                        String disabledGradeCode, Integer serialNo, String additionalFlag) {
        this.id = id;
        this.reportCode = reportCode;
        this.injuryName = injuryName;
        this.injuryType = injuryType;
        this.injuryTypeCode = injuryTypeCode;
        this.casualtyType = casualtyType;
        this.casualtyTypeCode = casualtyTypeCode;
        this.certificateType = certificateType;
        this.certificateTypeCode = certificateTypeCode;
        this.certificateNo = certificateNo;
        this.injuryDescription = injuryDescription;
        this.hospital = hospital;
        this.sex = sex;
        this.age = age;
        this.householdRegister = householdRegister;
        this.profession = profession;
        this.plateNo = plateNo;
        this.peopleType = peopleType;
        this.peopleTypeCode = peopleTypeCode;
        this.injuryGrade = injuryGrade;
        this.injuryGradeCode = injuryGradeCode;
        this.isSelfMedical = isSelfMedical;
        this.disabledGrade = disabledGrade;
        this.disabledGradeCode = disabledGradeCode;
        this.serialNo = serialNo;
        this.additionalFlag = additionalFlag;
    }
    @Generated(hash = 176606012)
    public SurveyInjury() {
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
    public String getInjuryName() {
        return this.injuryName;
    }
    public void setInjuryName(String injuryName) {
        this.injuryName = injuryName;
    }
    public String getInjuryType() {
        return this.injuryType;
    }
    public void setInjuryType(String injuryType) {
        this.injuryType = injuryType;
    }
    public String getInjuryTypeCode() {
        return this.injuryTypeCode;
    }
    public void setInjuryTypeCode(String injuryTypeCode) {
        this.injuryTypeCode = injuryTypeCode;
    }
    public String getCasualtyType() {
        return this.casualtyType;
    }
    public void setCasualtyType(String casualtyType) {
        this.casualtyType = casualtyType;
    }
    public String getCasualtyTypeCode() {
        return this.casualtyTypeCode;
    }
    public void setCasualtyTypeCode(String casualtyTypeCode) {
        this.casualtyTypeCode = casualtyTypeCode;
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
    public String getInjuryDescription() {
        return this.injuryDescription;
    }
    public void setInjuryDescription(String injuryDescription) {
        this.injuryDescription = injuryDescription;
    }
    public String getHospital() {
        return this.hospital;
    }
    public void setHospital(String hospital) {
        this.hospital = hospital;
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
    public String getHouseholdRegister() {
        return this.householdRegister;
    }
    public void setHouseholdRegister(String householdRegister) {
        this.householdRegister = householdRegister;
    }
    public String getProfession() {
        return this.profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }
    public String getPlateNo() {
        return this.plateNo;
    }
    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }
    public String getPeopleType() {
        return this.peopleType;
    }
    public void setPeopleType(String peopleType) {
        this.peopleType = peopleType;
    }
    public String getPeopleTypeCode() {
        return this.peopleTypeCode;
    }
    public void setPeopleTypeCode(String peopleTypeCode) {
        this.peopleTypeCode = peopleTypeCode;
    }
    public String getInjuryGrade() {
        return this.injuryGrade;
    }
    public void setInjuryGrade(String injuryGrade) {
        this.injuryGrade = injuryGrade;
    }
    public String getInjuryGradeCode() {
        return this.injuryGradeCode;
    }
    public void setInjuryGradeCode(String injuryGradeCode) {
        this.injuryGradeCode = injuryGradeCode;
    }
    public String getIsSelfMedical() {
        return this.isSelfMedical;
    }
    public void setIsSelfMedical(String isSelfMedical) {
        this.isSelfMedical = isSelfMedical;
    }
    public String getDisabledGrade() {
        return this.disabledGrade;
    }
    public void setDisabledGrade(String disabledGrade) {
        this.disabledGrade = disabledGrade;
    }
    public String getDisabledGradeCode() {
        return this.disabledGradeCode;
    }
    public void setDisabledGradeCode(String disabledGradeCode) {
        this.disabledGradeCode = disabledGradeCode;
    }
    public Integer getSerialNo() {
        return this.serialNo;
    }
    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }
    public String getAdditionalFlag() {
        return this.additionalFlag;
    }
    public void setAdditionalFlag(String additionalFlag) {
        this.additionalFlag = additionalFlag;
    }



}