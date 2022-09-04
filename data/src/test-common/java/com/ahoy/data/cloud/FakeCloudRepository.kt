package com.ahoy.data.cloud

import com.ahoy.data.repository.BaseCloudRepository
import com.ahoy.data.restful.FakeApiService
import com.ahoy.entities.task.query.ForeCastQuery
import com.ahoy.entities.task.query.SearchQuery
import com.ahoy.entities.task.remote.RemoteForecast
import com.ahoy.entities.task.remote.RemoteSearchResultItem

/**
 * The only difference between this and the real CloudRepository is the ApiService is
 * fake and it's not being injected so I can change it at runtime.
 * That way I can alter the FakeApiService for each individual test.
 */

open class FakeCloudRepository(private val apIs: FakeApiService) : BaseCloudRepository {
    /**ForeCast*/
    override suspend fun getForeCast(foreCastQuery: ForeCastQuery): RemoteForecast {
        return apIs.getForeCastList(cityName = foreCastQuery.cityName)
    }

    override suspend fun getCities(searchQuery: SearchQuery): List<RemoteSearchResultItem> {
        return apIs.getCitiesList(cityName = searchQuery.cityName)
    }


}
