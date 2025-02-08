package com.example.ktor.core.data.remote

import com.example.ktor.util.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val API_KEY = "ae8e8b25b85a5991eb2d28f8cd6de893"
    }

    /**
     * Fetches weather data for a given city name.
     */
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String = API_KEY
    ): WeatherResponse

    /**
     * Fetches weather data for a given latitude and longitude.
     */
    @GET("weather")
    suspend fun getWeatherByLocation(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = API_KEY
    ): WeatherResponse
}