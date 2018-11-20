package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * Created by wxl on 2017/8/22.
 *
 *   涉案车辆损失部位数据库表
 */
@Entity
public class ReportCarLossPart implements Serializable {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    private String id;  //数据库主键
    private String exrId;  //关联的涉案车辆数据库主键
    private String reportCode;//报案号
    private String plateNo;  //车牌号
    private String riskyType;  //险别名称
    private String riskyTypeCode;  //险别编码
    private String lossPart;   //损失部位
    private String lossPartCode;   //损失部位
    private String itemCode;//项目编码
    private String itemName;  //项目名称
    private String feeName;   //费用名称
    private String expactFee;  //预估费用
    private String lossDegreeDesc;   //损失程度描述
    private Integer serialNo;//序号
    @Generated(hash = 1260854706)
    public ReportCarLossPart(String id, String exrId, String reportCode,
                             String plateNo, String riskyType, String riskyTypeCode, String lossPart,
                             String lossPartCode, String itemCode, String itemName, String feeName,
                             String expactFee, String lossDegreeDesc, Integer serialNo) {
        this.id = id;
        this.exrId = exrId;
        this.reportCode = reportCode;
        this.plateNo = plateNo;
        this.riskyType = riskyType;
        this.riskyTypeCode = riskyTypeCode;
        this.lossPart = lossPart;
        this.lossPartCode = lossPartCode;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.feeName = feeName;
        this.expactFee = expactFee;
        this.lossDegreeDesc = lossDegreeDesc;
        this.serialNo = serialNo;
    }
    @Generated(hash = 2138095511)
    public ReportCarLossPart() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getExrId() {
        return this.exrId;
    }
    public void setExrId(String exrId) {
        this.exrId = exrId;
    }
    public String getReportCode() {
        return this.reportCode;
    }
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }
    public String getPlateNo() {
        return this.plateNo;
    }
    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }
    public String getRiskyType() {
        return this.riskyType;
    }
    public void setRiskyType(String riskyType) {
        this.riskyType = riskyType;
    }
    public String getRiskyTypeCode() {
        return this.riskyTypeCode;
    }
    public void setRiskyTypeCode(String riskyTypeCode) {
        this.riskyTypeCode = riskyTypeCode;
    }
    public String getLossPart() {
        return this.lossPart;
    }
    public void setLossPart(String lossPart) {
        this.lossPart = lossPart;
    }
    public String getLossPartCode() {
        return this.lossPartCode;
    }
    public void setLossPartCode(String lossPartCode) {
        this.lossPartCode = lossPartCode;
    }
    public String getItemName() {
        return this.itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getFeeName() {
        return this.feeName;
    }
    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }
    public String getExpactFee() {
        return this.expactFee;
    }
    public void setExpactFee(String expactFee) {
        this.expactFee = expactFee;
    }
    public String getLossDegreeDesc() {
        return this.lossDegreeDesc;
    }
    public void setLossDegreeDesc(String lossDegreeDesc) {
        this.lossDegreeDesc = lossDegreeDesc;
    }
    public String getItemCode() {
        return this.itemCode;
    }
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
    public Integer getSerialNo() {
        return this.serialNo;
    }
    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    

 }
