package com.example.autolistapps.data.model


import com.squareup.moshi.Json

data class All(
    @Json(name = "data")
    val `data`: Data?,
    @Json(name = "info")
    val info: InfoX?
)