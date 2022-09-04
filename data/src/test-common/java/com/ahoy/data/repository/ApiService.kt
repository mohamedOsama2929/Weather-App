package com.ahoy.data.repository

import com.ahoy.entities.task.remote.RemoteForecast
import com.ahoy.entities.task.remote.RemoteSearchResultItem
import retrofit2.http.Query


interface ApiService {


    /**ForeCast**/
    suspend fun getForeCastList(
        @Query("q") cityName: String,
        @Query("key") key: String = "BuildConfig.weatherKey",
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no",
        @Query("days") days: Int = 6,
    ): RemoteForecast

    /**ForeCast**/
    suspend fun getCitiesList(
        @Query("q") cityName: String,
        @Query("key") key: String = " BuildConfig.weatherKey"
    ): List<RemoteSearchResultItem>


}