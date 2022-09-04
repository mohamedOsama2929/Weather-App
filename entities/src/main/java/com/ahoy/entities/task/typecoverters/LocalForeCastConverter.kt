package com.ahoy.entities.task.typecoverters

import androidx.room.TypeConverter
import com.ahoy.entities.task.local.Forecast
import com.google.gson.Gson

class LocalForeCastConverter {

    @TypeConverter
    fun storedStringToForecast(value: String): Forecast {
        return Gson().fromJson(value, Forecast::class.java)
    }

    @TypeConverter
    fun forecastToStoredString(cl: Forecast): String? {
        return Gson().toJson(cl)
    }
}