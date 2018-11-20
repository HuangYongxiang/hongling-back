package com.hl.contract.table.manager;

import android.content.Context;
import android.text.TextUtils;

import com.hl.core.lib.util.common.UUIDUtil;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.table.dao.InsuranceItemDao;
import com.hl.contract.table.model.InsuranceItem;
import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;

import java.util.List;

/**
 * @Describe:进入定损险别操作表
 * @Author: liyu
 * @Date: 2018/1/15 13:39
 * @Copyright: hl
 */
public class EvalInsuranceItemManager {
    private static EvalInsuranceItemManager instance;
    private InsuranceItemDao insuranceItemDao;

    public static EvalInsuranceItemManager getInstance() {
        if (instance == null) {
            instance = new EvalInsuranceItemManager(SurveyApplication.get());
        }
        return instance;
    }

    public EvalInsuranceItemManager(Context context) {
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        insuranceItemDao = daoSession.getInsuranceItemDao();
    }

    /**
     * 查询险别
     * @param lossNo
     * @return
     */
    public List<InsuranceItem> getInsuranceItemListByLossNo(String lossNo){
        List<InsuranceItem> insuranceItemList = insuranceItemDao.queryBuilder().where(InsuranceItemDao.Properties.LossNo.eq(lossNo)).list();
        return insuranceItemList;
    }
    //批量删除险别
    public void deleteInsuranceItemBatch(List<InsuranceItem> list){
        insuranceItemDao.deleteInTx(list);
    }
    //批量添加险别
    public void insertInsuranceItemBatch(String lossNo, List<InsuranceItem> list){
        if(list != null && list.size() > 0){
            for(InsuranceItem insuranceItem : list){
                insuranceItem.setId(TextUtils.isEmpty(insuranceItem.getId()) ? UUIDUtil.getUUID():insuranceItem.getId());
                insuranceItem.setLossNo(lossNo);
            }
            insuranceItemDao.insertInTx(list);
        }
    }

}
