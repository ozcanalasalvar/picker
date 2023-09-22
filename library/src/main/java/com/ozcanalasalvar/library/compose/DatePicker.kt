package com.ozcanalasalvar.library.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp


@Composable
fun DatePicker() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        var spannedIndexOfDay by remember {
            mutableStateOf(3)
        }

        val months = listOf<String>(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        )

        val days = mutableListOf<String>().apply {
            for (index in 1..21) {
                add(index.toString())
            }
        }

        val years = mutableListOf<String>().apply {
            for (index in 1..50) {
                add((1995 + index).toString())
            }
        }


        Row(modifier = Modifier.fillMaxSize()) {


            InfiniteWheelView(
                modifier = Modifier,
                size = DpSize(150.dp, 225.dp),
                startIndex = spannedIndexOfDay,
                itemCount = days.size,
                rowOffset = 4,
                isEndless = false,
                onFocusItem = {
                    Log.d("SpannedIndex", "$it")
                }, content = {
                    Text(
                        text = days[it],
                        textAlign = TextAlign.End,
                        modifier = Modifier.width(100.dp)
                    )
                })

            InfiniteWheelView(
                modifier = Modifier,
                size = DpSize(150.dp, 225.dp),
                startIndex = 0,
                itemCount = months.size,
                rowOffset = 4,
                onFocusItem = {
                    Log.d("SpannedIndex", "$it")
                }, content = {
                    Text(
                        text = months[it],
                        textAlign = TextAlign.Start,
                        modifier = Modifier.width(100.dp)
                    )
                })

            InfiniteWheelView(
                modifier = Modifier,
                size = DpSize(150.dp, 225.dp),
                startIndex = 0,
                itemCount = years.size,
                rowOffset = 4,
                onFocusItem = {

                }, content = {
                    Text(
                        text = years[it],
                        textAlign = TextAlign.Start,
                        modifier = Modifier.width(100.dp)
                    )
                })
        }


        Column(
            Modifier
                .fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .height(97.dp)
                    .fillMaxWidth()
                    .background(Color(0x80FFFFFF)),
            )

            Box(
                modifier = Modifier
                    .border(
                        width = 0.5.dp,
                        color = Color(0x80000000),
                    )
                    .height(31.dp)
                    .fillMaxWidth()
            )


            Box(
                modifier = Modifier
                    .height(97.dp)
                    .fillMaxWidth()
                    .background(Color(0x80FFFFFF)),
            )
        }


    }
}