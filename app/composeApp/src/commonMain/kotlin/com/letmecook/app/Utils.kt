package com.letmecook.app

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MinimalGlowBackground(
    modifier: Modifier = Modifier,
    glowColor: Color = Color(0xFF2F3A8F).copy(alpha = 0.35f)
) {
    Box(
        modifier
            .fillMaxSize()
            .background(Color(0xFF0B0B0B))
    ) {
        Box(
            Modifier
                .size(220.dp)
                .offset(x = (-40).dp, y = (-20).dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(glowColor, Color.Transparent)
                    )
                )
                .blur(60.dp)
        )
    }
}

@Composable
fun TagChip(
    text: String,
    modifier: Modifier = Modifier,
    background: Color = Color("5b5fff1a"),
    contentColor: Color = Color("8B8FFF"),
    borderColor: Color = Color("5b5fff33"),
) {
    Box(
        modifier = modifier
            .background(
                color = background,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            color = contentColor,
            style = MaterialTheme.typography.labelMedium
        )
    }
}
