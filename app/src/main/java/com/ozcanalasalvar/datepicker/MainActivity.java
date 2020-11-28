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

    private TimePickerPopup pickerPopup;
    private DatePickerPopup datePickerPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        datePickerPopup = new DatePickerPopup(this);

        datePickerPopup = new DatePickerPopup.Builder()
                .from(this)
                .offset(3)
                .darkModeEnabled(true)
                .textSize(20)
                .endDate(DateUtils.getTimeMiles(2050, 10, 25))
                .currentDate(DateUtils.getCurrentTime())
                .startDate(DateUtils.getTimeMiles(1995, 0, 1))
                .listener((dp, date, day, month, year) -> Toast.makeText(getApplicationContext(), "" + day + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT).show())
                .build();

        pickerPopup = new TimePickerPopup.Builder()
                .from(this)
                .offset(3)
                .textSize(19)
                .setTime(12, 12)
                .listener((timePicker, hour, minute) -> Toast.makeText(getApplicationContext(), "" + hour + ":" + minute, Toast.LENGTH_SHORT).show())
                .build();
//+1
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
        datePickerPopup.show();
    }

    public void openTimePicker(View view) {
        pickerPopup.show();
    }
}