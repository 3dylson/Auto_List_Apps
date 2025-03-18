package com.example.autolistapps.data.model


import com.squareup.moshi.Json

data class Responses(
    @Json(name = "listApps")
    val listApps: ListApps?
)