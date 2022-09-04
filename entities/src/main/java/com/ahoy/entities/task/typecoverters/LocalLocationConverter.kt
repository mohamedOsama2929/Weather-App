package com.ahoy.entities.task.typecoverters

import androidx.room.TypeConverter
import com.ahoy.entities.task.local.LocalLocation
import com.google.gson.Gson

class LocalLocationConverter {

    @TypeConverter
    fun storedStringToLocalLocation(value: String): LocalLocation {
        return Gson().fromJson(value, LocalLocation::class.java)
    }

    @TypeConverter
    fun localLocationToStoredString(cl: LocalLocation): String? {
        return Gson().toJson(cl)
    }
}