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
    private DateUtil() {
    }

    /**
     * 类级内部类：静态的成员式内部类，该内部类的实例与外部类的实例灭有绑定关系，
     * 且只有被调用到才会装载，从而实现了延迟加载
     *
     * @author ydp
     */
    private static class instanceHolder {
        static DateUtil instance = new DateUtil();
        static String patternYearMonth = "yyyyMM";
        static String patternDateTimeStandard = "yyyy-MM-dd HH:mm:ss";
        static String patternDateTimeMillisecond = "yyyy-MM-dd HH:mm:ss.SSS";
    }

    /**
     * 单例模式：获取类实例
     *
     * @return
     */
    public static DateUtil getInstance() {
        return instanceHolder.instance;
    }

    /**
     * 获取当前年月
     *
     * @return
     */
    public String getYearMonth() {
        return this.convertDateToString(new Date(), instanceHolder.patternYearMonth);
    }


    /**
     * 获取当前时间：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public String getCurTimeStr() {
        return convertDateToString(new Date(), instanceHolder.patternDateTimeStandard);
    }

    /**
     * 获取当前时间：yyyy-MM-dd HH:mm:ss.SSS
     *
     * @return
     */
    public String getCurTimeMsecStr() {
        return convertDateToString(new Date(), instanceHolder.patternDateTimeMillisecond);
    }

    /**
     * 转换日期格式为指定string格式
     *
     * @param date
     * @param pattern
     * @return
     */
    public String convertDateToString(Date date, String pattern) {
        if (date == null || pattern == null) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 转换long为指定日期字符串
     *
     * @param time
     * @param pattern
     * @return
     */
    public String convertLongToDateStr(Long time, String pattern) {
        return this.convertDateToString(new Date(time), pattern);
    }

    /**
     * 将字符串转换为日期格式
     *
     * @param time
     * @param pattern
     * @return
     * @throws ParseException
     */
    public Date convertStrToDate(String time, String pattern) throws ParseException {
        if (time == null) {
            return null;
        }
        return new SimpleDateFormat(pattern).parse(time);
    }

    /**
     * 返回一个指定日期的下一天
     *
     * @param date 初始日期
     * @return 初始日期加一天后的日期
     */
    public Date getNextDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
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
     * 获得一个指定日期所在月的下个月的第一天
     *
     * @param date
     * @return
     */
    public Date getFirstDateInNextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 取得指定日期所在的月份
     *
     * @param date
     * @return
     */
    public final int getMonthOf(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    /**
     * 取得指定日期所在的小时
     *
     * @param date
     * @return
     */
    public final int getHourOf(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获入参时间后,在入参月所有日期列表（含当前日期）,格式"yyyyMMdd"
     *
     * @param date
     * @return
     */
    public List<String> getLastDayListOfMonth(Date date) {
        List<String> list = new ArrayList<String>();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        while (cal.get(Calendar.MONTH) == month) {
            list.add(df.format(cal.getTime()));
            cal.add(Calendar.DATE, 1);
        }
        return list;
    }
}
