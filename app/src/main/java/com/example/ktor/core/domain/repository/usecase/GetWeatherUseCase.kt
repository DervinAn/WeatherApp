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
    /**
     * Fetches weather data for a given city name.
     */
    suspend fun execute(city: String): Result<WeatherResponse, RootError> =
        safeApiCall { coreRepository.fetchWeather(city) }

    /**
     * Fetches weather data for a given latitude and longitude.
     */
    suspend fun executeByLocation(latitude: Double, longitude: Double): Result<WeatherResponse, RootError> =
        safeApiCall { coreRepository.fetchWeatherByLocation(latitude, longitude) }

    /**
     * Saves weather data to the local database.
     */
    suspend fun saveWeather(weatherEntity: WeatherEntity) {
        coreRepository.saveWeatherToDatabase(weatherEntity)
        Log.d("WeatherDao", "Weather saved to database: $weatherEntity")
    }

    /**
     * Fetches all saved weather data from the local database.
     */
    suspend fun getAllSavedWeather(): Result<List<WeatherEntity>, RootError> =
        try {
            val weatherData = coreRepository.getAllWeatherFromDatabase()
            if (weatherData.isEmpty()) {
                Result.Error(Error.Local.NO_DATA)
            } else {
                Result.Success(weatherData)
            }
        } catch (e: Exception) {
            Result.Error(Error.Unexpected.UNKNOWN_ERROR)
        }

    /**
     * A helper function to handle API calls safely and return a Result.
     */
    private suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T, RootError> =
        try {
            Result.Success(apiCall())
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