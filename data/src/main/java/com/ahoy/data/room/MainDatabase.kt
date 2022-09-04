package com.ahoy.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ahoy.entities.task.local.LocalForecast
import com.ahoy.entities.task.typecoverters.LocalCurrentConverter
import com.ahoy.entities.task.typecoverters.LocalForeCastConverter
import com.ahoy.entities.task.typecoverters.LocalLocationConverter

@Database(
    entities = [LocalForecast::class],
    version = 1
)
@TypeConverters(
    LocalCurrentConverter::class,
    LocalLocationConverter::class,
    LocalForeCastConverter::class
)
abstract class MainDatabase : RoomDatabase() {

    abstract fun favoriteCitiesDao(): FavoriteCitiesDao

    companion object {
        const val DATABASE_NAME: String = "main_db"
    }


}