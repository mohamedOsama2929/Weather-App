package com.ahoy.data.source.local

import com.ahoy.entities.task.local.LocalForecast


interface ILocalDataSource {

    /**
     * insert Favorite City Response
     */
    suspend fun insertFavoriteCity(favoriteCity: LocalForecast)

    /**
     * get Favorite Cities
     */
    suspend fun getFavoriteCities(): List<LocalForecast>


}