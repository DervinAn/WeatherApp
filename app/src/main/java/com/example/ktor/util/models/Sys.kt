package com.example.ktor.util.models

import kotlinx.serialization.Serializable

@Serializable
data class Sys(
    val type: Int? = null, // Optional field
    val id: Int? = null, // Optional field
    val country: String,
    val sunrise: Long,
    val sunset: Long
)