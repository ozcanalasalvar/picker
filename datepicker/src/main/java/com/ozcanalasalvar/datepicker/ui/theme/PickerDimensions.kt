package com.ozcanalasalvar.datepicker.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class PickerDimensions(
    val paddingSmall: Dp = 4.dp,
    val paddingMedium: Dp = 8.dp,
    val paddingLarge: Dp = 24.dp
)

internal val LocalDimensions = staticCompositionLocalOf { PickerDimensions() }