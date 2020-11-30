# DatePicker&TimePicker
Datepicker is an IOS datepicker widget-like date and time picker library.

The library has many different feature options to customize your widget.

## Features and Usage

<p align="center">
  <img src="https://user-images.githubusercontent.com/66948288/100557708-ee1bbb00-32bb-11eb-9905-80c8c775d705.gif" width="250">
  <img src="https://user-images.githubusercontent.com/66948288/100557710-f1af4200-32bb-11eb-8472-e64a3882f85e.gif" width="250">
   <img src="https://user-images.githubusercontent.com/66948288/100557680-c9274800-32bb-11eb-8b1a-f53e4a01d413.gif" width="250">
</p>
<br>


## DarkMode Support


<p align="center">
  <img src="https://user-images.githubusercontent.com/66948288/100557614-659d1a80-32bb-11eb-9855-0990892a3b4d.png" width="250">
  <img src="https://user-images.githubusercontent.com/66948288/100557616-68980b00-32bb-11eb-8ee1-36adbffbaea2.png" width="250">
</p>
<br>


#### Date Picker 

```java
        DatePicker datePicker = findViewById(R.id.datepicker);
        datePicker.setOffset(3);
        datePicker.setTextSize(19);
        datePicker.setPickerMode(DatePicker.DAY_ON_FIRST);
        datePicker.setMaxDate(/*long time*/);
        datePicker.setDate(/*long time*/);
        datePicker.setMinDate(/*long time*/);
        
        datePicker.setDataSelectListener(new DatePicker.DataSelectListener() {
            @Override
            public void onDateSelected(long date, int day, int month, int year) {
               
            }
        });
```

```xml    
    <com.ozcanalasalvar.library.view.datePicker.DatePicker
            android:id="@+id/datepicker"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:offset="3"
            app:pickerMode="dayFirst"
            app:textSize="19" />
```
##### Popup Usage

```java
        datePickerPopup = new DatePickerPopup.Builder()
                .from(/*context*/)
                .offset(3)
                .pickerMode(DatePicker.MONTH_ON_FIRST)
                .textSize(19)
                .endDate(/*long time*/)
                .currentDate(/*long time*/)
                .startDate(/*long time*/)
                .listener(new DatePickerPopup.OnDateSelectListener() {
                    @Override
                    public void onDateSelected(DatePicker dp, long date, int day, int month, int year) {
                        
                    }
                })
                .build();
```



### Time Picker

```java
        TimePicker timePicker = findViewById(R.id.timepicker);
        timePicker.setOffset(2);
        timePicker.setTextSize(19);
        timePicker.setHour(/*hour*/);
        timePicker.setMinute(/*minute*/);
        timePicker.setTimeSelectListener(new TimePicker.TimeSelectListener() {
            @Override
            public void onTimeSelected(int hour, int minute) {
                
            }
        });
```

```xml    
    <com.ozcanalasalvar.library.view.timePicker.TimePicker
            android:id="@+id/timepicker"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:offset="2"
            app:textSize="17" />
```
##### Popup Usage

```java
       timePickerPopup = new TimePickerPopup.Builder()
                .from(this)
                .offset(3)
                .textSize(17)
                .setTime(/*hour*/, /*minute*/)
                .listener(new TimePickerPopup.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelected(TimePicker timePicker, int hour, int minute) {
                        
                    }
                })
                .build();
```


## Implementation Gradle

###### Add it in your root build.gradle at the end of repositories

```groovy
    repositories {
        maven { url 'https://jitpack.io' }
    }
```

###### Add the dependency

```groovy
    dependencies {
	    implementation 'com.github.OzcanAlasalvar:DatePicker:1.0.4'
	}
```
