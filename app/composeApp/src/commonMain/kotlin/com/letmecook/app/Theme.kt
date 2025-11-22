package com.letmecook.app

import androidx.compose.ui.graphics.Color


fun Color(hex: String): Color {
    val cleanHex = hex.removePrefix("#")
    val colorLong = when (cleanHex.length) {
        6 -> 0xFF000000 or cleanHex.toLong(16) // RRGGBB -> FF RRGGBB
        8 -> {
            // Convert RRGGBBAA -> AARRGGBB
            val rrggbb = cleanHex.substring(0, 6).toLong(16)
            val aa = cleanHex.substring(6, 8).toLong(16)
            (aa shl 24) or rrggbb
        }
        else -> throw IllegalArgumentException("Invalid hex color")
    }
    return Color(colorLong)
}
object AppColors {
    val Primary = Color("#1E2139")
    val PrimaryFontColor = Color("#8D909F")
    val SecondaryFontColor = Color("#FFFFFF")
}