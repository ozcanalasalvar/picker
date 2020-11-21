package com.ozcanalasalvar.datepicker.factory;

public interface DateFactoryListener {
    void onYearChanged();

    void onMonthChanged();

    void onDayChanged();

    void onConfigsChanged();
}
