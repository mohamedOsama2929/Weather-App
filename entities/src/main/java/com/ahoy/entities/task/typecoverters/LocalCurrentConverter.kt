package com.ahoy.entities.task.typecoverters

import androidx.room.TypeConverter
import com.ahoy.entities.task.local.LocalCurrent
import com.google.gson.Gson

class LocalCurrentConverter {

    @TypeConverter
    fun storedStringToLocalCurrent(value: String): LocalCurrent {
        return Gson().fromJson(value, LocalCurrent::class.java)
    }

    @TypeConverter
    fun localCurrentToStoredString(cl: LocalCurrent): String? {
        return Gson().toJson(cl)
    }
}