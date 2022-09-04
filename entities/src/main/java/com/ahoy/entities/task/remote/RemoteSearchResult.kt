package com.ahoy.entities.task.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteSearchResult(

    @field:SerializedName("RemoteSearchResult")
    @Expose
    val remoteSearchResult: List<RemoteSearchResultItem>? = null
)

data class RemoteSearchResultItem(

    @field:SerializedName("country")
    @Expose
    val country: String? = null,

    @field:SerializedName("name")
    @Expose
    val name: String? = null,

    @field:SerializedName("lon")
    @Expose
    val lon: Double? = null,

    @field:SerializedName("id")
    @Expose
    val id: Int? = null,

    @field:SerializedName("region")
    @Expose
    val region: String? = null,

    @field:SerializedName("lat")
    @Expose
    val lat: Double? = null,

    @field:SerializedName("url")
    @Expose
    val url: String? = null
)
