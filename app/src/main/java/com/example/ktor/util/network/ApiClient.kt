package com.example.ktor.util.network

import android.util.Log
import com.example.ktor.core.data.remote.ApiService
import com.example.ktor.util.models.WeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object KtorClient {
    private const val BASEURL = "https://api.openweathermap.org/data/2.5/weather?q="
    private const val API_KEY = "ae8e8b25b85a5991eb2d28f8cd6de893"

    val client: HttpClient by lazy {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.ANDROID
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
            }
        }
    }

    suspend fun getWeather(city: String): WeatherResponse {
        val url = "$BASEURL$city&appid=$API_KEY"
        Log.d("KtorClient", "KtorClient initialized")
        return client.get(url).body()

    }
    suspend fun getWeatherByLocation(latitude: Double, longitude: Double): WeatherResponse{
        val url = "$BASEURL?lat=$latitude&lon=$longitude&appid=$API_KEY"
        Log.d("KtorClient", "KtorClient initialized")
        return client.get(url).body()
    }
}


object RetrofitClient {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/" // Ensure this ends with a '/'
    private const val API_KEY = "ae8e8b25b85a5991eb2d28f8cd6de893"



    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)  // Ensure this ends with a "/"
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun getWeatherUrl(city: String): String {
        return "$BASE_URL$city&appid=$API_KEY"
    }

}


