package com.hl.contract.table.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.hl.contract.table.model.UserInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_INFO".
*/
public class UserInfoDao extends AbstractDao<UserInfo, String> {

    public static final String TABLENAME = "USER_INFO";

    /**
     * Properties of entity UserInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        public final static Property UserName = new Property(1, String.class, "userName", false, "USER_NAME");
        public final static Property PassWord = new Property(2, String.class, "passWord", false, "PASS_WORD");
        public final static Property UserId = new Property(3, String.class, "userId", false, "USER_ID");
        public final static Property HandlerName = new Property(4, String.class, "handlerName", false, "HANDLER_NAME");
        public final static Property CompanyCode = new Property(5, String.class, "companyCode", false, "COMPANY_CODE");
        public final static Property CompanyName = new Property(6, String.class, "companyName", false, "COMPANY_NAME");
        public final static Property BranchCompanyCode = new Property(7, String.class, "branchCompanyCode", false, "BRANCH_COMPANY_CODE");
        public final static Property BranchCompanyName = new Property(8, String.class, "branchCompanyName", false, "BRANCH_COMPANY_NAME");
        public final static Property Addr = new Property(9, String.class, "addr", false, "ADDR");
        public final static Property Telephone = new Property(10, String.class, "telephone", false, "TELEPHONE");
        public final static Property SurveyCeritCode = new Property(11, String.class, "surveyCeritCode", false, "SURVEY_CERIT_CODE");
        public final static Property DeviceNo = new Property(12, String.class, "deviceNo", false, "DEVICE_NO");
        public final static Property MachineModel = new Property(13, String.class, "machineModel", false, "MACHINE_MODEL");
        public final static Property SignInId = new Property(14, String.class, "signInId", false, "SIGN_IN_ID");
        public final static Property Account = new Property(15, String.class, "account", false, "ACCOUNT");
        public final static Property Name = new Property(16, String.class, "name", false, "NAME");
        public final static Property StoreCode = new Property(17, String.class, "storeCode", false, "STORE_CODE");
        public final static Property StoreName = new Property(18, String.class, "storeName", false, "STORE_NAME");
        public final static Property RoleCode = new Property(19, String.class, "roleCode", false, "ROLE_CODE");
        public final static Property RoleName = new Property(20, String.class, "roleName", false, "ROLE_NAME");
        public final static Property RoleContent = new Property(21, String.class, "roleContent", false, "ROLE_CONTENT");
    }


    public UserInfoDao(DaoConfig config) {
        super(config);
    }
    
    public UserInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_INFO\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: id
                "\"USER_NAME\" TEXT," + // 1: userName
                "\"PASS_WORD\" TEXT," + // 2: passWord
                "\"USER_ID\" TEXT," + // 3: userId
                "\"HANDLER_NAME\" TEXT," + // 4: handlerName
                "\"COMPANY_CODE\" TEXT," + // 5: companyCode
                "\"COMPANY_NAME\" TEXT," + // 6: companyName
                "\"BRANCH_COMPANY_CODE\" TEXT," + // 7: branchCompanyCode
                "\"BRANCH_COMPANY_NAME\" TEXT," + // 8: branchCompanyName
                "\"ADDR\" TEXT," + // 9: addr
                "\"TELEPHONE\" TEXT," + // 10: telephone
                "\"SURVEY_CERIT_CODE\" TEXT," + // 11: surveyCeritCode
                "\"DEVICE_NO\" TEXT," + // 12: deviceNo
                "\"MACHINE_MODEL\" TEXT," + // 13: machineModel
                "\"SIGN_IN_ID\" TEXT," + // 14: signInId
                "\"ACCOUNT\" TEXT," + // 15: account
                "\"NAME\" TEXT," + // 16: name
                "\"STORE_CODE\" TEXT," + // 17: storeCode
                "\"STORE_NAME\" TEXT," + // 18: storeName
                "\"ROLE_CODE\" TEXT," + // 19: roleCode
                "\"ROLE_NAME\" TEXT," + // 20: roleName
                "\"ROLE_CONTENT\" TEXT);"); // 21: roleContent
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserInfo entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(2, userName);
        }
 
        String passWord = entity.getPassWord();
        if (passWord != null) {
            stmt.bindString(3, passWord);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(4, userId);
        }
 
        String handlerName = entity.getHandlerName();
        if (handlerName != null) {
            stmt.bindString(5, handlerName);
        }
 
        String companyCode = entity.getCompanyCode();
        if (companyCode != null) {
            stmt.bindString(6, companyCode);
        }
 
        String companyName = entity.getCompanyName();
        if (companyName != null) {
            stmt.bindString(7, companyName);
        }
 
        String branchCompanyCode = entity.getBranchCompanyCode();
        if (branchCompanyCode != null) {
            stmt.bindString(8, branchCompanyCode);
        }
 
        String branchCompanyName = entity.getBranchCompanyName();
        if (branchCompanyName != null) {
            stmt.bindString(9, branchCompanyName);
        }
 
        String addr = entity.getAddr();
        if (addr != null) {
            stmt.bindString(10, addr);
        }
 
        String telephone = entity.getTelephone();
        if (telephone != null) {
            stmt.bindString(11, telephone);
        }
 
        String surveyCeritCode = entity.getSurveyCeritCode();
        if (surveyCeritCode != null) {
            stmt.bindString(12, surveyCeritCode);
        }
 
        String deviceNo = entity.getDeviceNo();
        if (deviceNo != null) {
            stmt.bindString(13, deviceNo);
        }
 
        String machineModel = entity.getMachineModel();
        if (machineModel != null) {
            stmt.bindString(14, machineModel);
        }
 
        String signInId = entity.getSignInId();
        if (signInId != null) {
            stmt.bindString(15, signInId);
        }
 
        String account = entity.getAccount();
        if (account != null) {
            stmt.bindString(16, account);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(17, name);
        }
 
        String storeCode = entity.getStoreCode();
        if (storeCode != null) {
            stmt.bindString(18, storeCode);
        }
 
        String storeName = entity.getStoreName();
        if (storeName != null) {
            stmt.bindString(19, storeName);
        }
 
        String roleCode = entity.getRoleCode();
        if (roleCode != null) {
            stmt.bindString(20, roleCode);
        }
 
        String roleName = entity.getRoleName();
        if (roleName != null) {
            stmt.bindString(21, roleName);
        }
 
        String roleContent = entity.getRoleContent();
        if (roleContent != null) {
            stmt.bindString(22, roleContent);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserInfo entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(2, userName);
        }
 
        String passWord = entity.getPassWord();
        if (passWord != null) {
            stmt.bindString(3, passWord);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(4, userId);
        }
 
        String handlerName = entity.getHandlerName();
        if (handlerName != null) {
            stmt.bindString(5, handlerName);
        }
 
        String companyCode = entity.getCompanyCode();
        if (companyCode != null) {
            stmt.bindString(6, companyCode);
        }
 
        String companyName = entity.getCompanyName();
        if (companyName != null) {
            stmt.bindString(7, companyName);
        }
 
        String branchCompanyCode = entity.getBranchCompanyCode();
        if (branchCompanyCode != null) {
            stmt.bindString(8, branchCompanyCode);
        }
 
        String branchCompanyName = entity.getBranchCompanyName();
        if (branchCompanyName != null) {
            stmt.bindString(9, branchCompanyName);
        }
 
        String addr = entity.getAddr();
        if (addr != null) {
            stmt.bindString(10, addr);
        }
 
        String telephone = entity.getTelephone();
        if (telephone != null) {
            stmt.bindString(11, telephone);
        }
 
        String surveyCeritCode = entity.getSurveyCeritCode();
        if (surveyCeritCode != null) {
            stmt.bindString(12, surveyCeritCode);
        }
 
        String deviceNo = entity.getDeviceNo();
        if (deviceNo != null) {
            stmt.bindString(13, deviceNo);
        }
 
        String machineModel = entity.getMachineModel();
        if (machineModel != null) {
            stmt.bindString(14, machineModel);
        }
 
        String signInId = entity.getSignInId();
        if (signInId != null) {
            stmt.bindString(15, signInId);
        }
 
        String account = entity.getAccount();
        if (account != null) {
            stmt.bindString(16, account);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(17, name);
        }
 
        String storeCode = entity.getStoreCode();
        if (storeCode != null) {
            stmt.bindString(18, storeCode);
        }
 
        String storeName = entity.getStoreName();
        if (storeName != null) {
            stmt.bindString(19, storeName);
        }
 
        String roleCode = entity.getRoleCode();
        if (roleCode != null) {
            stmt.bindString(20, roleCode);
        }
 
        String roleName = entity.getRoleName();
        if (roleName != null) {
            stmt.bindString(21, roleName);
        }
 
        String roleContent = entity.getRoleContent();
        if (roleContent != null) {
            stmt.bindString(22, roleContent);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public UserInfo readEntity(Cursor cursor, int offset) {
        UserInfo entity = new UserInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // passWord
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // userId
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // handlerName
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // companyCode
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // companyName
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // branchCompanyCode
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // branchCompanyName
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // addr
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // telephone
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // surveyCeritCode
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // deviceNo
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // machineModel
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // signInId
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // account
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // name
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // storeCode
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // storeName
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // roleCode
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // roleName
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21) // roleContent
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setUserName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPassWord(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUserId(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setHandlerName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCompanyCode(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCompanyName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setBranchCompanyCode(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setBranchCompanyName(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setAddr(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setTelephone(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setSurveyCeritCode(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setDeviceNo(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setMachineModel(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setSignInId(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setAccount(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setName(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setStoreCode(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setStoreName(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setRoleCode(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setRoleName(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setRoleContent(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
     }
    
    @Override
    protected final String updateKeyAfterInsert(UserInfo entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public String getKey(UserInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
