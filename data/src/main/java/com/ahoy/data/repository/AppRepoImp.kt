package com.ahoy.data.repository

import com.ahoy.data.source.cloud.BaseCloudRepository
import com.ahoy.data.source.local.LocalDataSource
import com.ahoy.entities.task.local.LocalForecast
import com.ahoy.entities.task.query.ForeCastQuery
import com.ahoy.entities.task.query.SearchQuery
import com.ahoy.entities.task.remote.RemoteForecast
import com.ahoy.entities.task.remote.RemoteSearchResultItem
import javax.inject.Inject

open class AppRepoImp @Inject constructor(
    private val cloudRepository: BaseCloudRepository,
    private val localDataSource: LocalDataSource
) : AppRepository {

    override suspend fun getForeCast(foreCastQuery: ForeCastQuery): RemoteForecast {
        return cloudRepository.getForeCast(foreCastQuery)
    }

    override suspend fun getCities(searchQuery: SearchQuery): List<RemoteSearchResultItem> {
        return cloudRepository.getCities(searchQuery)
    }

    override suspend fun insertFavirouteCity(favoriteCity: LocalForecast) {
        return localDataSource.insertFavoriteCity(favoriteCity)
    }

    override suspend fun getLocalFavirouteCities(): List<LocalForecast> {
        return localDataSource.getFavoriteCities()
    }

}