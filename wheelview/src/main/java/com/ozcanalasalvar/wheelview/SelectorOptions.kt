package com.ozcanalasalvar.wheelview

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class SelectorOptions(
    val enabled: Boolean = true,
    val selectEffectEnabled: Boolean = true,
    val color: Color = Color.Black.copy(alpha = 0.7f),
    val width: Dp = 0.5.dp,
    val alpha: Float = 0.5f
)