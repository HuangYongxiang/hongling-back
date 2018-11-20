package com.hl.contract.table.manager;

import android.content.Context;

import com.hl.core.lib.util.UtilManager;
import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.table.dao.ReportInfoDao;
import com.hl.contract.table.model.ReportInfo;
import com.hl.contract.util.SurveyClaimUtil;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;


/**
 * Create data： 2017/1/24.
 * Author: ChenXuefei
 * Function:
 */

public class ReportInfoManager {
    public final static String TAG = "ReportManager";
    public static ReportInfoManager instance;
    private ReportInfoDao reportInfoDao;


    public static ReportInfoManager getInstance() {
        if (instance == null) {
            instance = new ReportInfoManager(SurveyApplication.get());
        }
        return instance;
    }
    private ReportInfoManager(Context context) {
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        reportInfoDao = daoSession.getReportInfoDao();
    }

    /**
     * 查询案件列表用于首页
     *
     * @return 案件列表
     */
    public List<ReportInfo> getReportInfoList() {
        List<ReportInfo> reportInfoList = reportInfoDao.queryBuilder()
                .where(ReportInfoDao.Properties.CompleteFlag.notEq("1"),
                        ReportInfoDao.Properties.UserId.eq(UtilManager.SP.survey().getString(SurveyClaimUtil.USER_ID, ""))).orderAsc(ReportInfoDao.Properties.AccidentTime).list();
        return reportInfoList;
    }

    /**
     * @param isDone 案件是否已经完成，true为已经完成
     *               根据案件状态过滤案件列表
     */
    public List<ReportInfo> getFilterReportIList(boolean isDone) {
        List<ReportInfo> reportInfoList;
        if (isDone) {
            reportInfoList = reportInfoDao.queryBuilder()
                    .where(ReportInfoDao.Properties.CompleteFlag.eq("1")).list();
        } else {
            reportInfoList = reportInfoDao.queryBuilder()
                    .where(ReportInfoDao.Properties.CompleteFlag.eq("0")).list();
        }

        return reportInfoList;
    }

    /**
     * @param reportCode 根据报案号,车牌号，报案人姓名，出事地点的一部分查找案件
     */
    public List<ReportInfo> getReportListByReportCode(String reportCode) {
        QueryBuilder qb = reportInfoDao.queryBuilder();
        qb.whereOr(ReportInfoDao.Properties.ReportCode.like("%" + reportCode + "%"), ReportInfoDao.Properties.PlateNum.like("%" + reportCode + "%"), ReportInfoDao.Properties.AccidentPlace.like("%" + reportCode + "%"), ReportInfoDao.Properties.ReportPersonName.like("%" + reportCode + "%"));
        List<ReportInfo> reportInfoList = (List<ReportInfo>) qb.list();

        return reportInfoList;
    }

    /**
     * @return 返回未完成案件个数
     */
    public int getClaimNumber() {
        List<ReportInfo> reportInfoList = reportInfoDao.queryBuilder()
                .where(ReportInfoDao.Properties.CompleteFlag.notEq("1"),
                        ReportInfoDao.Properties.UserId.eq(UtilManager.SP.survey().getString(SurveyClaimUtil.USER_ID, ""))).list();
        if (reportInfoList == null) {
            return 0;
        } else
            return reportInfoList.size();
    }

    public List<ReportInfo> getReportInfoListByReportCode(String reportCode){
        List<ReportInfo> reportInfoList = reportInfoDao.queryBuilder()
                .where(ReportInfoDao.Properties.ReportCode.eq(reportCode)).list();
        return reportInfoList;
    }

    /**
     * @param reportCode
     * @return 用于报案详情页面的报案详情
     */
    public ReportInfo getReportInfo(String reportCode) {
        List<ReportInfo> reportInfoList = getReportInfoListByReportCode(reportCode);
        ReportInfo reportInfo = null;
        if (reportInfoList != null && reportInfoList.size() > 0) {
//            reportInfoEvent.setReportInfo(reportInfoList.get(0));
//            EventBus.post(reportInfoEvent);
            reportInfo = reportInfoList.get(0);
        }
        return reportInfo;
    }

    public void insertReportInfo(ReportInfo reportInfo){
        if(reportInfo != null){
            reportInfoDao.insert(reportInfo);
        }
    }

    public void updateReportInfo(ReportInfo reportInfo){
        if(reportInfo != null){
            reportInfoDao.update(reportInfo);
        }
    }

}
