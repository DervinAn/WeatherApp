package com.example.ktor.core.data.repository

import android.util.Log
import com.example.ktor.core.data.local.WeatherDao
import com.example.ktor.core.data.local.WeatherEntity
import com.example.ktor.core.data.remote.ApiService
import com.example.ktor.core.domain.repository.CoreRepository
import com.example.ktor.core.domain.repository.Result
import com.example.ktor.util.models.WeatherResponse
import retrofit2.HttpException

class RepositoryImpl(
    /** Injection **/
    private val apiService: ApiService,
    private val weatherDao: WeatherDao
) : CoreRepository {

    override suspend fun fetchWeather(city: String): WeatherResponse {
        return apiService.getWeather(city, "ae8e8b25b85a5991eb2d28f8cd6de893")
    }
//    override suspend fun fetchWeather(city: String): WeatherResponse {
//        return try {
//            Result.Success(apiService.getWeather(city, "ae8e8b25b85a5991eb2d28f8cd6de893"))
//        } catch (e: HttpException) {
//            when (e.code()) {
//                408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
//                else -> TODO()
//            }
//        }
//    }


    /** Fetch weather by latitude and longitude**/
    override suspend fun fetchWeatherByLocation(latitude: Double, longitude: Double): WeatherResponse {
        return apiService.getWeatherByLocation(latitude, longitude, "ae8e8b25b85a5991eb2d28f8cd6de893")
    }


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
