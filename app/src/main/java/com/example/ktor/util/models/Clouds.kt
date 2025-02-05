@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package com.example.ktor.util.models

import kotlinx.serialization.Serializable


@Serializable
data class Clouds(
    val all: Int
)