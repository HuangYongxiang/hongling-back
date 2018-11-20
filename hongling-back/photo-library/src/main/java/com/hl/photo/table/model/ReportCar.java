package com.hl.photo.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;
import java.util.List;

/**
 * Create data： 2017/6/22.
 * Author: liyu
 * Function:报案涉车车辆：列表（多条）
 */
@Entity
public class ReportCar implements Serializable {
    @Transient
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String flowId;
    private String evalId;
    private String reportCode;//报案号

    private String carType;//车辆类型 1代表本车 2 代表三者车
    private String carTypeCode;//车辆类型编码 1代表本车 2 代表三者车
    private String vehicleModel;//厂牌型号名称
    private String vehicleModelCode;//厂牌型号编码
    private String engineNo;//发动机号
    private String vinNo;//VIN/车架号
    private String plateNo;//号牌号码
    private String plateType;//号牌种类
    private String plateTypeCode;//号牌种类编码
    private String isMainCarDamaged;//本车是否受损


    private Double amountDamages;//损失金额
    private String dutyRatio;//责任比例
    private String insurancePolicyNo;//交强险保单号
    private String insuranceCompany;//交强险承保公司
    private String commercialPolicyNo;//商业险保单号
    private String commercialCompany;//商业险承保公司

    //隐藏

    private String vehicleType;//车辆种类
    private String vehicleTypeCode;//车辆种类编码
    private String useProperty;//使用性质（购买、租用、自用）
    private String usePropertyCode;//使用性质编码
    private Double vehiclePrice;//新车购置价
    private Double realPrice;//实际价值
    private String isImport;//国产/进口
    private String makeDate;//制造年月（出厂年份）
    private String enrolDate;//初次登记年月
    private int seat;//座位数量
    private String power;//功率
    private String displacement;//排量
    private Double tonnage;//吨位（核定载质量）
    private String carColor;//车身颜色
    private String plateColor;//车牌颜色
    private String plateColorCode;//车牌颜色编码
    private String guardAlarm;//防盗装置
    private String exemptFlag;//免验标志
    private String driverArea;//行驶区域
    private Integer serialNo;//序号，理赔同步过来
    private String additionalFlag;//新增标记,调度过来的不可以删除，新增的可删除

    @Transient
    private List<ReportCarLossPart> reportCarLossPartList;

    public List<ReportCarLossPart> getReportCarLossPartList() {
        return reportCarLossPartList;
    }

    public void setReportCarLossPartList(List<ReportCarLossPart> reportCarLossPartList) {
        this.reportCarLossPartList = reportCarLossPartList;
    }

    @Generated(hash = 1363856523)
    public ReportCar(String id, String flowId, String evalId, String reportCode,
                     String carType, String carTypeCode, String vehicleModel, String vehicleModelCode,
                     String engineNo, String vinNo, String plateNo, String plateType,
                     String plateTypeCode, String isMainCarDamaged, Double amountDamages,
                     String dutyRatio, String insurancePolicyNo, String insuranceCompany,
                     String commercialPolicyNo, String commercialCompany, String vehicleType,
                     String vehicleTypeCode, String useProperty, String usePropertyCode,
                     Double vehiclePrice, Double realPrice, String isImport, String makeDate,
                     String enrolDate, int seat, String power, String displacement, Double tonnage,
                     String carColor, String plateColor, String plateColorCode, String guardAlarm,
                     String exemptFlag, String driverArea, Integer serialNo, String additionalFlag) {
        this.id = id;
        this.flowId = flowId;
        this.evalId = evalId;
        this.reportCode = reportCode;
        this.carType = carType;
        this.carTypeCode = carTypeCode;
        this.vehicleModel = vehicleModel;
        this.vehicleModelCode = vehicleModelCode;
        this.engineNo = engineNo;
        this.vinNo = vinNo;
        this.plateNo = plateNo;
        this.plateType = plateType;
        this.plateTypeCode = plateTypeCode;
        this.isMainCarDamaged = isMainCarDamaged;
        this.amountDamages = amountDamages;
        this.dutyRatio = dutyRatio;
        this.insurancePolicyNo = insurancePolicyNo;
        this.insuranceCompany = insuranceCompany;
        this.commercialPolicyNo = commercialPolicyNo;
        this.commercialCompany = commercialCompany;
        this.vehicleType = vehicleType;
        this.vehicleTypeCode = vehicleTypeCode;
        this.useProperty = useProperty;
        this.usePropertyCode = usePropertyCode;
        this.vehiclePrice = vehiclePrice;
        this.realPrice = realPrice;
        this.isImport = isImport;
        this.makeDate = makeDate;
        this.enrolDate = enrolDate;
        this.seat = seat;
        this.power = power;
        this.displacement = displacement;
        this.tonnage = tonnage;
        this.carColor = carColor;
        this.plateColor = plateColor;
        this.plateColorCode = plateColorCode;
        this.guardAlarm = guardAlarm;
        this.exemptFlag = exemptFlag;
        this.driverArea = driverArea;
        this.serialNo = serialNo;
        this.additionalFlag = additionalFlag;
    }

