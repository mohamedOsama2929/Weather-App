package com.ahoy.domain.base

interface ModelMapper<From, To> {
    fun convert(from: From?): To
}