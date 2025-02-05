package com.example.ktor.core.presentation.mapper

import android.util.Log
import androidx.compose.runtime.Composable

@Composable
fun CheckDayOrNight(
    dt: Long,        // Current timestamp
    sunrise: Long,   // Sunrise timestamp
    sunset: Long     // Sunset timestamp
) {
    val isNight = dt < sunrise || dt > sunset // Compare timestamps

    val message = if (isNight) {
       Log.d("isNight", isNight.toString())
    } else {
        Log.d("isNight", isNight.toString())
    }
}
