package com.example.risaapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = JungleGreen,
    onPrimary = Color.White,
    background = JungleGreen,
    onBackground = Color.White
)

@Composable
fun RisaAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography, // If Type.kt  is created
        content = content
    )
}
