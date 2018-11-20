package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @Describe:  险别
 * @Author: liyu
 * @Date: 2018/1/8 14:00
 * @Copyright: hl
 */

@Entity
public class InsuranceItem {
    @Id
    private String id;
    private String lossNo;//对应公估系统查勘定损编号
    private String insureTerm;//险别
    private String insureTermCode;//险别代码
    @Generated(hash = 1999962118)
    public InsuranceItem(String id, String lossNo, String insureTerm,
                         String insureTermCode) {
        this.id = id;
        this.lossNo = lossNo;
        this.insureTerm = insureTerm;
        this.insureTermCode = insureTermCode;
    }
    @Generated(hash = 1696046739)
    public InsuranceItem() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getLossNo() {
        return this.lossNo;
    }
    public void setLossNo(String lossNo) {
        this.lossNo = lossNo;
    }
    public String getInsureTerm() {
        return this.insureTerm;
    }
    public void setInsureTerm(String insureTerm) {
        this.insureTerm = insureTerm;
    }
    public String getInsureTermCode() {
        return this.insureTermCode;
    }
    public void setInsureTermCode(String insureTermCode) {
        this.insureTermCode = insureTermCode;
    }
}
