package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @Describe:  进入定损传报案、保单的数据的情况使用：承保标的车
 * @Author: liyu
 * @Date: 2018/1/13 13:08
 * @Copyright: hl
 */
@Entity
public class InsuranceCar {
    @Id
    private String id;
    private String flowId;
    private String evalId;
    private String reportCode;//报案号
    private Double realPrice;//实际价值
    private Double vehiclePrice;//新车购置价
    private String isImport;//国产/进口
    private String vehicleType;//车辆种类代码
    private String vehicleTypeName;//车辆种类名称
    private String carColor;//车身颜色代码
    private String CarColorName;//车身颜色名称
    private String enrolDate;//初次登记年月
    private String useProperty;//使用性质
    private String driverArea;//行驶区域
    private Long seat;//座位
    private String vinNo;//车架号（VIN码）
    private String engineNo;//发动机号
    private String vehicleModel;//车辆厂牌型号
    private String plateNum;//车牌号码
    private Double power;//功率
    private String displacement;//排量
    private Double tonnage;//吨位
    private String plateColor;//车牌颜色
    private String createTime;//创建时间
    private String makeDate;//制造年月
    private String guardAlarm;//防盗装置
    private String exemptFlag;//免验标志
    private String belongProperty;//所属性质
    @Generated(hash = 944187575)
    public InsuranceCar(String id, String flowId, String evalId, String reportCode,
            Double realPrice, Double vehiclePrice, String isImport,
            String vehicleType, String vehicleTypeName, String carColor,
            String CarColorName, String enrolDate, String useProperty,
            String driverArea, Long seat, String vinNo, String engineNo,
            String vehicleModel, String plateNum, Double power, String displacement,
            Double tonnage, String plateColor, String createTime, String makeDate,
            String guardAlarm, String exemptFlag, String belongProperty) {
        this.id = id;
        this.flowId = flowId;
        this.evalId = evalId;
        this.reportCode = reportCode;
        this.realPrice = realPrice;
        this.vehiclePrice = vehiclePrice;
        this.isImport = isImport;
        this.vehicleType = vehicleType;
        this.vehicleTypeName = vehicleTypeName;
        this.carColor = carColor;
        this.CarColorName = CarColorName;
        this.enrolDate = enrolDate;
        this.useProperty = useProperty;
        this.driverArea = driverArea;
        this.seat = seat;
        this.vinNo = vinNo;
        this.engineNo = engineNo;
        this.vehicleModel = vehicleModel;
        this.plateNum = plateNum;
        this.power = power;
        this.displacement = displacement;
        this.tonnage = tonnage;
        this.plateColor = plateColor;
        this.createTime = createTime;
        this.makeDate = makeDate;
        this.guardAlarm = guardAlarm;
        this.exemptFlag = exemptFlag;
        this.belongProperty = belongProperty;
    }
    @Generated(hash = 624981271)
    public InsuranceCar() {
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
    public Double getRealPrice() {
        return this.realPrice;
    }
    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }
    public Double getVehiclePrice() {
        return this.vehiclePrice;
    }
    public void setVehiclePrice(Double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }
    public String getIsImport() {
        return this.isImport;
    }
    public void setIsImport(String isImport) {
        this.isImport = isImport;
    }
    public String getVehicleType() {
        return this.vehicleType;
    }
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    public String getVehicleTypeName() {
        return this.vehicleTypeName;
    }
    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }
    public String getCarColor() {
        return this.carColor;
    }
    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }
    public String getCarColorName() {
        return this.CarColorName;
    }
    public void setCarColorName(String CarColorName) {
        this.CarColorName = CarColorName;
    }
    public String getEnrolDate() {
        return this.enrolDate;
    }
    public void setEnrolDate(String enrolDate) {
        this.enrolDate = enrolDate;
    }
    public String getUseProperty() {
        return this.useProperty;
    }
    public void setUseProperty(String useProperty) {
        this.useProperty = useProperty;
    }
    public String getDriverArea() {
        return this.driverArea;
    }
    public void setDriverArea(String driverArea) {
        this.driverArea = driverArea;
    }
    public Long getSeat() {
        return this.seat;
    }
    public void setSeat(Long seat) {
        this.seat = seat;
    }
    public String getVinNo() {
        return this.vinNo;
    }
    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }
    public String getEngineNo() {
        return this.engineNo;
    }
    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }
    public String getVehicleModel() {
        return this.vehicleModel;
    }
    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }
    public String getPlateNum() {
        return this.plateNum;
    }
    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }
    public Double getPower() {
        return this.power;
    }
    public void setPower(Double power) {
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
    public String getPlateColor() {
        return this.plateColor;
    }
    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getMakeDate() {
        return this.makeDate;
    }
    public void setMakeDate(String makeDate) {
        this.makeDate = makeDate;
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
    public String getBelongProperty() {
        return this.belongProperty;
    }
    public void setBelongProperty(String belongProperty) {
        this.belongProperty = belongProperty;
    }
}
