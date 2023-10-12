package com.ozcanalasalvar.datepicker.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember


object PickerTheme {

    val colors: PickerColors
        @Composable @ReadOnlyComposable get() = if (!isSystemInDarkTheme()) lightColors() else darkColors()

    val typography: PickerTypography
        @Composable @ReadOnlyComposable get() = LocalTypography.current

    val dimensions: PickerDimensions
        @Composable @ReadOnlyComposable get() = LocalDimensions.current

    val pallets: List<androidx.compose.ui.graphics.Color>
        @Composable @ReadOnlyComposable get() = if (!isSystemInDarkTheme()) lightPallet else darkPallet
}