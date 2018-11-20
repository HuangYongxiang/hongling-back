package com.hl.photo.table.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.hl.photo.table.model.PhotoInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PHOTO_INFO".
*/
public class PhotoInfoDao extends AbstractDao<PhotoInfo, Long> {

    public static final String TABLENAME = "PHOTO_INFO";

    /**
     * Properties of entity PhotoInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ReportCode = new Property(1, String.class, "reportCode", false, "REPORT_CODE");
        public final static Property FlowId = new Property(2, String.class, "flowId", false, "FLOW_ID");
        public final static Property TaskType = new Property(3, String.class, "taskType", false, "TASK_TYPE");
        public final static Property ImageName = new Property(4, String.class, "imageName", false, "IMAGE_NAME");
        public final static Property ImagePath = new Property(5, String.class, "imagePath", false, "IMAGE_PATH");
        public final static Property ImageDescribe = new Property(6, String.class, "imageDescribe", false, "IMAGE_DESCRIBE");
        public final static Property ImageLatitude = new Property(7, String.class, "imageLatitude", false, "IMAGE_LATITUDE");
        public final static Property ImageLongitude = new Property(8, String.class, "imageLongitude", false, "IMAGE_LONGITUDE");
        public final static Property ImageAddress = new Property(9, String.class, "imageAddress", false, "IMAGE_ADDRESS");
        public final static Property ImageTime = new Property(10, String.class, "imageTime", false, "IMAGE_TIME");
        public final static Property ImageUpload = new Property(11, String.class, "imageUpload", false, "IMAGE_UPLOAD");
        public final static Property SignId = new Property(12, String.class, "signId", false, "SIGN_ID");
        public final static Property SignName = new Property(13, String.class, "signName", false, "SIGN_NAME");
        public final static Property ImageType = new Property(14, String.class, "imageType", false, "IMAGE_TYPE");
        public final static Property ImageSubtype = new Property(15, String.class, "imageSubtype", false, "IMAGE_SUBTYPE");
        public final static Property UploadEmp = new Property(16, String.class, "uploadEmp", false, "UPLOAD_EMP");
        public final static Property UploadEmpName = new Property(17, String.class, "uploadEmpName", false, "UPLOAD_EMP_NAME");
        public final static Property UploadTime = new Property(18, String.class, "uploadTime", false, "UPLOAD_TIME");
        public final static Property SeedKey = new Property(19, String.class, "seedKey", false, "SEED_KEY");
        public final static Property EventCode = new Property(20, int.class, "eventCode", false, "EVENT_CODE");
        public final static Property Tag = new Property(21, int.class, "tag", false, "TAG");
    }


    public PhotoInfoDao(DaoConfig config) {
        super(config);
    }
    
    public PhotoInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PHOTO_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"REPORT_CODE\" TEXT," + // 1: reportCode
                "\"FLOW_ID\" TEXT," + // 2: flowId
                "\"TASK_TYPE\" TEXT," + // 3: taskType
                "\"IMAGE_NAME\" TEXT," + // 4: imageName
                "\"IMAGE_PATH\" TEXT," + // 5: imagePath
                "\"IMAGE_DESCRIBE\" TEXT," + // 6: imageDescribe
                "\"IMAGE_LATITUDE\" TEXT," + // 7: imageLatitude
                "\"IMAGE_LONGITUDE\" TEXT," + // 8: imageLongitude
                "\"IMAGE_ADDRESS\" TEXT," + // 9: imageAddress
                "\"IMAGE_TIME\" TEXT," + // 10: imageTime
                "\"IMAGE_UPLOAD\" TEXT," + // 11: imageUpload
                "\"SIGN_ID\" TEXT," + // 12: signId
                "\"SIGN_NAME\" TEXT," + // 13: signName
                "\"IMAGE_TYPE\" TEXT," + // 14: imageType
                "\"IMAGE_SUBTYPE\" TEXT," + // 15: imageSubtype
                "\"UPLOAD_EMP\" TEXT," + // 16: uploadEmp
                "\"UPLOAD_EMP_NAME\" TEXT," + // 17: uploadEmpName
                "\"UPLOAD_TIME\" TEXT," + // 18: uploadTime
                "\"SEED_KEY\" TEXT," + // 19: seedKey
                "\"EVENT_CODE\" INTEGER NOT NULL ," + // 20: eventCode
                "\"TAG\" INTEGER NOT NULL );"); // 21: tag
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PHOTO_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PhotoInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String reportCode = entity.getReportCode();
        if (reportCode != null) {
            stmt.bindString(2, reportCode);
        }
 
        String flowId = entity.getFlowId();
        if (flowId != null) {
            stmt.bindString(3, flowId);
        }
 
        String taskType = entity.getTaskType();
        if (taskType != null) {
            stmt.bindString(4, taskType);
        }
 
        String imageName = entity.getImageName();
        if (imageName != null) {
            stmt.bindString(5, imageName);
        }
 
        String imagePath = entity.getImagePath();
        if (imagePath != null) {
            stmt.bindString(6, imagePath);
        }
 
        String imageDescribe = entity.getImageDescribe();
        if (imageDescribe != null) {
            stmt.bindString(7, imageDescribe);
        }
 
        String imageLatitude = entity.getImageLatitude();
        if (imageLatitude != null) {
            stmt.bindString(8, imageLatitude);
        }
 
        String imageLongitude = entity.getImageLongitude();
        if (imageLongitude != null) {
            stmt.bindString(9, imageLongitude);
        }
 
        String imageAddress = entity.getImageAddress();
        if (imageAddress != null) {
            stmt.bindString(10, imageAddress);
        }
 
        String imageTime = entity.getImageTime();
        if (imageTime != null) {
            stmt.bindString(11, imageTime);
        }
 
        String imageUpload = entity.getImageUpload();
        if (imageUpload != null) {
            stmt.bindString(12, imageUpload);
        }
 
        String signId = entity.getSignId();
        if (signId != null) {
            stmt.bindString(13, signId);
        }
 
        String signName = entity.getSignName();
        if (signName != null) {
            stmt.bindString(14, signName);
        }
 
        String imageType = entity.getImageType();
        if (imageType != null) {
            stmt.bindString(15, imageType);
        }
 
        String imageSubtype = entity.getImageSubtype();
        if (imageSubtype != null) {
            stmt.bindString(16, imageSubtype);
        }
 
        String uploadEmp = entity.getUploadEmp();
        if (uploadEmp != null) {
            stmt.bindString(17, uploadEmp);
        }
 
        String uploadEmpName = entity.getUploadEmpName();
        if (uploadEmpName != null) {
            stmt.bindString(18, uploadEmpName);
        }
 
        String uploadTime = entity.getUploadTime();
        if (uploadTime != null) {
            stmt.bindString(19, uploadTime);
        }
 
        String seedKey = entity.getSeedKey();
        if (seedKey != null) {
            stmt.bindString(20, seedKey);
        }
        stmt.bindLong(21, entity.getEventCode());
        stmt.bindLong(22, entity.getTag());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PhotoInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String reportCode = entity.getReportCode();
        if (reportCode != null) {
            stmt.bindString(2, reportCode);
        }
 
        String flowId = entity.getFlowId();
        if (flowId != null) {
            stmt.bindString(3, flowId);
        }
 
        String taskType = entity.getTaskType();
        if (taskType != null) {
            stmt.bindString(4, taskType);
        }
 
        String imageName = entity.getImageName();
        if (imageName != null) {
            stmt.bindString(5, imageName);
        }
 
        String imagePath = entity.getImagePath();
        if (imagePath != null) {
            stmt.bindString(6, imagePath);
        }
 
        String imageDescribe = entity.getImageDescribe();
        if (imageDescribe != null) {
            stmt.bindString(7, imageDescribe);
        }
 
        String imageLatitude = entity.getImageLatitude();
        if (imageLatitude != null) {
            stmt.bindString(8, imageLatitude);
        }
 
        String imageLongitude = entity.getImageLongitude();
        if (imageLongitude != null) {
            stmt.bindString(9, imageLongitude);
        }
 
        String imageAddress = entity.getImageAddress();
        if (imageAddress != null) {
            stmt.bindString(10, imageAddress);
        }
 
        String imageTime = entity.getImageTime();
        if (imageTime != null) {
            stmt.bindString(11, imageTime);
        }
 
        String imageUpload = entity.getImageUpload();
        if (imageUpload != null) {
            stmt.bindString(12, imageUpload);
        }
 
        String signId = entity.getSignId();
        if (signId != null) {
            stmt.bindString(13, signId);
        }
 
        String signName = entity.getSignName();
        if (signName != null) {
            stmt.bindString(14, signName);
        }
 
        String imageType = entity.getImageType();
        if (imageType != null) {
            stmt.bindString(15, imageType);
        }
 
        String imageSubtype = entity.getImageSubtype();
        if (imageSubtype != null) {
            stmt.bindString(16, imageSubtype);
        }
 
        String uploadEmp = entity.getUploadEmp();
        if (uploadEmp != null) {
            stmt.bindString(17, uploadEmp);
        }
 
        String uploadEmpName = entity.getUploadEmpName();
        if (uploadEmpName != null) {
            stmt.bindString(18, uploadEmpName);
        }
 
        String uploadTime = entity.getUploadTime();
        if (uploadTime != null) {
            stmt.bindString(19, uploadTime);
        }
 
        String seedKey = entity.getSeedKey();
        if (seedKey != null) {
            stmt.bindString(20, seedKey);
        }
        stmt.bindLong(21, entity.getEventCode());
        stmt.bindLong(22, entity.getTag());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PhotoInfo readEntity(Cursor cursor, int offset) {
        PhotoInfo entity = new PhotoInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // reportCode
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // flowId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // taskType
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // imageName
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // imagePath
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // imageDescribe
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // imageLatitude
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // imageLongitude
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // imageAddress
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // imageTime
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // imageUpload
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // signId
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // signName
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // imageType
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // imageSubtype
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // uploadEmp
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // uploadEmpName
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // uploadTime
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // seedKey
            cursor.getInt(offset + 20), // eventCode
            cursor.getInt(offset + 21) // tag
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PhotoInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setReportCode(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFlowId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTaskType(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setImageName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setImagePath(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setImageDescribe(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setImageLatitude(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setImageLongitude(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setImageAddress(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setImageTime(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setImageUpload(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setSignId(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setSignName(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setImageType(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setImageSubtype(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setUploadEmp(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setUploadEmpName(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setUploadTime(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setSeedKey(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setEventCode(cursor.getInt(offset + 20));
        entity.setTag(cursor.getInt(offset + 21));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PhotoInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PhotoInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PhotoInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}