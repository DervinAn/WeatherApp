package com.example.ktor.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "weather_table")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val main: String,
    val description: String,
    val temperature: Double,
    val city: String,
    val humidity: Int? =null,
    val icon: String= null.toString(),
)
