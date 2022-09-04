package com.ahoy.entities.task.local

data class SearchResultItem(
    val country: String = "",
    val name: String = "",
    val lon: Double = 0.0,
    val id: Int = 0,
    val region: String = "",
    val lat: Double = 0.0,
    val url: String = ""
)
