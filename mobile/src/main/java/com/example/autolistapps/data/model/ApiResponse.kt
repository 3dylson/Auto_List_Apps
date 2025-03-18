package com.example.autolistapps.data.model


import com.squareup.moshi.Json

data class ApiResponse(
    @Json(name = "responses")
    val responses: Responses?,
    @Json(name = "status")
    val status: String?
)