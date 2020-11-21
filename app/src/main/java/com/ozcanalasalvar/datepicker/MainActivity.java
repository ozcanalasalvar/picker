package com.ozcanalasalvar.datepicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.ozcanalasalvar.datepicker.utils.DateUtils;
import com.ozcanalasalvar.datepicker.view.datePicker.DatePicker;
import com.ozcanalasalvar.datepicker.view.popup.DatePickerPopup;
import com.ozcanalasalvar.datepicker.view.popup.TimePickerPopup;
import com.ozcanalasalvar.datepicker.view.timePicker.TimePicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//
//        DatePicker datePicker = findViewById(R.id.date_picker);
//        datePicker.setOffset(3);
//        datePicker.setDarkModeEnabled(true);
//        datePicker.setTextSize(19);
//        datePicker.setMaxDate(DateUtils.getTimeMiles(2050, 10, 25));
//        datePicker.setDate(DateUtils.getCurrentTime());
//        datePicker.setMinDate(DateUtils.getTimeMiles(1995, 1, 12));
//
//
//        datePicker.setDataSelectListener(new DatePicker.DataSelectListener() {
//            @Override
//            public void onDateSelected(long date, int day, int month, int year) {
//                Toast.makeText(getApplicationContext(), "" + day + "/" + (month + 1) + "/" + year, Toast.LENGTH_LONG).show();
//            }
//        });
    }


    public void openDatePicker(View view) {
        DatePickerPopup popup = new DatePickerPopup.Builder()
                .from(this)
                .offset(3)
                .darkModeEnabled(true)
                .textSize(19)
                .endDate(DateUtils.getTimeMiles(2050, 10, 25))
                .currentDate(DateUtils.getCurrentTime())
                .startDate(DateUtils.getTimeMiles(1995, 0, 1))
                .listener((dp, date, day, month, year) -> Toast.makeText(getApplicationContext(), "" + day + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT).show())
                .build();
        popup.show();
    }

    public void openTimePicker(View view) {
        TimePickerPopup popup = new TimePickerPopup.Builder()
                .from(this)
                .offset(3)
                .textSize(17)
                .setTime(12, 12)
                .listener(new TimePickerPopup.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelected(TimePicker timePicker, int hour, int minute) {
                        Toast.makeText(getApplicationContext(), "" + hour + ":" + minute, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
        popup.show();
    }
}