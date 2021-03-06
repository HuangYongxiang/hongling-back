package com.hl.photo.table.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.hl.photo.table.model.TaskInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TASK_INFO".
*/
public class TaskInfoDao extends AbstractDao<TaskInfo, String> {

    public static final String TABLENAME = "TASK_INFO";

    /**
     * Properties of entity TaskInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        public final static Property ReportCode = new Property(1, String.class, "reportCode", false, "REPORT_CODE");
        public final static Property FlowId = new Property(2, String.class, "flowId", false, "FLOW_ID");
        public final static Property DispatchType = new Property(3, String.class, "dispatchType", false, "DISPATCH_TYPE");
        public final static Property TaskNo = new Property(4, String.class, "taskNo", false, "TASK_NO");
        public final static Property TaskCode = new Property(5, String.class, "taskCode", false, "TASK_CODE");
        public final static Property FlowCode = new Property(6, String.class, "flowCode", false, "FLOW_CODE");
        public final static Property MainVeFlag = new Property(7, String.class, "mainVeFlag", false, "MAIN_VE_FLAG");
        public final static Property LossName = new Property(8, String.class, "lossName", false, "LOSS_NAME");
        public final static Property DispatchPersonId = new Property(9, String.class, "dispatchPersonId", false, "DISPATCH_PERSON_ID");
        public final static Property DispatchPersonName = new Property(10, String.class, "dispatchPersonName", false, "DISPATCH_PERSON_NAME");
        public final static Property DispatchTime = new Property(11, String.class, "dispatchTime", false, "DISPATCH_TIME");
        public final static Property Remark = new Property(12, String.class, "remark", false, "REMARK");
        public final static Property EvaluationPersonId = new Property(13, String.class, "evaluationPersonId", false, "EVALUATION_PERSON_ID");
        public final static Property EvaluationPersonName = new Property(14, String.class, "evaluationPersonName", false, "EVALUATION_PERSON_NAME");
        public final static Property DealCompCode = new Property(15, String.class, "dealCompCode", false, "DEAL_COMP_CODE");
        public final static Property DealCompName = new Property(16, String.class, "dealCompName", false, "DEAL_COMP_NAME");
        public final static Property StatusCode = new Property(17, String.class, "statusCode", false, "STATUS_CODE");
        public final static Property SendPhoneFlag = new Property(18, String.class, "sendPhoneFlag", false, "SEND_PHONE_FLAG");
        public final static Property AccidentPlace = new Property(19, String.class, "accidentPlace", false, "ACCIDENT_PLACE");
        public final static Property ReportTime = new Property(20, String.class, "reportTime", false, "REPORT_TIME");
        public final static Property ReportPersonName = new Property(21, String.class, "reportPersonName", false, "REPORT_PERSON_NAME");
        public final static Property PlateNo = new Property(22, String.class, "plateNo", false, "PLATE_NO");
        public final static Property CompleteDegree = new Property(23, double.class, "completeDegree", false, "COMPLETE_DEGREE");
        public final static Property CompleteFlag = new Property(24, String.class, "completeFlag", false, "COMPLETE_FLAG");
        public final static Property ChildStateCode = new Property(25, String.class, "childStateCode", false, "CHILD_STATE_CODE");
    }


    public TaskInfoDao(DaoConfig config) {
        super(config);
    }
    
    public TaskInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TASK_INFO\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: id
                "\"REPORT_CODE\" TEXT," + // 1: reportCode
                "\"FLOW_ID\" TEXT," + // 2: flowId
                "\"DISPATCH_TYPE\" TEXT," + // 3: dispatchType
                "\"TASK_NO\" TEXT," + // 4: taskNo
                "\"TASK_CODE\" TEXT," + // 5: taskCode
                "\"FLOW_CODE\" TEXT," + // 6: flowCode
                "\"MAIN_VE_FLAG\" TEXT," + // 7: mainVeFlag
                "\"LOSS_NAME\" TEXT," + // 8: lossName
                "\"DISPATCH_PERSON_ID\" TEXT," + // 9: dispatchPersonId
                "\"DISPATCH_PERSON_NAME\" TEXT," + // 10: dispatchPersonName
                "\"DISPATCH_TIME\" TEXT," + // 11: dispatchTime
                "\"REMARK\" TEXT," + // 12: remark
                "\"EVALUATION_PERSON_ID\" TEXT," + // 13: evaluationPersonId
                "\"EVALUATION_PERSON_NAME\" TEXT," + // 14: evaluationPersonName
                "\"DEAL_COMP_CODE\" TEXT," + // 15: dealCompCode
                "\"DEAL_COMP_NAME\" TEXT," + // 16: dealCompName
                "\"STATUS_CODE\" TEXT," + // 17: statusCode
                "\"SEND_PHONE_FLAG\" TEXT," + // 18: sendPhoneFlag
                "\"ACCIDENT_PLACE\" TEXT," + // 19: accidentPlace
                "\"REPORT_TIME\" TEXT," + // 20: reportTime
                "\"REPORT_PERSON_NAME\" TEXT," + // 21: reportPersonName
                "\"PLATE_NO\" TEXT," + // 22: plateNo
                "\"COMPLETE_DEGREE\" REAL NOT NULL ," + // 23: completeDegree
                "\"COMPLETE_FLAG\" TEXT," + // 24: completeFlag
                "\"CHILD_STATE_CODE\" TEXT);"); // 25: childStateCode
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TASK_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TaskInfo entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String reportCode = entity.getReportCode();
        if (reportCode != null) {
            stmt.bindString(2, reportCode);
        }
 
        String flowId = entity.getFlowId();
        if (flowId != null) {
            stmt.bindString(3, flowId);
        }
 
        String dispatchType = entity.getDispatchType();
        if (dispatchType != null) {
            stmt.bindString(4, dispatchType);
        }
 
        String taskNo = entity.getTaskNo();
        if (taskNo != null) {
            stmt.bindString(5, taskNo);
        }
 
        String taskCode = entity.getTaskCode();
        if (taskCode != null) {
            stmt.bindString(6, taskCode);
        }
 
        String flowCode = entity.getFlowCode();
        if (flowCode != null) {
            stmt.bindString(7, flowCode);
        }
 
        String mainVeFlag = entity.getMainVeFlag();
        if (mainVeFlag != null) {
            stmt.bindString(8, mainVeFlag);
        }
 
        String lossName = entity.getLossName();
        if (lossName != null) {
            stmt.bindString(9, lossName);
        }
 
        String dispatchPersonId = entity.getDispatchPersonId();
        if (dispatchPersonId != null) {
            stmt.bindString(10, dispatchPersonId);
        }
 
        String dispatchPersonName = entity.getDispatchPersonName();
        if (dispatchPersonName != null) {
            stmt.bindString(11, dispatchPersonName);
        }
 
        String dispatchTime = entity.getDispatchTime();
        if (dispatchTime != null) {
            stmt.bindString(12, dispatchTime);
        }
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(13, remark);
        }
 
        String evaluationPersonId = entity.getEvaluationPersonId();
        if (evaluationPersonId != null) {
            stmt.bindString(14, evaluationPersonId);
        }
 
        String evaluationPersonName = entity.getEvaluationPersonName();
        if (evaluationPersonName != null) {
            stmt.bindString(15, evaluationPersonName);
        }
 
        String dealCompCode = entity.getDealCompCode();
        if (dealCompCode != null) {
            stmt.bindString(16, dealCompCode);
        }
 
        String dealCompName = entity.getDealCompName();
        if (dealCompName != null) {
            stmt.bindString(17, dealCompName);
        }
 
        String statusCode = entity.getStatusCode();
        if (statusCode != null) {
            stmt.bindString(18, statusCode);
        }
 
        String sendPhoneFlag = entity.getSendPhoneFlag();
        if (sendPhoneFlag != null) {
            stmt.bindString(19, sendPhoneFlag);
        }
 
        String accidentPlace = entity.getAccidentPlace();
        if (accidentPlace != null) {
            stmt.bindString(20, accidentPlace);
        }
 
        String reportTime = entity.getReportTime();
        if (reportTime != null) {
            stmt.bindString(21, reportTime);
        }
 
        String reportPersonName = entity.getReportPersonName();
        if (reportPersonName != null) {
            stmt.bindString(22, reportPersonName);
        }
 
        String plateNo = entity.getPlateNo();
        if (plateNo != null) {
            stmt.bindString(23, plateNo);
        }
        stmt.bindDouble(24, entity.getCompleteDegree());
 
        String completeFlag = entity.getCompleteFlag();
        if (completeFlag != null) {
            stmt.bindString(25, completeFlag);
        }
 
        String childStateCode = entity.getChildStateCode();
        if (childStateCode != null) {
            stmt.bindString(26, childStateCode);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TaskInfo entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String reportCode = entity.getReportCode();
        if (reportCode != null) {
            stmt.bindString(2, reportCode);
        }
 
        String flowId = entity.getFlowId();
        if (flowId != null) {
            stmt.bindString(3, flowId);
        }
 
        String dispatchType = entity.getDispatchType();
        if (dispatchType != null) {
            stmt.bindString(4, dispatchType);
        }
 
        String taskNo = entity.getTaskNo();
        if (taskNo != null) {
            stmt.bindString(5, taskNo);
        }
 
        String taskCode = entity.getTaskCode();
        if (taskCode != null) {
            stmt.bindString(6, taskCode);
        }
 
        String flowCode = entity.getFlowCode();
        if (flowCode != null) {
            stmt.bindString(7, flowCode);
        }
 
        String mainVeFlag = entity.getMainVeFlag();
        if (mainVeFlag != null) {
            stmt.bindString(8, mainVeFlag);
        }
 
        String lossName = entity.getLossName();
        if (lossName != null) {
            stmt.bindString(9, lossName);
        }
 
        String dispatchPersonId = entity.getDispatchPersonId();
        if (dispatchPersonId != null) {
            stmt.bindString(10, dispatchPersonId);
        }
 
        String dispatchPersonName = entity.getDispatchPersonName();
        if (dispatchPersonName != null) {
            stmt.bindString(11, dispatchPersonName);
        }
 
        String dispatchTime = entity.getDispatchTime();
        if (dispatchTime != null) {
            stmt.bindString(12, dispatchTime);
        }
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(13, remark);
        }
 
        String evaluationPersonId = entity.getEvaluationPersonId();
        if (evaluationPersonId != null) {
            stmt.bindString(14, evaluationPersonId);
        }
 
        String evaluationPersonName = entity.getEvaluationPersonName();
        if (evaluationPersonName != null) {
            stmt.bindString(15, evaluationPersonName);
        }
 
        String dealCompCode = entity.getDealCompCode();
        if (dealCompCode != null) {
            stmt.bindString(16, dealCompCode);
        }
 
        String dealCompName = entity.getDealCompName();
        if (dealCompName != null) {
            stmt.bindString(17, dealCompName);
        }
 
        String statusCode = entity.getStatusCode();
        if (statusCode != null) {
            stmt.bindString(18, statusCode);
        }
 
        String sendPhoneFlag = entity.getSendPhoneFlag();
        if (sendPhoneFlag != null) {
            stmt.bindString(19, sendPhoneFlag);
        }
 
        String accidentPlace = entity.getAccidentPlace();
        if (accidentPlace != null) {
            stmt.bindString(20, accidentPlace);
        }
 
        String reportTime = entity.getReportTime();
        if (reportTime != null) {
            stmt.bindString(21, reportTime);
        }
 
        String reportPersonName = entity.getReportPersonName();
        if (reportPersonName != null) {
            stmt.bindString(22, reportPersonName);
        }
 
        String plateNo = entity.getPlateNo();
        if (plateNo != null) {
            stmt.bindString(23, plateNo);
        }
        stmt.bindDouble(24, entity.getCompleteDegree());
 
        String completeFlag = entity.getCompleteFlag();
        if (completeFlag != null) {
            stmt.bindString(25, completeFlag);
        }
 
        String childStateCode = entity.getChildStateCode();
        if (childStateCode != null) {
            stmt.bindString(26, childStateCode);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public TaskInfo readEntity(Cursor cursor, int offset) {
        TaskInfo entity = new TaskInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // reportCode
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // flowId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // dispatchType
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // taskNo
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // taskCode
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // flowCode
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // mainVeFlag
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // lossName
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // dispatchPersonId
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // dispatchPersonName
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // dispatchTime
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // remark
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // evaluationPersonId
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // evaluationPersonName
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // dealCompCode
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // dealCompName
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // statusCode
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // sendPhoneFlag
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // accidentPlace
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // reportTime
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // reportPersonName
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // plateNo
            cursor.getDouble(offset + 23), // completeDegree
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // completeFlag
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25) // childStateCode
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TaskInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setReportCode(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFlowId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDispatchType(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTaskNo(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTaskCode(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setFlowCode(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setMainVeFlag(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setLossName(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setDispatchPersonId(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setDispatchPersonName(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setDispatchTime(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setRemark(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setEvaluationPersonId(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setEvaluationPersonName(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setDealCompCode(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setDealCompName(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setStatusCode(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setSendPhoneFlag(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setAccidentPlace(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setReportTime(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setReportPersonName(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setPlateNo(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setCompleteDegree(cursor.getDouble(offset + 23));
        entity.setCompleteFlag(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setChildStateCode(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
     }
    
    @Override
    protected final String updateKeyAfterInsert(TaskInfo entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public String getKey(TaskInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(TaskInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
