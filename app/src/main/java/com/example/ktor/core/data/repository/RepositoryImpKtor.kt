package com.example.ktor.core.data.repository

import com.example.ktor.core.data.local.WeatherEntity
import com.example.ktor.core.domain.repository.CoreRepository
import com.example.ktor.util.models.WeatherResponse
import com.example.ktor.util.network.KtorClient


class RepositoryImplKtor(
    private val ktorClient: KtorClient
) : CoreRepository {
    override suspend fun fetchWeather(city: String): WeatherResponse {
        // Call the KtorClient to fetch weather data for the given city
        return ktorClient.getWeather(city)
    }

    override suspend fun fetchWeatherByLocation(latitude: Double, longitude: Double): WeatherResponse {
        // Call the KtorClient to fetch weather data based on location
        return ktorClient.getWeatherByLocation(latitude, longitude)
    }

    override suspend fun saveWeatherToDatabase(weatherEntity: WeatherEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllWeatherFromDatabase(): List<WeatherEntity> {
        TODO("Not yet implemented")
    }
}