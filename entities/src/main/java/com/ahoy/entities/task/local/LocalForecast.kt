package com.ahoy.entities.task.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ahoy.entities.task.typecoverters.LocalCurrentConverter
import com.ahoy.entities.task.typecoverters.LocalForeCastConverter
import com.ahoy.entities.task.typecoverters.LocalLocationConverter

@Entity(tableName = "faviroute")
data class LocalForecast(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "name")
    var name: String = "",

    @TypeConverters(LocalCurrentConverter::class)
    @ColumnInfo(name = "LocalCurrent")
    val current: LocalCurrent = LocalCurrent(),

    @TypeConverters(LocalLocationConverter::class)
    @ColumnInfo(name = "LocalLocation")
    val location: LocalLocation = LocalLocation(),

    @TypeConverters(LocalForeCastConverter::class)
    @ColumnInfo(name = "Forecast")
    val forecast: Forecast = Forecast()
)

data class ForeCastDayItem(
    val date: String = "",
    val dateEpoch: Int = 0,
    val day: Day = Day(),
    var isF: Boolean = true
)

data class Forecast(
    val foreCastDay: List<ForeCastDayItem?>? = listOf()
)

data class LocalLocation(
    val localtime: String = "",
    val country: String = "",
    val localtimeEpoch: Int = 0,
    val name: String = "",
    val lon: Double = 0.0,
    val region: String = "",
    val lat: Double = 0.0,
    val tzId: String = ""
)


data class LocalCondition(
    val code: Int = 0,
    val icon: String = "",
    val text: String = ""
)

data class Day(
    val avgVisKm: Double = 0.0,
    val avgTempF: Double = 0.0,
    val avgTempC: Double = 0.0,
    val maxTempC: Double = 0.0,
    val maxTempF: Double = 0.0,
    val avgVisMiles: Double = 0.0,
    val dailyWillItRain: Int? = null,
    val minTempF: Double = 0.0,
    val avgHumidity: Double = 0.0,
    val condition: LocalCondition? = LocalCondition(),
    val maxWindMph: Double = 0.0,
)

data class LocalCurrent(
    val feelsLikeC: Double = 0.0,
    val lastUpdated: String = "",
    val windDegree: Int = 0,
    val isDay: Int = 0,
    val tempC: Double = 0.0,
    val tempF: Double = 0.0,
    val condition: LocalCondition = LocalCondition(),
    val windMph: Double = 0.0,
    val humidity: Int = 0,
)
