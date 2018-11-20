package com.hl.contract.table.manager;

import android.content.Context;

import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.table.dao.SurveyPaymentDao;
import com.hl.contract.table.model.SurveyPayment;

import java.util.List;


/**
 * created by liyu at 2017/7/19.
 * desc: 查勘中领款信息的 数据库管理类
 */

public class PaymentManager {
    private static PaymentManager instance;
    private SurveyPaymentDao surveyPaymentDao; // 领款信息

    public static PaymentManager getInstance() {
        if (instance == null) {
            synchronized (PaymentManager.class) {
                if (instance == null) {
                    instance = new PaymentManager(SurveyApplication.get());
                }
            }
        }
        return instance;
    }

    public PaymentManager(Context context) {
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        surveyPaymentDao = daoSession.getSurveyPaymentDao();
    }


//    /**
//     * 创建一个新的SurveyPayment
//     */
//    public SurveyPayment createSurveyPayment(SurveyPayment surveyPayment) {
//        surveyPayment.setId(UUIDUtil.getUUID());
//        surveyPaymentDao.insert(surveyPayment);
//        return surveyPayment;
//    }


    /**
     * @param reportCode
     * @return 领款信息
     * @desc 根据报案号，获取领款信息
     */
    public SurveyPayment getSurveyPayment(String reportCode) {
        List<SurveyPayment> surveyPaymentList = surveyPaymentDao.queryBuilder()
                .where(SurveyPaymentDao.Properties.ReportCode.eq(reportCode)).list();
        if (surveyPaymentList != null && surveyPaymentList.size() > 0) {
            return surveyPaymentList.get(0);
        } else {
            return null;
        }
    }

    public void insertSurveyPayment(SurveyPayment surveyPayment) {
        surveyPaymentDao.insert(surveyPayment);
    }

    public void updateSurveyPayment(SurveyPayment surveyPayment) {
        surveyPaymentDao.update(surveyPayment);
    }

    public List<SurveyPayment> getSurveyPaymentList(String reportCode) {
        List<SurveyPayment> surveyPaymentList = surveyPaymentDao.queryBuilder()
                .where(SurveyPaymentDao.Properties.ReportCode.eq(reportCode)).list();
        if (surveyPaymentList != null && surveyPaymentList.size() > 0) {
            return surveyPaymentList;
        } else {
            return null;
        }
    }

    /**
     * @param reportCode
     * @param dataId
     * @return 根据id和报案号获取单条支付信息
     */
    public SurveyPayment getSurveyPayment(String reportCode, String dataId) {
        if (reportCode == null || dataId == null) {
            return null;
        }

        List<SurveyPayment> surveyPaymentList = surveyPaymentDao.queryBuilder()
                .where(SurveyPaymentDao.Properties.ReportCode.eq(reportCode),
                        SurveyPaymentDao.Properties.Id.eq(dataId)).list();

        if (surveyPaymentList != null && surveyPaymentList.size() > 0) {
            return surveyPaymentList.get(0);
        }
        return null;
    }
    public void deleteSurveyPayment(SurveyPayment surveyPayment) {
        surveyPaymentDao.delete(surveyPayment);
    }

    public void deleteSurveyPaymentById(String id){
        List<SurveyPayment> surveyInjuryList = surveyPaymentDao.queryBuilder()
                .where(SurveyPaymentDao.Properties.Id.eq(id)).list();
        if (surveyInjuryList != null && surveyInjuryList.size() > 0) {
            surveyPaymentDao.delete(surveyInjuryList.get(0));
        }
    }

    /**
     * 获取支付的最大序号(新增时序号递增)
     * @param reportCode
     * @return
     */
    public int getSurveyPaymentMaxSerialNo(String reportCode) {
        Integer maxSerialNo = 0;
        if (reportCode == null) {
            return maxSerialNo;
        }
        List<SurveyPayment> surveyInjuryList = surveyPaymentDao.queryBuilder()
                .where(SurveyPaymentDao.Properties.ReportCode.eq(reportCode)).orderDesc(SurveyPaymentDao.Properties.SerialNo).list();

        if (surveyInjuryList != null && surveyInjuryList.size() > 0) {
            maxSerialNo = surveyInjuryList.get(0).getSerialNo();
            return maxSerialNo == null ? 0 : maxSerialNo;
        }
        return maxSerialNo;
    }

}
