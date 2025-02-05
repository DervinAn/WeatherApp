package com.example.ktor.util.local

import android.content.Context
import androidx.room.Room
import com.example.ktor.core.data.local.WeatherDatabase

object DatabaseProvider {
    private var INSTANCE: WeatherDatabase? = null

    fun getDatabase(context: Context): WeatherDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                WeatherDatabase::class.java,
                "weather_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
