package com.hl.contract.table.manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hl.core.lib.util.common.TimeUtil;
import com.hl.core.lib.util.common.UUIDUtil;
import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.CargoFeeInfoDao;
import com.hl.contract.table.dao.CargoMainInfoDao;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.table.model.CargoFeeInfo;
import com.hl.contract.table.model.CargoMainInfo;

import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.List;

/**
 * created by liyu at 2017/7/20.
 * desc: 物损任务
 */

public class CargoTaskManager  {

    public static String TAG  = "CargoTaskManager";
    public static CargoTaskManager instance;
    private Context mContext;

    private CargoMainInfoDao mCargoMainInfoDao; // 财产损失信息：主信息 dao
    private CargoFeeInfoDao mCargoFeeInfoDao;  // 财产损失信息：列表（多条）


    // 数据库 DAO 对象

    private CargoTaskManager(Context context){
        mContext = context;
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        mCargoMainInfoDao = daoSession.getCargoMainInfoDao();
        mCargoFeeInfoDao = daoSession.getCargoFeeInfoDao();
    };

    public static CargoTaskManager getInstance() {
        if (instance == null) {
            instance = new CargoTaskManager(SurveyApplication.get());
        }
        return instance;
    }

    /**
     * 获取物损基本信息
     * @param taskNo
     * @return
     */
    public CargoMainInfo getCargoMainInfo(String reportCode , String taskNo){
        List<CargoMainInfo> list =  mCargoMainInfoDao.queryBuilder()
                .where(CargoMainInfoDao.Properties.FlowId.eq(taskNo))
                .where(CargoMainInfoDao.Properties.ReportCode.eq(reportCode)).list();
        CargoMainInfo cargoMainInfo = null;
        if (list!=null && list.size()>0){
            return list.get(0);
        }else{
            return cargoMainInfo;
        }
    }

    /**
     * 插入物损主信息
     * @param info
     */
    public void insertCargoBasicInfo(CargoMainInfo info){
        mCargoMainInfoDao.insert(info);
    }

    /**
     * 保存 物损信息
     */
    public void saveCargoBasicInfo(CargoMainInfo info){
        if (mCargoMainInfoDao.load(info.getId())==null){
            mCargoMainInfoDao.insert(info);
        }else {
            mCargoMainInfoDao.update(info);
        }
    }

    public void createCargoFee(CargoFeeInfo cargoFeeInfo){
        if (TextUtils.isEmpty(cargoFeeInfo.getId())){
            cargoFeeInfo.setId(UUIDUtil.getUUID());
        }
        mCargoFeeInfoDao.insert(cargoFeeInfo);
    }

    /**
     * 根据Id获取物损列表明细
     * @param id 物损清单Id
     */
    public CargoFeeInfo getCargoFeeInfo(String id){
        return mCargoFeeInfoDao.load(id);
    }

    /**
     * 获取物损列表明细
     * @param flowid
     * @return
     */
    public List<CargoFeeInfo> getCargoFeeInfoList(String reportCode ,String flowid){
        List<CargoFeeInfo> list = mCargoFeeInfoDao.queryBuilder()
                .where(CargoFeeInfoDao.Properties.ReportCode.eq(reportCode))
                .where(CargoFeeInfoDao.Properties.FlowId.eq(flowid)).list();
        if (list.size() > 0){
            return list;
        }else{
            return null;
        }
    }

    /**
     * 删除一条消息
     * @param cargoFeeInfo
     */
    public void deletCargoFeeIno(CargoFeeInfo cargoFeeInfo){
        mCargoFeeInfoDao.delete(cargoFeeInfo);
    }

    public void deleteCargoFeeInfo(String id){
        mCargoFeeInfoDao.deleteByKey(id);
    }

    /**
     *插入一条物损信息
     */

    public void insertCargoFeeInfo(CargoFeeInfo info){
//            mCargoFeeInfoDao.insertOrReplace(info);
//        String flowId = info.getFlowId();
//        List<CargoFeeInfo> cargoFeeInfoList = mCargoFeeInfoDao.queryBuilder()
//                .where(CargoFeeInfoDao.Properties.FlowId.eq(flowId)).list();
//        if (cargoFeeInfoList!=null && cargoFeeInfoList.size()>0){
//            mCargoFeeInfoDao.update(info);
//        }else {
//            mCargoFeeInfoDao.insert(info);
//        }
        info.setId(UUIDUtil.getUUID());
        info.setCreateDate(TimeUtil.dateToStringYMDHMS(new Date()));
        mCargoFeeInfoDao.insert(info);
    }

    /**
     *
     * 更新一条物损信息
     */
    public void  updateCargoFeeInfo(CargoFeeInfo info){
//        info.setUpdateBy();
        info.setUpdateDate(TimeUtil.dateToStringYMDHMS(new Date()));
        mCargoFeeInfoDao.update(info);

    }
}
