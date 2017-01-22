package org.test.days.validations;

import org.test.days.dto.UserDate;
import org.test.days.util.DateUtils;

/**
 * Created by svshinde83 on 20/01/2017.
 * <p>
 * Validation class provides Date related validations.
 * All methods are static in it for it to be re-usable and no need to instantiate.
 */
public class DateValidations {

    /**
     * Accepts a comma limited String and checks if numeric dates are entered.
     *
     * @param givenDates
     * @return
     * @throws NumberFormatException
     */
    public static boolean checkDateFormat(String givenDates) throws NumberFormatException {
        boolean isValidFormat = false;
        try {
            if (givenDates.contains(",")) {
                String[] dates = givenDates.split(",");

                if (dates.length > 2) {
                    System.out.println("Invalid format of date. Please provide start and end dates between years 1900 & 2010 in the following format :DD MM YYYY, DD MM YYYY");
                    return isValidFormat;
                }

                String startDate = dates[0].replaceAll("\\s", "");
                Integer.parseInt(startDate);

                String endDate = dates[1].replaceAll("\\s", "");
                Integer.parseInt(endDate);
                isValidFormat = true;
                return isValidFormat;
            }
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("Invalid format of date. Please provide start and end dates between years 1900 & 2010 in the following format :DD MM YYYY, DD MM YYYY");
        }
        System.out.println("Invalid format of date. Please provide start and end dates between years 1900 & 2010 in the following format :DD MM YYYY, DD MM YYYY");
        return isValidFormat;
    }

    /**
     * Accepts a start and end date and checks if it is valid for a leap year or a non leap year and finally if start date is earlier than end date.
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws NumberFormatException
     */

    public static boolean validateStartEndDates(UserDate startDate, UserDate endDate) throws NumberFormatException {

        boolean validDate = false;

        if (validateYear(startDate.getYear(), endDate.getYear())) {
            return validDate;
        }

        if (validateMonth(startDate.getMonth(), endDate.getMonth())) {
            return validDate;
        }

        if (validateDate(startDate.getDay(), endDate.getDay(), startDate.getMonth(), endDate.getMonth(), startDate.getYear(), endDate.getYear())) {
            return validDate;
        }

        if (validateStartDateBeforeEndDate(startDate, endDate)) {
            return validDate;
        }

        validDate = true;
        return validDate;
    }

    /**
     * Checks if start date is earlier than end date.
     *
     * @param startDate
     * @param endDate
     * @return
     */
    private static boolean validateStartDateBeforeEndDate(UserDate startDate, UserDate endDate) {
        boolean inValidStartEndDate = false;
        if (startDate.getYear() > endDate.getYear()) {
            inValidStartEndDate = true;
            return inValidStartEndDate;
        }

        if (startDate.getYear() == endDate.getYear()) {
            if (startDate.getMonth() > endDate.getMonth()) {
                inValidStartEndDate = true;
                return inValidStartEndDate;
            } else if (startDate.getMonth() == endDate.getMonth() && startDate.getDay() > endDate.getDay()) {
                inValidStartEndDate = true;
                return inValidStartEndDate;
            }
        }
        return inValidStartEndDate;
    }

    /**
     * Checks if the months entered are valid or not.
     *
     * @param startMonth
     * @param endMonth
     * @return
     */
    public static boolean validateMonth(int startMonth, int endMonth) {
        boolean inValidMonth = false;
        if (startMonth < 1 || endMonth < 1 || startMonth > 12 || endMonth > 12) {
            inValidMonth = true;
        }
        return inValidMonth;
    }

    /**
     * Checks if the dates given for start and end are valid.
     *
     * @param startDate
     * @param endDate
     * @param startMonth
     * @param endMonth
     * @param startYear
     * @param endYear
     * @return
     */
    public static boolean validateDate(int startDate, int endDate, int startMonth, int endMonth, int startYear, int endYear) {

        boolean inValidDate = false;

        if (isInValidDay(startDate, startMonth, startYear)
                || isInValidDay(endDate, endMonth, endYear)) {
            inValidDate = true;
        }

        return inValidDate;
    }

    /**
     * Checks if it is a leap year and month is february for day input
     * else  checks day provided for other months.
     *
     * @param date
     * @param month
     * @param year
     * @return
     */
    private static boolean isInValidDay(int date, int month, int year) {
        boolean inValidDate = false;

        if (month == 2) {
            if (DateUtils.isLeapYear(year) && (date < 1 || date > 29)) {
                inValidDate = true;
            } else if (date < 1 || date > 28) {
                inValidDate = true;
            }
            return inValidDate;
        }

        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (date < 1 || date > 31) {
                inValidDate = true;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (date < 1 || date > 30) {
                inValidDate = true;
            }
        }
        return inValidDate;
    }

    /**
     * Boundary checking for business requirements for start and end year provided.
     *
     * @param startYear
     * @param endYear
     * @return
     */
    public static boolean validateYear(int startYear, int endYear) {
        boolean inValidYear = false;
        if (startYear < 1900 || startYear > 2010 ||
                endYear < 1900 || endYear > 2010) {
            inValidYear = true;
        }
        return inValidYear;
    }
}