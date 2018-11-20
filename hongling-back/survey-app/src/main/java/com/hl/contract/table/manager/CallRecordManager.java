package com.hl.contract.table.manager;

import android.content.Context;
import android.os.Handler;

import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.CallRecordDao;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.table.model.CallRecord;

import java.util.List;

/**
 * Created by liyu on 2017/12/7.
 */

public class CallRecordManager {

    private static CallRecordManager instance;
    private Context mContext;
    private Handler mHandler;
    private CallRecordDao callRecordDao;

    public static CallRecordManager getInstance() {
        if (instance == null) {
            synchronized (CallRecordManager.class){
                if (instance == null) {
                    instance = new CallRecordManager(SurveyApplication.get());
                }
            }
        }
        return instance;
    }

    private CallRecordManager(Context context) {
        mContext = context;
        mHandler = new Handler();
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        callRecordDao = daoSession.getCallRecordDao();
    }

    public List<CallRecord> getCallRecordListByReportNo(String reportNo){
        List<CallRecord> list = callRecordDao.queryBuilder().where(CallRecordDao.Properties.ReportNo.eq(reportNo))
                .orderDesc(CallRecordDao.Properties.ContactTime).list();
        return list;
    }

    public List<CallRecord> getCallRecordListByReportNoAndTelephone(String reportNo,String telephone){
        List<CallRecord> list = callRecordDao.queryBuilder() .where(CallRecordDao.Properties.ReportNo.eq(reportNo)
                ,CallRecordDao.Properties.ContactTelephone.eq(telephone))
                .orderDesc(CallRecordDao.Properties.ContactTime).list();
        return list;
    }

    public long saveCallRecordData( CallRecord callRecord){
        return callRecordDao.insert(callRecord);
    }

    public void deleteAllRecordByReportNo(String reportNo){
        List<CallRecord> list = getCallRecordListByReportNo(reportNo);
        if(list != null && list.size() > 0){
            callRecordDao.deleteInTx(list);
        }
    }
}
