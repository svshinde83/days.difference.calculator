package com.abc.days.util;

import com.abc.days.dto.UserDate;
import com.abc.days.validations.DateValidations;

/**
 * Created by svshinde83 on 20/01/2017.
 * <p>
 * Utility Class that does the ground level work of calculations with given Dates.
 * All Methods are static in order for them to be re-used and no need of instantiation.
 */
public class DateUtils {


    /**
     * Checks if a given year is a Leap Year i.e. Feb month with 28 or 29 days for a given year.
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {

        return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
    }

    /**
     * Considering the year and month, how many days are in a given month of a year.
     *
     * @param month
     * @param year
     * @return
     */
    public static int getDaysForAMonth(int month, int year) {
        int noOfDays = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                noOfDays = 31;
                break;

            case 2:
                noOfDays = isLeapYear(year) ? 29 : 28;
                break;

            case 4:
            case 6:
            case 9:
            case 11:
                noOfDays = 30;
        }

        return noOfDays;
    }

    /**
     * Cumulative days for a pair of year provided.
     *
     * @param startYear
     * @param endYear
     * @return
     */
    public static int summationOfDays(int startYear, int endYear) {
        int days = 0;
        for (int i = startYear + 1; i < endYear; i++) {
            days += isLeapYear(i) ? 366 : 365;
        }
        return days;
    }

    /**
     * @param startMonth
     * @param endMonth
     * @param year
     * @return
     */
    public static int daysCalculation(int startMonth, int endMonth, int year) {
        int days = 0;
        for (int i = startMonth; i < endMonth; i++) {
            days += getDaysForAMonth(i, year);
        }
        return days;
    }

    /**
     * Retrieves start and end dates
     *
     * @param input
     * @return
     */
    public static String[] retrieveDates(String input) {

        if (DateValidations.checkDateFormat(input)) {
            return input.split(",");
        }
        return null;
    }

    /**
     * Forms a dto date Object from a given String[] date.
     *
     * @param dateArgs
     * @return
     */
    public static UserDate retrieveDate(String[] dateArgs) {
        UserDate date = new UserDate(
                Integer.parseInt(dateArgs[0]),
                Integer.parseInt(dateArgs[1]),
                Integer.parseInt(dateArgs[2])
        );
        return date;
    }
}
