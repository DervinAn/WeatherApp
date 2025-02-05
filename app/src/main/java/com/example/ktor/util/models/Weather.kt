package com.example.ktor.util.models

import kotlinx.serialization.Serializable


@Serializable
data class Weather(
    val id: Int,
    val main: String,
    val description: String,
//    val icon: String
)
