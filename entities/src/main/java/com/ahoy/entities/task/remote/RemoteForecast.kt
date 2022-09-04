package com.ahoy.entities.task.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteForecast(

    @field:SerializedName("current")
    @Expose
    val current: Current? = null,

    @field:SerializedName("location")
    @Expose
    val location: Location? = null,

    @field:SerializedName("forecast")
    @Expose
    val forecast: Forecast? = null
)

data class ForeCastDayItem(

    @field:SerializedName("date")
    @Expose
    val date: String? = null,

    @field:SerializedName("astro")
    @Expose
    val astro: Astro? = null,

    @field:SerializedName("date_epoch")
    @Expose
    val dateEpoch: Int? = null,

    @field:SerializedName("hour")
    @Expose
    val hour: List<HourItem?>? = null,

    @field:SerializedName("day")
    @Expose
    val day: Day? = null
)

data class Forecast(

    @field:SerializedName("forecastday")
    @Expose
    val forecastday: List<ForeCastDayItem?>? = null
)

data class Location(

    @field:SerializedName("localtime")
    @Expose
    val localtime: String? = null,

    @field:SerializedName("country")
    @Expose
    val country: String? = null,

    @field:SerializedName("localtime_epoch")
    @Expose
    val localtimeEpoch: Int? = null,

    @field:SerializedName("name")
    @Expose
    val name: String? = null,

    @field:SerializedName("lon")
    @Expose
    val lon: Double? = null,

    @field:SerializedName("region")
    @Expose
    val region: String? = null,

    @field:SerializedName("lat")
    @Expose
    val lat: Double? = null,

    @field:SerializedName("tz_id")
    @Expose
    val tzId: String? = null
)

data class HourItem(

    @field:SerializedName("feelslike_c")
    @Expose
    val feelslikeC: Double? = null,

    @field:SerializedName("feelslike_f")
    @Expose
    val feelslikeF: Double? = null,

    @field:SerializedName("wind_degree")
    @Expose
    val windDegree: Int? = null,

    @field:SerializedName("windchill_f")
    @Expose
    val windchillF: Double? = null,

    @field:SerializedName("windchill_c")
    @Expose
    val windchillC: Double? = null,

    @field:SerializedName("temp_c")
    @Expose
    val tempC: Double? = null,

    @field:SerializedName("temp_f")
    @Expose
    val tempF: Double? = null,

    @field:SerializedName("cloud")
    @Expose
    val cloud: Int? = null,

    @field:SerializedName("wind_kph")
    @Expose
    val windKph: Double? = null,

    @field:SerializedName("wind_mph")
    @Expose
    val windMph: Double? = null,

    @field:SerializedName("humidity")
    @Expose
    val humidity: Int? = null,

    @field:SerializedName("dewpoint_f")
    @Expose
    val dewpointF: Double? = null,

    @field:SerializedName("will_it_rain")
    @Expose
    val willItRain: Int? = null,

    @field:SerializedName("uv")
    @Expose
    val uv: Double? = null,

    @field:SerializedName("heatindex_f")
    @Expose
    val heatindexF: Double? = null,

    @field:SerializedName("dewpoint_c")
    @Expose
    val dewpointC: Double? = null,

    @field:SerializedName("is_day")
    @Expose
    val isDay: Int? = null,

    @field:SerializedName("precip_in")
    @Expose
    val precipIn: Double? = null,

    @field:SerializedName("heatindex_c")
    @Expose
    val heatindexC: Double? = null,

    @field:SerializedName("wind_dir")
    @Expose
    val windDir: String? = null,

    @field:SerializedName("gust_mph")
    @Expose
    val gustMph: Double? = null,

    @field:SerializedName("pressure_in")
    @Expose
    val pressureIn: Double? = null,

    @field:SerializedName("chance_of_rain")
    @Expose
    val chanceOfRain: Int? = null,

    @field:SerializedName("gust_kph")
    @Expose
    val gustKph: Double? = null,

    @field:SerializedName("precip_mm")
    @Expose
    val precipMm: Double? = null,

    @field:SerializedName("condition")
    @Expose
    val condition: Condition? = null,

    @field:SerializedName("will_it_snow")
    @Expose
    val willItSnow: Int? = null,

    @field:SerializedName("vis_km")
    @Expose
    val visKm: Double? = null,

    @field:SerializedName("time_epoch")
    @Expose
    val timeEpoch: Int? = null,

    @field:SerializedName("time")
    @Expose
    val time: String? = null,

    @field:SerializedName("chance_of_snow")
    @Expose
    val chanceOfSnow: Int? = null,

    @field:SerializedName("pressure_mb")
    @Expose
    val pressureMb: Double? = null,

    @field:SerializedName("vis_miles")
    @Expose
    val visMiles: Double? = null
)

