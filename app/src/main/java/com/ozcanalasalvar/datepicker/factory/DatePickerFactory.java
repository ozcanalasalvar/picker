package com.ozcanalasalvar.datepicker.factory;

import com.ozcanalasalvar.datepicker.DateModel;
import com.ozcanalasalvar.datepicker.DateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatePickerFactory {
    private DateModel maxDate;
    private DateModel minDate;
    private DateModel selectedDate;
    private FactoryListener listener;
    private int monthMin;


    public DatePickerFactory(FactoryListener listener) {
        this.listener = listener;
        monthMin = 0;
        minDate = new DateModel(DateUtils.getTimeMiles(FactoryConstant.MIN_YEAR, FactoryConstant.MIN_MONTH, 1));
        maxDate = new DateModel(DateUtils.getTimeMiles(FactoryConstant.MAX_YEAR, FactoryConstant.MAX_MONTH, 1));
        selectedDate = new DateModel(DateUtils.getCurrentTime());
    }

    public void setSelectedYear(int year) {
        selectedDate.setYear(year);
        if (selectedDate.getMonth() < minDate.getMonth()) {
            selectedDate.setMonth(minDate.getMonth());
        } else if (selectedDate.getMonth() > maxDate.getMonth()) {
            selectedDate.setMonth(maxDate.getMonth());
        }
        selectedDate.updateModel();
        listener.onYearChanged();
    }


    public void setSelectedMonth(int month) {
        selectedDate.setMonth(month);
        selectedDate.updateModel();
        listener.onMonthChanged();
    }


    public void setSelectedDay(int day) {
        selectedDate.setDay(day);
        selectedDate.updateModel();
        listener.onDayChanged();
    }

    public DateModel getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(long maxDate) {
        this.maxDate = new DateModel(maxDate);
        listener.onConfigsChanged();
    }

    public DateModel getMinDate() {
        return minDate;
    }

    public void setMinDate(long minDate) {
        this.minDate = new DateModel(minDate);
        listener.onConfigsChanged();
    }


    public DateModel getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(long selectedDate) {
        this.selectedDate = new DateModel(selectedDate);
        listener.onConfigsChanged();
    }

    public List<String> getDayList() {
        int max = DateUtils.getMonthDayCount(selectedDate.getDate());
        int min = 0;
        if (selectedDate.getYear() == maxDate.getYear() && selectedDate.getMonth() == maxDate.getMonth()) {
            max = maxDate.getDay();
        }
        if (selectedDate.getYear() == minDate.getYear() && selectedDate.getMonth() == minDate.getMonth()) {
            min = minDate.getDay() - 1;
        }

        List<String> days = new ArrayList<>();
        for (int i = min; i < max; i++) {
            days.add("" + (i + 1));
        }

        return days;
    }

    public List<String> getMonthList() {
        List<String> monthsArray = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

        int max = monthsArray.size();
        if (selectedDate.getYear() == maxDate.getYear()) {
            max = maxDate.getMonth() + 1;
        }
        if (selectedDate.getYear() == minDate.getYear()) {
            this.monthMin = minDate.getMonth();
        } else
            this.monthMin = 0;

        List<String> months = new ArrayList<>();
        for (int i = monthMin; i < max; i++) {
            months.add(monthsArray.get(i));
        }
        return months;
    }

    public List<String> getYearList() {
        int yearCount = Math.abs(minDate.getYear() - maxDate.getYear()) + 1;
        List<String> years = new ArrayList<>();
        for (int i = 0; i < yearCount; i++) {
            years.add("" + (minDate.getYear() + i));
        }
        return years;
    }


    public int getMonthMin() {
        return monthMin;
    }
}
