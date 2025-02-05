package com.example.ktor.core.data.remote

import com.example.ktor.util.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // Get weather by city name
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,  // Query parameter for city name
        @Query("appid") apiKey: String = "ae8e8b25b85a5991eb2d28f8cd6de893"  // Add the API key in the query string
    ): WeatherResponse

    // Get weather by latitude and longitude
    @GET("weather")
    suspend fun getWeatherByLocation(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = "ae8e8b25b85a5991eb2d28f8cd6de893"  // API key query parameter
    ): WeatherResponse
}
