package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;


/**
 * Create data： 2017/7/14.
 * Author: liyu
 * Function:人伤住院信息
 */
@Entity
public class InjuryHospitalInfo{
    @Id
    private String id;
    private String taskNo;
    private String reportCode;

    private String visitHospital; // 就诊医院
    private String indate; // 入院日期
    private String outdate; // 出院日期
    private String liveDays; // 拟住院天数
    private String crudeDays; // 拟治疗天数
    private String attendDoctor; // 主治医师
    private String attendDoctorPhone; // 主治医师联系方式
    private String incurredFee; // 已发生费用
    private String remainFee; // 预计剩余费用
    private String deleteFee; // 剔除费用（社保费用）
    private String crudeWay; //治疗方式
    private String crudeWayCode; //治疗方式编码
    private String diagnosisDescribe; // 主要诊断描述
    private String treatmentCondition; // 治疗情况
    private String needTransfer; //是否需要转院治疗
    private String needTransferCode; //是否需要转院治疗编码
    @Generated(hash = 1942037980)
    public InjuryHospitalInfo(String id, String taskNo, String reportCode,
            String visitHospital, String indate, String outdate, String liveDays,
            String crudeDays, String attendDoctor, String attendDoctorPhone,
            String incurredFee, String remainFee, String deleteFee, String crudeWay,
            String crudeWayCode, String diagnosisDescribe,
            String treatmentCondition, String needTransfer,
            String needTransferCode) {
        this.id = id;
        this.taskNo = taskNo;
        this.reportCode = reportCode;
        this.visitHospital = visitHospital;
        this.indate = indate;
        this.outdate = outdate;
        this.liveDays = liveDays;
        this.crudeDays = crudeDays;
        this.attendDoctor = attendDoctor;
        this.attendDoctorPhone = attendDoctorPhone;
        this.incurredFee = incurredFee;
        this.remainFee = remainFee;
        this.deleteFee = deleteFee;
        this.crudeWay = crudeWay;
        this.crudeWayCode = crudeWayCode;
        this.diagnosisDescribe = diagnosisDescribe;
        this.treatmentCondition = treatmentCondition;
        this.needTransfer = needTransfer;
        this.needTransferCode = needTransferCode;
    }
    @Generated(hash = 91369536)
    public InjuryHospitalInfo() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTaskNo() {
        return this.taskNo;
    }
    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }
    public String getReportCode() {
        return this.reportCode;
    }
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }
    public String getVisitHospital() {
        return this.visitHospital;
    }
    public void setVisitHospital(String visitHospital) {
        this.visitHospital = visitHospital;
    }
    public String getIndate() {
        return this.indate;
    }
    public void setIndate(String indate) {
        this.indate = indate;
    }
    public String getOutdate() {
        return this.outdate;
    }
    public void setOutdate(String outdate) {
        this.outdate = outdate;
    }
    public String getLiveDays() {
        return this.liveDays;
    }
    public void setLiveDays(String liveDays) {
        this.liveDays = liveDays;
    }
    public String getCrudeDays() {
        return this.crudeDays;
    }
    public void setCrudeDays(String crudeDays) {
        this.crudeDays = crudeDays;
    }
    public String getAttendDoctor() {
        return this.attendDoctor;
    }
    public void setAttendDoctor(String attendDoctor) {
        this.attendDoctor = attendDoctor;
    }
    public String getAttendDoctorPhone() {
        return this.attendDoctorPhone;
    }
    public void setAttendDoctorPhone(String attendDoctorPhone) {
        this.attendDoctorPhone = attendDoctorPhone;
    }
    public String getIncurredFee() {
        return this.incurredFee;
    }
    public void setIncurredFee(String incurredFee) {
        this.incurredFee = incurredFee;
    }
    public String getRemainFee() {
        return this.remainFee;
    }
    public void setRemainFee(String remainFee) {
        this.remainFee = remainFee;
    }
    public String getDeleteFee() {
        return this.deleteFee;
    }
    public void setDeleteFee(String deleteFee) {
        this.deleteFee = deleteFee;
    }
    public String getCrudeWay() {
        return this.crudeWay;
    }
    public void setCrudeWay(String crudeWay) {
        this.crudeWay = crudeWay;
    }
    public String getCrudeWayCode() {
        return this.crudeWayCode;
    }
    public void setCrudeWayCode(String crudeWayCode) {
        this.crudeWayCode = crudeWayCode;
    }
    public String getDiagnosisDescribe() {
        return this.diagnosisDescribe;
    }
    public void setDiagnosisDescribe(String diagnosisDescribe) {
        this.diagnosisDescribe = diagnosisDescribe;
    }
    public String getTreatmentCondition() {
        return this.treatmentCondition;
    }
    public void setTreatmentCondition(String treatmentCondition) {
        this.treatmentCondition = treatmentCondition;
    }
    public String getNeedTransfer() {
        return this.needTransfer;
    }
    public void setNeedTransfer(String needTransfer) {
        this.needTransfer = needTransfer;
    }
    public String getNeedTransferCode() {
        return this.needTransferCode;
    }
    public void setNeedTransferCode(String needTransferCode) {
        this.needTransferCode = needTransferCode;
    }

}
