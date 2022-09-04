package com.ahoy.data.repository

import com.ahoy.entities.task.query.ForeCastQuery
import com.ahoy.entities.task.query.SearchQuery
import com.ahoy.entities.task.remote.RemoteForecast
import com.ahoy.entities.task.remote.RemoteSearchResultItem


class CloudRepository(private val apIs: ApiService) : BaseCloudRepository {
    /**ForeCast*/
    override suspend fun getForeCast(foreCastQuery: ForeCastQuery): RemoteForecast {
        return apIs.getForeCastList(cityName = foreCastQuery.cityName)
    }

    override suspend fun getCities(searchQuery: SearchQuery): List<RemoteSearchResultItem> {
        return apIs.getCitiesList(cityName = searchQuery.cityName)
    }

}
