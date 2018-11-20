package com.hl.contract.table;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hl.contract.table.dao.DaoMaster;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.table.dao.SearchRecordsDao;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.EncryptedDatabase;

import java.io.File;
import java.io.IOException;

/**
 * @Describe:
 * @Package: com.hl.mobile.mylibrary.download.db
 * @Author: liyu
 * @Date: 2018/1/2 0002 下午 18:10
 * @Copyright: hl
 */
public class SurveyGreenDaoHelper {
    private static SurveyGreenDaoHelper Instance;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private static String dbName="hongling.db";
    private static String dbPath="/hongling/database";
    public static SurveyGreenDaoHelper getInstance() {
        synchronized (SurveyGreenDaoHelper.class){
            if (Instance == null) {
                Instance = new SurveyGreenDaoHelper();
            }
        }
        return Instance;
    }

    /**
     * 获取DaoMaster
     *
     * @param context
     * @return
     */
    public DaoMaster getDaoMaster(Context context) {

        if (daoMaster == null) {

            try{
                ContextWrapper wrapper = new ContextWrapper(context) {
                /**
                 * 获得数据库路径，如果不存在，则创建对象对象
                 *
                 * @param name
                 */
                @Override
                public File getDatabasePath(String name) {
                    // 判断是否存在sd卡
                    boolean sdExist = android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment.getExternalStorageState());
                    if (!sdExist) {// 如果不存在,
                        Log.e("SD卡管理：", "SD卡不存在，请加载SD卡");
                        return null;
                    } else {// 如果存在
                        // 获取sd卡路径
                        String dbDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
                        dbDir +=dbPath ;// 数据库所在目录
                        String dbPath = dbDir + "/" + name;// 数据库路径
                        // 判断目录是否存在，不存在则创建该目录
                        File dirFile = new File(dbDir);

                        if (!dirFile.exists())
                            dirFile.mkdirs();
                        // 数据库文件是否创建成功
                        boolean isFileCreateSuccess = false;
                        // 判断文件是否存在，不存在则创建该文件
                        File dbFile = new File(dbPath);
                        if (!dbFile.exists()) {
                            try {
                                isFileCreateSuccess = dbFile.createNewFile();// 创建文件
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else
                            try {
                                dbFile.delete();
                                isFileCreateSuccess = dbFile.createNewFile();// 创建文件
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //isFileCreateSuccess = true;
                        // 返回数据库文件对象
                        if (isFileCreateSuccess)
                            return dbFile;
                        else
                            return super.getDatabasePath(name);
                    }
                }

                /**
                 * 重载这个方法，是用来打开SD卡上的数据库的，android 2.3及以下会调用这个方法。
                 *
                 * @param name
                 * @param mode
                 * @param factory
                 */
                @Override
                public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
                    return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
                }

                /**
                 * Android 4.0会调用此方法获取数据库。
                 *
                 * @see ContextWrapper#openOrCreateDatabase(String,
                 *      int,
                 *      SQLiteDatabase.CursorFactory,
                 *      DatabaseErrorHandler)
                 * @param name
                 * @param mode
                 * @param factory
                 * @param errorHandler
                 */
                @Override
                public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
                    return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
                }
                };
                //适用于未加密的数据库
                DaoMaster.OpenHelper helper = new MySQLiteOpenHelper(wrapper,dbName,null);
                daoMaster = new DaoMaster(helper.getWritableDatabase()); //获取未加密的数据库

                //适用于加密的数据库
//                MyEncryptedSQLiteOpenHelper helper = new MyEncryptedSQLiteOpenHelper(wrapper,"test.db",null);
//                daoMaster = new DaoMaster(helper.getEncryptedWritableDb("1234"));//获取可读写的加密数据库
                //daoMaster = new DaoMaster(helper.getEncryptedReadableDb("1234"));//获取只读的加密数据库

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return daoMaster;
    }

    /**
     * 获取DaoSession对象
     *
     * @param context
     * @return
     */
    public DaoSession getDaoSession(Context context) {

        if (daoSession == null) {
            if (daoMaster == null) {
                getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }

        return daoSession;
    }

    /**
     * 适用于未加密的数据库
     */
    private class MySQLiteOpenHelper extends DaoMaster.OpenHelper {

        public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
        }

        private static final String UPGRADE="upgrade";

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if(oldVersion < 2){
                MigrationHelper.migrate(db,SearchRecordsDao.class);
            }
            Log.e(UPGRADE,"upgrade run success");
        }
    }

    /**
     * 适用于加密的数据库
     */
    private class MyEncryptedSQLiteOpenHelper extends DaoMaster.OpenHelper {

        private final Context context;
        private final String name;
        private final int version = DaoMaster.SCHEMA_VERSION;

        private boolean loadSQLCipherNativeLibs = true;

        public MyEncryptedSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
            this.context=context;
            this.name=name;
        }

        private static final String UPGRADE="upgrade";

        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {

            Log.e(UPGRADE,"upgrade run success");
        }

        @Override
        public Database getEncryptedWritableDb(String password) {
            MyEncryptedHelper encryptedHelper = new MyEncryptedHelper(context,name,version,loadSQLCipherNativeLibs);
            return encryptedHelper.wrap(encryptedHelper.getReadableDatabase(password));
        }

        private class MyEncryptedHelper extends net.sqlcipher.database.SQLiteOpenHelper {
            public MyEncryptedHelper(Context context, String name, int version, boolean loadLibs) {
                super(context, name, null, version);
                if (loadLibs) {
                    net.sqlcipher.database.SQLiteDatabase.loadLibs(context);
                }
            }

            @Override
            public void onCreate(net.sqlcipher.database.SQLiteDatabase db) {
                SurveyGreenDaoHelper.MyEncryptedSQLiteOpenHelper.this.onCreate(wrap(db));
            }

            @Override
            public void onUpgrade(net.sqlcipher.database.SQLiteDatabase db, int oldVersion, int newVersion) {
                SurveyGreenDaoHelper.MyEncryptedSQLiteOpenHelper.this.onUpgrade(wrap(db), oldVersion, newVersion);
            }

            @Override
            public void onOpen(net.sqlcipher.database.SQLiteDatabase db) {
                SurveyGreenDaoHelper.MyEncryptedSQLiteOpenHelper.this.onOpen(wrap(db));
            }

            protected Database wrap(net.sqlcipher.database.SQLiteDatabase sqLiteDatabase) {
                return new EncryptedDatabase(sqLiteDatabase);
            }
        }
    }
}
