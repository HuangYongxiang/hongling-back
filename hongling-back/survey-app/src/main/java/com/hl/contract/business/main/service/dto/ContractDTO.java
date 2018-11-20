package com.hl.contract.business.main.service.dto;

import java.util.Date;


public class ContractDTO {

    private String contractNo;//合同号

    private String productCode;

    private String productName;//产品


    private String carOwnerName;//车主姓名
    private String carOwnerSex;//车主性别 1-男 0女

    private String carOwnerTypeCode;//车主证件类型

    private String carOwnerTypeName;

    private String carOwnerCardNo;//车主证件号

    private String carOwnerTelephone;//车主手机
    private String carOwnerEmail;//车主邮箱

    private String carBrand;//车辆品牌

    private String carBrandCode;

    private String carSystem;//车系

    private String carSystemCode;

    private String carModel;//车型

    private String carModelCode;

    private String carAddr;//地址

    private String carPlateNo;//车牌
    private String carVinNo;//车vin

    private String carTelephone;//手机号

    private Double carPurchasePrice;//新车购置价

    private String useTypeCode;

    private String useTypeName;//用途

    private String batteryNo;//电池号
    private String carBussinessDamageInsuranceOrnot;//是否是否投保商业车损险 0-否 1是

    private Date purchaseDate;//购车日期
    private String carOwnerRelationshipCode;//服务人与车主关系

    private String carOwnerRelationshipName;


    private String servicePurchaserSex;

    private String servicePurchaserName;

    private String servicePurchaserCardTypeCode;


    private String servicePurchaserCardTypeName;
    private String servicePurchaserCardNo;

    private String servicePurchaserTelephone;
    private String servicePurchaserEmail;

    private String contractState;//状态



    private Date startTime;//生效日期
    private int timeLength;//年限
    private String policyNo;//保单号
    private String policyOrgName;//承保机构
    private String account;//账号

    private String checkCode;

    private String checkRemark;


