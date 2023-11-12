# DatePicker&TimePicker
Picker is a date and time picker library that supports view-based and jetpack compose UI design.

The library has many different feature options to customize your widget. Also checkout this [Wheelview](https://github.com/ozcanalasalvar/Wheelview).


<a target="_blank" href="https://github-readme-medium-recent-article.vercel.app/medium/@ozcanalasalvar/2"><img src="https://github-readme-medium-recent-article.vercel.app/medium/@ozcanalasalvar/2" alt="Recent Article 2"> 

<br>

<p align="start">
  <img src="https://github.com/ozcanalasalvar/picker/blob/master/art/popupgif.gif" width="300">
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://github.com/ozcanalasalvar/picker/blob/master/art/popupdark.gif" width="300">
</p>
<br>

<p align="start">
  <img src="https://github.com/ozcanalasalvar/picker/blob/master/art/datepickergif.gif" width="300">
	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://github.com/ozcanalasalvar/picker/blob/master/art/datepickerdarkgif.gif" width="300">
</p>

<br>

<br>
<p align="start">
   <img src="https://github.com/ozcanalasalvar/picker/blob/master/art/timepickergif.gif" width="300">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <img src="https://github.com/ozcanalasalvar/picker/blob/master/art/timepickerdarkgif.gif" width="300">
</p>
<br>
<br>
<br>


# Jetpack Compose Usage

<br>

```java
WheelDatePicker(
	offset =/*offset*/,
	yearsRange = IntRange(/*minYear*/,/*maxYear*/),
	startDate =/*startDate*/,
	textSize =/*textSize*/,
	selectorEffectEnabled =/*selectorEffectEnabled*/,
	darkModeEnabled =/*darkModeEnabled*/,
	onDateChanged = { day, month, year, date ->
	  /*Handle date changes*/
	}
)
```

#### Parameters
Parameter | Type | Description
------ | ----- | --
offset | Int | Count of items to be shown on picker 
yearsRange | IntRange | Min and max date of picker 
startDate | Long | Selected date of picker by default today date 
textSize | Int | Size of text on picker 
selectorEffectEnabled | Boolean | Uses to enable or disable the selection effect
darkModeEnabled | Boolean | Uses to  enable or disable dark mode. If you disable it, although you set display settings dark, component stay light mode
onDateSelected |  | Called when the values in the picker date picker are updated 

<br>
<br>


```java
WheelTimePicker(
	offset =/*offset*/,
	timeFormat = TimeFormat.CLOCK_12H,
	startTime =/*startTime*/,
	textSize =/*textSize*/,
        selectorEffectEnabled =/*selectorEffectEnabled*/, 
	darkModeEnabled =/*darkModeEnabled*/,
	onTimeChanged = { hour, minute, format ->
	  /*Handle time changes*/       
	}
)
```

#### Parameters
Parameter | Type | Description
------ | ----- | --
offset | Int | Count of items to be shown on picker 
timeFormat | TimeFormat | Time format of picker. TimeFormat.CLOCK_12H or TimeFormat.CLOCK_24H
startTime | Time | Selected time of picker by default current time
textSize | Int | Size of text on picker 
selectorEffectEnabled | Boolean | Uses to enable or disable the selection effect
darkModeEnabled | Boolean | Uses to  enable or disable dark mode. If you disable it, although you set display settings dark, component stay light mode
onTimeSelected |  | Called when the values in the picker date picker are updated


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
            setDateChangeListener(object : DatePicker.DateChangeListener {
                override fun onDateChanged(date: Long, day: Int, month: Int, year: Int) {
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
<br>
<br>

#### XML attributes
Parameter | Type | Description
------ | ----- | --
offset | Int | Count of items to be shown on picker 
textSize | Int | Size of text on picker 
darkModeEnabled | Boolean | Uses to  enable or disable dark mode. If you disable it, although you set display settings dark, component stay light mode


##### Popup Usage

```java
 val datePickerPopup = DatePickerPopup.Builder()
            .from(/*context*/)
            .offset(/*offset*/)
            .textSize(/*textSize*/)
            .selectedDate(getCurrentTime())
            .darkModeEnabled(/*darkModeEnabled*/)
            .listener(object : DatePickerPopup.DateSelectListener {
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
            setTimeChangeListener(object : TimePicker.TimeChangeListener {
                override fun onTimeChanged(hour: Int, minute: Int, timeFormat: String?) {
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

#### XML attributes
Parameter | Type | Description
------ | ----- | --
offset | Int | Count of items to be shown on picker 
is24HourViewEnabled | Boolen | Time format of picker. If true TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H
textSize | Int | Size of text on picker 
darkModeEnabled | Boolean | Uses to  enable or disable dark mode. If you disable it, although you set display settings dark, component stay light mode
<br>
<br>

##### Popup Usage

```java
val pickerPopup = TimePickerPopup.Builder()
            .from(/*context*/)
            .offset(/*offset*/)
            .textSize(/*textSize*/)
            .setTime(/*hour*/, /*minute*/)
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .darkModeEnabled(/*darkModeEnabled*/)
            .listener(object : TimePickerPopup.TimeSelectListener {
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

###### Add it in your root build.gradle at the end of repositories:

```groovy
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		mavenCentral()
		maven { url 'https://jitpack.io' }
	}
}
```

###### Add the dependency

```groovy
dependencies {
	implementation 'com.github.ozcanalasalvar.picker:datepicker:2.0.7'
	implementation 'com.github.ozcanalasalvar.picker:wheelview:2.0.7'

	//For view based UI's
	implementation 'androidx.compose.material3:material3:Tag'
}
```
