package com.ozcanalasalvar.library.compose

import android.util.Log
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
import com.ozcanalasalvar.library.factory.DatePickerFactory
import com.ozcanalasalvar.library.model.DateModel


@Composable
fun DatePicker(
    offset: Int = 4,
    minDate: DateModel = DateModel(year = 1970, month = 1, day = 1),
    maxDate: DateModel = DateModel(year = 2050, month = 1, day = 1),
    selectedDate: DateModel = DateModel(year = 2023, month = 10, day = 13),
) {

    val factory = DatePickerFactory()
    factory.maxDate = maxDate
    factory.minDate = minDate
    factory.selectedDate = selectedDate

    val months = factory.monthList

    val days = factory.dayList

    val years = factory.yearList

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


            InfiniteWheelView(modifier = Modifier.weight(1f),
                size = DpSize(150.dp, height),
                selection = 8,
                itemCount = days.size,
                rowOffset = offset,
                isEndless = false,
                onFocusItem = {
                    Log.d("SpannedIndex", "$it")
                },
                content = {
                    Text(
                        text = days[it],
                        textAlign = TextAlign.End,
                        modifier = Modifier.width(50.dp),
                        fontSize = 16.sp
                    )
                })

            InfiniteWheelView(modifier = Modifier.weight(2f),
                size = DpSize(150.dp, height),
                selection = 0,
                itemCount = months.size,
                rowOffset = offset,
                onFocusItem = {
                    Log.d("SpannedIndex", "$it")
                },
                content = {
                    Text(
                        text = months[it],
                        textAlign = TextAlign.Start,
                        modifier = Modifier.width(100.dp),
                        fontSize = 16.sp
                    )
                })

            InfiniteWheelView(modifier = Modifier.weight(1f),
                size = DpSize(150.dp, height),
                selection = 0,
                itemCount = years.size,
                rowOffset = offset,
                onFocusItem = {

                },
                content = {
                    Text(
                        text = years[it],
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