    private String storeName;
    private Double paySum;
    private String discountType;//0否 1是
    private String motorNum;//电机编号
    private String yanbaoStartMiles;//起始里程
    private String address;//地址
    private String productType;//产品类型
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.contract_no
     *
     * @return the value of contract.contract_no
     *
     * @mbg.generated
     */
    public String getContractNo() {
        return contractNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.contract_no
     *
     * @param contractNo the value for contract.contract_no
     *
     * @mbg.generated
     */
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.product_code
     *
     * @return the value of contract.product_code
     *
     * @mbg.generated
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.product_code
     *
     * @param productCode the value for contract.product_code
     *
     * @mbg.generated
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.product_name
     *
     * @return the value of contract.product_name
     *
     * @mbg.generated
     */
    public String getProductName() {
        return productName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.product_name
     *
     * @param productName the value for contract.product_name
     *
     * @mbg.generated
     */
    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.car_owner_name
     *
     * @return the value of contract.car_owner_name
     *
     * @mbg.generated
     */
    public String getCarOwnerName() {
        return carOwnerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.car_owner_name
     *
     * @param carOwnerName the value for contract.car_owner_name
     *
     * @mbg.generated
     */
    public void setCarOwnerName(String carOwnerName) {
        this.carOwnerName = carOwnerName == null ? null : carOwnerName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.car_owner_sex
     *
     * @return the value of contract.car_owner_sex
     *
     * @mbg.generated
     */
    public String getCarOwnerSex() {
        return carOwnerSex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.car_owner_sex
     *
     * @param carOwnerSex the value for contract.car_owner_sex
     *
     * @mbg.generated
     */
    public void setCarOwnerSex(String carOwnerSex) {
        this.carOwnerSex = carOwnerSex == null ? null : carOwnerSex.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.car_owner_type_code
     *
     * @return the value of contract.car_owner_type_code
     *
     * @mbg.generated
     */
    public String getCarOwnerTypeCode() {
        return carOwnerTypeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.car_owner_type_code
     *
     * @param carOwnerTypeCode the value for contract.car_owner_type_code
     *
     * @mbg.generated
     */
    public void setCarOwnerTypeCode(String carOwnerTypeCode) {
        this.carOwnerTypeCode = carOwnerTypeCode == null ? null : carOwnerTypeCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.car_owner_type_name
     *
     * @return the value of contract.car_owner_type_name
     *
     * @mbg.generated
     */
    public String getCarOwnerTypeName() {
        return carOwnerTypeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.car_owner_type_name
     *
     * @param carOwnerTypeName the value for contract.car_owner_type_name
     *
     * @mbg.generated
     */
    public void setCarOwnerTypeName(String carOwnerTypeName) {
        this.carOwnerTypeName = carOwnerTypeName == null ? null : carOwnerTypeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.car_owner_card_no
     *
     * @return the value of contract.car_owner_card_no
     *
     * @mbg.generated
     */
    public String getCarOwnerCardNo() {
        return carOwnerCardNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.car_owner_card_no
     *
     * @param carOwnerCardNo the value for contract.car_owner_card_no
     *
     * @mbg.generated
     */
    public void setCarOwnerCardNo(String carOwnerCardNo) {
        this.carOwnerCardNo = carOwnerCardNo == null ? null : carOwnerCardNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.car_owner_telephone
     *
     * @return the value of contract.car_owner_telephone
     *
     * @mbg.generated
     */
    public String getCarOwnerTelephone() {
        return carOwnerTelephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.car_owner_telephone
     *
     * @param carOwnerTelephone the value for contract.car_owner_telephone
     *
     * @mbg.generated
     */
    public void setCarOwnerTelephone(String carOwnerTelephone) {
        this.carOwnerTelephone = carOwnerTelephone == null ? null : carOwnerTelephone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.car_owner_email
     *
     * @return the value of contract.car_owner_email
     *
     * @mbg.generated
     */
    public String getCarOwnerEmail() {
        return carOwnerEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.car_owner_email
     *
     * @param carOwnerEmail the value for contract.car_owner_email
     *
     * @mbg.generated
     */
    public void setCarOwnerEmail(String carOwnerEmail) {
        this.carOwnerEmail = carOwnerEmail == null ? null : carOwnerEmail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.car_brand
     *
     * @return the value of contract.car_brand
     *
     * @mbg.generated
     */
    public String getCarBrand() {
        return carBrand;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.car_brand
     *
     * @param carBrand the value for contract.car_brand
     *
     * @mbg.generated
     */
    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand == null ? null : carBrand.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.car_brand_code
     *
     * @return the value of contract.car_brand_code
     *
     * @mbg.generated
     */
    public String getCarBrandCode() {
        return carBrandCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.car_brand_code
     *
     * @param carBrandCode the value for contract.car_brand_code
     *
     * @mbg.generated
     */
    public void setCarBrandCode(String carBrandCode) {
        this.carBrandCode = carBrandCode == null ? null : carBrandCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.car_system
     *
     * @return the value of contract.car_system
     *
     * @mbg.generated
     */
    public String getCarSystem() {
        return carSystem;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.car_system
     *
     * @param carSystem the value for contract.car_system
     *
     * @mbg.generated
     */
    public void setCarSystem(String carSystem) {
        this.carSystem = carSystem == null ? null : carSystem.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.car_system_code
     *
     * @return the value of contract.car_system_code
     *
     * @mbg.generated
     */
    public String getCarSystemCode() {
        return carSystemCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.car_system_code
     *
     * @param carSystemCode the value for contract.car_system_code
     *
     * @mbg.generated
     */
    public void setCarSystemCode(String carSystemCode) {
        this.carSystemCode = carSystemCode == null ? null : carSystemCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.car_model
     *
     * @return the value of contract.car_model
     *
     * @mbg.generated
     */
    public String getCarModel() {
        return carModel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.car_model
     *
     * @param carModel the value for contract.car_model
     *
     * @mbg.generated
     */
    public void setCarModel(String carModel) {
        this.carModel = carModel == null ? null : carModel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.car_model_code
     *
     * @return the value of contract.car_model_code
     *
     * @mbg.generated
     */
    public String getCarModelCode() {
        return carModelCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.car_model_code
     *
     * @param carModelCode the value for contract.car_model_code
     *
     * @mbg.generated
     */
    public void setCarModelCode(String carModelCode) {
        this.carModelCode = carModelCode == null ? null : carModelCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.car_addr
     *
     * @return the value of contract.car_addr
     *
     * @mbg.generated
     */
    public String getCarAddr() {
        return carAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.car_addr
     *
     * @param carAddr the value for contract.car_addr
     *
     * @mbg.generated
     */
    public void setCarAddr(String carAddr) {
        this.carAddr = carAddr == null ? null : carAddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.car_plate_no
     *
     * @return the value of contract.car_plate_no
     *
     * @mbg.generated
     */
    public String getCarPlateNo() {
        return carPlateNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.car_plate_no
     *
     * @param carPlateNo the value for contract.car_plate_no
     *
     * @mbg.generated
     */
    public void setCarPlateNo(String carPlateNo) {
        this.carPlateNo = carPlateNo == null ? null : carPlateNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.car_telephone
     *
     * @return the value of contract.car_telephone
     *
     * @mbg.generated
     */
    public String getCarTelephone() {
        return carTelephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.car_telephone
     *
     * @param carTelephone the value for contract.car_telephone
     *
     * @mbg.generated
     */
    public void setCarTelephone(String carTelephone) {
        this.carTelephone = carTelephone == null ? null : carTelephone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.car_purchase_price
     *
     * @return the value of contract.car_purchase_price
     *
     * @mbg.generated
     */
    public Double getCarPurchasePrice() {
        return carPurchasePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.car_purchase_price
     *
     * @param carPurchasePrice the value for contract.car_purchase_price
     *
     * @mbg.generated
     */
    public void setCarPurchasePrice(Double carPurchasePrice) {
        this.carPurchasePrice = carPurchasePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.use_type_code
     *
     * @return the value of contract.use_type_code
     *
     * @mbg.generated
     */
    public String getUseTypeCode() {
        return useTypeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.use_type_code
     *
     * @param useTypeCode the value for contract.use_type_code
     *
     * @mbg.generated
     */
    public void setUseTypeCode(String useTypeCode) {
        this.useTypeCode = useTypeCode == null ? null : useTypeCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.use_type_Name
     *
     * @return the value of contract.use_type_Name
     *
     * @mbg.generated
     */
    public String getUseTypeName() {
        return useTypeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.use_type_Name
     *
     * @param useTypeName the value for contract.use_type_Name
     *
     * @mbg.generated
     */
    public void setUseTypeName(String useTypeName) {
        this.useTypeName = useTypeName == null ? null : useTypeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.battery_no
     *
     * @return the value of contract.battery_no
     *
     * @mbg.generated
     */
    public String getBatteryNo() {
        return batteryNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.battery_no
     *
     * @param batteryNo the value for contract.battery_no
     *
     * @mbg.generated
     */
    public void setBatteryNo(String batteryNo) {
        this.batteryNo = batteryNo == null ? null : batteryNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.car_bussiness_damage_insurance_ornot
     *
     * @return the value of contract.car_bussiness_damage_insurance_ornot
     *
     * @mbg.generated
     */
    public String getCarBussinessDamageInsuranceOrnot() {
        return carBussinessDamageInsuranceOrnot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.car_bussiness_damage_insurance_ornot
     *
     * @param carBussinessDamageInsuranceOrnot the value for contract.car_bussiness_damage_insurance_ornot
     *
     * @mbg.generated
     */
    public void setCarBussinessDamageInsuranceOrnot(String carBussinessDamageInsuranceOrnot) {
        this.carBussinessDamageInsuranceOrnot = carBussinessDamageInsuranceOrnot == null ? null : carBussinessDamageInsuranceOrnot.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.purchase_date
     *
     * @return the value of contract.purchase_date
     *
     * @mbg.generated
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.purchase_date
     *
     * @param purchaseDate the value for contract.purchase_date
     *
     * @mbg.generated
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.car_owner_relationship_code
     *
     * @return the value of contract.car_owner_relationship_code
     *
     * @mbg.generated
     */
    public String getCarOwnerRelationshipCode() {
        return carOwnerRelationshipCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.car_owner_relationship_code
     *
     * @param carOwnerRelationshipCode the value for contract.car_owner_relationship_code
     *
     * @mbg.generated
     */
    public void setCarOwnerRelationshipCode(String carOwnerRelationshipCode) {
        this.carOwnerRelationshipCode = carOwnerRelationshipCode == null ? null : carOwnerRelationshipCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.car_owner_relationship_name
     *
     * @return the value of contract.car_owner_relationship_name
     *
     * @mbg.generated
     */
    public String getCarOwnerRelationshipName() {
        return carOwnerRelationshipName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.car_owner_relationship_name
     *
     * @param carOwnerRelationshipName the value for contract.car_owner_relationship_name
     *
     * @mbg.generated
     */
    public void setCarOwnerRelationshipName(String carOwnerRelationshipName) {
        this.carOwnerRelationshipName = carOwnerRelationshipName == null ? null : carOwnerRelationshipName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.service_purchaser_sex
     *
     * @return the value of contract.service_purchaser_sex
     *
     * @mbg.generated
     */
    public String getServicePurchaserSex() {
        return servicePurchaserSex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.service_purchaser_sex
     *
     * @param servicePurchaserSex the value for contract.service_purchaser_sex
     *
     * @mbg.generated
     */
    public void setServicePurchaserSex(String servicePurchaserSex) {
        this.servicePurchaserSex = servicePurchaserSex == null ? null : servicePurchaserSex.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.service_purchaser_name
     *
     * @return the value of contract.service_purchaser_name
     *
     * @mbg.generated
     */
    public String getServicePurchaserName() {
        return servicePurchaserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.service_purchaser_name
     *
     * @param servicePurchaserName the value for contract.service_purchaser_name
     *
     * @mbg.generated
     */
    public void setServicePurchaserName(String servicePurchaserName) {
        this.servicePurchaserName = servicePurchaserName == null ? null : servicePurchaserName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.service_purchaser_card_type_code
     *
     * @return the value of contract.service_purchaser_card_type_code
     *
     * @mbg.generated
     */
    public String getServicePurchaserCardTypeCode() {
        return servicePurchaserCardTypeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.service_purchaser_card_type_code
     *
     * @param servicePurchaserCardTypeCode the value for contract.service_purchaser_card_type_code
     *
     * @mbg.generated
     */
    public void setServicePurchaserCardTypeCode(String servicePurchaserCardTypeCode) {
        this.servicePurchaserCardTypeCode = servicePurchaserCardTypeCode == null ? null : servicePurchaserCardTypeCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.service_purchaser_card_type_name
     *
     * @return the value of contract.service_purchaser_card_type_name
     *
     * @mbg.generated
     */
    public String getServicePurchaserCardTypeName() {
        return servicePurchaserCardTypeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.service_purchaser_card_type_name
     *
     * @param servicePurchaserCardTypeName the value for contract.service_purchaser_card_type_name
     *
     * @mbg.generated
     */
    public void setServicePurchaserCardTypeName(String servicePurchaserCardTypeName) {
        this.servicePurchaserCardTypeName = servicePurchaserCardTypeName == null ? null : servicePurchaserCardTypeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.service_purchaser_card_no
     *
     * @return the value of contract.service_purchaser_card_no
     *
     * @mbg.generated
     */
    public String getServicePurchaserCardNo() {
        return servicePurchaserCardNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.service_purchaser_card_no
     *
     * @param servicePurchaserCardNo the value for contract.service_purchaser_card_no
     *
     * @mbg.generated
     */
    public void setServicePurchaserCardNo(String servicePurchaserCardNo) {
        this.servicePurchaserCardNo = servicePurchaserCardNo == null ? null : servicePurchaserCardNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.service_purchaser_telephone
     *
     * @return the value of contract.service_purchaser_telephone
     *
     * @mbg.generated
     */
    public String getServicePurchaserTelephone() {
        return servicePurchaserTelephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.service_purchaser_telephone
     *
     * @param servicePurchaserTelephone the value for contract.service_purchaser_telephone
     *
     * @mbg.generated
     */
    public void setServicePurchaserTelephone(String servicePurchaserTelephone) {
        this.servicePurchaserTelephone = servicePurchaserTelephone == null ? null : servicePurchaserTelephone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.service_purchaser_email
     *
     * @return the value of contract.service_purchaser_email
     *
     * @mbg.generated
     */
    public String getServicePurchaserEmail() {
        return servicePurchaserEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.service_purchaser_email
     *
     * @param servicePurchaserEmail the value for contract.service_purchaser_email
     *
     * @mbg.generated
     */
    public void setServicePurchaserEmail(String servicePurchaserEmail) {
        this.servicePurchaserEmail = servicePurchaserEmail == null ? null : servicePurchaserEmail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.contract_state
     *
     * @return the value of contract.contract_state
     *
     * @mbg.generated
     */
    public String getContractState() {
        return contractState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.contract_state
     *
     * @param contractState the value for contract.contract_state
     *
     * @mbg.generated
     */
    public void setContractState(String contractState) {
        this.contractState = contractState == null ? null : contractState.trim();
    }

    public String getCarVinNo() {
        return carVinNo;
    }

    public void setCarVinNo(String carVinNo) {
        this.carVinNo = carVinNo;
    }


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(int timeLength) {
        this.timeLength = timeLength;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getPolicyOrgName() {
        return policyOrgName;
    }

    public void setPolicyOrgName(String policyOrgName) {
        this.policyOrgName = policyOrgName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Double getPaySum() {
        return paySum;
    }

    public void setPaySum(Double paySum) {
        this.paySum = paySum;
    }
    public String getDiscountType(){
        return discountType;
    }
    public void setDiscountType(String discountType){
        this.discountType = discountType;
    }

    public String getMotorNum() {
        return motorNum;
    }

    public void setMotorNum(String motorNum) {
        this.motorNum = motorNum;
    }

    public String getYanbaoStartMiles() {
        return yanbaoStartMiles;
    }

    public void setYanbaoStartMiles(String yanbaoStartMiles) {
        this.yanbaoStartMiles = yanbaoStartMiles;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}