package com.hl.contract.table.manager;

import android.content.Context;
import android.database.Cursor;

import com.hl.core.lib.CoreApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.table.dao.SearchRecordsDao;
import com.hl.contract.table.model.SearchRecords;

import java.util.ArrayList;
import java.util.List;

/**
 * @Describe:
 * @Author: liyu
 * @Date: 2018/1/23 18:13
 * @Copyright: hl
 */
public class SearchRecordManager {
    private static SearchRecordManager instance;
    private SearchRecordsDao searchRecordsDao;

    public static SearchRecordManager getInstance() {
        if (instance == null) {
            instance = new SearchRecordManager(CoreApplication.get());
        }
        return instance;
    }

    public SearchRecordManager(Context context) {
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        searchRecordsDao = daoSession.getSearchRecordsDao();
    }

    public void insertSearchRecord(SearchRecords searchRecord) {
        searchRecordsDao.insert(searchRecord);
    }

    public List<String> getLateRecords(String userCode, String type, int size) {
        size = size == 0 ? 10 : size;
        List<String> result = new ArrayList<>();
        String querySql = "SELECT DISTINCT "
                + SearchRecordsDao.Properties.KeyWord.columnName +
                " from "
                + SearchRecordsDao.TABLENAME
                + " where "
                + SearchRecordsDao.Properties.HandlerCode.columnName + " = '" + userCode +
                "' and "
                + SearchRecordsDao.Properties.SearchType.columnName + " = '" + type +
                "' order by " + SearchRecordsDao.Properties.OperatingTime.columnName + " desc ";
        Cursor cursor = searchRecordsDao.getDatabase().rawQuery(querySql, null);
        if (cursor != null) {
            while (cursor.moveToNext())
                result.add(cursor.getString(0));
            cursor.close();
        }
        if (result.size() > size) {
            result = result.subList(0, size);
        }
        return result;
    }

}
