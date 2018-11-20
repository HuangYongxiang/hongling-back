package com.hl.contract.table.manager;

import android.content.Context;

import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.table.dao.InsuranceDao;
import com.hl.contract.table.model.Insurance;
import com.hl.contract.util.SurveyClaimUtil;

import java.util.List;



/**
 * Created by hl on 2017/9/14.
 */

public class InsuranceManager {
    public static InsuranceManager instance;
    private Context mContext;
    private InsuranceDao insuranceDao;

    public static InsuranceManager getInstance() {
        if (instance == null) {
            instance = new InsuranceManager(SurveyApplication.get());
        }
        return instance;
    }

    private InsuranceManager(Context context) {
        mContext = context;
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);

        insuranceDao = daoSession.getInsuranceDao();
    }



    public void insertOrReplaceInTxInsuranceList(List<Insurance> insuranceList) {
        if (insuranceList != null && insuranceList.size() > 0) {
            insuranceDao.insertOrReplaceInTx(insuranceList);
        }
    }

}
