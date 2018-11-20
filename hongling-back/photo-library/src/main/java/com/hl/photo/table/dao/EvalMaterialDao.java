package com.hl.photo.table.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.hl.photo.table.model.EvalMaterial;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "EVAL_MATERIAL".
*/
public class EvalMaterialDao extends AbstractDao<EvalMaterial, String> {

    public static final String TABLENAME = "EVAL_MATERIAL";

    /**
     * Properties of entity EvalMaterial.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        public final static Property FlowId = new Property(1, String.class, "flowId", false, "FLOW_ID");
        public final static Property EvalId = new Property(2, String.class, "evalId", false, "EVAL_ID");
        public final static Property MateName = new Property(3, String.class, "mateName", false, "MATE_NAME");
        public final static Property MateCode = new Property(4, String.class, "mateCode", false, "MATE_CODE");
        public final static Property MateHandaddFlag = new Property(5, String.class, "mateHandaddFlag", false, "MATE_HANDADD_FLAG");
        public final static Property EvalMateAmount = new Property(6, Double.class, "evalMateAmount", false, "EVAL_MATE_AMOUNT");
        public final static Property EvalUnitPrice = new Property(7, Double.class, "evalUnitPrice", false, "EVAL_UNIT_PRICE");
        public final static Property EvalMateSum = new Property(8, Double.class, "evalMateSum", false, "EVAL_MATE_SUM");
        public final static Property GroupGradeName = new Property(9, String.class, "groupGradeName", false, "GROUP_GRADE_NAME");
        public final static Property EvalRemark = new Property(10, String.class, "evalRemark", false, "EVAL_REMARK");
        public final static Property MateId = new Property(11, String.class, "mateId", false, "MATE_ID");
        public final static Property RepairId = new Property(12, String.class, "repairId", false, "REPAIR_ID");
        public final static Property MateItemCode = new Property(13, String.class, "mateItemCode", false, "MATE_ITEM_CODE");
        public final static Property MateItemName = new Property(14, String.class, "mateItemName", false, "MATE_ITEM_NAME");
        public final static Property ApprUnitPrice = new Property(15, Double.class, "apprUnitPrice", false, "APPR_UNIT_PRICE");
        public final static Property ApprMateAmount = new Property(16, Double.class, "apprMateAmount", false, "APPR_MATE_AMOUNT");
        public final static Property ApprMateSum = new Property(17, Double.class, "apprMateSum", false, "APPR_MATE_SUM");
        public final static Property ApprRemark = new Property(18, String.class, "apprRemark", false, "APPR_REMARK");
        public final static Property MateApprCheckState = new Property(19, String.class, "mateApprCheckState", false, "MATE_APPR_CHECK_STATE");
        public final static Property ApprState = new Property(20, String.class, "apprState", false, "APPR_STATE");
        public final static Property FormateMateApprState = new Property(21, String.class, "formateMateApprState", false, "FORMATE_MATE_APPR_STATE");
        public final static Property DelFlag = new Property(22, String.class, "delFlag", false, "DEL_FLAG");
        public final static Property AdditionalFlag = new Property(23, String.class, "additionalFlag", false, "ADDITIONAL_FLAG");
        public final static Property RuleModel = new Property(24, String.class, "ruleModel", false, "RULE_MODEL");
    }


    public EvalMaterialDao(DaoConfig config) {
        super(config);
    }
    
    public EvalMaterialDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"EVAL_MATERIAL\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: id
                "\"FLOW_ID\" TEXT," + // 1: flowId
                "\"EVAL_ID\" TEXT," + // 2: evalId
                "\"MATE_NAME\" TEXT," + // 3: mateName
                "\"MATE_CODE\" TEXT," + // 4: mateCode
                "\"MATE_HANDADD_FLAG\" TEXT," + // 5: mateHandaddFlag
                "\"EVAL_MATE_AMOUNT\" REAL," + // 6: evalMateAmount
                "\"EVAL_UNIT_PRICE\" REAL," + // 7: evalUnitPrice
                "\"EVAL_MATE_SUM\" REAL," + // 8: evalMateSum
                "\"GROUP_GRADE_NAME\" TEXT," + // 9: groupGradeName
                "\"EVAL_REMARK\" TEXT," + // 10: evalRemark
                "\"MATE_ID\" TEXT," + // 11: mateId
                "\"REPAIR_ID\" TEXT," + // 12: repairId
                "\"MATE_ITEM_CODE\" TEXT," + // 13: mateItemCode
                "\"MATE_ITEM_NAME\" TEXT," + // 14: mateItemName
                "\"APPR_UNIT_PRICE\" REAL," + // 15: apprUnitPrice
                "\"APPR_MATE_AMOUNT\" REAL," + // 16: apprMateAmount
                "\"APPR_MATE_SUM\" REAL," + // 17: apprMateSum
                "\"APPR_REMARK\" TEXT," + // 18: apprRemark
                "\"MATE_APPR_CHECK_STATE\" TEXT," + // 19: mateApprCheckState
                "\"APPR_STATE\" TEXT," + // 20: apprState
                "\"FORMATE_MATE_APPR_STATE\" TEXT," + // 21: formateMateApprState
                "\"DEL_FLAG\" TEXT," + // 22: delFlag
                "\"ADDITIONAL_FLAG\" TEXT," + // 23: additionalFlag
                "\"RULE_MODEL\" TEXT);"); // 24: ruleModel
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"EVAL_MATERIAL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, EvalMaterial entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String flowId = entity.getFlowId();
        if (flowId != null) {
            stmt.bindString(2, flowId);
        }
 
        String evalId = entity.getEvalId();
        if (evalId != null) {
            stmt.bindString(3, evalId);
        }
 
        String mateName = entity.getMateName();
        if (mateName != null) {
            stmt.bindString(4, mateName);
        }
 
        String mateCode = entity.getMateCode();
        if (mateCode != null) {
            stmt.bindString(5, mateCode);
        }
 
        String mateHandaddFlag = entity.getMateHandaddFlag();
        if (mateHandaddFlag != null) {
            stmt.bindString(6, mateHandaddFlag);
        }
 
        Double evalMateAmount = entity.getEvalMateAmount();
        if (evalMateAmount != null) {
            stmt.bindDouble(7, evalMateAmount);
        }
 
        Double evalUnitPrice = entity.getEvalUnitPrice();
        if (evalUnitPrice != null) {
            stmt.bindDouble(8, evalUnitPrice);
        }
 
        Double evalMateSum = entity.getEvalMateSum();
        if (evalMateSum != null) {
            stmt.bindDouble(9, evalMateSum);
        }
 
        String groupGradeName = entity.getGroupGradeName();
        if (groupGradeName != null) {
            stmt.bindString(10, groupGradeName);
        }
 
        String evalRemark = entity.getEvalRemark();
        if (evalRemark != null) {
            stmt.bindString(11, evalRemark);
        }
 
        String mateId = entity.getMateId();
        if (mateId != null) {
            stmt.bindString(12, mateId);
        }
 
        String repairId = entity.getRepairId();
        if (repairId != null) {
            stmt.bindString(13, repairId);
        }
 
        String mateItemCode = entity.getMateItemCode();
        if (mateItemCode != null) {
            stmt.bindString(14, mateItemCode);
        }
 
        String mateItemName = entity.getMateItemName();
        if (mateItemName != null) {
            stmt.bindString(15, mateItemName);
        }
 
        Double apprUnitPrice = entity.getApprUnitPrice();
        if (apprUnitPrice != null) {
            stmt.bindDouble(16, apprUnitPrice);
        }
 
        Double apprMateAmount = entity.getApprMateAmount();
        if (apprMateAmount != null) {
            stmt.bindDouble(17, apprMateAmount);
        }
 
        Double apprMateSum = entity.getApprMateSum();
        if (apprMateSum != null) {
            stmt.bindDouble(18, apprMateSum);
        }
 
        String apprRemark = entity.getApprRemark();
        if (apprRemark != null) {
            stmt.bindString(19, apprRemark);
        }
 
        String mateApprCheckState = entity.getMateApprCheckState();
        if (mateApprCheckState != null) {
            stmt.bindString(20, mateApprCheckState);
        }
 
        String apprState = entity.getApprState();
        if (apprState != null) {
            stmt.bindString(21, apprState);
        }
 
        String formateMateApprState = entity.getFormateMateApprState();
        if (formateMateApprState != null) {
            stmt.bindString(22, formateMateApprState);
        }
 
        String delFlag = entity.getDelFlag();
        if (delFlag != null) {
            stmt.bindString(23, delFlag);
        }
 
        String additionalFlag = entity.getAdditionalFlag();
        if (additionalFlag != null) {
            stmt.bindString(24, additionalFlag);
        }
 
        String ruleModel = entity.getRuleModel();
        if (ruleModel != null) {
            stmt.bindString(25, ruleModel);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, EvalMaterial entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String flowId = entity.getFlowId();
        if (flowId != null) {
            stmt.bindString(2, flowId);
        }
 
        String evalId = entity.getEvalId();
        if (evalId != null) {
            stmt.bindString(3, evalId);
        }
 
        String mateName = entity.getMateName();
        if (mateName != null) {
            stmt.bindString(4, mateName);
        }
 
        String mateCode = entity.getMateCode();
        if (mateCode != null) {
            stmt.bindString(5, mateCode);
        }
 
        String mateHandaddFlag = entity.getMateHandaddFlag();
        if (mateHandaddFlag != null) {
            stmt.bindString(6, mateHandaddFlag);
        }
 
        Double evalMateAmount = entity.getEvalMateAmount();
        if (evalMateAmount != null) {
            stmt.bindDouble(7, evalMateAmount);
        }
 
        Double evalUnitPrice = entity.getEvalUnitPrice();
        if (evalUnitPrice != null) {
            stmt.bindDouble(8, evalUnitPrice);
        }
 
        Double evalMateSum = entity.getEvalMateSum();
        if (evalMateSum != null) {
            stmt.bindDouble(9, evalMateSum);
        }
 
        String groupGradeName = entity.getGroupGradeName();
        if (groupGradeName != null) {
            stmt.bindString(10, groupGradeName);
        }
 
        String evalRemark = entity.getEvalRemark();
        if (evalRemark != null) {
            stmt.bindString(11, evalRemark);
        }
 
        String mateId = entity.getMateId();
        if (mateId != null) {
            stmt.bindString(12, mateId);
        }
 
        String repairId = entity.getRepairId();
        if (repairId != null) {
            stmt.bindString(13, repairId);
        }
 
        String mateItemCode = entity.getMateItemCode();
        if (mateItemCode != null) {
            stmt.bindString(14, mateItemCode);
        }
 
        String mateItemName = entity.getMateItemName();
        if (mateItemName != null) {
            stmt.bindString(15, mateItemName);
        }
 
        Double apprUnitPrice = entity.getApprUnitPrice();
        if (apprUnitPrice != null) {
            stmt.bindDouble(16, apprUnitPrice);
        }
 
        Double apprMateAmount = entity.getApprMateAmount();
        if (apprMateAmount != null) {
            stmt.bindDouble(17, apprMateAmount);
        }
 
        Double apprMateSum = entity.getApprMateSum();
        if (apprMateSum != null) {
            stmt.bindDouble(18, apprMateSum);
        }
 
        String apprRemark = entity.getApprRemark();
        if (apprRemark != null) {
            stmt.bindString(19, apprRemark);
        }
 
        String mateApprCheckState = entity.getMateApprCheckState();
        if (mateApprCheckState != null) {
            stmt.bindString(20, mateApprCheckState);
        }
 
        String apprState = entity.getApprState();
        if (apprState != null) {
            stmt.bindString(21, apprState);
        }
 
        String formateMateApprState = entity.getFormateMateApprState();
        if (formateMateApprState != null) {
            stmt.bindString(22, formateMateApprState);
        }
 
        String delFlag = entity.getDelFlag();
        if (delFlag != null) {
            stmt.bindString(23, delFlag);
        }
 
        String additionalFlag = entity.getAdditionalFlag();
        if (additionalFlag != null) {
            stmt.bindString(24, additionalFlag);
        }
 
        String ruleModel = entity.getRuleModel();
        if (ruleModel != null) {
            stmt.bindString(25, ruleModel);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public EvalMaterial readEntity(Cursor cursor, int offset) {
        EvalMaterial entity = new EvalMaterial( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // flowId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // evalId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // mateName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // mateCode
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // mateHandaddFlag
            cursor.isNull(offset + 6) ? null : cursor.getDouble(offset + 6), // evalMateAmount
            cursor.isNull(offset + 7) ? null : cursor.getDouble(offset + 7), // evalUnitPrice
            cursor.isNull(offset + 8) ? null : cursor.getDouble(offset + 8), // evalMateSum
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // groupGradeName
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // evalRemark
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // mateId
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // repairId
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // mateItemCode
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // mateItemName
            cursor.isNull(offset + 15) ? null : cursor.getDouble(offset + 15), // apprUnitPrice
            cursor.isNull(offset + 16) ? null : cursor.getDouble(offset + 16), // apprMateAmount
            cursor.isNull(offset + 17) ? null : cursor.getDouble(offset + 17), // apprMateSum
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // apprRemark
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // mateApprCheckState
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // apprState
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // formateMateApprState
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // delFlag
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // additionalFlag
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24) // ruleModel
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, EvalMaterial entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setFlowId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setEvalId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMateName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setMateCode(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setMateHandaddFlag(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setEvalMateAmount(cursor.isNull(offset + 6) ? null : cursor.getDouble(offset + 6));
        entity.setEvalUnitPrice(cursor.isNull(offset + 7) ? null : cursor.getDouble(offset + 7));
        entity.setEvalMateSum(cursor.isNull(offset + 8) ? null : cursor.getDouble(offset + 8));
        entity.setGroupGradeName(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setEvalRemark(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setMateId(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setRepairId(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setMateItemCode(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setMateItemName(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setApprUnitPrice(cursor.isNull(offset + 15) ? null : cursor.getDouble(offset + 15));
        entity.setApprMateAmount(cursor.isNull(offset + 16) ? null : cursor.getDouble(offset + 16));
        entity.setApprMateSum(cursor.isNull(offset + 17) ? null : cursor.getDouble(offset + 17));
        entity.setApprRemark(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setMateApprCheckState(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setApprState(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setFormateMateApprState(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setDelFlag(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setAdditionalFlag(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setRuleModel(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
     }
    
    @Override
    protected final String updateKeyAfterInsert(EvalMaterial entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public String getKey(EvalMaterial entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(EvalMaterial entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
