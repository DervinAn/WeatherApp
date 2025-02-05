    package com.example.ktor.core.domain.repository

    import com.example.ktor.core.data.local.WeatherEntity
    import com.example.ktor.util.models.WeatherResponse

    interface CoreRepository {
        suspend fun fetchWeather(city: String): WeatherResponse
        suspend fun fetchWeatherByLocation(latitude: Double, longitude: Double): WeatherResponse


        suspend fun saveWeatherToDatabase(weatherEntity: WeatherEntity)
        suspend fun getAllWeatherFromDatabase(): List<WeatherEntity>
    }