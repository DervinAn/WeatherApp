package com.example.ktor.util.models

import kotlinx.serialization.Serializable




@Serializable
data class Coord(
    val lon: Double,
    val lat: Double
)