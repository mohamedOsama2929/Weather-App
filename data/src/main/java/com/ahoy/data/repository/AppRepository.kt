package com.ahoy.data.repository

import com.ahoy.entities.task.local.LocalForecast
import com.ahoy.entities.task.query.ForeCastQuery
import com.ahoy.entities.task.query.SearchQuery
import com.ahoy.entities.task.remote.RemoteForecast
import com.ahoy.entities.task.remote.RemoteSearchResultItem

interface AppRepository {

    /**ForeCast**/
    suspend fun getForeCast(foreCastQuery: ForeCastQuery): RemoteForecast


    /**Search**/
    suspend fun getCities(searchQuery: SearchQuery): List<RemoteSearchResultItem>

    suspend fun insertFavirouteCity(favoriteCity: LocalForecast)

    suspend fun getLocalFavirouteCities(): List<LocalForecast>

}