package com.ozcanalasalvar.datepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.ozcanalasalvar.datepicker.utils.DateUtils;
import com.ozcanalasalvar.datepicker.view.DatePicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatePicker datePicker = findViewById(R.id.date_picker);
        datePicker.setOffset(3);
        datePicker.setMaxDate(DateUtils.getTimeMiles(2050, 10, 25));
        datePicker.setDate(DateUtils.getCurrentTime());
        datePicker.setMinDate(DateUtils.getTimeMiles(1995, 1, 12));
        datePicker.setTextSize(19);


        datePicker.setDataSelectListener(new DatePicker.DataSelectListener() {
            @Override
            public void onDateSelected(long date, int day, int month, int year) {
                Toast.makeText(getApplicationContext(), "" + day + "/" + (month+1) + "/" + year, Toast.LENGTH_LONG).show();
            }
        });
    }
}