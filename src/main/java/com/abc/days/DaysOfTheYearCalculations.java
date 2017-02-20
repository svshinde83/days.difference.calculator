package com.abc.days;

import com.abc.days.dto.UserDate;
import com.abc.days.util.DateUtils;
import java.util.Scanner;
import com.abc.days.validations.DateValidations;

/**
 * Created by svshinde83 on 19/01/2017.
 *
 * This class is the start of the test requirements.
 */
public class DaysOfTheYearCalculations {

    public static void main(String[] args) {
        DaysOfTheYearCalculations calculations = new DaysOfTheYearCalculations();
        try {
            calculations.getDays();
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid format of date. Please provide start and end dates between years 1900 & 2010 in the following format :DD MM YYYY, DD MM YYYY");
        }
    }

    /**
     * This function interacts with the user and retrieves start and end dates.
     * in the format DD MM YYYY, DD MM YYYY
     * It first retrieves user Input dates, unless they choose to quit by entering "q".
     * It checks for the format and asks the user to retry until the right format is input.
     */
    public void getDays() {

        while (true) {
            System.out.println("Please provide start and end dates between years 1900 & 2010 in the following format :DD MM YYYY, DD MM YYYY \n" +
                    "To quit please type q and enter.");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine().trim();
            if ("q".equals(userInput)) {
                System.out.println("System quitting now.");
                System.exit(1);
            }

            String[] dates = DateUtils.retrieveDates(userInput);
            while (dates == null) {
                System.out.println("Please enter start and end dates in format : DD MM YYYY, DD MM YYYY \n" +
                        "To quit please type q and enter.");
                userInput = scanner.nextLine().trim();
                dates = DateUtils.retrieveDates(userInput);
                if ("q".equals(userInput)) {
                    System.out.println("System quitting now.");
                    System.exit(1);
                }
            }

            //parsing Start Date
            String startCompleteDate = dates[0].trim();
            String[] startDateArray = startCompleteDate.split(" ");
            UserDate startDt = DateUtils.retrieveDate(startDateArray);

            //parsing End Date
            String endCompleteDate = dates[1].trim();
            String[] endCompleteDateArray = endCompleteDate.split(" ");
            UserDate endDt = DateUtils.retrieveDate(endCompleteDateArray);

            // Validating Start and End Date Ranges
            if (DateValidations.validateStartEndDates(startDt, endDt)) {
                calculateNumberOfDays(startDt, endDt);
            }
        }
    }

    /**
     * This function calculates and prints the number of days for given dates
     *
     * @param startDate
     * @param endDate
     */
    public void calculateNumberOfDays(UserDate startDate, UserDate endDate) {

        int totalDays = 0;
        int totalStartDays = 0;
        int totalEndDays = 0;
        if (startDate.getYear() == endDate.getYear()) {
            if (startDate.getMonth() == endDate.getMonth()) {
                totalDays = endDate.getDay() - startDate.getDay();
            } else {
                totalStartDays = DateUtils.getDaysForAMonth(startDate.getMonth(), startDate.getYear()) - startDate.getDay();
                totalStartDays += DateUtils.daysCalculation(startDate.getMonth() + 1, endDate.getMonth(), startDate.getYear());
                totalEndDays = endDate.getDay();
                totalDays = totalStartDays + totalEndDays;
            }
        } else {
            totalStartDays = DateUtils.getDaysForAMonth(startDate.getMonth(), startDate.getYear()) - startDate.getDay();
            totalStartDays += DateUtils.daysCalculation(startDate.getMonth() + 1, 12 + 1, startDate.getYear());
            totalEndDays = endDate.getDay() + DateUtils.daysCalculation(1, endDate.getMonth(), endDate.getYear());
            int daysOfYear = DateUtils.summationOfDays(startDate.getYear(), endDate.getYear());
            totalDays = totalStartDays + totalEndDays + daysOfYear;
        }
        System.out.println(startDate + "," + endDate + ":" + totalDays);
    }
}