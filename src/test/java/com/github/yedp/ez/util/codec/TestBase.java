package com.github.yedp.ez.util.codec;

import com.github.yedp.ez.util.DateUtil;

import java.util.Date;

public class TestBase {
    public void assertEquals(String str1, String str2) {
        assert str1.equals(str2) : "str1:" + str1 + "; str2:" + str2;
    }

    public void assertEquals(Integer num1, Integer num2) {
        assert num1.equals(num2) : "num1:" + num1 + "; num2:" + num2;
    }

    public void assertEquals(Long num1, Long num2) {
        assert num1.equals(num2) : "num1:" + num1 + "; num2:" + num2;
    }

    public void assertEquals(Date date1, Date date2) {
        assert date1.equals(date2) : "date1:" + DateUtil.format(date1, DateUtil.PATTERN_MILLIS) + "; date2:" + DateUtil.format(date2, DateUtil.PATTERN_MILLIS);
    }
}
