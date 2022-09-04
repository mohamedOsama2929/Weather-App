package com.ahoy.domain.base

interface TripleModelMapper<From1, From2, To> {
    fun convert(from1: From1?, from2: From2?): To
}