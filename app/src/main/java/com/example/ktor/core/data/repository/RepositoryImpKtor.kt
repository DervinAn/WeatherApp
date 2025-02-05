package com.example.ktor.core.data.repository

import android.util.Log
import com.example.ktor.core.data.local.WeatherDao
import com.example.ktor.core.data.local.WeatherEntity
import com.example.ktor.core.data.remote.KtorApiService
import com.example.ktor.core.domain.repository.CoreRepository
import com.example.ktor.util.models.WeatherResponse

class RepositoryImplKtor(
    private val ktorApiService: KtorApiService,
    private val weatherDao: WeatherDao
) : CoreRepository {

    // Fetch weather by city name
    override suspend fun fetchWeather(city: String): WeatherResponse {
        return ktorApiService.getWeather(city)
    }

    // Fetch weather by latitude and longitude
    override suspend fun fetchWeatherByLocation(latitude: Double, longitude: Double): WeatherResponse {
        return ktorApiService.getWeatherByLocation(latitude, longitude)
    }

    // Save weather data to the local database
    override suspend fun saveWeatherToDatabase(weatherEntity: WeatherEntity) {
        /*** Check if the city exists in the database**/
        val existingWeather = weatherDao.getWeatherByCity(weatherEntity.city)
        if (existingWeather != null) {

            val updatedWeather = existingWeather.copy(
                temperature = weatherEntity.temperature,
                description = weatherEntity.description,
                main = weatherEntity.main,
                humidity = weatherEntity.humidity,
                icon = weatherEntity.icon
            )
            weatherDao.updateWeather(updatedWeather)
            Log.d("WeatherRepository", "Updated weather for city: ${weatherEntity.city}")
        } else {

            weatherDao.insertWeather(weatherEntity)
            Log.d("WeatherRepository", "Inserted new weather for city: ${weatherEntity.city}")
        }
    }

    override suspend fun getAllWeatherFromDatabase(): List<WeatherEntity> {
        return weatherDao.getAllWeather()
    }
}