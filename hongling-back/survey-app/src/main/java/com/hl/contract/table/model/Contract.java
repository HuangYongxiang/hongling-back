package com.hl.contract.table.model;

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
public class Contract implements Serializable {
    @Transient
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String userId;
    private String evalId;
    private String reportCode;//报案号

    //车主信息
    private String ownerName;//姓名
    private String ownerSex;//性别
    private String ownerCertificateType;//证件类型
    private String ownerCertificateTypeCode;//证件类型编码
    private String ownerCertificateNo;//证件号码
    private String ownerTelePhone;//联系电话
    private String ownerEmail;//邮箱

    //车辆信息
    private String proComCode;//省份代码
    private String proComName;//省份名称
    private String cityComCode;//城市代码
    private String cityComName;//城市名称
    private String countyComCode;//县代码
    private String countyComName;//县名称
    private String region;//地区
    private String plateNo;//号牌号码
    private String brandName;//品牌名称
    private String brandCode;//品牌编码
    private String familyId;//车系ID
    private String familyName;//车系名称
    private String familyCode;//车系编码
    private String modelName;//理赔车型名称
    private String modelCode;//理赔车型编码
    private String carTelePhone;//手机
    private String carEmail;//邮箱
    private Double vehiclePrice;//新车购置价
    private String enrolDate;//初次登记年月
    private String buyCarDate;//购车日期
    private String usePropertyCode;//使用性质编码、车辆用途
    private String usePropertyName;//使用性质编码、车辆用途
    private String batteryNumber;//电池编号
    private String isInsureCommercial;//是否投保商业车损险
    private String commercialCompany;//商业险承保公司
    private String commercialCompanyCode;//保险公司编码
    private String policyNo;//保单号
    private String engineNo;//发动机号
    private String vinNo;//VIN/车架号

    //服务购买人信息
    private String relation;//与车主关系
    private String relationCode;//与车主关系
    private String serviceName;//姓名
    private String serviceSex;//性别
    private String serviceCertificateType;//证件类型
    private String serviceCertificateTypeCode;//证件类型编码
    private String serviceCertificateNo;//证件号码
    private String serviceTelePhone;//联系电话
    private String serviceEmail;//邮箱

    private Integer serialNo;//序号，理赔同步过来
    private String additionalFlag;//新增标记,调度过来的不可以删除，新增的可删除
    private String exemptFlag;//审核标志

    private String productCode;
    private String productName;//产品

    private String termOfValidityDate;//服务有效期
    private String serviceStartDate;//服务起始时间
    private String serviceEndDate;//服务终止时间
    private String mileage;//行驶里程
    private Double amountDamages;//损失金额




    private String carType;//车辆类型 1代表本车 2 代表三者车
    private String carTypeCode;//车辆类型编码 1代表本车 2 代表三者车
    private String vehicleModel;//厂牌型号名称
    private String vehicleModelCode;//厂牌型号编码
    private String plateType;//号牌种类
    private String plateTypeCode;//号牌种类编码
    private String isMainCarDamaged;//本车是否受损

    private String dutyRatio;//责任比例
    private String insurancePolicyNo;//交强险保单号
    private String insuranceCompany;//交强险承保公司

    private String commercialPolicyNo;//商业险保单号
    private int age;//驾驶员年龄
    private String licenseNumber;//驾驶证号码
    private String driveType; //准驾车型
    private String driveTypeCode; //准驾车型编码
    private String driverTerritorial; //驾驶员属地
    private String certificationAuthority;//颁证机关
    private String degreeOfEducation;//文化程度
    private String profession; //行业
    private String issueDate; //初次领证日期
    private String unitOrAddress;// 单位或地址

    //隐藏

