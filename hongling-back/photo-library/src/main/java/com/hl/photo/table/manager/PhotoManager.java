package com.hl.photo.table.manager;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.hl.photo.business.photo.PhotoListener;
import com.hl.photo.table.PhotoGreenDaoHelper;
import com.hl.photo.table.dao.DaoSession;
import com.hl.photo.table.dao.PhotoInfoDao;
import com.hl.photo.table.model.PhotoInfo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by liyu on 2017/1/5.
 */

public class PhotoManager {

    private static PhotoManager instance;
    private Context mContext;
    private Set<PhotoListener> listeners;
    private Handler mHandler;
    private PhotoInfoDao photoInfoDao;

    public static PhotoManager getInstance() {
        if (instance == null) {
            synchronized (PhotoManager.class){
                if (instance == null) {
                    Context context = null;
                    instance = new PhotoManager(context);
                }
            }
        }
        return instance;
    }

    private PhotoManager(Context context) {
        mContext = context;
        listeners = new HashSet<>();
        mHandler = new Handler(Looper.getMainLooper());
        DaoSession daoSession = PhotoGreenDaoHelper.getInstance().getDaoSession(context);
        photoInfoDao = daoSession.getPhotoInfoDao();
    }


    public void registerFactoryListener(PhotoListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public void unregisterFactoryListener(PhotoListener l) {
        if(listeners.contains(l)){
            listeners.remove(l);
        }
    }
    public List<PhotoInfo> getPhotoListByReportCode(String reportNo){
        List<PhotoInfo> list = photoInfoDao.queryBuilder()
                .where(PhotoInfoDao.Properties.ReportCode.eq(reportNo)).list();
        return list;
    }

    /**
     * 读取未上传的图片
     * @param reportNo
     * @return
     */
    public List<PhotoInfo> getUnUploadPhotoListByReportCode(String reportNo){
        List<PhotoInfo> list = photoInfoDao.queryBuilder()
                .where(PhotoInfoDao.Properties.ReportCode.eq(reportNo),PhotoInfoDao.Properties.ImageUpload.eq("0")).list();
        return list;
    }
    public List<PhotoInfo> getPhotoListByReportCodeAndTaskType(PhotoInfo photoInfo){
        List<PhotoInfo> list = photoInfoDao.queryBuilder()
                .where(PhotoInfoDao.Properties.ReportCode.eq(photoInfo.getReportCode()),
//                        PhotoInfoDao.Properties.FlowId.eq(photoInfo.getFlowId()),
                        PhotoInfoDao.Properties.TaskType.eq(photoInfo.getTaskType())).list();
        return list;
    }
    public List<PhotoInfo> getPhotoListByReportCodeAndUpload(PhotoInfo photoInfo){
        List<PhotoInfo> list = photoInfoDao.queryBuilder()
                .where(PhotoInfoDao.Properties.ReportCode.eq(photoInfo.getReportCode()),
//                        PhotoInfoDao.Properties.FlowId.eq(photoInfo.getFlowId()),
                        PhotoInfoDao.Properties.ImageUpload.eq("0")).list();
        return list;
    }

    public long savePhotoInfoData( PhotoInfo photoInfo){
        return photoInfoDao.insert(photoInfo);
    }
    public void savePhotoInfoAllData( List<PhotoInfo> photoInfos){
        photoInfoDao.insertInTx(photoInfos);
    }

    public List<PhotoInfo> checkIsExist( PhotoInfo photoInfo){
        List<PhotoInfo> list = photoInfoDao.queryBuilder()
                .where(PhotoInfoDao.Properties.ReportCode.eq(photoInfo.getReportCode()),
                        PhotoInfoDao.Properties.FlowId.eq(photoInfo.getFlowId()),
                        PhotoInfoDao.Properties.TaskType.eq(photoInfo.getTaskType()),
                        PhotoInfoDao.Properties.ImageName.eq(photoInfo.getImageName())).list();
        return list;
    }

    public void updatePhotoInfo(PhotoInfo photoInfo){
        photoInfoDao.update(photoInfo);
    }
    public void updatePhotoInfoBatch(List<PhotoInfo> photoInfos){
        photoInfoDao.updateInTx(photoInfos);
    }

    public List<PhotoInfo> getPhotoInfoByReportNoAndEventCode(String reportNo , int eventCode ){
        List<PhotoInfo> list = photoInfoDao.queryBuilder().where(PhotoInfoDao.Properties.ReportCode.eq(reportNo), PhotoInfoDao.Properties.EventCode.eq(eventCode)).list();
        return list;
    }

    /**
     * 根据离线上传返回状态 ，跟新
     * @param status
     * @param reportNo
     * @param eventCode
     */
    public void updatePhotpInfoStatusWhenUploadSucess(final String status , final String reportNo , final int eventCode){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<PhotoInfo> list = getPhotoInfoByReportNoAndEventCode(reportNo, eventCode);
                if(list != null && list.size() > 0){
                    for(PhotoInfo photo : list){
                        photo.setImageUpload(status);
                    }
                    photoInfoDao.updateInTx(list);
                }
            }
        }).start();

    }

    public void deletePhotoInfo(PhotoInfo photoInfo){
        photoInfoDao.delete(photoInfo);
    }


    /**
     * 去重查询图片大类，得到已拍照的小类种类数量
     * @param reportNo
     * @param imageType
     * @return
     */
    public int getDistinctImageListByReportNoAndImageType(String reportNo, String imageType){
        String querySql = "SELECT COUNT (DISTINCT "
                + PhotoInfoDao.Properties.ImageSubtype.columnName+
                ") from "
                + PhotoInfoDao.TABLENAME
                + " where "
                + PhotoInfoDao.Properties.ReportCode.columnName + " = '" + reportNo +
                "' and "
                + PhotoInfoDao.Properties.ImageType.columnName + " = '" + imageType +"'";

        int count = 0;
        Cursor cursor = photoInfoDao.getDatabase().rawQuery(querySql, null);
        if(cursor != null){
            if (cursor.moveToFirst()){
                count = cursor.getInt(0);
            }
            cursor.close();
        }
        return count;
    }

    /**
     * 去重查询图片大类，得到已拍照的小类种类数量
     * @param reportNo
     * @param imageType
     * @return
     */
    public int getDistinctImageListByReportNoAndImageTypeAndSignName(String reportNo, String imageType ,String signName){
        String querySql = "SELECT COUNT (DISTINCT "
                + PhotoInfoDao.Properties.ImageSubtype.columnName+
                ") from "
                + PhotoInfoDao.TABLENAME
                + " where "
                + PhotoInfoDao.Properties.ReportCode.columnName + " = '" + reportNo +
                "' and "
                + PhotoInfoDao.Properties.ImageType.columnName + " = '" + imageType +
                "' and "
                + PhotoInfoDao.Properties.SignName.columnName + " = '" + signName +"'";

        int count = 0;
        Cursor cursor = photoInfoDao.getDatabase().rawQuery(querySql, null);
        if(cursor != null){
            if (cursor.moveToFirst()){
                count = cursor.getInt(0);
            }
            cursor.close();
        }
        return count;
    }

    /**
     * 根据报案号和照片大类获取照片
     * @param reportNo
     * @param imageType
     * @return
     */
    public List<PhotoInfo> getImageListByReportNoAndImageType(String reportNo, String imageType){
        List<PhotoInfo> list = photoInfoDao.queryBuilder()
                .where(PhotoInfoDao.Properties.ReportCode.eq(reportNo),
                        PhotoInfoDao.Properties.ImageType.eq(imageType)).list();
        return list;
    }
    /**
     * 根据报案号和照片小类获取照片
     * @param reportNo
     * @param imageSubType
     * @return
     */
    public List<PhotoInfo> getImageListByReportNoAndSubType(String reportNo, String imageSubType){
        List<PhotoInfo> list = photoInfoDao.queryBuilder()
                .where(PhotoInfoDao.Properties.ReportCode.eq(reportNo),
                        PhotoInfoDao.Properties.ImageSubtype.eq(imageSubType)).list();
        return list;
    }


    /**
     * 根据报案号和照片小类获取照片
     * @param reportNo
     * @param imageSubType
     * @return
     */
    public List<PhotoInfo> getImageListByReportNoAndSubTypeAndUnUpload(String reportNo, String imageSubType){
        List<PhotoInfo> list = photoInfoDao.queryBuilder()
                .where(PhotoInfoDao.Properties.ReportCode.eq(reportNo),
                        PhotoInfoDao.Properties.ImageSubtype.eq(imageSubType),
                         PhotoInfoDao.Properties.ImageUpload.eq("0")).list();
        return list;
    }



    /**
     * 根据报案号和照片小类和人名、车牌获取照片
     * @param reportNo
     * @param imageSubType
     * @return
     */
    public List<PhotoInfo> getImageListByReportNoAndSubTypeAndSignName(String reportNo, String imageSubType,String signName){
        if(TextUtils.isEmpty(reportNo) || TextUtils.isEmpty(imageSubType) || TextUtils.isEmpty(signName)){
            return null;
        }
        List<PhotoInfo> list = photoInfoDao.queryBuilder()
                .where(PhotoInfoDao.Properties.ReportCode.eq(reportNo),
                        PhotoInfoDao.Properties.ImageSubtype.eq(imageSubType),
                        PhotoInfoDao.Properties.SignName.eq(signName)).list();
        return list;
    }

