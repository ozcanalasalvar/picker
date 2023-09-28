package com.ozcanalasalvar.library.utils

import com.ozcanalasalvar.library.model.DateModel
import com.ozcanalasalvar.library.utils.DateUtils


fun DateModel.monthsOfDate(): List<Int> {
    val months = mutableListOf<Int>()
//    if (this.year == minDate.year) {
//        for (month in minDate.month..11) {
//            months.add(month)
//        }
//    } else if (this.year == maxDate.year) {
//        for (month in 0..maxDate.month) {
//            months.add(month)
//        }
//    } else {
        for (month in 0..11) {
            months.add(month)
        }
//    }

    return months
}


fun DateModel.daysOfDate(): List<Int> {
    val days = mutableListOf<Int>()
    val monthDayCount = DateUtils.getMonthDayCount(this.date)

//    if (this.year == minDate.year && this.month == minDate.month && minDate.day != 1) {
//        for (day in minDate.day..monthDayCount) {
//            days.add(day)
//        }
//    } else if (this.year == maxDate.year && this.month == maxDate.month && maxDate.day != monthDayCount) {
//        for (day in 1..maxDate.day) {
//            days.add(day)
//        }
//    } else {
        for (day in 1..monthDayCount) {
            days.add(day)
        }
//    }

    return days
}


fun DateModel.isFullMonthOfYear(minDate: DateModel, maxDate: DateModel): Boolean =
    !((this.year == minDate.year && minDate.month != 11) || (this.year == maxDate.year && maxDate.month != 0))

fun DateModel.isFullDayOfMonth(minDate: DateModel, maxDate: DateModel): Boolean {
    val monthDayCount = DateUtils.getMonthDayCount(this.date)
    val isMin =
        (this.year == minDate.year && this.month == minDate.month && minDate.day != 1)
    val isMax =
        (this.year == maxDate.year && this.month == maxDate.month && maxDate.day != monthDayCount)
    return !(isMin || isMax)
}

fun DateModel.withYear(year: Int): DateModel {
    return DateModel(year, this.month, this.day)
}

fun DateModel.withMonth(month: Int): DateModel {
    return DateModel(this.year, month, this.day)
}

fun DateModel.withDay(day: Int): DateModel {
    return DateModel(this.year, this.month, day)
}
