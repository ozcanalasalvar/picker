package com.ozcanalasalvar.sample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ozcanalasalvar.library.compose.DatePicker
import com.ozcanalasalvar.library.compose.InfiniteWheelView
import com.ozcanalasalvar.sample.ui.theme.DatePickerTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DatePickerTheme {
                // A surface container using the 'background' color from the theme
                DatePicker()


//                InfiniteWheelView(modifier = Modifier,
//                    size = DpSize(150.dp, 500.dp),
//                    selection = 0,
//                    itemCount = 28,
//                    rowOffset = 4,
//                    onFocusItem = {
//                        Log.d("SpannedIndex", "$it")
//                    },
//                    content = {
//                       Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription ="test" )
//                    })
            }
        }
    }
}


@Preview
@Composable
fun WheelViewPreview() {
    DatePicker()
}