    private String vehicleType;//车辆种类
    private String vehicleTypeCode;//车辆种类编码
    private String useProperty;//使用性质（购买、租用、自用）
    private Double realPrice;//实际价值
    private String isImport;//国产/进口
    private String makeDate;//制造年月（出厂年份）
    private int seat;//座位数量
    private String powner;//功率
    private String displacement;//排量
    private Double tonnage;//吨位（核定载质量）
    private String carColor;//车身颜色
    private String plateColor;//车牌颜色
    private String plateColorCode;//车牌颜色编码
    private String guardAlarm;//防盗装置
    private String driverArea;//行驶区域
    private String discountType;//0否 1是
    private String motorNum;//电机编号
    private String yanbaoStartMiles;//起始里程
    private String address;//地址
    private String productType;//产品类型
    private String productTypeCode;//产品类型编码
    @Generated(hash = 1101342572)
    public Contract(String id, String userId, String evalId, String reportCode, String ownerName, String ownerSex, String ownerCertificateType,
            String ownerCertificateTypeCode, String ownerCertificateNo, String ownerTelePhone, String ownerEmail, String proComCode,
            String proComName, String cityComCode, String cityComName, String countyComCode, String countyComName, String region,
            String plateNo, String brandName, String brandCode, String familyId, String familyName, String familyCode, String modelName,
            String modelCode, String carTelePhone, String carEmail, Double vehiclePrice, String enrolDate, String buyCarDate,
            String usePropertyCode, String usePropertyName, String batteryNumber, String isInsureCommercial, String commercialCompany,
            String commercialCompanyCode, String policyNo, String engineNo, String vinNo, String relation, String relationCode,
            String serviceName, String serviceSex, String serviceCertificateType, String serviceCertificateTypeCode,
            String serviceCertificateNo, String serviceTelePhone, String serviceEmail, Integer serialNo, String additionalFlag,
            String exemptFlag, String productCode, String productName, String termOfValidityDate, String serviceStartDate,
            String serviceEndDate, String mileage, Double amountDamages, String carType, String carTypeCode, String vehicleModel,
            String vehicleModelCode, String plateType, String plateTypeCode, String isMainCarDamaged, String dutyRatio,
            String insurancePolicyNo, String insuranceCompany, String commercialPolicyNo, int age, String licenseNumber, String driveType,
            String driveTypeCode, String driverTerritorial, String certificationAuthority, String degreeOfEducation, String profession,
            String issueDate, String unitOrAddress, String vehicleType, String vehicleTypeCode, String useProperty, Double realPrice,
            String isImport, String makeDate, int seat, String powner, String displacement, Double tonnage, String carColor, String plateColor,
            String plateColorCode, String guardAlarm, String driverArea, String discountType, String motorNum, String yanbaoStartMiles,
            String address, String productType, String productTypeCode) {
        this.id = id;
        this.userId = userId;
        this.evalId = evalId;
        this.reportCode = reportCode;
        this.ownerName = ownerName;
        this.ownerSex = ownerSex;
        this.ownerCertificateType = ownerCertificateType;
        this.ownerCertificateTypeCode = ownerCertificateTypeCode;
        this.ownerCertificateNo = ownerCertificateNo;
        this.ownerTelePhone = ownerTelePhone;
        this.ownerEmail = ownerEmail;
        this.proComCode = proComCode;
        this.proComName = proComName;
        this.cityComCode = cityComCode;
        this.cityComName = cityComName;
        this.countyComCode = countyComCode;
        this.countyComName = countyComName;
        this.region = region;
        this.plateNo = plateNo;
        this.brandName = brandName;
        this.brandCode = brandCode;
        this.familyId = familyId;
        this.familyName = familyName;
        this.familyCode = familyCode;
        this.modelName = modelName;
        this.modelCode = modelCode;
        this.carTelePhone = carTelePhone;
        this.carEmail = carEmail;
        this.vehiclePrice = vehiclePrice;
        this.enrolDate = enrolDate;
        this.buyCarDate = buyCarDate;
        this.usePropertyCode = usePropertyCode;
        this.usePropertyName = usePropertyName;
        this.batteryNumber = batteryNumber;
        this.isInsureCommercial = isInsureCommercial;
        this.commercialCompany = commercialCompany;
        this.commercialCompanyCode = commercialCompanyCode;
        this.policyNo = policyNo;
        this.engineNo = engineNo;
        this.vinNo = vinNo;
        this.relation = relation;
        this.relationCode = relationCode;
        this.serviceName = serviceName;
        this.serviceSex = serviceSex;
        this.serviceCertificateType = serviceCertificateType;
        this.serviceCertificateTypeCode = serviceCertificateTypeCode;
        this.serviceCertificateNo = serviceCertificateNo;
        this.serviceTelePhone = serviceTelePhone;
        this.serviceEmail = serviceEmail;
        this.serialNo = serialNo;
        this.additionalFlag = additionalFlag;
        this.exemptFlag = exemptFlag;
        this.productCode = productCode;
        this.productName = productName;
        this.termOfValidityDate = termOfValidityDate;
        this.serviceStartDate = serviceStartDate;
        this.serviceEndDate = serviceEndDate;
        this.mileage = mileage;
        this.amountDamages = amountDamages;
        this.carType = carType;
        this.carTypeCode = carTypeCode;
        this.vehicleModel = vehicleModel;
        this.vehicleModelCode = vehicleModelCode;
        this.plateType = plateType;
        this.plateTypeCode = plateTypeCode;
        this.isMainCarDamaged = isMainCarDamaged;
        this.dutyRatio = dutyRatio;
        this.insurancePolicyNo = insurancePolicyNo;
        this.insuranceCompany = insuranceCompany;
        this.commercialPolicyNo = commercialPolicyNo;
        this.age = age;
        this.licenseNumber = licenseNumber;
        this.driveType = driveType;
        this.driveTypeCode = driveTypeCode;
        this.driverTerritorial = driverTerritorial;
        this.certificationAuthority = certificationAuthority;
        this.degreeOfEducation = degreeOfEducation;
        this.profession = profession;
        this.issueDate = issueDate;
        this.unitOrAddress = unitOrAddress;
        this.vehicleType = vehicleType;
        this.vehicleTypeCode = vehicleTypeCode;
        this.useProperty = useProperty;
        this.realPrice = realPrice;
        this.isImport = isImport;
        this.makeDate = makeDate;
        this.seat = seat;
        this.powner = powner;
        this.displacement = displacement;
        this.tonnage = tonnage;
        this.carColor = carColor;
        this.plateColor = plateColor;
        this.plateColorCode = plateColorCode;
        this.guardAlarm = guardAlarm;
        this.driverArea = driverArea;
        this.discountType = discountType;
        this.motorNum = motorNum;
        this.yanbaoStartMiles = yanbaoStartMiles;
        this.address = address;
        this.productType = productType;
        this.productTypeCode = productTypeCode;
    }

