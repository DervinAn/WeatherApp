package com.example.ktor.util.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ktor.R


@Composable
fun ErrorMessage(message: String,modifier: Modifier) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        if (message=="No internet connection") {
            Image(
                painter = painterResource(id = R.drawable.baseline_wifi_off_24),
                contentDescription = null,
                modifier = Modifier.padding(16.dp)
            )
        }

        Text(
            text = message,
            color = Color.Red,
            fontSize = 25.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}