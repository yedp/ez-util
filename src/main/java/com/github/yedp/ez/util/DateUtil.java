package com.github.yedp.ez.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author yedp
 * 单例模式：lazy initailization holder class模式
 */
public class DateUtil {

    public static final String PATTERN_YM = "yyyy-MM";
    public static final String PATTERN_YMD = "yyyy-MM-dd";
    public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";

//1、日期字符串互转---------------------------------------------------------------------------------------------------

    /**
     * 转换日期格式为指定string格式
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date == null || pattern == null) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 转换日期格式为指定string格式
     *
     * @param date
     * @return
     */
    public static String format(Date date) {

        return format(date, PATTERN_STANDARD);
    }


    /**
     * 转换long为指定日期字符串
     *
     * @param time
     * @param pattern
     * @return
     */
    public static String format(Long time, String pattern) {
        return format(new Date(time), pattern);
    }

    /**
     * 将字符串转换为日期格式
     *
     * @param timeStr
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parse(String timeStr, String pattern) throws ParseException {
        if (timeStr == null) {
            return null;
        }
        return new SimpleDateFormat(pattern).parse(timeStr);
    }

    /**
     * 将字符串转换为日期格式
     *
     * @param timeStr
     * @return
     * @throws ParseException
     */
    public static Date parse(String timeStr) throws ParseException {
        return parse(timeStr, PATTERN_STANDARD);
    }
//2、日期获取-------------------------------------------------------------------------------------------------

    /**
     * 返回日期添加
     *
     * @param date Date
     * @return int
     */
    public static int getDate(Date date, int dateField) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(dateField);
    }

    public static int getMinute(Date date) {
        return getDate(date, Calendar.MINUTE);
    }

    public static int getHour(Date date) {
        return getDate(date, Calendar.HOUR);
    }

    public static int getDayOfWeek(Date date) {
        return getDate(date, Calendar.DAY_OF_WEEK);
    }

    public static int getDayOfMonth(Date date) {
        return getDate(date, Calendar.DAY_OF_MONTH);
    }

    public static int getDayOfYear(Date date) {
        return getDate(date, Calendar.DAY_OF_YEAR);
    }

    public static int getWeekOfMonth(Date date) {
        return getDate(date, Calendar.WEEK_OF_MONTH);
    }

    public static int getWeekOfYear(Date date) {
        return getDate(date, Calendar.WEEK_OF_YEAR);
    }

    public static int getMonth(Date date) {
        return getDate(date, Calendar.MONTH) + 1;
    }

    public static int getYear(Date date) {
        return getDate(date, Calendar.YEAR);
    }

    /**
     * 取得一个指定日期所在月的第一天
     *
     * @param date
     * @return
     */
    public Date getFirstDateOfMonth(Date date) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        calender.set(Calendar.DATE, 1);
        calender.set(Calendar.HOUR_OF_DAY, 0);
        calender.set(Calendar.MINUTE, 0);
        calender.set(Calendar.SECOND, 0);
        return calender.getTime();
    }

    /**
     * 取得一个指定日期所在月的最后一天
     *
     * @return
     */
    public Date getLastDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获入参时间后,在入参月所有日期列表（含当前日期）,格式"yyyyMMdd"
     *
     * @param date
     * @return
     */
    public List<String> getLastDayListOfMonth(Date date) {
        List<String> list = new ArrayList<String>();
        SimpleDateFormat df = new SimpleDateFormat(PATTERN_YMD);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        while (cal.get(Calendar.MONTH) == month) {
            list.add(df.format(cal.getTime()));
            cal.add(Calendar.DATE, 1);
        }
        return list;
    }

    /**
     * 获得某月的天数
     *
     * @param year  int
     * @param month int
     * @return int
     */
    public static int getDaysOfMonth(int year, int month) {
        int days = 0;
        if (month == 2) {
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                days = 29;
            } else {
                days = 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            days = 30;
        } else {
            days = 31;
        }
        return days;
    }

    public static int getDaysOfMonth(Date date) {
        if (date == null) {
            return 0;
        }
        int year = getYear(date);
        int month = getMonth(date);
        return getDaysOfMonth(year, month);
    }


    public static long dayDiff(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / 86400000;
    }

    public static int yearDiff(Date date1, Date date2) {
        return getYear(date2) - getYear(date1);
    }


//3、日期增减-------------------------------------------------------------------------------------------------

    /**
     * 日期添加
     *
     * @param date
     * @param dateField
     * @param amount
     * @return
     */
    public static Date addDate(Date date, int dateField, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateField, amount);
        return calendar.getTime();
    }

    public static Date addSecond(Date date, int amount) {
        return addDate(date, Calendar.SECOND, amount);
    }

    public static Date addMinute(Date date, int amount) {
        return addDate(date, Calendar.MINUTE, amount);
    }

    public static Date addHour(Date date, int amount) {
        return addDate(date, Calendar.HOUR, amount);
    }

    public static Date addDay(Date date, int amount) {
        return addDate(date, Calendar.DAY_OF_MONTH, amount);
    }

    public static Date addWeek(Date date, int amount) {
        return addDate(date, Calendar.WEEK_OF_YEAR, amount);
    }

    public static Date addMonth(Date date, int amount) {
        return addDate(date, Calendar.MONTH, amount);
    }


}
