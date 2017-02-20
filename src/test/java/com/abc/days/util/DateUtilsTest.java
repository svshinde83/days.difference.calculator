package com.abc.days.util;

import com.abc.days.dto.UserDate;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by svshinde83 on 20/01/2017.
 */
public class DateUtilsTest {

    @Test
    public void testIsLeapYear() throws Exception {
        boolean isLeapYear = DateUtils.isLeapYear(2000);
        Assert.assertTrue(isLeapYear);

        isLeapYear = DateUtils.isLeapYear(1998);
        Assert.assertFalse(isLeapYear);
    }

    @Test
    public void testGetDaysOfMonth() throws Exception {

        int days = DateUtils.getDaysForAMonth(2, 2000);
        Assert.assertEquals(days, 29);

        days = DateUtils.getDaysForAMonth(3, 2001);
        Assert.assertEquals(days, 31);

        days = DateUtils.getDaysForAMonth(6, 2001);
        Assert.assertEquals(days, 30);
    }

    @Test
    public void testSummationOfDays() throws Exception {

        int days = DateUtils.summationOfDays(1900, 2010);
        Assert.assertEquals(39812, days);
        days = DateUtils.summationOfDays(2000, 2000);
        Assert.assertEquals(0, days);

    }

    @Test
    public void testDaysCalculation() throws Exception {
        int days = DateUtils.daysCalculation(2, 11, 2010);
        Assert.assertEquals(273, days);
    }

    @Test
    public void testRetrieveDates() throws Exception {
        String[] dates = DateUtils.retrieveDates("22 10 2010, 01 01 1900");
        Assert.assertEquals(2, dates.length);
        Assert.assertEquals("22 10 2010", dates[0]);
        Assert.assertEquals("01 01 1900", dates[1].trim());
    }

    @Test
    public void testRetrieveDate() throws Exception {
        String[] dates = {"22", "10", "2010"};
        UserDate userDate = DateUtils.retrieveDate(dates);
        Assert.assertEquals(22, userDate.getDay());
        Assert.assertEquals(10, userDate.getMonth());
        Assert.assertEquals(2010, userDate.getYear());
    }
}