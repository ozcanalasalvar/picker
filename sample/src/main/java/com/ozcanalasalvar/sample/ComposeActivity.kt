package com.ozcanalasalvar.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ozcanalasalvar.library.compose.InfiniteWheelView
import com.ozcanalasalvar.library.compose.WheelView
import com.ozcanalasalvar.sample.ui.theme.DatePickerTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DatePickerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InfiniteWheelView(modifier = Modifier)
                }
            }
        }
    }
}



@Preview
@Composable
fun WheelViewPreview() {
    WheelView()
}