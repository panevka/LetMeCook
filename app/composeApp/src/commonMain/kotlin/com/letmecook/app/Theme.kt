package com.letmecook.app

import androidx.compose.ui.graphics.Color

fun Color(hex: String): Color {
    val cleanHex = hex.removePrefix("#")
    val colorLong = if (cleanHex.length == 6) {
        0xFF000000 or cleanHex.toLong(16)
    } else {
        cleanHex.toLong(16)
    }
    return Color(colorLong)
}

object AppColors {
    val Primary = Color("#1E2139")
    val PrimaryFontColor = Color("#8D909F")
    val SecondaryFontColor = Color("#FFFFFF")
}