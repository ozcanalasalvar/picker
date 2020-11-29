package com.ozcanalasalvar.library.factory;

import com.ozcanalasalvar.library.utils.DateUtils;

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
        if (hour > 23)
            hour = 23;
        this.hour = hour;
        listener.onHourChanged(hour);
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        if (minute > 59)
            minute = 59;
        this.minute = minute;
        listener.onMinuteChanged(minute);
    }

}
