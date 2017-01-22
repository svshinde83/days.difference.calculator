package org.test.days.validations;

import org.junit.Assert;
import org.junit.Test;
import org.test.days.dto.UserDate;

/**
 * Created by svshinde83 on 20/01/2017.
 */
public class DateValidationsTest {


    @Test
    public void testCheckDateFormat() throws Exception {
        boolean valid = DateValidations.checkDateFormat("20 01 2005, 01 01 2010");
        Assert.assertTrue(valid);

        valid = DateValidations.checkDateFormat("");
        Assert.assertFalse(valid);
        valid = DateValidations.checkDateFormat("20 sss yyyy");
        Assert.assertFalse(valid);
    }

    @Test
    public void testValidateStartEndDates() throws Exception {
        UserDate startDate = new UserDate(02, 07, 1998);
        UserDate endDate = new UserDate(03, 07, 1998);
        boolean valid = DateValidations.validateStartEndDates(startDate, endDate);
        Assert.assertTrue(valid);

        startDate = new UserDate(04, 07, 1998);
        endDate = new UserDate(03, 07, 1998);
        valid = DateValidations.validateStartEndDates(startDate, endDate);
        Assert.assertFalse(valid);

        startDate = new UserDate(04, 07, 2011);
        endDate = new UserDate(03, 07, 1998);
        valid = DateValidations.validateStartEndDates(startDate, endDate);
        Assert.assertFalse(valid);

        startDate = new UserDate(04, 07, 201);
        endDate = new UserDate(03, 07, 1998);
        valid = DateValidations.validateStartEndDates(startDate, endDate);
        Assert.assertFalse(valid);

        startDate = new UserDate(04, 07, 1899);
        endDate = new UserDate(03, 07, 2011);
        valid = DateValidations.validateStartEndDates(startDate, endDate);
        Assert.assertFalse(valid);
    }

    @Test
    public void testValidateMonth() throws Exception {
        boolean valid = DateValidations.validateMonth(13, -1);
        Assert.assertTrue(valid);
        valid = DateValidations.validateMonth(2, 4);
        Assert.assertFalse(valid);
    }

    @Test
    public void testValidateDate() throws Exception {
        UserDate startDate = new UserDate(02, 07, 1998);
        UserDate endDate = new UserDate(03, 07, 1998);
        boolean valid = DateValidations.validateDate(startDate.getDay(), endDate.getDay(), startDate.getMonth(), endDate.getMonth(), startDate.getYear(), endDate.getYear());
        Assert.assertFalse(valid);

        // Check Leap Year
        startDate = new UserDate(30, 02, 2000);
        endDate = new UserDate(03, 07, 1998);
        valid = DateValidations.validateDate(startDate.getDay(), endDate.getDay(), startDate.getMonth(), endDate.getMonth(), startDate.getYear(), endDate.getYear());
        Assert.assertTrue(valid);
    }

    @Test
    public void testValidateYear() throws Exception {
        boolean valid = DateValidations.validateYear(1899, 2011);
        Assert.assertTrue(valid);

        valid = DateValidations.validateYear(2000, 2002);
        Assert.assertFalse(valid);
    }
}