package com.ozcanalasalvar.datepicker.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class PickerColors(
    primary: Color,
    textPrimary: Color,
    textSecondary: Color,
    background: Color,
    onBackground: Color,
    error: Color,
    isLight: Boolean
) {
    var primary by mutableStateOf(primary)
        private set
    var textSecondary by mutableStateOf(textSecondary)
        private set
    var textPrimary by mutableStateOf(textPrimary)
        private set
    var error by mutableStateOf(error)
        private set
    var background by mutableStateOf(background)
        private set
    var onbackground by mutableStateOf(onBackground)
        private set
    var isLight by mutableStateOf(isLight)
        internal set

    fun copy(
        primary: Color = this.primary,
        textPrimary: Color = this.textPrimary,
        textSecondary: Color = this.textSecondary,
        error: Color = this.error,
        background: Color = this.background,
        onBackground: Color = this.onbackground,
        isLight: Boolean = this.isLight
    ): PickerColors = PickerColors(
        primary,
        textPrimary,
        textSecondary,
        error,
        background,
        onBackground,
        isLight
    )

    fun updateColorsFrom(other: PickerColors) {
        primary = other.primary
        textPrimary = other.textPrimary
        textSecondary = other.textSecondary
        background = other.background
        error = other.error
    }
}

val colorLightPrimary = Color(0xFFFFFFFF)
val colorLightTextPrimary = Color(0xFF000000)
val colorLightTextSecondary = Color(0xFF6C727A)
val colorLightBackground = Color(0xFFFFFFFF)
val colorLightOnBackground = Color(0x99FFFFFF)
val colorLightError = Color(0xFFD62222)

val colorDarkPrimary = Color(0xFF000000)
val colorDarkTextPrimary = Color(0xFFFFFFFF)
val colorDarkTextSecondary = Color(0xFFFFFFFF)
val colorDarkBackground = Color(0xFF090A0A)
val colorDarkOnBackground = Color(0x99000000)
val colorDarkError = Color(0xFFD62222)

fun lightColors(
    primary: Color = colorLightPrimary,
    textPrimary: Color = colorLightTextPrimary,
    textSecondary: Color = colorLightTextSecondary,
    background: Color = colorLightBackground,
    onBackground: Color = colorLightOnBackground,
    error: Color = colorLightError
): PickerColors = PickerColors(
    primary = primary,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    background = background,
    error = error,
    onBackground =onBackground,
    isLight = true
)

fun darkColors(
    primary: Color = colorDarkPrimary,
    textPrimary: Color = colorDarkTextPrimary,
    textSecondary: Color = colorDarkTextSecondary,
    background: Color = colorDarkBackground,
    onBackground: Color = colorDarkOnBackground,
    error: Color = colorDarkError
): PickerColors = PickerColors(
    primary = primary,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    background = background,
    error = error,
    onBackground = onBackground,
    isLight = false
)

val LocalColors = staticCompositionLocalOf { lightColors() }