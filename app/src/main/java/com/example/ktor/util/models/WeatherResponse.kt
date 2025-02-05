@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package com.example.ktor.util.models

import kotlinx.serialization.Serializable


@Serializable
data class WeatherResponse(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val rain: Rain? = null, // Optional field
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)
