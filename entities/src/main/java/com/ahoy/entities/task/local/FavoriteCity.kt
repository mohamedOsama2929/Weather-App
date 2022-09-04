package com.ahoy.entities.task.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_city")
data class FavoriteCity(
/*
    val id: Int = 0,
*/
    @PrimaryKey(autoGenerate = false)
    val cityName: String = ""
)