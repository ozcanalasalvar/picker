package com.ozcanalasalvar.library.compose.timepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.material.timepicker.TimeFormat
import com.ozcanalasalvar.library.compose.InfiniteWheelView
import com.ozcanalasalvar.library.model.TimeModel
import com.ozcanalasalvar.library.utils.DateUtils


@Composable
fun WheelTimePicker(
    offset: Int = 4,
    selectorEffectEnabled: Boolean = true,
    timeFormat: Int = TimeFormat.CLOCK_24H,
    startTime: TimeModel = TimeModel(DateUtils.getCurrentHour(), DateUtils.getCurrentMinute()),
    textSize: Int = 16,
    onTimeSelected: (Int, Int, String?) -> Unit = { _, _, _ -> }
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
        onTimeSelected(selectedTime.hour, selectedTime.minute, selectedTime.format)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        val height = (2 * offset * (fontSize + 10) + 1).dp


        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 100.dp, end = 100.dp)
        ) {


            InfiniteWheelView(modifier = Modifier.weight(3f),
                size = DpSize(150.dp, height),
                selection = 0,
                itemCount = hours.size,
                rowOffset = offset,
                selectorEffectEnabled = selectorEffectEnabled,
                onFocusItem = {
                    selectedTime = selectedTime.copy(hour = hours[it])
                },
                content = {
                    Text(
                        text = if (hours[it] < 10) "0${hours[it]}" else "${hours[it]}",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(50.dp),
                        fontSize = fontSize.sp
                    )
                })


            InfiniteWheelView(modifier = Modifier.weight(3f),
                size = DpSize(150.dp, height),
                selection = 0,
                itemCount = minutes.size,
                rowOffset = offset,
                selectorEffectEnabled = selectorEffectEnabled,
                onFocusItem = {
                    selectedTime = selectedTime.copy(minute = minutes[it])
                },
                content = {
                    Text(
                        text = if (minutes[it] < 10) "0${minutes[it]}" else "${minutes[it]}",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(100.dp),
                        fontSize = fontSize.sp
                    )
                })

            if (timeFormat == TimeFormat.CLOCK_12H) {
                InfiniteWheelView(modifier = Modifier.weight(2f),
                    size = DpSize(150.dp, height),
                    selection = 0,
                    itemCount = formats.size,
                    rowOffset = offset,
                    isEndless = false,
                    selectorEffectEnabled = selectorEffectEnabled,
                    onFocusItem = {
                        selectedTime = selectedTime.copy(format = formats[it])
                    },
                    content = {
                        Text(
                            text = formats[it].toString(),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.width(100.dp),
                            fontSize = fontSize.sp
                        )
                    })
            }

        }


        Column(
            Modifier.fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .weight(offset.toFloat())
                    .fillMaxWidth()
                    .background(Color(0x99FFFFFF)),
            )


            Column(
                modifier = Modifier
                    .weight(1.10f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .height(0.5.dp)
                        .alpha(0.5f)
                        .background(Color.Black)
                        .fillMaxWidth()
                )
                Box(
                    modifier = Modifier
                        .height(0.5.dp)
                        .alpha(0.5f)
                        .background(Color.Black)
                        .fillMaxWidth()
                )

            }



            Box(
                modifier = Modifier
                    .weight(offset.toFloat())
                    .fillMaxWidth()
                    .background(Color(0x99FFFFFF)),
            )
        }


    }
}