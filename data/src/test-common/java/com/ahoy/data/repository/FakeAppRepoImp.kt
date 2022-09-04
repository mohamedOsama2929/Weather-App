package com.ahoy.data.repository

import com.ahoy.data.cloud.FakeCloudRepository
import com.ahoy.entities.task.query.ForeCastQuery
import com.ahoy.entities.task.query.SearchQuery
import com.ahoy.entities.task.remote.RemoteForecast
import com.ahoy.entities.task.remote.RemoteSearchResultItem
import javax.inject.Inject

class FakeAppRepoImp @Inject constructor(
    private val cloudRepository: FakeCloudRepository
) : AppRepository {


    override suspend fun getForeCast(foreCastQuery: ForeCastQuery): RemoteForecast {
        return cloudRepository.getForeCast(foreCastQuery)
    }

    override suspend fun getCities(searchQuery: SearchQuery): List<RemoteSearchResultItem> {
        return cloudRepository.getCities(searchQuery)
    }


}