package com.example.autolistapps.data.model


import com.squareup.moshi.Json

data class Datasets(
    @Json(name = "all")
    val all: All?
)