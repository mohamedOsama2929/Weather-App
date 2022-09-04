package com.ahoy.data.restful

import com.ahoy.data.BuildConfig
import com.ahoy.entities.task.remote.RemoteForecast
import com.ahoy.entities.task.remote.RemoteSearchResultItem
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {


    /**ForeCast**/
    @GET(Config.FORECAST)
    suspend fun getForeCastList(
        @Query("q") cityName: String,
        @Query("key") key: String = BuildConfig.weatherKey,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no",
        @Query("days") days: Int = 6,
    ): RemoteForecast

    /**ForeCast**/
    @GET(Config.SEARCH)
    suspend fun getCitiesList(
        @Query("q") cityName: String,
        @Query("key") key: String = BuildConfig.weatherKey
    ): List<RemoteSearchResultItem>


}