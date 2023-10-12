# DatePicker&TimePicker
Picker is a date and time picker library that supports view-based and jetpack compose UI design.

The library has many different feature options to customize your widget.


<a target="_blank" href="https://github-readme-medium-recent-article.vercel.app/medium/@ozcanalasalvar/0"><img src="https://github-readme-medium-recent-article.vercel.app/medium/@ozcanalasalvar/0" alt="Recent Article 0"> 



<br>

<p align="center">
  <img src="https://github.com/ozcanalasalvar/picker/blob/master/art/datepicker_record.gif" width="300">
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://github.com/ozcanalasalvar/picker/blob/master/art/datepicker_dark.gif" width="300">
</p>
<br>

<p align="center">
  <img src="https://github.com/ozcanalasalvar/picker/blob/master/art/datepicker_image.jpg" width="300">
	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://github.com/ozcanalasalvar/picker/blob/master/art/datepicker_image_dark.jpg" width="300">
</p>

<br>

<br>
<p align="center">
   <img src="https://github.com/ozcanalasalvar/picker/blob/master/art/timepicker_image.jpg" width="300">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <img src="https://github.com/ozcanalasalvar/picker/blob/master/art/timepicker_image2.jpg" width="300">
</p>
<br>
<br>
<br>


# Compose Usage

<br>

```java
WheelDatePicker(
	offset =/*offset*/,
	yearsRange =/*yearsRange*/,
	startDate =/*startDate*/,
	textSize =/*textSize*/,
	selectorEffectEnabled =/*selectorEffectEnabled*/,
	darkModeEnabled =/*darkModeEnabled*/,
	onDateSelected = day, month, year, date -> {
	  /*Handle date changes*/
	}
)
```


```java
WheelTimePicker(
	offset =/*offset*/,
	selectorEffectEnabled =/*selectorEffectEnabled*/,
	timeFormat = TimeFormat.CLOCK_12H,
	startTime =/*startTime*/,
	textSize =/*textSize*/,
	darkModeEnabled =/*darkModeEnabled*/,
	onTimeSelected = { hour, minute, format ->
	  /*Handle time changes*/       
	}
)
```
<br>
<br>
<br>

# View Usage

<br>

> **_NOTE:_** If you would like to use library on view based ui, add [androidx.compose.material3](https://developer.android.com/jetpack/androidx/releases/compose-material3) to gradle dependencies.

<br>

#### Date Picker 

```java
datePicker.apply {
            setOffset(/*offset*/)
            setTextSize(/*textSize*/)
            setDate(getCurrentTime())
            setDarkModeEnabled(/*darkModeEnabled*/)
            setDataSelectListener(object : DatePicker.DataSelectListener {
                override fun onDateSelected(date: Long, day: Int, month: Int, year: Int) {
                    //Handle date changes
                }
            })
        }
```

```xml    
    <com.ozcanalasalvar.datepicker.view.datepicker.DatePicker
            android:id="@+id/datepicker"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:darkModeEnabled="true"
            app:offset="3"
            app:textSize="19" />
```
##### Popup Usage

```java
 val datePickerPopup = DatePickerPopup.Builder()
            .from(/*context*/)
            .offset(/*offset*/)
            .textSize(/*textSize*/)
            .selectedDate(getCurrentTime())
            .darkModeEnabled(/*darkModeEnabled*/)
            .listener(object : DatePickerPopup.OnDateSelectListener {
                override fun onDateSelected(
                    dp: DatePicker?,
                    date: Long,
                    day: Int,
                    month: Int,
                    year: Int
                ) {

                }
            })
            .build()

datePickerPopup.show(supportFragmentManager, TAG)
```



### Time Picker

```java
timePicker.apply {
            setOffset(/*offset*/)
            setTextSize(/*textSize*/)
            setTimeFormat(TimeFormat.CLOCK_12H)
            setTime(/*hour*/, /*minute*/)
            setDarkModeEnabled(/*darkModeEnabled*/)
            setTimeSelectListener(object : TimePicker.TimeSelectListener {
                override fun onTimeSelected(hour: Int, minute: Int, timeFormat: String?) {
                    //Handle time changes
                }
            })
        }
```

```xml    
<com.ozcanalasalvar.datepicker.view.timepicker.TimePicker
            android:id="@+id/timepicker"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="10dp"
            app:darkModeEnabled="true"
            app:is24HourViewEnabled="false"
            app:offset="2"
            app:textSize="17" />
```
##### Popup Usage

```java
val pickerPopup = TimePickerPopup.Builder()
            .from(/*context*/)
            .offset(/*offset*/)
            .textSize(/*textSize*/)
            .setTime(/*hour*/, /*minute*/)
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .darkModeEnabled(/*darkModeEnabled*/)
            .listener(object : TimePickerPopup.OnTimeSelectListener {
                override fun onTimeSelected(
                    timePicker: TimePicker?,
                    hour: Int,
                    minute: Int,
                    format: String?
                ) {
                    
                }
            })
            .build()

pickerPopup.show(supportFragmentManager, TAG)
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
	implementation 'com.github.ozcanalasalvar.picker:datepicker:2.0.2'
	implementation 'com.github.ozcanalasalvar.picker:wheelview:2.0.2'

	//For view based UI's
	implementation 'androidx.compose.material3:material3:1.1.2'
}
```
