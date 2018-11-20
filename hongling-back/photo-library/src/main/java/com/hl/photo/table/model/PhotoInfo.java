package com.hl.photo.table.model;

import com.hl.photo.business.entity.BasePo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * Created by liyu on 2017/1/21.
 */


@Entity
public class PhotoInfo extends BasePo implements Serializable{
    @Transient
    private static final long serialVersionUID = 1L;
    @Id(autoincrement=true)
    private Long id;//	主键
    private String reportCode;//报案号
    private String flowId;
    private String taskType;//定损　人伤　物损
    private String imageName;//影像名称
    private String imagePath;//图片路径
    private String imageDescribe;//影像说明
    private String imageLatitude;
    private String imageLongitude;
    private String imageAddress;
    private String imageTime;//拍照时间
    private String imageUpload;//1：上传   0：未上传  2: 正在上传

    private String signId;//人名 对应的序列id 1 2 3 4.。。
    private String signName;//人名、物名、车牌号
    private String imageType;//影像大类
    private String imageSubtype;//影像子类
    private String uploadEmp;//上传人代码
    private String uploadEmpName;//上传人名称
    private String uploadTime;//上传时间
    private String seedKey;//用来取图片字典表的
    private int eventCode;//离线任务事件编码, 用来绑定离线任务
    @Transient
    private String imageData;//图片上传内容
    @Transient
    private String isChecked ;//是否选中，0否1是

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportCode() {
        return this.reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getFlowId() {
        return this.flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getImageName() {
        return this.imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageDescribe() {
        return this.imageDescribe;
    }

    public void setImageDescribe(String imageDescribe) {
        this.imageDescribe = imageDescribe;
    }

    public String getImageLatitude() {
        return this.imageLatitude;
    }

    public void setImageLatitude(String imageLatitude) {
        this.imageLatitude = imageLatitude;
    }

    public String getImageLongitude() {
        return this.imageLongitude;
    }

    public void setImageLongitude(String imageLongitude) {
        this.imageLongitude = imageLongitude;
    }

    public String getImageAddress() {
        return this.imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getImageTime() {
        return this.imageTime;
    }

    public void setImageTime(String imageTime) {
        this.imageTime = imageTime;
    }

    public String getImageUpload() {
        return this.imageUpload;
    }

    public void setImageUpload(String imageUpload) {
        this.imageUpload = imageUpload;
    }

    public String getSignId() {
        return this.signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public String getSignName() {
        return this.signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getImageType() {
        return this.imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageSubtype() {
        return this.imageSubtype;
    }

    public void setImageSubtype(String imageSubtype) {
        this.imageSubtype = imageSubtype;
    }

    public String getUploadEmp() {
        return this.uploadEmp;
    }

    public void setUploadEmp(String uploadEmp) {
        this.uploadEmp = uploadEmp;
    }

    public String getUploadEmpName() {
        return this.uploadEmpName;
    }

    public void setUploadEmpName(String uploadEmpName) {
        this.uploadEmpName = uploadEmpName;
    }

    public String getUploadTime() {
        return this.uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getSeedKey() {
        return this.seedKey;
    }

    public void setSeedKey(String seedKey) {
        this.seedKey = seedKey;
    }

    public int getEventCode() {
        return this.eventCode;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }

//    @Transient
    private int tag ;  //用于照片上传页面，被选中上传的位置

//    @Generated(hash = 773124449)
    public PhotoInfo(Long id, String reportCode, String flowId, String taskType,
                     String imageName, String imagePath, String imageDescribe,
                     String imageLatitude, String imageLongitude, String imageAddress,
                     String imageTime, String imageUpload, String signId, String signName,
                     String imageType, String imageSubtype, String uploadEmp,
                     String uploadEmpName, String uploadTime, String seedKey,
                     int eventCode) {
        this.id = id;
        this.reportCode = reportCode;
        this.flowId = flowId;
        this.taskType = taskType;
        this.imageName = imageName;
        this.imagePath = imagePath;
        this.imageDescribe = imageDescribe;
        this.imageLatitude = imageLatitude;
        this.imageLongitude = imageLongitude;
        this.imageAddress = imageAddress;
        this.imageTime = imageTime;
        this.imageUpload = imageUpload;
        this.signId = signId;
        this.signName = signName;
        this.imageType = imageType;
        this.imageSubtype = imageSubtype;
        this.uploadEmp = uploadEmp;
        this.uploadEmpName = uploadEmpName;
        this.uploadTime = uploadTime;
        this.seedKey = seedKey;
        this.eventCode = eventCode;
    }

    @Generated(hash = 2143356537)
    public PhotoInfo() {
    }

    @Generated(hash = 1092895263)
    public PhotoInfo(Long id, String reportCode, String flowId, String taskType,
            String imageName, String imagePath, String imageDescribe, String imageLatitude,
            String imageLongitude, String imageAddress, String imageTime, String imageUpload,
            String signId, String signName, String imageType, String imageSubtype,
            String uploadEmp, String uploadEmpName, String uploadTime, String seedKey,
            int eventCode, int tag) {
        this.id = id;
        this.reportCode = reportCode;
        this.flowId = flowId;
        this.taskType = taskType;
        this.imageName = imageName;
        this.imagePath = imagePath;
        this.imageDescribe = imageDescribe;
        this.imageLatitude = imageLatitude;
        this.imageLongitude = imageLongitude;
        this.imageAddress = imageAddress;
        this.imageTime = imageTime;
        this.imageUpload = imageUpload;
        this.signId = signId;
        this.signName = signName;
        this.imageType = imageType;
        this.imageSubtype = imageSubtype;
        this.uploadEmp = uploadEmp;
        this.uploadEmpName = uploadEmpName;
        this.uploadTime = uploadTime;
        this.seedKey = seedKey;
        this.eventCode = eventCode;
        this.tag = tag;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }
}
