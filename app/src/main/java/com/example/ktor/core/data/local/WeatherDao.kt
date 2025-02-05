package com.example.ktor.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weatherEntity: WeatherEntity)


    @Query("SELECT * FROM weather_table")
    suspend fun getAllWeather(): List<WeatherEntity>

    @Query("SELECT * FROM weather_table WHERE city = :cityName LIMIT 1")
    suspend fun getWeatherByCity(cityName: String): WeatherEntity?

    @Update
    suspend fun updateWeather(weather: WeatherEntity)
}