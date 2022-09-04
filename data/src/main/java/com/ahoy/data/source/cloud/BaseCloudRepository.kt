package com.ahoy.data.source.cloud


import com.ahoy.entities.task.query.ForeCastQuery
import com.ahoy.entities.task.query.SearchQuery
import com.ahoy.entities.task.remote.RemoteForecast
import com.ahoy.entities.task.remote.RemoteSearchResultItem


interface BaseCloudRepository {

    /**ForeCast**/
    suspend fun getForeCast(foreCastQuery: ForeCastQuery): RemoteForecast

    /**Search**/
    suspend fun getCities(searchQuery: SearchQuery): List<RemoteSearchResultItem>


}