package com.ozcanalasalvar.sample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.material.timepicker.TimeFormat
import com.ozcanalasalvar.library.compose.datepicker.WheelDatePicker
import com.ozcanalasalvar.library.compose.timepicker.WheelTimePicker
import com.ozcanalasalvar.sample.ui.theme.DatePickerTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DatePickerTheme {
                Content()
            }
        }
    }
}

@Composable
fun Content() {
    Column(modifier = Modifier.fillMaxSize()) {
        // A surface container using the 'background' color from the theme
        WheelDatePicker(onDateSelected = { day, month, year, date ->
            Log.d("SelectedDate", "$day / $month / $year")
        })


        WheelTimePicker(onTimeSelected = { hour, minute, format ->
            Log.d("SelectedDate", "$hour : $minute  $format")
        })

        WheelTimePicker(timeFormat = TimeFormat.CLOCK_12H,
            onTimeSelected = { hour, minute, format ->
                Log.d("SelectedDate", "$hour : $minute  $format")
            })
    }
}

@Preview
@Composable
fun ContentPreview() {
    Content()
}