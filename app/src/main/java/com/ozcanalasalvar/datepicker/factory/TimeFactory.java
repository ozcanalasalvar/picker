package com.ozcanalasalvar.datepicker.factory;

import com.ozcanalasalvar.datepicker.utils.DateUtils;

public class TimeFactory {

    private int hour;
    private int minute;
    private TimeFactoryListener listener;

    public TimeFactory(TimeFactoryListener listener) {
        this.listener = listener;
        this.hour = DateUtils.getCurrentHour();
        this.minute = DateUtils.getCurrentMinute();
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
        listener.onHourChanged();
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
        listener.onMinuteChanged();
    }

}
