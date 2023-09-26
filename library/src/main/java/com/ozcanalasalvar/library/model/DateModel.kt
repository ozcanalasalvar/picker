package com.ozcanalasalvar.library.model;

import com.ozcanalasalvar.library.utils.DateUtils;

public class DateModel {

    private int year;
    private int month;
    private int day;
    private long date;

    public DateModel() {

    }

    public DateModel(long date) {
        setDate(date);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
        this.day = DateUtils.getDay(date);
        this.month = DateUtils.getMonth(date);
        this.year = DateUtils.getYear(date);
    }

    public void updateModel() {
        setDate(DateUtils.getTimeMiles(this.year, this.month, this.day));
    }

    @Override
    public String toString() {
        return "DateModel{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", date=" + date +
                '}';
    }
}
