package com.hl.photo.table.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.hl.photo.table.model.DictInfo;
import com.hl.photo.table.model.EvalMaterial;
import com.hl.photo.table.model.PhotoInfo;
import com.hl.photo.table.model.ReportCar;
import com.hl.photo.table.model.ReportCarLossPart;
import com.hl.photo.table.model.SurveyInjury;
import com.hl.photo.table.model.SurveyProperty;
import com.hl.photo.table.model.TaskInfo;

import com.hl.photo.table.dao.DictInfoDao;
import com.hl.photo.table.dao.EvalMaterialDao;
import com.hl.photo.table.dao.PhotoInfoDao;
import com.hl.photo.table.dao.ReportCarDao;
import com.hl.photo.table.dao.ReportCarLossPartDao;
import com.hl.photo.table.dao.SurveyInjuryDao;
import com.hl.photo.table.dao.SurveyPropertyDao;
import com.hl.photo.table.dao.TaskInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig dictInfoDaoConfig;
    private final DaoConfig evalMaterialDaoConfig;
    private final DaoConfig photoInfoDaoConfig;
    private final DaoConfig reportCarDaoConfig;
    private final DaoConfig reportCarLossPartDaoConfig;
    private final DaoConfig surveyInjuryDaoConfig;
    private final DaoConfig surveyPropertyDaoConfig;
    private final DaoConfig taskInfoDaoConfig;

    private final DictInfoDao dictInfoDao;
    private final EvalMaterialDao evalMaterialDao;
    private final PhotoInfoDao photoInfoDao;
    private final ReportCarDao reportCarDao;
    private final ReportCarLossPartDao reportCarLossPartDao;
    private final SurveyInjuryDao surveyInjuryDao;
    private final SurveyPropertyDao surveyPropertyDao;
    private final TaskInfoDao taskInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        dictInfoDaoConfig = daoConfigMap.get(DictInfoDao.class).clone();
        dictInfoDaoConfig.initIdentityScope(type);

        evalMaterialDaoConfig = daoConfigMap.get(EvalMaterialDao.class).clone();
        evalMaterialDaoConfig.initIdentityScope(type);

        photoInfoDaoConfig = daoConfigMap.get(PhotoInfoDao.class).clone();
        photoInfoDaoConfig.initIdentityScope(type);

        reportCarDaoConfig = daoConfigMap.get(ReportCarDao.class).clone();
        reportCarDaoConfig.initIdentityScope(type);

        reportCarLossPartDaoConfig = daoConfigMap.get(ReportCarLossPartDao.class).clone();
        reportCarLossPartDaoConfig.initIdentityScope(type);

        surveyInjuryDaoConfig = daoConfigMap.get(SurveyInjuryDao.class).clone();
        surveyInjuryDaoConfig.initIdentityScope(type);

        surveyPropertyDaoConfig = daoConfigMap.get(SurveyPropertyDao.class).clone();
        surveyPropertyDaoConfig.initIdentityScope(type);

        taskInfoDaoConfig = daoConfigMap.get(TaskInfoDao.class).clone();
        taskInfoDaoConfig.initIdentityScope(type);

        dictInfoDao = new DictInfoDao(dictInfoDaoConfig, this);
        evalMaterialDao = new EvalMaterialDao(evalMaterialDaoConfig, this);
        photoInfoDao = new PhotoInfoDao(photoInfoDaoConfig, this);
        reportCarDao = new ReportCarDao(reportCarDaoConfig, this);
        reportCarLossPartDao = new ReportCarLossPartDao(reportCarLossPartDaoConfig, this);
        surveyInjuryDao = new SurveyInjuryDao(surveyInjuryDaoConfig, this);
        surveyPropertyDao = new SurveyPropertyDao(surveyPropertyDaoConfig, this);
        taskInfoDao = new TaskInfoDao(taskInfoDaoConfig, this);

        registerDao(DictInfo.class, dictInfoDao);
        registerDao(EvalMaterial.class, evalMaterialDao);
        registerDao(PhotoInfo.class, photoInfoDao);
        registerDao(ReportCar.class, reportCarDao);
        registerDao(ReportCarLossPart.class, reportCarLossPartDao);
        registerDao(SurveyInjury.class, surveyInjuryDao);
        registerDao(SurveyProperty.class, surveyPropertyDao);
        registerDao(TaskInfo.class, taskInfoDao);
    }
    
    public void clear() {
        dictInfoDaoConfig.clearIdentityScope();
        evalMaterialDaoConfig.clearIdentityScope();
        photoInfoDaoConfig.clearIdentityScope();
        reportCarDaoConfig.clearIdentityScope();
        reportCarLossPartDaoConfig.clearIdentityScope();
        surveyInjuryDaoConfig.clearIdentityScope();
        surveyPropertyDaoConfig.clearIdentityScope();
        taskInfoDaoConfig.clearIdentityScope();
    }

    public DictInfoDao getDictInfoDao() {
        return dictInfoDao;
    }

    public EvalMaterialDao getEvalMaterialDao() {
        return evalMaterialDao;
    }

    public PhotoInfoDao getPhotoInfoDao() {
        return photoInfoDao;
    }

    public ReportCarDao getReportCarDao() {
        return reportCarDao;
    }

    public ReportCarLossPartDao getReportCarLossPartDao() {
        return reportCarLossPartDao;
    }

    public SurveyInjuryDao getSurveyInjuryDao() {
        return surveyInjuryDao;
    }

    public SurveyPropertyDao getSurveyPropertyDao() {
        return surveyPropertyDao;
    }

    public TaskInfoDao getTaskInfoDao() {
        return taskInfoDao;
    }

}