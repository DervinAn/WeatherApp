package com.example.ktor.util.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rain(
    @SerialName("1h") val oneHour: Double? = null // Optional field
)