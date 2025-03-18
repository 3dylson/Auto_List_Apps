package com.example.autolistapps.data

import com.example.autolistapps.data.remote.RetrofitClient
import com.example.autolistapps.domain.model.AppItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AppRepository {
    private val api = RetrofitClient.apiAptoide

    fun getListApps(): Flow<Result<List<AppItem>>> = flow {
        try {
            val response = api.getListApps()
            val appItemList = response.responses?.listApps?.datasets?.all?.data?.list?.map {
                AppItem(
                    id = it?.id,
                    name = it?.name,
                    icon = it?.icon,
                    storeName = it?.storeName,
                    graphic = it?.graphic
                )
            }
            emit(Result.success(appItemList ?: emptyList()))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}