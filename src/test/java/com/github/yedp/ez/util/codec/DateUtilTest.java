package com.github.yedp.ez.util.codec;

import com.github.yedp.ez.util.DateUtil;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class DateUtilTest extends TestBase {
    String date1Str = "2020-05-25 10:53:42";
    String date1YMD = "2020-05-25";
    Long date1Long = 1590375222000L;
    Date date1 = new Date(date1Long);
    String date2Str = "2021-01-25 10:53:42";
    Date date2 = new Date(1611543222000L);


    @Test
    public void format() {
        assertEquals(date1Str, DateUtil.format(date1));
    }

    @Test
    public void formatWithPattern() {
        assertEquals(date1YMD, DateUtil.format(date1, DateUtil.PATTERN_YMD));
    }

    @Test
    public void formatLongWithPattern() {
        assertEquals(date1YMD, DateUtil.format(date1Long, DateUtil.PATTERN_YMD));
    }

    @Test
    public void parse() throws ParseException {
        assertEquals(date1, DateUtil.parse(date1Str));
    }

    @Test
    public void parseWithPattern() throws ParseException {
        assertEquals(date1, DateUtil.parse(date1Str, DateUtil.PATTERN_STANDARD));
    }

    @Test
    public void getMinute() throws ParseException {
        assertEquals(53, DateUtil.getMinute(date1));
    }

    @Test
    public void getHour() throws ParseException {
        assertEquals(10, DateUtil.getHour(date1));
    }

    @Test
    public void getDayOfWeek() throws ParseException {
        assertEquals(1, DateUtil.getDayOfWeek(date1));
    }

    @Test
    public void getDayOfMonth() throws ParseException {
        assertEquals(25, DateUtil.getDayOfMonth(date2));
    }

    @Test
    public void getDayOfYear() throws ParseException {
        assertEquals(145, DateUtil.getDayOfYear(new Date(1621911222000L)));
    }

    @Test
    public void getWeekOfMonth() throws ParseException {
        assertEquals(5, DateUtil.getWeekOfMonth(date2));
    }

    @Test
    public void getWeekOfYear() throws ParseException {
        assertEquals(5, DateUtil.getWeekOfYear(date2));
    }

    @Test
    public void getMonth() throws ParseException {
        assertEquals(5, DateUtil.getMonth(date1));
    }

    @Test
    public void getYear() throws ParseException {
        assertEquals(2020, DateUtil.getYear(date1));
    }

    @Test
    public void getCurDay() {
        assertEquals(DateUtil.format(new Date(), DateUtil.PATTERN_YMD), DateUtil.curDay());
    }

    @Test
    public void getYearMonth() {
        assertEquals(DateUtil.format(new Date(), DateUtil.PATTERN_YM), DateUtil.curYearMonth());
    }

    @Test
    public void getCurTimeStr() {
        assertEquals(DateUtil.format(new Date(), DateUtil.PATTERN_STANDARD), DateUtil.curTimeStr());
    }

    @Test
    public void getFirstDateOfWeek() throws ParseException {
        assertEquals(DateUtil.parse("2021-01-25",DateUtil.PATTERN_YMD), DateUtil.getFirstDateOfWeek(date2));
    }
    @Test
    public void getLastDateOfWeek() throws ParseException {
        assertEquals(DateUtil.parse("2021-01-31 23:59:59",DateUtil.PATTERN_STANDARD), DateUtil.getLastDateOfWeek(date2));
    }
    @Test
    public void getFirstDateOfMonth() throws ParseException {
        assertEquals(DateUtil.parse("2021-01-01",DateUtil.PATTERN_YMD), DateUtil.getFirstDateOfMonth(date2));
    }
    @Test
    public void getLastDateOfMonth() throws ParseException {
        assertEquals(DateUtil.parse("2021-01-31 23:59:59",DateUtil.PATTERN_STANDARD), DateUtil.getLastDateOfMonth(date2));
    }
    @Test
    public void getStartDate() throws ParseException {
        assertEquals(DateUtil.parse("1900-01-01 00:00:00"),DateUtil.getStartDate());
    }
    @Test
    public void getEndDate() throws ParseException {
        assertEquals(DateUtil.parse("2199-01-01 00:00:00"),DateUtil.getEndDate());

    }

    @Test
    public void addSecond() throws ParseException {
        assertEquals(DateUtil.parse("2021-01-25 10:53:52"),DateUtil.addSecond(date2,10));

    }

    @Test
    public void addMinute() throws ParseException {
        assertEquals(DateUtil.parse("2021-01-25 11:03:42"),DateUtil.addMinute(date2,10));

    }

    @Test
    public void addHour() throws ParseException {
        assertEquals(DateUtil.parse("2021-01-25 20:53:42"),DateUtil.addHour(date2,10));

    }
    @Test
    public void addDay() throws ParseException {
        assertEquals(DateUtil.parse("2021-02-04 10:53:42"),DateUtil.addDay(date2,10));

    }
    @Test
    public void addWeek() throws ParseException {
        assertEquals(DateUtil.parse("2021-04-05 10:53:42"),DateUtil.addWeek(date2,10));
    }
    @Test
    public void addMonth() throws ParseException {
        assertEquals(DateUtil.parse("2021-11-25 10:53:42"),DateUtil.addMonth(date2,10));
    }

    @Test
    public void minuteDiff() throws ParseException {
        assertEquals(10L,DateUtil.minuteDiff(date2,DateUtil.addMinute(date2,10)));
    }


    @Test
    public void hourDiff() throws ParseException {
        assertEquals(60L,DateUtil.hourDiff(date2,DateUtil.addHour(date2,60)));
    }

    @Test
    public void dayDiff() throws ParseException {
        assertEquals(2L,DateUtil.dayDiff(date2,DateUtil.addHour(date2,60)));
    }

    @Test
    public void monthDiff() throws ParseException {
        assertEquals(60,DateUtil.monthDiff(date2,DateUtil.addMonth(date2,60)));
    }

    @Test
    public void yearDiff() throws ParseException {
        assertEquals(1,DateUtil.yearDiff(date1,date2));
    }
}
