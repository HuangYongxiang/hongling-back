package com.hl.photo.table.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.hl.photo.table.model.ReportCarLossPart;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "REPORT_CAR_LOSS_PART".
*/
public class ReportCarLossPartDao extends AbstractDao<ReportCarLossPart, String> {

    public static final String TABLENAME = "REPORT_CAR_LOSS_PART";

    /**
     * Properties of entity ReportCarLossPart.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        public final static Property ExrId = new Property(1, String.class, "exrId", false, "EXR_ID");
        public final static Property ReportCode = new Property(2, String.class, "reportCode", false, "REPORT_CODE");
        public final static Property PlateNo = new Property(3, String.class, "plateNo", false, "PLATE_NO");
        public final static Property RiskyType = new Property(4, String.class, "riskyType", false, "RISKY_TYPE");
        public final static Property RiskyTypeCode = new Property(5, String.class, "riskyTypeCode", false, "RISKY_TYPE_CODE");
        public final static Property LossPart = new Property(6, String.class, "lossPart", false, "LOSS_PART");
        public final static Property LossPartCode = new Property(7, String.class, "lossPartCode", false, "LOSS_PART_CODE");
        public final static Property ItemCode = new Property(8, String.class, "itemCode", false, "ITEM_CODE");
        public final static Property ItemName = new Property(9, String.class, "itemName", false, "ITEM_NAME");
        public final static Property FeeName = new Property(10, String.class, "feeName", false, "FEE_NAME");
        public final static Property ExpactFee = new Property(11, String.class, "expactFee", false, "EXPACT_FEE");
        public final static Property LossDegreeDesc = new Property(12, String.class, "lossDegreeDesc", false, "LOSS_DEGREE_DESC");
        public final static Property SerialNo = new Property(13, Integer.class, "serialNo", false, "SERIAL_NO");
    }


    public ReportCarLossPartDao(DaoConfig config) {
        super(config);
    }
    
    public ReportCarLossPartDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"REPORT_CAR_LOSS_PART\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: id
                "\"EXR_ID\" TEXT," + // 1: exrId
                "\"REPORT_CODE\" TEXT," + // 2: reportCode
                "\"PLATE_NO\" TEXT," + // 3: plateNo
                "\"RISKY_TYPE\" TEXT," + // 4: riskyType
                "\"RISKY_TYPE_CODE\" TEXT," + // 5: riskyTypeCode
                "\"LOSS_PART\" TEXT," + // 6: lossPart
                "\"LOSS_PART_CODE\" TEXT," + // 7: lossPartCode
                "\"ITEM_CODE\" TEXT," + // 8: itemCode
                "\"ITEM_NAME\" TEXT," + // 9: itemName
                "\"FEE_NAME\" TEXT," + // 10: feeName
                "\"EXPACT_FEE\" TEXT," + // 11: expactFee
                "\"LOSS_DEGREE_DESC\" TEXT," + // 12: lossDegreeDesc
                "\"SERIAL_NO\" INTEGER);"); // 13: serialNo
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"REPORT_CAR_LOSS_PART\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ReportCarLossPart entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String exrId = entity.getExrId();
        if (exrId != null) {
            stmt.bindString(2, exrId);
        }
 
        String reportCode = entity.getReportCode();
        if (reportCode != null) {
            stmt.bindString(3, reportCode);
        }
 
        String plateNo = entity.getPlateNo();
        if (plateNo != null) {
            stmt.bindString(4, plateNo);
        }
 
        String riskyType = entity.getRiskyType();
        if (riskyType != null) {
            stmt.bindString(5, riskyType);
        }
 
        String riskyTypeCode = entity.getRiskyTypeCode();
        if (riskyTypeCode != null) {
            stmt.bindString(6, riskyTypeCode);
        }
 
        String lossPart = entity.getLossPart();
        if (lossPart != null) {
            stmt.bindString(7, lossPart);
        }
 
        String lossPartCode = entity.getLossPartCode();
        if (lossPartCode != null) {
            stmt.bindString(8, lossPartCode);
        }
 
        String itemCode = entity.getItemCode();
        if (itemCode != null) {
            stmt.bindString(9, itemCode);
        }
 
        String itemName = entity.getItemName();
        if (itemName != null) {
            stmt.bindString(10, itemName);
        }
 
        String feeName = entity.getFeeName();
        if (feeName != null) {
            stmt.bindString(11, feeName);
        }
 
        String expactFee = entity.getExpactFee();
        if (expactFee != null) {
            stmt.bindString(12, expactFee);
        }
 
        String lossDegreeDesc = entity.getLossDegreeDesc();
        if (lossDegreeDesc != null) {
            stmt.bindString(13, lossDegreeDesc);
        }
 
        Integer serialNo = entity.getSerialNo();
        if (serialNo != null) {
            stmt.bindLong(14, serialNo);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ReportCarLossPart entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String exrId = entity.getExrId();
        if (exrId != null) {
            stmt.bindString(2, exrId);
        }
 
        String reportCode = entity.getReportCode();
        if (reportCode != null) {
            stmt.bindString(3, reportCode);
        }
 
        String plateNo = entity.getPlateNo();
        if (plateNo != null) {
            stmt.bindString(4, plateNo);
        }
 
        String riskyType = entity.getRiskyType();
        if (riskyType != null) {
            stmt.bindString(5, riskyType);
        }
 
        String riskyTypeCode = entity.getRiskyTypeCode();
        if (riskyTypeCode != null) {
            stmt.bindString(6, riskyTypeCode);
        }
 
        String lossPart = entity.getLossPart();
        if (lossPart != null) {
            stmt.bindString(7, lossPart);
        }
 
        String lossPartCode = entity.getLossPartCode();
        if (lossPartCode != null) {
            stmt.bindString(8, lossPartCode);
        }
 
        String itemCode = entity.getItemCode();
        if (itemCode != null) {
            stmt.bindString(9, itemCode);
        }
 
        String itemName = entity.getItemName();
        if (itemName != null) {
            stmt.bindString(10, itemName);
        }
 
        String feeName = entity.getFeeName();
        if (feeName != null) {
            stmt.bindString(11, feeName);
        }
 
        String expactFee = entity.getExpactFee();
        if (expactFee != null) {
            stmt.bindString(12, expactFee);
        }
 
        String lossDegreeDesc = entity.getLossDegreeDesc();
        if (lossDegreeDesc != null) {
            stmt.bindString(13, lossDegreeDesc);
        }
 
        Integer serialNo = entity.getSerialNo();
        if (serialNo != null) {
            stmt.bindLong(14, serialNo);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public ReportCarLossPart readEntity(Cursor cursor, int offset) {
        ReportCarLossPart entity = new ReportCarLossPart( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // exrId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // reportCode
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // plateNo
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // riskyType
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // riskyTypeCode
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // lossPart
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // lossPartCode
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // itemCode
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // itemName
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // feeName
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // expactFee
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // lossDegreeDesc
            cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13) // serialNo
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ReportCarLossPart entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setExrId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setReportCode(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPlateNo(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setRiskyType(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setRiskyTypeCode(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setLossPart(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setLossPartCode(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setItemCode(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setItemName(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setFeeName(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setExpactFee(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setLossDegreeDesc(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setSerialNo(cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13));
     }
    
    @Override
    protected final String updateKeyAfterInsert(ReportCarLossPart entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public String getKey(ReportCarLossPart entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ReportCarLossPart entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