data class Condition(

    @field:SerializedName("code")
    @Expose
    val code: Int? = null,

    @field:SerializedName("icon")
    @Expose
    val icon: String? = null,

    @field:SerializedName("text")
    @Expose
    val text: String? = null
)

data class Day(

    @field:SerializedName("avgvis_km")
    @Expose
    val avgvisKm: Double? = null,

    @field:SerializedName("uv")
    @Expose
    val uv: Double? = null,

    @field:SerializedName("avgtemp_f")
    @Expose
    val avgtempF: Double? = null,

    @field:SerializedName("avgtemp_c")
    @Expose
    val avgtempC: Double? = null,

    @field:SerializedName("daily_chance_of_snow")
    @Expose
    val dailyChanceOfSnow: Int? = null,

    @field:SerializedName("maxtemp_c")
    @Expose
    val maxtempC: Double? = null,

    @field:SerializedName("maxtemp_f")
    @Expose
    val maxtempF: Double? = null,

    @field:SerializedName("mintemp_c")
    @Expose
    val mintempC: Double? = null,

    @field:SerializedName("avgvis_miles")
    @Expose
    val avgvisMiles: Double? = null,

    @field:SerializedName("daily_will_it_rain")
    @Expose
    val dailyWillItRain: Int? = null,

    @field:SerializedName("mintemp_f")
    @Expose
    val mintempF: Double? = null,

    @field:SerializedName("totalprecip_in")
    @Expose
    val totalprecipIn: Double? = null,

    @field:SerializedName("avghumidity")
    @Expose
    val avghumidity: Double? = null,

    @field:SerializedName("condition")
    @Expose
    val condition: Condition? = null,

    @field:SerializedName("maxwind_kph")
    @Expose
    val maxwindKph: Double? = null,

    @field:SerializedName("maxwind_mph")
    @Expose
    val maxwindMph: Double? = null,

    @field:SerializedName("daily_chance_of_rain")
    @Expose
    val dailyChanceOfRain: Int? = null,

    @field:SerializedName("totalprecip_mm")
    @Expose
    val totalprecipMm: Double? = null,

    @field:SerializedName("daily_will_it_snow")
    @Expose
    val dailyWillItSnow: Int? = null
)

data class Astro(

    @field:SerializedName("moonset")
    @Expose
    val moonset: String? = null,

    @field:SerializedName("moon_illumination")
    @Expose
    val moonIllumination: String? = null,

    @field:SerializedName("sunrise")
    @Expose
    val sunrise: String? = null,

    @field:SerializedName("moon_phase")
    @Expose
    val moonPhase: String? = null,

    @field:SerializedName("sunset")
    @Expose
    val sunset: String? = null,

    @field:SerializedName("moonrise")
    @Expose
    val moonrise: String? = null
)

data class Current(

    @field:SerializedName("feelslike_c")
    @Expose
    val feelslikeC: Double? = null,

    @field:SerializedName("uv")
    @Expose
    val uv: Double? = null,

    @field:SerializedName("last_updated")
    @Expose
    val lastUpdated: String? = null,

    @field:SerializedName("feelslike_f")
    @Expose
    val feelslikeF: Double? = null,

    @field:SerializedName("wind_degree")
    @Expose
    val windDegree: Int? = null,

    @field:SerializedName("last_updated_epoch")
    @Expose
    val lastUpdatedEpoch: Int? = null,

    @field:SerializedName("is_day")
    @Expose
    val isDay: Int? = null,

    @field:SerializedName("precip_in")
    @Expose
    val precipIn: Double? = null,

    @field:SerializedName("wind_dir")
    @Expose
    val windDir: String? = null,

    @field:SerializedName("gust_mph")
    @Expose
    val gustMph: Double? = null,

    @field:SerializedName("temp_c")
    @Expose
    val tempC: Double? = null,

    @field:SerializedName("pressure_in")
    @Expose
    val pressureIn: Double? = null,

    @field:SerializedName("gust_kph")
    @Expose
    val gustKph: Double? = null,

    @field:SerializedName("temp_f")
    @Expose
    val tempF: Double? = null,

    @field:SerializedName("precip_mm")
    @Expose
    val precipMm: Double? = null,

    @field:SerializedName("cloud")
    @Expose
    val cloud: Int? = null,

    @field:SerializedName("wind_kph")
    @Expose
    val windKph: Double? = null,

    @field:SerializedName("condition")
    @Expose
    val condition: Condition? = null,

    @field:SerializedName("wind_mph")
    @Expose
    val windMph: Double? = null,

    @field:SerializedName("vis_km")
    @Expose
    val visKm: Double? = null,

    @field:SerializedName("humidity")
    @Expose
    val humidity: Int? = null,

    @field:SerializedName("pressure_mb")
    @Expose
    val pressureMb: Double? = null,

    @field:SerializedName("vis_miles")
    @Expose
    val visMiles: Double? = null
)
