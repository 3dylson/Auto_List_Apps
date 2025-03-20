package com.example.autolistapps.data

import android.util.Log
import com.example.autolistapps.data.remote.RetrofitClient
import com.example.autolistapps.domain.model.AppItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor() : AppRepository {
    private val api = RetrofitClient.apiAptoide

    override val appList: Flow<List<AppItem>> = flow {
        val networkApps = getListApps().getOrNull() ?: emptyList()
        emit(networkApps)
    }

    override suspend fun getListApps(): Result<List<AppItem>> {
        return try {
            val response = api.getListApps()
            val appItemList =
                response.responses?.listApps?.datasets?.all?.data?.list?.mapNotNull { appResponseItem ->
                    appResponseItem?.let {
                        AppItem(
                            id = it.id,
                            name = it.name,
                            icon = it.icon,
                            storeName = it.storeName,
                            graphic = it.graphic
                        )
                    }
                } ?: emptyList()

            Result.success(appItemList)
        } catch (e: Exception) {
            Log.e("AppRepository", "Error fetching app list: ${e.message}")
            Result.failure(e)
        }
    }

    override fun getAppById(id: Int): Flow<AppItem?> = flow {
        val app = getListApps().getOrNull()?.find { it.id == id }
        emit(app)
    }

    fun hasNewApps(): Boolean {
        return true
    }
}