package com.ahoy.data.util

import com.ahoy.entities.task.remote.RemoteForecast
import com.ahoy.entities.task.remote.RemoteSearchResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject


open class MockJson
@Inject
constructor() {
    /**
     * Reads input file and converts to json
     */
    fun getJson(fileName: String): String {
        val bytes = javaClass.classLoader!!.getResourceAsStream(fileName).readBytes()
        return String(bytes)
    }


    fun getForeCast(): RemoteForecast {
        val rawJson = getJson("forecast.json")
        return Gson().fromJson(
            rawJson,
            object : TypeToken<RemoteForecast>() {}.type
        )
    }

    fun search(): RemoteSearchResult {
        val rawJson = getJson("search.json")
        return Gson().fromJson(
            rawJson,
            object : TypeToken<RemoteSearchResult>() {}.type
        )
    }


}