package com.ozcanalasalvar.datepicker.model

import com.ozcanalasalvar.datepicker.utils.DateUtils

class Date {
    var year = 0
    var month = 0
    var day = 0
    var date: Long = DateUtils.getTimeMiles(this.year, this.month, this.day)

    constructor() {}
    constructor(
        year: Int,
        month: Int,
        day: Int,
    ) {
        this.date = DateUtils.getTimeMiles(year, month, day)
        updateDate(this.date)
    }

    constructor(date: Long) {
        updateDate(date)
    }


    private fun updateDate(date: Long) {
        this.date = date
        day = DateUtils.getDay(date)
        month = DateUtils.getMonth(date)
        year = DateUtils.getYear(date)
    }


    override fun toString(): String {
        return "DateModel{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", date=" + date +
                '}'
    }
}