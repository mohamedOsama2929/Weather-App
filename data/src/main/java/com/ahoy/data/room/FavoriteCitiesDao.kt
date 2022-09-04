package com.ahoy.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahoy.entities.task.local.LocalForecast

@Dao
interface FavoriteCitiesDao {

    @Query("SELECT * FROM faviroute")
    suspend fun getFavoritesCities(): List<LocalForecast>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCityLocal(localForecast: LocalForecast)


}