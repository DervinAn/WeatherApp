    package com.example.ktor.core.presentation.mapper

    import com.example.ktor.R

    private val dayWeatherIcons = mapOf(
        // Rain-related weather
        "Rain" to R.drawable.rain,
        "Light rain" to R.drawable.rain_wea,
        "Heavy rain" to R.drawable.rain,
        "Drizzle" to R.drawable.rain,
        "Showers" to R.drawable.rain,

        // Cloud-related weather
        "Clouds" to R.drawable.cloudy_wea,
        "Overcast clouds" to R.drawable.weather1,
        "Broken clouds" to R.drawable.weather1,
        "Partly cloudy" to R.drawable.weather1,
        "Scattered clouds" to R.drawable.weather1,

        // Clear weather
        "Clear sky" to R.drawable.sun,
        "Sunny" to R.drawable.sun,
        "Bright sky" to R.drawable.sun,

        // Glow of the sun
        "Glow of sun" to R.drawable.glow_of_sun,
        "Sunrise glow" to R.drawable.glow_of_sun,

        // Special cloud-related weather
        "Clouds spark" to R.drawable.cloud_sparks,
        "Thunderstorm clouds" to R.drawable.cloud_sparks,
        "Electric clouds" to R.drawable.cloud_sparks,

        // Potential rain
        "Potential rain" to R.drawable.potential_for_rain,
        "Possible drizzle" to R.drawable.potential_for_rain,
        "Light showers" to R.drawable.potential_for_rain
    )

    private val nightWeatherIcons = mapOf(
        // Clear night
        "Clear sky" to R.drawable.sun,
        "Sunny" to R.drawable.glow_of_sun,
        "Bright sky" to R.drawable.icon_of_sun,

        // Cloudy night
        "Clouds" to R.drawable.cloudy_moon,
        "Overcast clouds" to R.drawable.cloudy_moon,
        "Broken clouds" to R.drawable.cloudy_moon,
        "Partly cloudy" to R.drawable.cloudy_moon,
        "Scattered clouds" to R.drawable.cloudy_moon,

        // Moon-specific weather
        "Moon" to R.drawable.half_moon
    )

    fun getDrawableIdForWeather(weather: String, isNight: Boolean = false): Int {
        val weatherKey = weather.lowercase().trim() // Normalize input for consistency

        return if (isNight) {
            nightWeatherIcons[weatherKey] ?: R.drawable.half_moon // Default to half moon at night
        } else {
            dayWeatherIcons[weatherKey] ?: R.drawable.rain // Default to rain during the day
        }
    }