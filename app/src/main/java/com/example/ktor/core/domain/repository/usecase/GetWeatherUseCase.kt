package com.example.ktor.core.domain.repository.usecase

import android.util.Log
import com.example.ktor.core.data.local.WeatherEntity
import com.example.ktor.core.domain.repository.CoreRepository
import com.example.ktor.core.domain.repository.Error
import com.example.ktor.core.domain.repository.Result
import com.example.ktor.core.domain.repository.RootError
import com.example.ktor.util.models.WeatherResponse
import io.ktor.utils.io.errors.IOException
import retrofit2.HttpException

class GetWeatherUseCase(
    private val coreRepository: CoreRepository,
) {
    suspend fun execute(city: String): Result<WeatherResponse, RootError> {
        return try{
            val response = coreRepository.fetchWeather(city)
            Result.Success(response)
        } catch (e: IOException) {
            Result.Error(Error.Network.NO_INTERNET_CONNECTION)
        } catch (e: HttpException) {
            when (e.code()) {
                408 -> Result.Error(Error.Network.REQUEST_TIMEOUT)
                in 500..599 -> Result.Error(Error.Network.SERVER_ERROR)
                else -> Result.Error(Error.Unexpected.UNKNOWN_ERROR)
            }
        } catch (e: Exception) {
            Result.Error(Error.Unexpected.UNKNOWN_ERROR)
        }
    }

    suspend fun executeByLocation(latitude: Double, longitude: Double): WeatherResponse {
        return coreRepository.fetchWeatherByLocation(latitude, longitude)
    }
    // Save weather to the local database
    suspend fun saveWeather(weatherEntity: WeatherEntity) {
        coreRepository.saveWeatherToDatabase(weatherEntity)
        Log.d("WeatherDao", "Weather saved to database: $weatherEntity")
    }

    // Fetch all saved weather data from the local database
    suspend fun getAllSavedWeather(): Result<List<WeatherEntity>, RootError> {
        return try {
            val weatherData = coreRepository.getAllWeatherFromDatabase()
            if (weatherData.isEmpty()) {
                Result.Error(Error.Local.NO_DATA)
            } else {
                Result.Success(weatherData)
            }
        } catch (e: Exception) {
            Result.Error(Error.Unexpected.UNKNOWN_ERROR)
        }
    }
}