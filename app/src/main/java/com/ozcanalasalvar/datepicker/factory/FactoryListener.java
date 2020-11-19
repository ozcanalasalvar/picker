package com.ozcanalasalvar.datepicker.factory;

public interface FactoryListener {
    void onYearChanged();

    void onMonthChanged();

    void onDayChanged();

    void onConfigsChanged();
}
