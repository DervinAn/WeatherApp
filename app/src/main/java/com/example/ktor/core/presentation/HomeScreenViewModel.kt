package com.example.ktor.core.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktor.core.data.local.WeatherEntity
import com.example.ktor.core.domain.repository.FieldValidator
import com.example.ktor.core.domain.repository.Result
import com.example.ktor.core.domain.repository.usecase.GetWeatherUseCase
import com.example.ktor.util.models.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
import com.example.ktor.core.domain.repository.Error

class HomeScreenViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val fieldValidator: FieldValidator
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<WeatherResponse>>(UiState.Loading)
    val state: StateFlow<UiState<WeatherResponse>> get() = _state

    private val _city = MutableStateFlow("Bechar")
    val city: StateFlow<String> get() = _city

    private val _allWeather = MutableStateFlow<List<WeatherEntity>>(emptyList())
    val allWeather: StateFlow<List<WeatherEntity>> get() = _allWeather

    init {
        fetchLocalWeatherData()
        fetchWeather(_city.value)
    }
    fun updateCity(newCity: String) {
            viewModelScope.launch {
                _city.update {
                    newCity
                }
                fetchWeather(newCity)
            }
    }

    fun fetchWeather(city: String) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            when (val result = getWeatherUseCase.execute(city)) {
                is  Result.Success -> {
                    _state.value = UiState.Success(result.data)
                    saveWeatherData(city, result.data) // Save fetched data to DB
                }

                is Result.Error -> { // Handle errors properly
                    val errorMessage = when (result.error) {
                        Error.Network.NO_INTERNET_CONNECTION -> "No internet connection"
                        Error.Network.REQUEST_TIMEOUT -> "Request timed out. Try again!"
                        Error.Network.SERVER_ERROR -> "Server error. Please try again later."
                        Error.Unexpected.UNKNOWN_ERROR -> "An unexpected error occurred"
                        else -> "Something went wrong"
                    }
                    _state.value = UiState.Error(errorMessage)
                    Log.e("WeatherFetchError", errorMessage)
                }
            }
        }
    }

    fun fetchWeatherByLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val response = getWeatherUseCase.executeByLocation(latitude, longitude)
                _state.value = UiState.Success(response)
                saveWeatherData(city.value, response)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Unknown error")
                Log.e("WeatherFetchError", e.message ?: "Error fetching weather by location")
            }
        }
    }

    private fun saveWeatherData(city: String, response: WeatherResponse) {
        val weatherEntity = WeatherEntity(
            city = city,
            temperature = ((response.main.temp-273.15)*10).roundToInt() / 10.0,
            description = response.weather[0].description,
            id = 0,
            main = response.weather[0].main,
            humidity = response.main.humidity,
            icon = response.weather[0].id.toString()
        )
        viewModelScope.launch {
            getWeatherUseCase.saveWeather(weatherEntity)
            Log.d("WeatherViewModel", "Weather saved to database: $weatherEntity")
        }
    }

    private fun fetchLocalWeatherData() {
        viewModelScope.launch {
            when(val result = getWeatherUseCase.getAllSavedWeather()){
                is Result.Success -> {
                    _allWeather.value = result.data
                }
                is Result.Error -> {
                    val errorMessage = when (result.error) {
                        Error.Network.NO_INTERNET_CONNECTION -> "No internet connection"
                        Error.Network.REQUEST_TIMEOUT -> "Request timed out. Try again!"
                        Error.Network.SERVER_ERROR -> "Server error. Please try again later."
                        Error.Unexpected.UNKNOWN_ERROR -> "An unexpected error occurred"
                        else -> "Something went wrong"
                    }
                    _state.value = UiState.Error(errorMessage)
                }
            }
        }
    }
    fun checkField(field: String) {
        when(val result = fieldValidator.isFieldEmpty(field)){
            is Result.Success-> {
//                _state.value = UiState.Success(Unit)
            }
            is Result.Error-> {
                val errorMessage = when (result.error) {
                    Error.Validation.EMPTY_FIELD -> "Field is empty"
                    else -> "Something went wrong"
                }
                _state.value = UiState.Error(errorMessage)
            }
        }
    }
}

sealed class UiState<out T> {
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
    object Loading : UiState<Nothing>()
}
