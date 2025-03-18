package com.example.autolistapps.data.model


import com.squareup.moshi.Json

data class Data(
    @Json(name = "hidden")
    val hidden: Int?,
    @Json(name = "limit")
    val limit: Int?,
    @Json(name = "list")
    val list: List<Item0?>?,
    @Json(name = "next")
    val next: Int?,
    @Json(name = "offset")
    val offset: Int?,
    @Json(name = "total")
    val total: Int?
)