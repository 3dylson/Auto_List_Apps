package com.example.autolistapps.data.model


import com.squareup.moshi.Json

data class InfoX(
    @Json(name = "status")
    val status: String?,
    @Json(name = "time")
    val time: Time?
)