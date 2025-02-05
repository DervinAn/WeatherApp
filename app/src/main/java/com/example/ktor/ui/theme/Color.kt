package com.example.ktor.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val  Dark_blue_gray = Color(0xFF444E72)
val  soft_blue_gray = Color(0xFF838BAA)
val gradientColors1 =
    listOf(
        Color(0xFF47BFDF),
        Color(0xFF4A91FF)
    )

val gradientBlue = Brush.linearGradient(
    colors = gradientColors1,
    start = androidx.compose.ui.geometry.Offset(0f, 0f), // Top-left corner
    end = androidx.compose.ui.geometry.Offset(0f, Float.POSITIVE_INFINITY) // Bottom-right corner
)

val gradientColors2 =  listOf(
    Color(0xFFFFFFFF),
    Color(0xFFBFBFBF)
)
val gradientGray = Brush.linearGradient(
    colors = com.example.ktor.ui.theme.gradientColors2,
    start = androidx.compose.ui.geometry.Offset(0f, 0f),
    end = androidx.compose.ui.geometry.Offset(0f, Float.POSITIVE_INFINITY)
)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)