package com.hl.contract.table.manager;

import android.content.Context;

import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.table.dao.PolicyInfoDao;
import com.hl.contract.table.model.PolicyInfo;

import java.util.List;


/**
 * @Describe: 保单表操作
 * @Author: liyu
 * @Date: 2018/2/23 10:51
 * @Copyright: hl
 */

public class PolicyInfoManager {
    public final static String TAG = "ReportManager";
    public static PolicyInfoManager instance;
    private PolicyInfoDao policyInfoDao;


    public static PolicyInfoManager getInstance() {
        if (instance == null) {
            instance = new PolicyInfoManager(SurveyApplication.get());
        }
        return instance;
    }
    private PolicyInfoManager(Context context) {
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        policyInfoDao = daoSession.getPolicyInfoDao();
    }

    /**
     * 获取标的车的承保单信息
     */
    public PolicyInfo getPolicyInfo(String reportNo) {
        List<PolicyInfo> list = getPolicyInfoListByReportNo(reportNo);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return new PolicyInfo();
        }
    }

    /**
     * @param reportCode
     * @param isInsurancePolicy 是否是交强险
     * @return
     */
    public PolicyInfo getPolicyInfo(String reportCode, boolean isInsurancePolicy) {
        List<PolicyInfo> policyInfoList;
        if(isInsurancePolicy){
            policyInfoList = getCompulsoryPolicyInfoList(reportCode);//根据 riskCode 区别是否为交强
        }else {
            policyInfoList = getBusinessPolicyInfoList(reportCode);
        }
        if (policyInfoList != null && policyInfoList.size() > 0) {
            return policyInfoList.get(0);
        }
        return null;
    }

    public void insertOrReplaceInTxPolicyInfos(List<PolicyInfo> policyInfos) {
        if (policyInfos != null && policyInfos.size() > 0) {
            policyInfoDao.insertOrReplaceInTx(policyInfos);
        }
    }

    //商业险保单
    public List<PolicyInfo> getBusinessPolicyInfoList(String reportNo){
        List<PolicyInfo> policyInfoList = policyInfoDao.queryBuilder()
                .where(PolicyInfoDao.Properties.ReportCode.eq(reportNo),
                        PolicyInfoDao.Properties.RiskType.eq("DE")).list();
        return policyInfoList;
    }
//    //商业险保单 阳光
//    public List<PolicyInfo> getBusinessPolicyInfoList(String reportNo){
//        List<PolicyInfo> policyInfoList = policyInfoDao.queryBuilder()
//                .where(PolicyInfoDao.Properties.ReportCode.eq(reportNo),
//                        PolicyInfoDao.Properties.RiskType.eq("D"), //D 代表车险(包含交强险 商业险)
//                        PolicyInfoDao.Properties.RiskCode.notEq("DDA")).list();
//        return policyInfoList;
//    }

    //交强险保单
    public List<PolicyInfo> getCompulsoryPolicyInfoList(String reportNo){
        List<PolicyInfo> policyInfoList = policyInfoDao.queryBuilder()
                .where(PolicyInfoDao.Properties.ReportCode.eq(reportNo),
                        PolicyInfoDao.Properties.RiskType.eq("DZ")).list();
        return policyInfoList;
    }

    public List<PolicyInfo> getPolicyInfoListByReportNo(String reportNo){
        List<PolicyInfo> list = policyInfoDao.queryBuilder().where(PolicyInfoDao.Properties.ReportCode.eq(reportNo)).list();
        return list;
    }

    public void deletePolicyInfo(PolicyInfo policyInfo){
        if(policyInfo != null){
            policyInfoDao.delete(policyInfo);
        }
    }

    public void deletePolicyInfoBatch(List<PolicyInfo> policyInfoList){
        if(policyInfoList != null && policyInfoList.size() > 0){
            policyInfoDao.deleteInTx(policyInfoList);
        }
    }

}
