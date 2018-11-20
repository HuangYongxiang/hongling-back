package com.hl.core.lib.network.download.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @Describe:数据缓存（如：查询历史数据）
 * @Package: com.hl.core.lib.network.download.db
 * @Author: liyu
 * @Date: 2018/1/29 0029 上午 9:41
 * @Copyright: hl
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "app.db";
    //数据库升级，放弃老数据。
    public static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DownLoadDatabase.DownLoad.CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DownLoadDatabase.DownLoad.DROP_SQL);
        onCreate(db);
    }

}