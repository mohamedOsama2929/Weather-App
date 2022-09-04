package com.ahoy.data.restful

import com.ahoy.data.repository.ApiService
import com.ahoy.data.util.Constants
import com.ahoy.data.util.MockJson
import com.ahoy.entities.task.remote.RemoteForecast
import com.ahoy.entities.task.remote.RemoteSearchResultItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class FakeApiService
@Inject
constructor(
    private val mockJson: MockJson
) : ApiService {

    var foreCastName: String = Constants.FORECAST_NAME
    var searchName: String = Constants.SEARCH_NAME
    var networkDelay: Long = 0L


    override suspend fun getForeCastList(
        cityName: String,
        key: String,
        aqi: String,
        alerts: String,
        days: Int
    ): RemoteForecast {
        val rawJson = mockJson.getJson(foreCastName)
        delay(networkDelay)
        return Gson().fromJson(
            rawJson,
            object : TypeToken<RemoteForecast>() {}.type
        )
    }

    override suspend fun getCitiesList(
        cityName: String,
        key: String
    ): List<RemoteSearchResultItem> {
        val rawJson = mockJson.getJson(searchName)
        delay(networkDelay)
        return Gson().fromJson(
            rawJson,
            object : TypeToken<RemoteSearchResultItem>() {}.type
        )
    }


}