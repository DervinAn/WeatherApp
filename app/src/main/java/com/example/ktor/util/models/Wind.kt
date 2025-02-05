package com.example.ktor.util.models

import kotlinx.serialization.Serializable


@Serializable
data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double? = null // Optional field
)