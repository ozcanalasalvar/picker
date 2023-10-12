package com.ozcanalasalvar.sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import com.google.android.material.timepicker.TimeFormat
//import com.ozcanalasalvar.library.utils.DateUtils.getCurrentTime
//import com.ozcanalasalvar.library.view.datepicker.DatePicker
//import com.ozcanalasalvar.library.view.datepicker.DatePicker.DataSelectListener
//import com.ozcanalasalvar.library.view.popup.DatePickerPopup
//import com.ozcanalasalvar.library.view.popup.DatePickerPopup.OnDateSelectListener
//import com.ozcanalasalvar.library.view.popup.TimePickerPopup
//import com.ozcanalasalvar.library.view.popup.TimePickerPopup.OnTimeSelectListener
//import com.ozcanalasalvar.library.view.timepicker.TimePicker
//import com.ozcanalasalvar.library.view.timepicker.TimePicker.TimeSelectListener

class ViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        val textDate = findViewById<TextView>(R.id.text_date)
        val textTime = findViewById<TextView>(R.id.text_time)


//        val datePicker = findViewById<DatePicker>(R.id.datepicker)

//        datePicker.apply {
//            setOffset(3)
//            setTextSize(17)
//            setDate(getCurrentTime())
//            setDarkModeEnabled(false)
//            setDataSelectListener(object : DataSelectListener {
//                @SuppressLint("SetTextI18n")
//                override fun onDateSelected(date: Long, day: Int, month: Int, year: Int) {
//                    textDate.text = "" + day + "/" + (month + 1) + "/" + year
//                }
//            })
//        }

//        val timePicker = findViewById<TimePicker>(R.id.timepicker)

//        timePicker.apply {
//            setOffset(2)
//            setTextSize(19)
//            setTimeFormat(TimeFormat.CLOCK_12H)
//            setTime(9, 12)
//            setDarkModeEnabled(false)
//            setTimeSelectListener(object : TimeSelectListener {
//                override fun onTimeSelected(hour: Int, minute: Int, timeFormat: String?) {
//                    textTime.text = "$hour:$minute ${timeFormat?:""}"
//                }
//            })
//        }

    }


    fun openDatePicker(view: View) {
//        val datePickerPopup = DatePickerPopup.Builder()
//            .from(this)
//            .offset(3)
//            .textSize(19)
//            .selectedDate(getCurrentTime())
//            .darkModeEnabled(true)
//            .listener(object : OnDateSelectListener {
//                override fun onDateSelected(
//                    dp: DatePicker?,
//                    date: Long,
//                    day: Int,
//                    month: Int,
//                    year: Int
//                ) {
//                    Toast.makeText(
//                        this@ViewActivity,
//                        "" + day + "/" + (month + 1) + "/" + year,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            })
//            .build()
//
//        datePickerPopup.show(supportFragmentManager, "sad")
    }

    fun openTimePicker(view: View) {
//        val pickerPopup = TimePickerPopup.Builder()
//            .from(this)
//            .offset(3)
//            .textSize(17)
//            .setTime(12, 12)
//            .setTimeFormat(TimeFormat.CLOCK_24H)
//            .darkModeEnabled(true)
//            .listener(object : OnTimeSelectListener {
//                override fun onTimeSelected(
//                    timePicker: TimePicker?,
//                    hour: Int,
//                    minute: Int,
//                    format: String?
//                ) {
//                    Toast.makeText(this@ViewActivity, "$hour:$minute", Toast.LENGTH_SHORT).show()
//                }
//            })
//            .build()
//
//        pickerPopup.show(supportFragmentManager, "test")

    }
}