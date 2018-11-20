package com.hl.core.lib.util.common;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;

/**
 * Created by wtt on 2017/11/30.
 */

public class CallTellTimeUtil {


    /**
         * 利用系统CallLog获取通话历史记录
         */
      public static String getCallHistoryList(Context context, ContentResolver cur,String telphone){
        Cursor cs = cur.query(CallLog.Calls.CONTENT_URI,//系统方式获取通讯录存储地址
                new String[]{
                        CallLog.Calls.CACHED_NAME,//姓名
                        CallLog.Calls.NUMBER,//号码
                        CallLog.Calls.TYPE,//呼入/呼出(2)/未接
                        CallLog.Calls.DATE, //拨打时间
                        CallLog.Calls.DURATION//通话时长

        },null,null,CallLog.Calls.DEFAULT_SORT_ORDER);
         String callHistoryListStr="";
        int i =0;

          boolean hasRecord = cs.moveToFirst();
          long incoming = 0L;
          long outgoing = 0L;
          while (hasRecord) {
              int type = cs.getInt(cs.getColumnIndex(CallLog.Calls.TYPE));
              long duration = cs.getLong(cs.getColumnIndex(CallLog.Calls.DURATION));
              String tel = cs.getString(cs.getColumnIndex(CallLog.Calls.NUMBER));
              if(telphone.equals(tel)){
                  switch (type) {
                      case CallLog.Calls.INCOMING_TYPE:
                          incoming += duration;
                          break;
                      case CallLog.Calls.OUTGOING_TYPE:
                          outgoing += duration;
                      default:
                          break;
                  }
              }
              hasRecord = cs.moveToNext();
          }

          //通话时长
          long callDuration=incoming+outgoing;
          long min=callDuration/60;
          long sec=callDuration%60;
          callHistoryListStr=min+"分"+sec+"秒";
          return callHistoryListStr;
//
//        if (cs!=null && cs.getCount()>0){
//            for (cs.moveToFirst();!cs.isAfterLast() & i<50;cs.moveToFirst()){
//                String callName =cs.getString(0);
//                String callNumber =telphone;
//                //通话类型
//                int callType =Integer.parseInt(cs.getString(2));
//                String callTypeStr ="";
//                switch (callType) {
//                  case CallLog.Calls.INCOMING_TYPE:
//                     callTypeStr="呼入";
//                      break;
//                  case CallLog.Calls.OUTGOING_TYPE:
//                     callTypeStr="呼出";
//                     break;
//                  case CallLog.Calls.MISSED_TYPE:
//                     callTypeStr="未接";
//                     break;
//                              }
//                //拨打时间
//                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date callDate=new Date(Long.parseLong(cs.getString(3)));
//                String callDateStr=dateformat.format(callDate);
//              //通话时长
//                int callDuration=Integer.parseInt(cs.getString(4));
//                int min=callDuration/60;
//                int sec=callDuration%60;
//                String callDurationStr=min+"分"+sec+"秒";
//                String  callOne=callDurationStr;
//                callHistoryListStr+=callOne;
//                i++;
//
//
//            }
//        }
    }
}