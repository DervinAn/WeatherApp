package com.example.ktor.core.data.remote

import com.example.ktor.util.models.WeatherResponse
import com.example.ktor.util.network.KtorClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class KtorApiService(
    private val ktorClient: KtorClient
) {
    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        private const val API_KEY = "ae8e8b25b85a5991eb2d28f8cd6de893"
    }

    // Get weather by city name
    suspend fun getWeather(city: String): WeatherResponse {
        val response: HttpResponse = ktorClient.client.get("${BASE_URL}weather") {
            parameter("q", city)
            parameter("appid", API_KEY)
        }
        return Json.decodeFromString(response.bodyAsText())
    }

    // Get weather by latitude and longitude
    suspend fun getWeatherByLocation(latitude: Double, longitude: Double): WeatherResponse {
        val response: HttpResponse = ktorClient.client.get("${BASE_URL}weather") {
            parameter("lat", latitude)
            parameter("lon", longitude)
            parameter("appid", API_KEY)
        }
        return Json.decodeFromString(response.bodyAsText())
    }
}