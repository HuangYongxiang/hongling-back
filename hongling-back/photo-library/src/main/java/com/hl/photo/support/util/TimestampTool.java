package com.hl.photo.support.util;


import android.text.TextUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimestampTool {

    // 当前时间
    public static Timestamp crunttime() {

        return new Timestamp(System.currentTimeMillis());
    }

    //获取当前时间的字符串  2006-07-07
    public static String getCurrentDate() {
        Timestamp d = crunttime();
        return d.toString().substring(0, 10);
    }

    //获取当前时间的字符串  2006
    public static String getCurrentYear() {
        Timestamp d = crunttime();
        return d.toString().substring(0, 4);
    }

    //	获取当前时间的字符串 2006-07-07 22:10:10
    public static String getCurrentDateTime() {
        Timestamp d = crunttime();
        return d.toString().substring(0, 19);
    }

    //	获取当前时间的字符串(没有秒)   2006-07-07 22:10
    public static String getCurrentDateHm() {
        Timestamp d = crunttime();
        return d.toString().substring(0, 16);
    }

    //	获取给定时间的字符串,只有日期 2006-07-07
    public static String getStrDate(Timestamp t) {
        return t.toString().substring(0, 10);
    }

    //	获取给定时间的字符串   2006-07-07 22:10:10
    public static String getStrDateTime(Timestamp t) {
        return t.toString().substring(0, 19);
    }

    //	获取给定时间的字符串(没有秒)  2006-07-07 22:10
    public static String getStrDateHm(Timestamp t) {
        return t.toString().substring(0, 16);
    }

    // 返回日期 格式:2006-07-05
    public static Timestamp date(String str) {
        Timestamp tp = null;
        if (str.length() <= 10) {
            String[] string = str.trim().split("-");
            int one = Integer.parseInt(string[0]) - 1900;
            int two = Integer.parseInt(string[1]) - 1;
            int three = Integer.parseInt(string[2]);
            tp = new Timestamp(one, two, three, 0, 0, 0, 0);
        }
        return tp;
    }

    public static Timestamp addDate(int day) {
        return new Timestamp(crunttime().getTime() + day * 24 * 60 * 60 * 1000);
    }

    // 返回时间和日期  格式:2006-07-05 22:10:10
    public static Timestamp datetime(String str) {
        if(TextUtils.isEmpty(str)){
            return null;
        }
        Timestamp tp = null;
        if (str.length() > 10) {
            String[] string = str.trim().split(" ");
            String[] date = string[0].split("-");
            String[] time = string[1].split(":");
            int date1 = Integer.parseInt(date[0]) - 1900;
            int date2 = Integer.parseInt(date[1]) - 1;
            int date3 = Integer.parseInt(date[2]);
            int time1 = Integer.parseInt(time[0]);
            int time2 = Integer.parseInt(time[1]);
            int time3 = Integer.parseInt(time[2]);
            tp = new Timestamp(date1, date2, date3, time1, time2, time3, 0);
        }
        return tp;
    }


    // 返回日期和时间(没有秒)  格式:2006-07-05 22:10
    public static Timestamp datetimeHm(String str) {
        Timestamp tp = null;
        if (str.length() > 10) {
            String[] string = str.trim().split(" ");
            String[] date = string[0].split("-");
            String[] time = string[1].split(":");
            int date1 = Integer.parseInt(date[0]) - 1900;
            int date2 = Integer.parseInt(date[1]) - 1;
            int date3 = Integer.parseInt(date[2]);
            int time1 = Integer.parseInt(time[0]);
            int time2 = Integer.parseInt(time[1]);
            tp = new Timestamp(date1, date2, date3, time1, time2, 0, 0);
        }
        return tp;
    }

    //	获取当前日期之前的日期字符串 如 2007-04-15  前5月 就是 2006-11-15
    public static String getPreviousMonth(int month) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date());
        cal1.add(Calendar.MONTH, -month);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(cal1.getTime());
    }

    public static String getYearMonthDay() {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        return formatter.format(cal1.getTime());
    }

    //	获取当前日期之后的日期字符串 如 2007-04-15  后一天 就是 2007-04-16
    public static String getNextDay(int day) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date());
        cal1.add(Calendar.DAY_OF_MONTH, day);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(cal1.getTime());
    }

    //	获取指定日期之前的日期字符串 如 2007-04-15  前一天 就是 2007-04-14
    public static String getPreviousDay(String strDate, int day) {
        Calendar cal1 = Calendar.getInstance();
        String[] string = strDate.trim().split("-");
        int one = Integer.parseInt(string[0]) - 1900;
        int two = Integer.parseInt(string[1]) - 1;
        int three = Integer.parseInt(string[2]);
        cal1.setTime(new Date(one, two, three));
        cal1.add(Calendar.DAY_OF_MONTH, -day);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(cal1.getTime());
    }


    //	获取指定日期之后的日期字符串 如 2007-04-15  后一天 就是 2007-04-16
    public static String getNextDay(String strDate, int day) {
        Calendar cal1 = Calendar.getInstance();
        String[] string = strDate.trim().split("-");
        int one = Integer.parseInt(string[0]) - 1900;
        int two = Integer.parseInt(string[1]) - 1;
        int three = Integer.parseInt(string[2]);
        cal1.setTime(new Date(one, two, three));
        cal1.add(Calendar.DAY_OF_MONTH, day);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(cal1.getTime());
    }

    //	获取指定日期之前的月字符串 如 2007-04-15  后一月 就是 2007-05-15
    public static String getNextMonth(String strDate, int month) {
        Calendar cal1 = Calendar.getInstance();
        String[] string = strDate.trim().split("-");
        int one = Integer.parseInt(string[0]) - 1900;
        int two = Integer.parseInt(string[1]) - 1;
        int three = Integer.parseInt(string[2]);
        cal1.setTime(new Date(one, two, three));
        cal1.add(Calendar.MONTH, month);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(cal1.getTime());
    }

    //将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = (Date) formatter.parse(strDate, pos);
        if (strtodate == null || "".equals(strtodate)) {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            strtodate = (Date) formatter.parse(strDate, pos);
        }
        return strtodate;
    }

    public static Date stringToDate(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date strtodate = null;
        try {
            strtodate = sdf.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return strtodate;
    }

    public static Date strToDateHM(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = (Date) formatter.parse(strDate, pos);
        if (strtodate == null || "".equals(strtodate)) {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            strtodate = (Date) formatter.parse(strDate, pos);
        }
        return strtodate;
    }

    //根据输入的字符串返回日期字符串 2006-07-07 22:10   2006-07-07
    public static String getStrDate(String str) {
        if (str != null && str.length() > 10) {
            String[] string = str.trim().split(" ");
            return string[0];
        }else if(str!=null && str.length() == 10){
            return str;
        } else {
            return getCurrentDate();
        }
    }


    public static String getPolicyDate(String str) {
        if (str.length() > 10) {
            String[] string = str.trim().split(" ");
            return string[0];
        } else if (str.length() == 10) {
            return str;
        } else {
            return getCurrentDate();
        }
    }

    //	获取当前时间的字符串  2006-07-07 22:10:10 2006-07-07_221010
    public static String getStrDateTime() {
        Timestamp d = crunttime();
        return d.toString().replace(":", "").replace(" ", "_").replace(".", "");
    }

    //根据日期字符串，返回今天，昨天或日期
    public static String getDayOrDate(String str) {
        if (str != null && !str.equals("")) {
            if (getNextDay(str, 0).equals(getCurrentDate())) {
                str = "今天";
            } else if (getNextDay(str, 1).equals(getCurrentDate())) {
                str = "昨天";
            }
        }
        return str;
    }

    //	返回当前日期所在星期，2对应星期一
    public static int getMonOfWeek() {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date());
        return cal1.get(Calendar.DAY_OF_WEEK);
    }

    public static String date2String(Date date) {
        //因为目前的数据不对，非空数据也可能传过来空值，所以当时间为空时做个假数据传回
        if (date == null) {
            return "2017-1-12 12:13";
        }
        //假数据制造代码结束。该段代码以后要删除
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = formatter.format(date);
        return format;
    }

    public static String date2StringYMD(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String format = formatter.format(date);
        return format;
    }

    public static void main(String[] args) {
//		System.out.println(TimestampTool.getStrDateHm(TimestampTool.addDate(1)));
        System.out.println(TimestampTool.strToDateLong("2008-9-10 13:52:"));
        System.out.println(TimestampTool.getStrDate("2011-3-26 10:23:35"));
        //System.out.println(new java.sql.Date((new Date()).getTime()));
    }

    public static String getDateToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    public static int getAge(String birthYear , String birthMonth){
        int age = 0;
        if(!TextUtils.isEmpty(birthYear) && !TextUtils.isEmpty(birthMonth)){
            Date now = new Date();
            SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
            SimpleDateFormat format_M = new SimpleDateFormat("MM");
            String this_year = format_y.format(now);
            String this_month = format_M.format(now);
            // 初步，估算
            age = Integer.parseInt(this_year) - Integer.parseInt(birthYear);

            // 如果未到出生月份，则age - 1
            if (this_month.compareTo(birthMonth) < 0)
                age -= 1;
            if (age < 0)
                age = 0;
        }
        return age;
    }

    //yyyy-MM-dd HH:mm:ss 转 yyyy-MM-dd
    public static String getShortTime(String longTime){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String shortTime = null;
        try{
            Date date = formatter.parse(longTime);
            shortTime = sdf.format(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return shortTime;
    }

    /**
     * 获取指定网站的日期时间
     *
     * @param webUrl
     * @return
     * @author LIYU
     * @date   2017年11月23日
     */
    public static String getWebsiteDatetime(){
        String ee = "";
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        ee = dff.format(new Date());
        return ee;
    }

}
