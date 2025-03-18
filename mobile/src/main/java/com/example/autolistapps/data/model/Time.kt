package com.example.autolistapps.data.model


import com.squareup.moshi.Json

data class Time(
    @Json(name = "human")
    val human: String?,
    @Json(name = "seconds")
    val seconds: Double?
)