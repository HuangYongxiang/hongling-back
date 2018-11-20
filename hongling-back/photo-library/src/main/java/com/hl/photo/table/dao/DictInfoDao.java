package com.hl.photo.table.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.hl.photo.table.model.DictInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DICT_INFO".
*/
public class DictInfoDao extends AbstractDao<DictInfo, Long> {

    public static final String TABLENAME = "DICT_INFO";

    /**
     * Properties of entity DictInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Key = new Property(1, String.class, "key", false, "KEY");
        public final static Property SeedKey = new Property(2, String.class, "seedKey", false, "SEED_KEY");
        public final static Property TypeCode = new Property(3, String.class, "typeCode", false, "TYPE_CODE");
        public final static Property TypeName = new Property(4, String.class, "typeName", false, "TYPE_NAME");
        public final static Property Explain = new Property(5, String.class, "explain", false, "EXPLAIN");
    }


    public DictInfoDao(DaoConfig config) {
        super(config);
    }
    
    public DictInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DICT_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"KEY\" TEXT," + // 1: key
                "\"SEED_KEY\" TEXT," + // 2: seedKey
                "\"TYPE_CODE\" TEXT," + // 3: typeCode
                "\"TYPE_NAME\" TEXT," + // 4: typeName
                "\"EXPLAIN\" TEXT);"); // 5: explain
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DICT_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DictInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String key = entity.getKey();
        if (key != null) {
            stmt.bindString(2, key);
        }
 
        String seedKey = entity.getSeedKey();
        if (seedKey != null) {
            stmt.bindString(3, seedKey);
        }
 
        String typeCode = entity.getTypeCode();
        if (typeCode != null) {
            stmt.bindString(4, typeCode);
        }
 
        String typeName = entity.getTypeName();
        if (typeName != null) {
            stmt.bindString(5, typeName);
        }
 
        String explain = entity.getExplain();
        if (explain != null) {
            stmt.bindString(6, explain);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DictInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String key = entity.getKey();
        if (key != null) {
            stmt.bindString(2, key);
        }
 
        String seedKey = entity.getSeedKey();
        if (seedKey != null) {
            stmt.bindString(3, seedKey);
        }
 
        String typeCode = entity.getTypeCode();
        if (typeCode != null) {
            stmt.bindString(4, typeCode);
        }
 
        String typeName = entity.getTypeName();
        if (typeName != null) {
            stmt.bindString(5, typeName);
        }
 
        String explain = entity.getExplain();
        if (explain != null) {
            stmt.bindString(6, explain);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DictInfo readEntity(Cursor cursor, int offset) {
        DictInfo entity = new DictInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // key
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // seedKey
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // typeCode
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // typeName
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // explain
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DictInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setKey(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSeedKey(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTypeCode(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTypeName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setExplain(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DictInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DictInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DictInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}