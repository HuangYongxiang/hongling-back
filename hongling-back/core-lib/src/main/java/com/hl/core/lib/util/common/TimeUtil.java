package com.hl.core.lib.util.common;


import android.text.TextUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Describe: 日期、时间格式处理
 * @Package: com.hl.core.lib.util
 * @Author: liyu
 * @Date: 2018/1/4 20:09
 * @Copyright: hl
 */
public class TimeUtil {

    /**
     * 获取当前时间
     * @return Timestamp
     */
    public static Timestamp crunttime() {

        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取当前时间的字符串 2018-01-01 22:10:10
     * @return
     */
    public static String getCurrentDateTime() {
        Timestamp d = crunttime();
        return d.toString().substring(0, 19);
    }

    /**
     * 获取当前年月日  2018-01-01
     * @return String
     */
    public static String getCurrentDate() {
        Timestamp d = crunttime();
        return d.toString().substring(0, 10);
    }

    /**
     * 获取当前年  2018
     * @return String
     */
    public static String getCurrentYear() {
        Timestamp d = crunttime();
        return d.toString().substring(0, 4);
    }

    /**
     * 获取当前时间的字符串(没有秒)   2018-01-01 22:10
     * @return String
     */
    public static String getCurrentDateHm() {
        Timestamp d = crunttime();
        return d.toString().substring(0, 16);
    }

    /**
     * 获取当前年月日
     * @return yyyy年MM月dd日
     */
    public static String getYearMonthDay() {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        return formatter.format(cal1.getTime());
    }
    /**
     * 获取当前年月日
     * @return yyyy年MM月dd日
     */
    public static String getYearMonthDay(String time) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date(time));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        return formatter.format(cal1.getTime());
    }

    /**
     * Date格式转String
     * @param date
     * @return String : yyyy-MM-dd HH:mm:ss
     */
    public static String dateToStringYMDHMS(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formatter.format(date);
        return format;
    }

    /**
     * Date格式转String
     * @param date
     * @return String : yyyy-MM-dd HH:mm
     */
    public static String dateToStringYMDHM(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = formatter.format(date);
        return format;
    }

    /**
     * Date格式转String
     * @param date
     * @return String : yyyy-MM-dd
     */
    public static String dateToStringYMD(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String format = formatter.format(date);
        return format;
    }
    /**
     * Date格式转String
     * @param date
     * @return String : yyyy
     */
    public static String dateToStringY(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        String format = formatter.format(date);
        return format;
    }

    /**
     * 获取给定时间的字符串,只有日期 2018-01-01
     * @param t
     * @return String
     */
    public static String getStrDate(Timestamp t) {
        return t.toString().substring(0, 10);
    }

    /**
     * 获取给定时间的字符串 2018-01-01 22:10:10
     * @param t
     * @return String
     */
    public static String getStrDateTime(Timestamp t) {
        return t.toString().substring(0, 19);
    }

    /**
     * 获取给定时间的字符串(没有秒) 2018-01-01 22:10
     * @param t
     * @return String
     */
    public static String getStrDateHm(Timestamp t) {
        return t.toString().substring(0, 16);
    }

    /**
     * 返回日期，时间格式
     * @param str  格式:2018-01-01
     * @return Timestamp
     */
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

    /**
     * 返回时间和日期，时间格式
     * @param str 格式:2018-01-01 22:10:10
     * @return Timestamp
     */
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


    /**
     * 返回日期和时间(没有秒)，时间格式
     * @param str 格式:2018-01-01 22:10
     * @return Timestamp
     */
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

    /**
     * 计算几天后的时间，返回时间格式
     * @param day
     * @return Timestamp
     */
    public static Timestamp addDate(int day) {
        return new Timestamp(crunttime().getTime() + day * 24 * 60 * 60 * 1000);
    }

    /**
     * 获取当前日期之前的日期  月：如 2017-04-15  前5月 就是 2016-11-15
     * @param month
     * @return String
     */
    public static String getPreviousMonth(int month) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date());
        cal1.add(Calendar.MONTH, -month);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(cal1.getTime());
    }


    /**
     * 获取当前日期之后的日期字符串   日：2017-04-15  后一天 就是 2017-04-16
     * @param day
     * @return
     */
    public static String getNextDay(int day) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date());
        cal1.add(Calendar.DAY_OF_MONTH, day);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(cal1.getTime());
    }

    /**
     * 获取指定日期之后的日期字符串   日 ：如 2017-04-15  后一天 就是 2017-04-16
     * @param strDate 指定日期
     * @param day 指定天数
     * @return
     */
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

    /**
     * 获取指定日期之前的月字符串   月：如 20017-04-15  后一月 就是 2017-05-15
     * @param strDate 指定日期
     * @param month 指定月数
     * @return
     */
    public static String getNextMonth(String strDate, int month) {
        Calendar cal1 = Calendar.getInstance();
        String[] string = strDate.trim().split("-");
        int one = Integer.parseInt(string[0]) - 1900;
        int two = Integer.parseInt(string[1]) - 1;
        int three = Integer.parseInt(string[2]);
        cal1.setTime(new Date(one, two, three));
        cal1.add(Calendar.MONTH, month);
        cal1.add(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(cal1.getTime());
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     * @param strDate String
     * @return Date
     */
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

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm
     * @param strDate String
     * @return Date
     */
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

    /**
     * 根据输入的字符串返回日期字符串
     * @param str 2018-07-07 22:10
     * @return 2018-07-07
     */
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

    /**
     * 根据日期字符串，返回今天，昨天或日期
     * @param str
     * @return
     */
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

    /**
     * 返回当前日期所在星期，2对应星期一
     * @return
     */
    public static int getMonOfWeek() {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date());
        return cal1.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 根据出生年月返回年龄
     * @param birthYear  年
     * @param birthMonth  月
     * @return  年龄
     */
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

    /**
     * yyyy-MM-dd HH:mm:ss 转 yyyy-MM-dd
     * @param longTime
     * @return
     */
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
     * @return String
     */
    public static String getWebsiteDatetime(){
        String ee = "";
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        ee = dff.format(new Date());
        return ee;
    }

}
