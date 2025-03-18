package com.example.autolistapps.data.model


import com.squareup.moshi.Json

data class ListApps(
    @Json(name = "datasets")
    val datasets: Datasets?,
    @Json(name = "info")
    val info: InfoX?
)