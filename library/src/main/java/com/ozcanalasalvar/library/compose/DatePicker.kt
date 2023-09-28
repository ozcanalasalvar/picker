package com.ozcanalasalvar.library.compose

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
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ozcanalasalvar.library.compose.util.daysOfDate
import com.ozcanalasalvar.library.compose.util.monthsOfDate
import com.ozcanalasalvar.library.compose.util.withDay
import com.ozcanalasalvar.library.compose.util.withMonth
import com.ozcanalasalvar.library.compose.util.withYear
import com.ozcanalasalvar.library.model.DateModel
import com.ozcanalasalvar.library.utils.DateUtils
import java.text.DateFormatSymbols


@Composable
fun DatePicker(
    offset: Int = 4,
    yearsRange: IntRange = IntRange(1923, 2121),
    startDate: DateModel = DateModel(DateUtils.getCurrentTime()),
    selectorEffectEnabled: Boolean = true,
    onDateSelected: (Int, Int, Int, Long) -> Unit = { _, _, _, _ -> }
) {

    var selectedDate by remember { mutableStateOf(startDate) }

    val months = selectedDate.monthsOfDate()

    val days = selectedDate.daysOfDate()

    val years = mutableListOf<Int>().apply {
        for (year in yearsRange) {
            add(year)
        }
    }

    LaunchedEffect(selectedDate) {
        onDateSelected(selectedDate.day, selectedDate.month, selectedDate.year, selectedDate.date)
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        val height = (2 * offset * 26 + 1).dp


        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp)
        ) {


            key(days.size) {
                InfiniteWheelView(modifier = Modifier.weight(1f),
                    size = DpSize(150.dp, height),
                    selection = maxOf(days.indexOf(selectedDate.day), 0),
                    itemCount = days.size,
                    rowOffset = offset,
                    selectorEffectEnabled = selectorEffectEnabled,
                    onFocusItem = {
                        selectedDate = selectedDate.withDay(days[it])
                    },
                    content = {
                        Text(
                            text = days[it].toString(),
                            textAlign = TextAlign.End,
                            modifier = Modifier.width(50.dp),
                            fontSize = 16.sp
                        )
                    })
            }

            InfiniteWheelView(modifier = Modifier.weight(2f),
                size = DpSize(150.dp, height),
                selection = maxOf(months.indexOf(selectedDate.month), 0),
                itemCount = months.size,
                rowOffset = offset,
                selectorEffectEnabled = selectorEffectEnabled,
                onFocusItem = {
                    selectedDate = selectedDate.withMonth(months[it])
                },
                content = {
                    Text(
                        text = DateFormatSymbols().months[months[it]],
                        textAlign = TextAlign.Start,
                        modifier = Modifier.width(100.dp),
                        fontSize = 16.sp
                    )
                })


            InfiniteWheelView(modifier = Modifier.weight(1f),
                size = DpSize(150.dp, height),
                selection = years.indexOf(selectedDate.year),
                itemCount = years.size,
                rowOffset = offset,
                isEndless = years.size > offset * 2,
                selectorEffectEnabled = selectorEffectEnabled,
                onFocusItem = {
                    selectedDate = selectedDate.withYear(years[it])
                },
                content = {
                    Text(
                        text = years[it].toString(),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.width(100.dp),
                        fontSize = 16.sp
                    )
                })
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


@Preview
@Composable
fun DatePickerPreview() {
    DatePicker(onDateSelected = { _, _, _, _ -> })
}