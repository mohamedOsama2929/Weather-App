package com.ahoy.data.source.local

import com.ahoy.data.room.FavoriteCitiesDao
import com.ahoy.entities.task.local.LocalForecast
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val mainDao: FavoriteCitiesDao,

    ) : ILocalDataSource {
    override suspend fun insertFavoriteCity(favoriteCity: LocalForecast) {
        return mainDao.insertFavoriteCityLocal(favoriteCity)
    }

    override suspend fun getFavoriteCities(): List<LocalForecast> {
        return mainDao.getFavoritesCities()
    }


}