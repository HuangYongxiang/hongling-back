package com.hl.contract.table.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @Describe: 联系记录
 * @Package: com.hl.contract.table.model
 * @Author: liyu
 * @Date: 2018/3/27 16:27
 * @Copyright: hl
 */
@Entity
public class CallRecord {
    @Id
    private String id;
    private String reportNo;//报案号
    private String contactPerson;//联系人
    private String contactTelephone;//联系电话
    private String contactPlace;//联系地点
    private String contactTime;//联系时间
    private String contactLong;//联系时长
    private String contactLongitude;//联系经度
    private String contactLatitude;//联系纬度
    @Generated(hash = 880851846)
    public CallRecord(String id, String reportNo, String contactPerson,
            String contactTelephone, String contactPlace, String contactTime,
            String contactLong, String contactLongitude, String contactLatitude) {
        this.id = id;
        this.reportNo = reportNo;
        this.contactPerson = contactPerson;
        this.contactTelephone = contactTelephone;
        this.contactPlace = contactPlace;
        this.contactTime = contactTime;
        this.contactLong = contactLong;
        this.contactLongitude = contactLongitude;
        this.contactLatitude = contactLatitude;
    }
    @Generated(hash = 1744672525)
    public CallRecord() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getReportNo() {
        return this.reportNo;
    }
    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }
    public String getContactPerson() {
        return this.contactPerson;
    }
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    public String getContactTelephone() {
        return this.contactTelephone;
    }
    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }
    public String getContactPlace() {
        return this.contactPlace;
    }
    public void setContactPlace(String contactPlace) {
        this.contactPlace = contactPlace;
    }
    public String getContactTime() {
        return this.contactTime;
    }
    public void setContactTime(String contactTime) {
        this.contactTime = contactTime;
    }
    public String getContactLong() {
        return this.contactLong;
    }
    public void setContactLong(String contactLong) {
        this.contactLong = contactLong;
    }
    public String getContactLongitude() {
        return this.contactLongitude;
    }
    public void setContactLongitude(String contactLongitude) {
        this.contactLongitude = contactLongitude;
    }
    public String getContactLatitude() {
        return this.contactLatitude;
    }
    public void setContactLatitude(String contactLatitude) {
        this.contactLatitude = contactLatitude;
    }

}
