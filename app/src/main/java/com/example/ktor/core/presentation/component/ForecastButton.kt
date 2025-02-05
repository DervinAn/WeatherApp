package com.example.ktor.core.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ktor.util.presentation.TextWea

@Composable
fun ForecastButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier
            .fillMaxWidth(0.6f)
            .padding(bottom = 26.dp)
            .height(50.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp
        ),
        shape = ButtonDefaults.shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        onClick = { onClick() }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center // Center the text inside the Box
        ) {
            TextWea(
                text = "Forecast report",
                textAlign = TextAlign.Center,
                textColor = Color.Black
            )
        }
    }
}