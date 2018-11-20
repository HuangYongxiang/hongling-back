package com.hl.contract.table.manager;

import android.content.Context;

import com.hl.core.lib.CoreApplication;
import com.hl.core.lib.util.common.UUIDUtil;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.table.dao.InjuryFeeInfoDao;
import com.hl.contract.table.dao.InjuryHospitalInfoDao;
import com.hl.contract.table.dao.InjuryMainInfoDao;
import com.hl.contract.table.model.InjuryFeeInfo;
import com.hl.contract.table.model.InjuryHospitalInfo;
import com.hl.contract.table.model.InjuryMainInfo;

import java.util.List;

/**
 * created by liyu at 2017/7/21.
 * desc: 人伤网络访问
 */

public class InjuryTaskManager  {

    public static String TAG ;
    public static InjuryTaskManager instance;
    private Context mContext;

    public InjuryMainInfoDao mInjuryMainInfoDao; //  人伤基本信息
    public InjuryFeeInfoDao mInjuryFeeInfoDao; // 人伤清单信息
    public InjuryHospitalInfoDao mInjuryHospitalInfoDao; //


    // 数据库 DAO 对象
    private InjuryTaskManager(Context context){
        TAG = getClass().getSimpleName();
        mContext = context;
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(mContext);
        mInjuryMainInfoDao = daoSession.getInjuryMainInfoDao();
        mInjuryFeeInfoDao = daoSession.getInjuryFeeInfoDao();
        mInjuryHospitalInfoDao = daoSession.getInjuryHospitalInfoDao();

    };

    public static InjuryTaskManager getInstance(){
        if (instance == null) {
            instance = new InjuryTaskManager(CoreApplication.get());
        }
        return instance;
    }


    /**
     * 获取人伤基本信息
     * @param reportNo
     * @return
     */
    public InjuryMainInfo getInjuryMainInfo(String reportNo, String taskNo){
        List<InjuryMainInfo> list =  mInjuryMainInfoDao.queryBuilder()
                .where(InjuryMainInfoDao.Properties.ReportCode.eq(reportNo))
                .where(InjuryMainInfoDao.Properties.TaskNo.eq(taskNo)) .list();
        if (list!=null && list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }
    /**
     * 获取人伤列表明细
     * @param reportNo
     * @return
     */
    public List<InjuryFeeInfo> getInjuryFeeInfoList(String reportNo, String taskNo){
        List<InjuryFeeInfo> list = mInjuryFeeInfoDao.queryBuilder()
                .where(InjuryFeeInfoDao.Properties.ReportCode.eq(reportNo))
                .where(InjuryFeeInfoDao.Properties.TaskNo.eq(taskNo)).list();
        return list;
    }
    /**
     * 保存InjuryFeeInfo
     */
    public void saveInjuryFeeInfo(InjuryFeeInfo injuryFeeInfo){
        mInjuryFeeInfoDao.insert(injuryFeeInfo);
    }

    /**
     * 删除一条消息
     * @param injuryFeeInfo
     */
    public void deletInjuryFeeIno(InjuryFeeInfo injuryFeeInfo){
        mInjuryFeeInfoDao.delete(injuryFeeInfo);
    }

    /**

     * @param taskNo 任务号
     * @return
     */
    public List<InjuryHospitalInfo> getInjuryHospitalInfo(String reportCode , String taskNo){
        List<InjuryHospitalInfo> infos = mInjuryHospitalInfoDao.queryBuilder()
                .where(InjuryHospitalInfoDao.Properties.TaskNo.eq(taskNo))
                .where(InjuryHospitalInfoDao.Properties.ReportCode.eq(reportCode)).list();
        return infos;

    }

    /**
     * 保存InjuryFeeInfo
     */
    public void saveInjuryHospitalInfo(InjuryHospitalInfo injuryHospitalInfo){
        List<InjuryHospitalInfo> hospitalInfos = mInjuryHospitalInfoDao.queryBuilder()
                .where(InjuryHospitalInfoDao.Properties.Id.eq(injuryHospitalInfo.getId())).list();
        if (hospitalInfos != null && hospitalInfos.size() > 0){
            mInjuryHospitalInfoDao.update(injuryHospitalInfo);
        }else{
            mInjuryHospitalInfoDao.insert(injuryHospitalInfo);
        }
    }

    /**
     * 删除一条消息
     * @param injuryHospitalInfo
     */
    public void deletInjuryHospitalInfo(InjuryHospitalInfo injuryHospitalInfo){
        mInjuryHospitalInfoDao.delete(injuryHospitalInfo);
    }

    /**
     * 保存或者更新数据
     * @param injuryMainInfo
     */
    public void saveInjuryMainInfo(InjuryMainInfo injuryMainInfo) {
        if (injuryMainInfo.getId() == null){
            injuryMainInfo.setId(UUIDUtil.getUUID());
        }
        String reportCode = injuryMainInfo.getReportCode();
        String taskNo = injuryMainInfo.getTaskNo();
        List<InjuryMainInfo> infos = mInjuryMainInfoDao.queryBuilder()
                .where(InjuryMainInfoDao.Properties.ReportCode.eq(reportCode))
                .where(InjuryMainInfoDao.Properties.TaskNo.eq(taskNo)).list();
        if (infos != null && infos.size() > 0){
            mInjuryMainInfoDao.update(injuryMainInfo);
        }else{
            mInjuryMainInfoDao.insert(injuryMainInfo);
        }
    }
}
