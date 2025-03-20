package com.example.autolistapps.data

import com.example.autolistapps.domain.model.AppItem
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    val appList: Flow<List<AppItem>>

    suspend fun getListApps(): Result<List<AppItem>>
    fun getAppById(id: Int): Flow<AppItem?>
    fun hasNewApps(): Boolean
    suspend fun addAll(appList: List<AppItem>)

}