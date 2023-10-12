package com.ozcanalasalvar.datepicker.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.ozcanalasalvar.datepicker.ui.theme.PickerTheme
import com.ozcanalasalvar.datepicker.ui.theme.colorLightOnBackground
import com.ozcanalasalvar.datepicker.ui.theme.colorLightTextPrimary

@Composable
fun SelectorView(modifier: Modifier = Modifier, darkModeEnabled: Boolean, offset: Int) {
    Column(
        modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .weight(offset.toFloat())
                .fillMaxWidth()
                .background(if (darkModeEnabled) PickerTheme.colors.onbackground else colorLightOnBackground),
        )


        Column(
            modifier = Modifier
                .weight(1.13f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .height(0.5.dp)
                    .alpha(0.5f)
                    .background(if (darkModeEnabled) PickerTheme.colors.textPrimary else colorLightTextPrimary)
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .height(0.5.dp)
                    .alpha(0.5f)
                    .background(if (darkModeEnabled) PickerTheme.colors.textPrimary else colorLightTextPrimary)
                    .fillMaxWidth()
            )

        }



        Box(
            modifier = Modifier
                .weight(offset.toFloat())
                .fillMaxWidth()
                .background(if (darkModeEnabled) PickerTheme.colors.onbackground else colorLightOnBackground),
        )
    }
}