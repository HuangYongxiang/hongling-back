package com.hl.contract.table.manager;

import android.content.Context;
import android.text.TextUtils;

import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.table.dao.ReportCarLossPartDao;
import com.hl.contract.table.model.ReportCarLossPart;

import java.util.List;

/**
 * Created by chenxuefei on 2017/2/5.
 */
public class ReportCarLossPartManager {
    private static ReportCarLossPartManager instance;
    private ReportCarLossPartDao reportCarLossPartDao;

    public static ReportCarLossPartManager getInstance(){
        if(instance == null){
            synchronized (ReportCarLossPartManager.class){
                if(instance == null){
                    instance = new ReportCarLossPartManager(SurveyApplication.get());
                }
            }
        }
        return  instance;
    }

    public ReportCarLossPartManager(Context context) {

        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        reportCarLossPartDao = daoSession.getReportCarLossPartDao();
    }

    //通过id获取车辆损失部位
    public ReportCarLossPart getReportCarLossPartById(String id){
       if(TextUtils.isEmpty(id)){
           return null;
       }
        ReportCarLossPart reportCarLossPart =null;
        List<ReportCarLossPart>  reportCarLossParts   = reportCarLossPartDao.queryBuilder()
                .where(ReportCarLossPartDao.Properties.Id.eq(id)).list();

        if(reportCarLossParts!=null&&reportCarLossParts.size()>0){
            reportCarLossPart = reportCarLossParts.get(0);
        }
         return reportCarLossPart;
    }


   //通过id删除车辆损失部位
    public void deleteReportCarLossPartById(String id){
        if(TextUtils.isEmpty(id)){
            return ;
        }
        List<ReportCarLossPart>  reportCarLossParts   = reportCarLossPartDao.queryBuilder()
                .where(ReportCarLossPartDao.Properties.Id.eq(id)).list();

        if(reportCarLossParts!=null&&reportCarLossParts.size()>0){
            reportCarLossPartDao.delete(reportCarLossParts.get(0));
        }
     }

   //通过exrId删除车辆损失部位
    public void deleteReportCarLossPartByExrId(String exrId){
        if(TextUtils.isEmpty(exrId)){
            return ;
        }
        List<ReportCarLossPart>  reportCarLossParts   = reportCarLossPartDao.queryBuilder()
                .where(ReportCarLossPartDao.Properties.ExrId.eq(exrId)).list();

        if(reportCarLossParts!=null&&reportCarLossParts.size()>0){
            reportCarLossPartDao.delete(reportCarLossParts.get(0));
        }
     }


    //通过报案号获取车辆损失部位列表
     public List<ReportCarLossPart> getReportCarLossPartListByReportcode(String reportcode){
         if(TextUtils.isEmpty(reportcode)){
             return  null;
         }
         List<ReportCarLossPart>  reportCarLossParts   = reportCarLossPartDao.queryBuilder()
                 .where(ReportCarLossPartDao.Properties.ReportCode.eq(reportcode)).list();
       return  reportCarLossParts;
     }


    //通过涉案车辆关联id获取车辆损失部位列表
    public List<ReportCarLossPart> getReportCarLossPartListByExraId(String eId){
        if(TextUtils.isEmpty(eId)){
            return  null;
        }
        List<ReportCarLossPart>  reportCarLossParts   = reportCarLossPartDao.queryBuilder()
                .where(ReportCarLossPartDao.Properties.ExrId.eq(eId)).list();
        return  reportCarLossParts;
    }



    //保存车辆损失部位
    public void saveReportCarLossPart(ReportCarLossPart reportCarLossPart){
       if(reportCarLossPart==null){
         return;
       }
       String id  = reportCarLossPart.getId();
       if(TextUtils.isEmpty(id)){
           return;
       }
        ReportCarLossPart temp = getReportCarLossPartById(id);
        if(temp!=null){   //更新
            reportCarLossPart.setId(temp.getId());
            reportCarLossPartDao.update(reportCarLossPart);
        }else{  //新增
            reportCarLossPartDao.insertOrReplace(reportCarLossPart);
        }
    }

    public void insertOrReplaceInTxReportCarLossPartList(List<ReportCarLossPart> reportCarLossPartList){
        if(reportCarLossPartList != null && reportCarLossPartList.size() > 0){
            reportCarLossPartDao.insertOrReplaceInTx(reportCarLossPartList);
        }
    }
}
