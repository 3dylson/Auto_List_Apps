package com.example.autolistapps.data.remote

import com.example.autolistapps.data.model.ApiResponse
import retrofit2.http.GET

interface AptoideAPI {

    @GET(LIST_APPS)
    suspend fun getListApps(): ApiResponse
}