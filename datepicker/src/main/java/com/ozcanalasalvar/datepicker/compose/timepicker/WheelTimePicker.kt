package com.ozcanalasalvar.datepicker.compose.timepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.material.timepicker.TimeFormat
import com.ozcanalasalvar.datepicker.compose.component.SelectorView
import com.ozcanalasalvar.datepicker.model.Time
import com.ozcanalasalvar.datepicker.ui.theme.PickerTheme
import com.ozcanalasalvar.datepicker.ui.theme.colorLightPrimary
import com.ozcanalasalvar.datepicker.ui.theme.colorLightTextPrimary
import com.ozcanalasalvar.datepicker.ui.theme.lightPallet
import com.ozcanalasalvar.datepicker.utils.DateUtils
import com.ozcanalasalvar.wheelview.WheelView
import com.ozcanalasalvar.wheelview.SelectorOptions


@Composable
fun WheelTimePicker(
    offset: Int = 4,
    selectorEffectEnabled: Boolean = true,
    timeFormat: Int = TimeFormat.CLOCK_24H,
    startTime: Time = Time(DateUtils.getCurrentHour(), DateUtils.getCurrentMinute()),
    textSize: Int = 16,
    onTimeChanged: (Int, Int, String?) -> Unit = { _, _, _ -> },
    darkModeEnabled: Boolean = true,
) {

    var selectedTime by remember { mutableStateOf(startTime) }

    val formats = listOf<String>("AM", "PM")


    val hours = mutableListOf<Int>().apply {
        for (hour in 0..if (timeFormat == TimeFormat.CLOCK_24H) 23 else 12) {
            add(hour)
        }
    }

    val minutes = mutableListOf<Int>().apply {
        for (minute in 0..59) {
            add(minute)
        }
    }
    val fontSize = maxOf(13, minOf(19, textSize))

    LaunchedEffect(selectedTime) {
        onTimeChanged(selectedTime.hour, selectedTime.minute, selectedTime.format)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .background(if (darkModeEnabled) PickerTheme.colors.primary else colorLightPrimary),
        contentAlignment = Alignment.Center
    ) {

        val height=( fontSize + 10) .dp


        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 100.dp, end = 100.dp)
        ) {


            WheelView(modifier = Modifier.weight(3f),
                itemSize = DpSize(150.dp, height),
                selection = 0,
                itemCount = hours.size,
                rowOffset = offset,
                selectorOption = SelectorOptions().copy(selectEffectEnabled = selectorEffectEnabled, enabled = false),
                onFocusItem = {
                    selectedTime = selectedTime.copy(hour = hours[it])
                },
                content = {
                    Text(
                        text = if (hours[it] < 10) "0${hours[it]}" else "${hours[it]}",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(50.dp),
                        fontSize = fontSize.sp,
                        color = if (darkModeEnabled) PickerTheme.colors.textPrimary else colorLightTextPrimary
                    )
                })


            WheelView(modifier = Modifier.weight(3f),
                itemSize = DpSize(150.dp, height),
                selection = 0,
                itemCount = minutes.size,
                rowOffset = offset,
                selectorOption = SelectorOptions().copy(selectEffectEnabled = selectorEffectEnabled, enabled = false),
                onFocusItem = {
                    selectedTime = selectedTime.copy(minute = minutes[it])
                },
                content = {
                    Text(
                        text = if (minutes[it] < 10) "0${minutes[it]}" else "${minutes[it]}",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(100.dp),
                        fontSize = fontSize.sp,
                        color = if (darkModeEnabled) PickerTheme.colors.textPrimary else colorLightTextPrimary
                    )
                })

            if (timeFormat == TimeFormat.CLOCK_12H) {
                WheelView(modifier = Modifier.weight(2f),
                    itemSize = DpSize(150.dp, height),
                    selection = 0,
                    itemCount = formats.size,
                    rowOffset = offset,
                    isEndless = false,
                    selectorOption = SelectorOptions().copy(selectEffectEnabled = selectorEffectEnabled, enabled = false),
                    onFocusItem = {
                        selectedTime = selectedTime.copy(format = formats[it])
                    },
                    content = {
                        Text(
                            text = formats[it].toString(),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.width(100.dp),
                            fontSize = fontSize.sp,
                            color = if (darkModeEnabled) PickerTheme.colors.textPrimary else colorLightTextPrimary
                        )
                    })
            }

        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = if (darkModeEnabled) PickerTheme.pallets else lightPallet
                    )
                ),
        ) {}

        SelectorView(darkModeEnabled= darkModeEnabled, offset = offset)

    }
}