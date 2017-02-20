package com.abc.days.dto;

/**
 * Created by svshinde83 on 20/01/2017.
 */
public class UserDate {

    private int day;
    private int month;
    private int year;

    public UserDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        return this.day + "" + this.month + "" + this.year;
    }
}