//    /**
//     * 根据报案号和照片小类删除照片
//     * @param photoInfo
//     * @return
//     */
//    public Long delImageListByReportNoAndSubType(PhotoInfo photoInfo){
//        List<PhotoInfo> list = photoInfoDao.delete()
//                .where(PhotoInfoDao.Properties.ReportCode.eq(reportNo),
//                        PhotoInfoDao.Properties.ImageSubtype.eq(imageSubType),
//                        PhotoInfoDao.Properties.SignName.eq(injuryName)).list();
//        return list;
//    }
//    /**
//     * 根据报案号和照片小类、人名、车牌删除照片
//     * @param reportNo
//     * @param imageSubType
//     * @return
//     */
//    public Long delImageListByReportNoAndSubTypeAndInjuryName(String reportNo, String imageSubType,String injuryName){
//        return claimImageDAO.delImageListByReportNoAndSubTypeAndInjuryName(reportNo,imageSubType,injuryName);
//    }
//
//    /**
//     * 保存照片
//     * @param imagePo
//     * @return
//     */
//    public Long saveImage(PhotoInfo imagePo){
//        return claimImageDAO.saveImage(imagePo);
//    }
//    /**
//     * 获取报案号的照片列表
//     * @param reportNo
//     * @return
//     */
//    public ArrayList<PhotoInfo> getListEvalImgByReportNo(String reportNo){
//        return claimImageDAO.getListEvalImgByReportNo(reportNo);
//    }
//    /**
//     * 获取报案号未上传的照片列表，时间排序
//     * @param reportNo
//     * @return
//     */
//    public ArrayList<PhotoInfo> getImageListNotUploadByReportNo(String reportNo){
//        return claimImageDAO.getImageListNotUploadByReportNo(reportNo);
//    }
}
