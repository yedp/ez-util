package com.github.yedp.ez.util.codec;

import com.github.yedp.ez.util.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

public class DateUtilTest extends TestBase {
    String date1Str = "2020-05-25 10:53:42";
    String date1YMD = "2020-05-25";
    Long date1Long = 1590375222000L;
    Date date1 = new Date(date1Long);
    String date2Str = "2021-01-25 10:53:42";
    Date date2 = new Date(1611543222000L);


    @Test
    public void format() {
        assertEquals(date1Str, DateUtils.format(date1));
    }

    @Test
    public void formatWithPattern() {
        assertEquals(date1YMD, DateUtils.format(date1, DateUtils.PATTERN_YMD));
    }

    @Test
    public void formatLongWithPattern() {
        assertEquals(date1YMD, DateUtils.format(date1Long, DateUtils.PATTERN_YMD));
    }

    @Test
    public void parse() throws ParseException {
        assertEquals(date1, DateUtils.parse(date1Str));
    }

    @Test
    public void parseWithPattern() throws ParseException {
        assertEquals(date1, DateUtils.parse(date1Str, DateUtils.PATTERN_STANDARD));
    }

    @Test
    public void getMinute() throws ParseException {
        assertEquals(53, DateUtils.getMinute(date1));
    }

    @Test
    public void getHour() throws ParseException {
        assertEquals(10, DateUtils.getHour(date1));
    }

    @Test
    public void getDayOfWeek() throws ParseException {
        assertEquals(1, DateUtils.getDayOfWeek(date1));
    }

    @Test
    public void getDayOfMonth() throws ParseException {
        assertEquals(25, DateUtils.getDayOfMonth(date2));
    }

    @Test
    public void getDayOfYear() throws ParseException {
        assertEquals(145, DateUtils.getDayOfYear(new Date(1621911222000L)));
    }

    @Test
    public void getWeekOfMonth() throws ParseException {
        assertEquals(5, DateUtils.getWeekOfMonth(date2));
    }

    @Test
    public void getWeekOfYear() throws ParseException {
        assertEquals(5, DateUtils.getWeekOfYear(date2));
    }

    @Test
    public void getMonth() throws ParseException {
        assertEquals(5, DateUtils.getMonth(date1));
    }

    @Test
    public void getYear() throws ParseException {
        assertEquals(2020, DateUtils.getYear(date1));
    }

    @Test
    public void getCurDay() {
        assertEquals(DateUtils.format(new Date(), DateUtils.PATTERN_YMD), DateUtils.curDay());
    }

    @Test
    public void getYearMonth() {
        assertEquals(DateUtils.format(new Date(), DateUtils.PATTERN_YM), DateUtils.curYearMonth());
    }

    @Test
    public void getCurTimeStr() {
        assertEquals(DateUtils.format(new Date(), DateUtils.PATTERN_STANDARD), DateUtils.curTimeStr());
    }

    @Test
    public void getFirstDateOfWeek() throws ParseException {
        assertEquals(DateUtils.parse("2021-01-25", DateUtils.PATTERN_YMD), DateUtils.getFirstDateOfWeek(date2));
    }
    @Test
    public void getLastDateOfWeek() throws ParseException {
        assertEquals(DateUtils.parse("2021-01-31 23:59:59", DateUtils.PATTERN_STANDARD), DateUtils.getLastDateOfWeek(date2));
    }
    @Test
    public void getFirstDateOfMonth() throws ParseException {
        assertEquals(DateUtils.parse("2021-01-01", DateUtils.PATTERN_YMD), DateUtils.getFirstDateOfMonth(date2));
    }
    @Test
    public void getLastDateOfMonth() throws ParseException {
        assertEquals(DateUtils.parse("2021-01-31 23:59:59", DateUtils.PATTERN_STANDARD), DateUtils.getLastDateOfMonth(date2));
    }
    @Test
    public void getStartDate() throws ParseException {
        assertEquals(DateUtils.parse("1900-01-01 00:00:00"), DateUtils.getStartDate());
    }
    @Test
    public void getEndDate() throws ParseException {
        assertEquals(DateUtils.parse("2199-01-01 00:00:00"), DateUtils.getEndDate());

    }

    @Test
    public void addSecond() throws ParseException {
        assertEquals(DateUtils.parse("2021-01-25 10:53:52"), DateUtils.addSecond(date2,10));

    }

    @Test
    public void addMinute() throws ParseException {
        assertEquals(DateUtils.parse("2021-01-25 11:03:42"), DateUtils.addMinute(date2,10));

    }

    @Test
    public void addHour() throws ParseException {
        assertEquals(DateUtils.parse("2021-01-25 20:53:42"), DateUtils.addHour(date2,10));

    }
    @Test
    public void addDay() throws ParseException {
        assertEquals(DateUtils.parse("2021-02-04 10:53:42"), DateUtils.addDay(date2,10));

    }
    @Test
    public void addWeek() throws ParseException {
        assertEquals(DateUtils.parse("2021-04-05 10:53:42"), DateUtils.addWeek(date2,10));
    }
    @Test
    public void addMonth() throws ParseException {
        assertEquals(DateUtils.parse("2021-11-25 10:53:42"), DateUtils.addMonth(date2,10));
    }

    @Test
    public void minuteDiff() throws ParseException {
        assertEquals(10L, DateUtils.minuteDiff(date2, DateUtils.addMinute(date2,10)));
    }


    @Test
    public void hourDiff() throws ParseException {
        assertEquals(60L, DateUtils.hourDiff(date2, DateUtils.addHour(date2,60)));
    }

    @Test
    public void dayDiff() throws ParseException {
        assertEquals(2L, DateUtils.dayDiff(date2, DateUtils.addHour(date2,60)));
    }

    @Test
    public void monthDiff() throws ParseException {
        assertEquals(60, DateUtils.monthDiff(date2, DateUtils.addMonth(date2,60)));
    }

    @Test
    public void yearDiff() throws ParseException {
        assertEquals(1, DateUtils.yearDiff(date1,date2));
    }
}
