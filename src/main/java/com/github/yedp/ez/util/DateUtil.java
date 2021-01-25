package com.github.yedp.ez.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author yedp
 * 日期工具类
 */
public class DateUtil {
    public static final String PATTERN_YM = "yyyy-MM";
    public static final String PATTERN_YMD = "yyyy-MM-dd";
    public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_MILLIS = "yyyy-MM-dd HH:mm:ss:SSS";
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
    public static int getDateFieldValue(Date date, int dateField) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(dateField);
    }

    public static int getMinute(Date date) {
        return getDateFieldValue(date, Calendar.MINUTE);
    }

    public static int getHour(Date date) {
        return getDateFieldValue(date, Calendar.HOUR);
    }

    public static int getDayOfWeek(Date date) {
        return getDateFieldValue(date, Calendar.DAY_OF_WEEK)-1;
    }

    public static int getDayOfMonth(Date date) {
        return getDateFieldValue(date, Calendar.DAY_OF_MONTH);
    }

    public static int getDayOfYear(Date date) {
        return getDateFieldValue(date, Calendar.DAY_OF_YEAR);
    }

    public static int getWeekOfMonth(Date date) {
        return getDateFieldValue(date, Calendar.WEEK_OF_MONTH);
    }

    public static int getWeekOfYear(Date date) {
        return getDateFieldValue(date, Calendar.WEEK_OF_YEAR);
    }

    public static int getMonth(Date date) {
        return getDateFieldValue(date, Calendar.MONTH) + 1;
    }

    public static int getYear(Date date) {
        return getDateFieldValue(date, Calendar.YEAR);
    }

    public static int getMinute() {
        return getDateFieldValue(curTime(), Calendar.MINUTE);
    }

    public static int getHour() {
        return getDateFieldValue(curTime(), Calendar.HOUR);
    }

    public static int getDayOfWeek() {
        return getDateFieldValue(curTime(), Calendar.DAY_OF_WEEK);
    }

    public static int getDayOfMonth() {
        return getDateFieldValue(curTime(), Calendar.DAY_OF_MONTH);
    }

    public static int getDayOfYear() {
        return getDateFieldValue(curTime(), Calendar.DAY_OF_YEAR);
    }

    public static int getWeekOfMonth() {
        return getDateFieldValue(curTime(), Calendar.WEEK_OF_MONTH);
    }

    public static int getWeekOfYear() {
        return getDateFieldValue(curTime(), Calendar.WEEK_OF_YEAR);
    }

    public static int getMonth() {
        return getDateFieldValue(curTime(), Calendar.MONTH) + 1;
    }

    public static int getYear() {
        return getDateFieldValue(curTime(), Calendar.YEAR);
    }


    public static Date curTime() {
        return Calendar.getInstance().getTime();
    }

    public static String curYearMonth() {
        return format(curTime(), PATTERN_YM);
    }

    public static String curDay() {
        return format(curTime(), PATTERN_YMD);
    }

    public static String curTimeStr() {
        return format(curTime(), PATTERN_STANDARD);
    }
    /**
     * 获取本周第一天
     *
     * @return
     */
    public static Date getFirstDateOfWeek(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());// Monday
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取本周最后一天
     *
     * @return
     */
    public static Date getLastDateOfWeek(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);// Sunday
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static Date getFirstDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getLastDateOfMonth(Date date) {
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

    public List<String> getLastDayListOfMonth(Date date) {
        List<String> list = new ArrayList<String>();
        SimpleDateFormat df = new SimpleDateFormat(PATTERN_YMD);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        while (calendar.get(Calendar.MONTH) == month) {
            list.add(df.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
        return list;
    }

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

    public static Date getStartOfDay(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getMinimum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    public static Date getEndOfDay(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getMaximum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    /**
     * 获取初始化时间
     *
     * @return 日期
     */
    public static Date getStartDate() {
        try {
            return new SimpleDateFormat(PATTERN_STANDARD).parse("1900-01-01 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    /**
     * 获取最终化时间
     *
     * @return 日期
     */
    public static Date getEndDate() {
        try {
            return new SimpleDateFormat(PATTERN_STANDARD).parse("2199-01-01 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

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

    public static Date subDate(Date date, int dateField, int amount) {
        return addDate(date, dateField, -amount);
    }

    public static Date subSecond(Date date, int amount) {
        return subDate(date, Calendar.SECOND, amount);
    }

    public static Date subMinute(Date date, int amount) {
        return subDate(date, Calendar.MINUTE, amount);
    }

    public static Date subHour(Date date, int amount) {
        return subDate(date, Calendar.HOUR, amount);
    }

    public static Date subDay(Date date, int amount) {
        return subDate(date, Calendar.DAY_OF_MONTH, amount);
    }

    public static Date subWeek(Date date, int amount) {
        return subDate(date, Calendar.WEEK_OF_YEAR, amount);
    }

    public static Date subMonth(Date date, int amount) {
        return subDate(date, Calendar.MONTH, amount);
    }

    //4、日期计算-------------------------------------------------------------------------------------------------------
    public static long minuteDiff(Date beginTime, Date endTime) {
        return (endTime.getTime() - beginTime.getTime()) / 60000;
    }
    public static long hourDiff(Date beginTime, Date endTime) {
        return (endTime.getTime() - beginTime.getTime()) / 3600000;
    }

    public static long dayDiff(Date beginTime, Date endTime) {
        return (endTime.getTime() - beginTime.getTime()) / 86400000;
    }

    public static Integer monthDiff(Date beginTime, Date endTime) {
        return (getYear(endTime) - getYear(beginTime)) * 12 + getMonth(endTime) - getMonth(beginTime);
    }

    public static int yearDiff(Date beginTime, Date endTime) {
        return getYear(endTime) - getYear(beginTime);
    }
}
