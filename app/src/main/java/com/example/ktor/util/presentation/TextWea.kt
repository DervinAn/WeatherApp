package com.example.ktor.util.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.ktor.ui.theme.overpassFontFamily

@Composable
fun TextWea(
    text: String="Default Text", fontSize: Int=18,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color = Color.White,
    shadow: Offset = Offset(1f, 1f),
    blur: Float = 1f,
    modifier: Modifier = Modifier
    ){
    Text(
        text,
        modifier = modifier,
        style = TextStyle(
            fontSize = fontSize.sp,
            fontWeight = fontWeight,
            textAlign = textAlign,
            fontFamily = overpassFontFamily,
            color = textColor,
            shadow = Shadow(Color(0xFF757474), shadow, blur)
        ),

    )

}