    @Generated(hash = 1343858295)
    public Contract() {
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
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
    public String getOwnerName() {
        return this.ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public String getOwnerSex() {
        return this.ownerSex;
    }
    public void setOwnerSex(String ownerSex) {
        this.ownerSex = ownerSex;
    }
    public String getOwnerCertificateType() {
        return this.ownerCertificateType;
    }
    public void setOwnerCertificateType(String ownerCertificateType) {
        this.ownerCertificateType = ownerCertificateType;
    }
    public String getOwnerCertificateTypeCode() {
        return this.ownerCertificateTypeCode;
    }
    public void setOwnerCertificateTypeCode(String ownerCertificateTypeCode) {
        this.ownerCertificateTypeCode = ownerCertificateTypeCode;
    }
    public String getOwnerCertificateNo() {
        return this.ownerCertificateNo;
    }
    public void setOwnerCertificateNo(String ownerCertificateNo) {
        this.ownerCertificateNo = ownerCertificateNo;
    }
    public String getOwnerTelePhone() {
        return this.ownerTelePhone;
    }
    public void setOwnerTelePhone(String ownerTelePhone) {
        this.ownerTelePhone = ownerTelePhone;
    }
    public String getOwnerEmail() {
        return this.ownerEmail;
    }
    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
    public String getProComCode() {
        return this.proComCode;
    }
    public void setProComCode(String proComCode) {
        this.proComCode = proComCode;
    }
    public String getProComName() {
        return this.proComName;
    }
    public void setProComName(String proComName) {
        this.proComName = proComName;
    }
    public String getCityComCode() {
        return this.cityComCode;
    }
    public void setCityComCode(String cityComCode) {
        this.cityComCode = cityComCode;
    }
    public String getCityComName() {
        return this.cityComName;
    }
    public void setCityComName(String cityComName) {
        this.cityComName = cityComName;
    }
    public String getCountyComCode() {
        return this.countyComCode;
    }
    public void setCountyComCode(String countyComCode) {
        this.countyComCode = countyComCode;
    }
    public String getCountyComName() {
        return this.countyComName;
    }
    public void setCountyComName(String countyComName) {
        this.countyComName = countyComName;
    }
    public String getRegion() {
        return this.region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getPlateNo() {
        return this.plateNo;
    }
    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }
    public String getBrandName() {
        return this.brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public String getBrandCode() {
        return this.brandCode;
    }
    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }
    public String getFamilyId() {
        return this.familyId;
    }
    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }
    public String getFamilyName() {
        return this.familyName;
    }
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
    public String getFamilyCode() {
        return this.familyCode;
    }
    public void setFamilyCode(String familyCode) {
        this.familyCode = familyCode;
    }
    public String getModelName() {
        return this.modelName;
    }
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    public String getModelCode() {
        return this.modelCode;
    }
    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }
    public String getCarTelePhone() {
        return this.carTelePhone;
    }
    public void setCarTelePhone(String carTelePhone) {
        this.carTelePhone = carTelePhone;
    }
    public String getCarEmail() {
        return this.carEmail;
    }
    public void setCarEmail(String carEmail) {
        this.carEmail = carEmail;
    }
    public Double getVehiclePrice() {
        return this.vehiclePrice;
    }
    public void setVehiclePrice(Double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }
    public String getEnrolDate() {
        return this.enrolDate;
    }
    public void setEnrolDate(String enrolDate) {
        this.enrolDate = enrolDate;
    }
    public String getBuyCarDate() {
        return this.buyCarDate;
    }
    public void setBuyCarDate(String buyCarDate) {
        this.buyCarDate = buyCarDate;
    }
    public String getUsePropertyCode() {
        return this.usePropertyCode;
    }
    public void setUsePropertyCode(String usePropertyCode) {
        this.usePropertyCode = usePropertyCode;
    }
    public String getUsePropertyName() {
        return this.usePropertyName;
    }
    public void setUsePropertyName(String usePropertyName) {
        this.usePropertyName = usePropertyName;
    }
    public String getBatteryNumber() {
        return this.batteryNumber;
    }
    public void setBatteryNumber(String batteryNumber) {
        this.batteryNumber = batteryNumber;
    }
    public String getIsInsureCommercial() {
        return this.isInsureCommercial;
    }
    public void setIsInsureCommercial(String isInsureCommercial) {
        this.isInsureCommercial = isInsureCommercial;
    }
    public String getCommercialCompany() {
        return this.commercialCompany;
    }
    public void setCommercialCompany(String commercialCompany) {
        this.commercialCompany = commercialCompany;
    }
    public String getCommercialCompanyCode() {
        return this.commercialCompanyCode;
    }
    public void setCommercialCompanyCode(String commercialCompanyCode) {
        this.commercialCompanyCode = commercialCompanyCode;
    }
    public String getPolicyNo() {
        return this.policyNo;
    }
    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
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
    public String getRelation() {
        return this.relation;
    }
    public void setRelation(String relation) {
        this.relation = relation;
    }
    public String getRelationCode() {
        return this.relationCode;
    }
    public void setRelationCode(String relationCode) {
        this.relationCode = relationCode;
    }
    public String getServiceName() {
        return this.serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public String getServiceSex() {
        return this.serviceSex;
    }
    public void setServiceSex(String serviceSex) {
        this.serviceSex = serviceSex;
    }
    public String getServiceCertificateType() {
        return this.serviceCertificateType;
    }
    public void setServiceCertificateType(String serviceCertificateType) {
        this.serviceCertificateType = serviceCertificateType;
    }
    public String getServiceCertificateTypeCode() {
        return this.serviceCertificateTypeCode;
    }
    public void setServiceCertificateTypeCode(String serviceCertificateTypeCode) {
        this.serviceCertificateTypeCode = serviceCertificateTypeCode;
    }
    public String getServiceCertificateNo() {
        return this.serviceCertificateNo;
    }
    public void setServiceCertificateNo(String serviceCertificateNo) {
        this.serviceCertificateNo = serviceCertificateNo;
    }
    public String getServiceTelePhone() {
        return this.serviceTelePhone;
    }
    public void setServiceTelePhone(String serviceTelePhone) {
        this.serviceTelePhone = serviceTelePhone;
    }
    public String getServiceEmail() {
        return this.serviceEmail;
    }
    public void setServiceEmail(String serviceEmail) {
        this.serviceEmail = serviceEmail;
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
    public String getExemptFlag() {
        return this.exemptFlag;
    }
    public void setExemptFlag(String exemptFlag) {
        this.exemptFlag = exemptFlag;
    }
    public String getProductCode() {
        return this.productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public String getProductName() {
        return this.productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getTermOfValidityDate() {
        return this.termOfValidityDate;
    }
    public void setTermOfValidityDate(String termOfValidityDate) {
        this.termOfValidityDate = termOfValidityDate;
    }
    public String getServiceStartDate() {
        return this.serviceStartDate;
    }
    public void setServiceStartDate(String serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }
    public String getServiceEndDate() {
        return this.serviceEndDate;
    }
    public void setServiceEndDate(String serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }
    public String getMileage() {
        return this.mileage;
    }
    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
    public Double getAmountDamages() {
        return this.amountDamages;
    }
    public void setAmountDamages(Double amountDamages) {
        this.amountDamages = amountDamages;
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
    public String getVehicleModelCode() {
        return this.vehicleModelCode;
    }
    public void setVehicleModelCode(String vehicleModelCode) {
        this.vehicleModelCode = vehicleModelCode;
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
    public String getIsMainCarDamaged() {
        return this.isMainCarDamaged;
    }
    public void setIsMainCarDamaged(String isMainCarDamaged) {
        this.isMainCarDamaged = isMainCarDamaged;
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
    public String getVehicleType() {
        return this.vehicleType;
    }
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    public String getVehicleTypeCode() {
        return this.vehicleTypeCode;
    }
    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }
    public String getUseProperty() {
        return this.useProperty;
    }
    public void setUseProperty(String useProperty) {
        this.useProperty = useProperty;
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
    public int getSeat() {
        return this.seat;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }
    public String getPowner() {
        return this.powner;
    }
    public void setPowner(String powner) {
        this.powner = powner;
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
    public String getPlateColorCode() {
        return this.plateColorCode;
    }
    public void setPlateColorCode(String plateColorCode) {
        this.plateColorCode = plateColorCode;
    }
    public String getGuardAlarm() {
        return this.guardAlarm;
    }
    public void setGuardAlarm(String guardAlarm) {
        this.guardAlarm = guardAlarm;
    }
    public String getDriverArea() {
        return this.driverArea;
    }
    public void setDriverArea(String driverArea) {
        this.driverArea = driverArea;
    }

    public String getDiscountType(){
        return this.discountType;
    }
    public void setDiscountType(String discountType){
        this.discountType = discountType;
    }

    public String getMotorNum() {
        return this.motorNum;
    }

    public void setMotorNum(String motorNum) {
        this.motorNum = motorNum;
    }

    public String getYanbaoStartMiles() {
        return this.yanbaoStartMiles;
    }

    public void setYanbaoStartMiles(String yanbaoStartMiles) {
        this.yanbaoStartMiles = yanbaoStartMiles;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProductType() {
        return this.productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductTypeCode() {
        return this.productTypeCode;
    }

    public void setProductTypeCode(String productTypeCode) {
        this.productTypeCode = productTypeCode;
    }
}