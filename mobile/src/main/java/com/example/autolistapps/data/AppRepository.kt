package com.example.autolistapps.data

import com.example.autolistapps.domain.model.AppItem
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    val appList: Flow<List<AppItem>>

    suspend fun refresh(): Result<List<AppItem>>
    fun getAppById(id: Int): Flow<AppItem?>
    suspend fun hasNewApps(): Boolean

}