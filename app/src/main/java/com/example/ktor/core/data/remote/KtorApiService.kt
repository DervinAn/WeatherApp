package com.example.ktor.core.data.remote

import com.example.ktor.util.models.WeatherResponse
import com.example.ktor.util.network.KtorClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class KtorApiService(
    private val ktorClient: KtorClient
) {
//    suspend fun getWeather(city: String): HttpResponse {
//        return ktorClient.client.get("https://api.openweathermap.org/data/2.5/weather") {
//            url {
//                parameters.append("q", city)
//                parameters.append("appid", "YOUR_API_KEY") // Replace with your API key
//            }
//        }
//    }
//
//    suspend fun getWeatherByLocation(latitude: Double, longitude: Double): WeatherResponse {
//        return ktorClient.client.get("https://api.openweathermap.org/data/2.5/weather") {
//            url {
//                parameters.append("lat", latitude.toString())
//                parameters.append("lon", longitude.toString())
//                parameters.append("appid", "YOUR_API_KEY") // Replace with your API key
//            }
//        }
//    }
}