    @Generated(hash = 1023779573)
    public ReportCar() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFlowId() {
        return this.flowId;
    }
    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }
    public String getEvalId() {
        return this.evalId;
    }
    public void setEvalId(String evalId) {
        this.evalId = evalId;
    }
    public String getReportCode() {
        return this.reportCode;
    }
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }
    public String getCarType() {
        return this.carType;
    }
    public void setCarType(String carType) {
        this.carType = carType;
    }
    public String getCarTypeCode() {
        return this.carTypeCode;
    }
    public void setCarTypeCode(String carTypeCode) {
        this.carTypeCode = carTypeCode;
    }
    public String getVehicleModel() {
        return this.vehicleModel;
    }
    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }
    public String getEngineNo() {
        return this.engineNo;
    }
    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }
    public String getVinNo() {
        return this.vinNo;
    }
    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }
    public String getPlateNo() {
        return this.plateNo;
    }
    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }
    public String getPlateType() {
        return this.plateType;
    }
    public void setPlateType(String plateType) {
        this.plateType = plateType;
    }
    public String getPlateTypeCode() {
        return this.plateTypeCode;
    }
    public void setPlateTypeCode(String plateTypeCode) {
        this.plateTypeCode = plateTypeCode;
    }
    public Double getAmountDamages() {
        return this.amountDamages;
    }
    public void setAmountDamages(Double amountDamages) {
        this.amountDamages = amountDamages;
    }
    public String getDutyRatio() {
        return this.dutyRatio;
    }
    public void setDutyRatio(String dutyRatio) {
        this.dutyRatio = dutyRatio;
    }
    public String getInsurancePolicyNo() {
        return this.insurancePolicyNo;
    }
    public void setInsurancePolicyNo(String insurancePolicyNo) {
        this.insurancePolicyNo = insurancePolicyNo;
    }
    public String getInsuranceCompany() {
        return this.insuranceCompany;
    }
    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }
    public String getCommercialPolicyNo() {
        return this.commercialPolicyNo;
    }
    public void setCommercialPolicyNo(String commercialPolicyNo) {
        this.commercialPolicyNo = commercialPolicyNo;
    }
    public String getCommercialCompany() {
        return this.commercialCompany;
    }
    public void setCommercialCompany(String commercialCompany) {
        this.commercialCompany = commercialCompany;
    }
    public String getVehicleType() {
        return this.vehicleType;
    }
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    public String getUseProperty() {
        return this.useProperty;
    }
    public void setUseProperty(String useProperty) {
        this.useProperty = useProperty;
    }
    public Double getVehiclePrice() {
        return this.vehiclePrice;
    }
    public void setVehiclePrice(Double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }
    public Double getRealPrice() {
        return this.realPrice;
    }
    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }
    public String getIsImport() {
        return this.isImport;
    }
    public void setIsImport(String isImport) {
        this.isImport = isImport;
    }
    public String getMakeDate() {
        return this.makeDate;
    }
    public void setMakeDate(String makeDate) {
        this.makeDate = makeDate;
    }
    public String getEnrolDate() {
        return this.enrolDate;
    }
    public void setEnrolDate(String enrolDate) {
        this.enrolDate = enrolDate;
    }
    public int getSeat() {
        return this.seat;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }
    public String getPower() {
        return this.power;
    }
    public void setPower(String power) {
        this.power = power;
    }
    public String getDisplacement() {
        return this.displacement;
    }
    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }
    public Double getTonnage() {
        return this.tonnage;
    }
    public void setTonnage(Double tonnage) {
        this.tonnage = tonnage;
    }
    public String getCarColor() {
        return this.carColor;
    }
    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }
    public String getPlateColor() {
        return this.plateColor;
    }
    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
    }
    public String getGuardAlarm() {
        return this.guardAlarm;
    }
    public void setGuardAlarm(String guardAlarm) {
        this.guardAlarm = guardAlarm;
    }
    public String getExemptFlag() {
        return this.exemptFlag;
    }
    public void setExemptFlag(String exemptFlag) {
        this.exemptFlag = exemptFlag;
    }
    public String getDriverArea() {
        return this.driverArea;
    }
    public void setDriverArea(String driverArea) {
        this.driverArea = driverArea;
    }
    public String getIsMainCarDamaged() {
        return this.isMainCarDamaged;
    }
    public void setIsMainCarDamaged(String isMainCarDamaged) {
        this.isMainCarDamaged = isMainCarDamaged;
    }
    public String getVehicleTypeCode() {
        return this.vehicleTypeCode;
    }
    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }
    public String getUsePropertyCode() {
        return this.usePropertyCode;
    }
    public void setUsePropertyCode(String usePropertyCode) {
        this.usePropertyCode = usePropertyCode;
    }
    public String getPlateColorCode() {
        return this.plateColorCode;
    }
    public void setPlateColorCode(String plateColorCode) {
        this.plateColorCode = plateColorCode;
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

    public String getVehicleModelCode() {
        return this.vehicleModelCode;
    }

    public void setVehicleModelCode(String vehicleModelCode) {
        this.vehicleModelCode = vehicleModelCode;
    }

}