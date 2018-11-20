package com.hl.contract.table.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.hl.contract.table.model.Insurance;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "INSURANCE".
*/
public class InsuranceDao extends AbstractDao<Insurance, String> {

    public static final String TABLENAME = "INSURANCE";

    /**
     * Properties of entity Insurance.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        public final static Property ReportCode = new Property(1, String.class, "reportCode", false, "REPORT_CODE");
        public final static Property PolicyId = new Property(2, String.class, "policyId", false, "POLICY_ID");
        public final static Property RiskCode = new Property(3, String.class, "riskCode", false, "RISK_CODE");
        public final static Property RiskName = new Property(4, String.class, "riskName", false, "RISK_NAME");
        public final static Property ItemCode = new Property(5, String.class, "itemCode", false, "ITEM_CODE");
        public final static Property ItemName = new Property(6, String.class, "itemName", false, "ITEM_NAME");
        public final static Property InsuranceSuitYear = new Property(7, Short.class, "insuranceSuitYear", false, "INSURANCE_SUIT_YEAR");
        public final static Property TotalInsSum = new Property(8, Double.class, "totalInsSum", false, "TOTAL_INS_SUM");
        public final static Property TotalInsFee = new Property(9, Double.class, "totalInsFee", false, "TOTAL_INS_FEE");
        public final static Property InsuranceProperty = new Property(10, String.class, "insuranceProperty", false, "INSURANCE_PROPERTY");
        public final static Property ItemAttribute = new Property(11, String.class, "itemAttribute", false, "ITEM_ATTRIBUTE");
        public final static Property NopayFlag = new Property(12, String.class, "nopayFlag", false, "NOPAY_FLAG");
    }


    public InsuranceDao(DaoConfig config) {
        super(config);
    }
    
    public InsuranceDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"INSURANCE\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: id
                "\"REPORT_CODE\" TEXT," + // 1: reportCode
                "\"POLICY_ID\" TEXT," + // 2: policyId
                "\"RISK_CODE\" TEXT," + // 3: riskCode
                "\"RISK_NAME\" TEXT," + // 4: riskName
                "\"ITEM_CODE\" TEXT," + // 5: itemCode
                "\"ITEM_NAME\" TEXT," + // 6: itemName
                "\"INSURANCE_SUIT_YEAR\" INTEGER," + // 7: insuranceSuitYear
                "\"TOTAL_INS_SUM\" REAL," + // 8: totalInsSum
                "\"TOTAL_INS_FEE\" REAL," + // 9: totalInsFee
                "\"INSURANCE_PROPERTY\" TEXT," + // 10: insuranceProperty
                "\"ITEM_ATTRIBUTE\" TEXT," + // 11: itemAttribute
                "\"NOPAY_FLAG\" TEXT);"); // 12: nopayFlag
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"INSURANCE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Insurance entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String reportCode = entity.getReportCode();
        if (reportCode != null) {
            stmt.bindString(2, reportCode);
        }
 
        String policyId = entity.getPolicyId();
        if (policyId != null) {
            stmt.bindString(3, policyId);
        }
 
        String riskCode = entity.getRiskCode();
        if (riskCode != null) {
            stmt.bindString(4, riskCode);
        }
 
        String riskName = entity.getRiskName();
        if (riskName != null) {
            stmt.bindString(5, riskName);
        }
 
        String itemCode = entity.getItemCode();
        if (itemCode != null) {
            stmt.bindString(6, itemCode);
        }
 
        String itemName = entity.getItemName();
        if (itemName != null) {
            stmt.bindString(7, itemName);
        }
 
        Short insuranceSuitYear = entity.getInsuranceSuitYear();
        if (insuranceSuitYear != null) {
            stmt.bindLong(8, insuranceSuitYear);
        }
 
        Double totalInsSum = entity.getTotalInsSum();
        if (totalInsSum != null) {
            stmt.bindDouble(9, totalInsSum);
        }
 
        Double totalInsFee = entity.getTotalInsFee();
        if (totalInsFee != null) {
            stmt.bindDouble(10, totalInsFee);
        }
 
        String insuranceProperty = entity.getInsuranceProperty();
        if (insuranceProperty != null) {
            stmt.bindString(11, insuranceProperty);
        }
 
        String itemAttribute = entity.getItemAttribute();
        if (itemAttribute != null) {
            stmt.bindString(12, itemAttribute);
        }
 
        String nopayFlag = entity.getNopayFlag();
        if (nopayFlag != null) {
            stmt.bindString(13, nopayFlag);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Insurance entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String reportCode = entity.getReportCode();
        if (reportCode != null) {
            stmt.bindString(2, reportCode);
        }
 
        String policyId = entity.getPolicyId();
        if (policyId != null) {
            stmt.bindString(3, policyId);
        }
 
        String riskCode = entity.getRiskCode();
        if (riskCode != null) {
            stmt.bindString(4, riskCode);
        }
 
        String riskName = entity.getRiskName();
        if (riskName != null) {
            stmt.bindString(5, riskName);
        }
 
        String itemCode = entity.getItemCode();
        if (itemCode != null) {
            stmt.bindString(6, itemCode);
        }
 
        String itemName = entity.getItemName();
        if (itemName != null) {
            stmt.bindString(7, itemName);
        }
 
        Short insuranceSuitYear = entity.getInsuranceSuitYear();
        if (insuranceSuitYear != null) {
            stmt.bindLong(8, insuranceSuitYear);
        }
 
        Double totalInsSum = entity.getTotalInsSum();
        if (totalInsSum != null) {
            stmt.bindDouble(9, totalInsSum);
        }
 
        Double totalInsFee = entity.getTotalInsFee();
        if (totalInsFee != null) {
            stmt.bindDouble(10, totalInsFee);
        }
 
        String insuranceProperty = entity.getInsuranceProperty();
        if (insuranceProperty != null) {
            stmt.bindString(11, insuranceProperty);
        }
 
        String itemAttribute = entity.getItemAttribute();
        if (itemAttribute != null) {
            stmt.bindString(12, itemAttribute);
        }
 
        String nopayFlag = entity.getNopayFlag();
        if (nopayFlag != null) {
            stmt.bindString(13, nopayFlag);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public Insurance readEntity(Cursor cursor, int offset) {
        Insurance entity = new Insurance( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // reportCode
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // policyId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // riskCode
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // riskName
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // itemCode
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // itemName
            cursor.isNull(offset + 7) ? null : cursor.getShort(offset + 7), // insuranceSuitYear
            cursor.isNull(offset + 8) ? null : cursor.getDouble(offset + 8), // totalInsSum
            cursor.isNull(offset + 9) ? null : cursor.getDouble(offset + 9), // totalInsFee
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // insuranceProperty
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // itemAttribute
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12) // nopayFlag
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Insurance entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setReportCode(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPolicyId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setRiskCode(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setRiskName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setItemCode(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setItemName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setInsuranceSuitYear(cursor.isNull(offset + 7) ? null : cursor.getShort(offset + 7));
        entity.setTotalInsSum(cursor.isNull(offset + 8) ? null : cursor.getDouble(offset + 8));
        entity.setTotalInsFee(cursor.isNull(offset + 9) ? null : cursor.getDouble(offset + 9));
        entity.setInsuranceProperty(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setItemAttribute(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setNopayFlag(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
     }
    
    @Override
    protected final String updateKeyAfterInsert(Insurance entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public String getKey(Insurance entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Insurance entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
