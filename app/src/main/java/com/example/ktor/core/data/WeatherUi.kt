package com.example.ktor.core.data

import androidx.annotation.DrawableRes
import com.example.ktor.core.presentation.mapper.getDrawableIdForWeather
import com.example.ktor.util.models.WeatherResponse

data class WeatherUi(
    val main: String,
    val description: String,
    val temperature: Double,
    val humidity: Int,
    @DrawableRes val icon: Int,
    val windSpeed: Double = 0.0,
    val isItNight: Boolean = false
)



fun WeatherResponse.toWeatherUi(): WeatherUi {
    val isItNight = isNight(sys.sunrise, sys.sunset, dt)
    return WeatherUi(
        main = weather.firstOrNull()?.main.orEmpty(),
        description = weather.firstOrNull()?.description.orEmpty(),
        temperature = main.temp,
        humidity = main.humidity,
        icon = getDrawableIdForWeather(
            weather.firstOrNull()?.main.orEmpty(),
            isItNight
        ),
        windSpeed = wind.speed,
        isItNight = isItNight
    )
}

private fun isNight(sunrise: Long?, sunset: Long?, currentTimestamp: Long): Boolean {
    return sunrise?.let { currentTimestamp < it } == true || sunset?.let { currentTimestamp > it } == true
}