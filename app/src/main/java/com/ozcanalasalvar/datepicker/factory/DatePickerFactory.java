package com.ozcanalasalvar.datepicker.factory;

import com.ozcanalasalvar.datepicker.DateModel;
import com.ozcanalasalvar.datepicker.DateUtils;

public class DatePickerFactory {
    private DateModel maxDate;
    private DateModel minDate;
    private DateModel selectedDate;
    private FactoryListener listener;


    public DatePickerFactory(FactoryListener listener) {
        this.listener = listener;
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
}
