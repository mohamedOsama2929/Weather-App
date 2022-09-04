package com.ahoy.domain.utils

import com.google.gson.GsonBuilder

inline fun <reified T> Any.mapTo(defaultValue: T): T =
    try {
        GsonBuilder().create().run {
            fromJson(toJson(this@mapTo), T::class.java)
        }
    } catch (e: Exception) {
        defaultValue
    }