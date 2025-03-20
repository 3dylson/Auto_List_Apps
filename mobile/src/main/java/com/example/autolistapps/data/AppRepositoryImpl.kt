package com.example.autolistapps.data

import android.util.Log
import com.example.autolistapps.data.local.AppItemLocalModelDao
import com.example.autolistapps.data.remote.AptoideAPI
import com.example.autolistapps.domain.model.AppItem
import com.example.autolistapps.domain.toDomainModel
import com.example.autolistapps.domain.toLocalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val local: AppItemLocalModelDao,
    private val remote: AptoideAPI
) : AppRepository {

    private var remoteAppsCache: List<AppItem>? = null

    override val appList: Flow<List<AppItem>> =
        local.getAll().map { localList ->
            localList.map { it.toDomainModel() }
        }

    override fun getAppById(id: Int): Flow<AppItem?> =
        local.getById(id).map { localModel ->
            localModel?.toDomainModel()
        }

    override suspend fun refresh(): Result<List<AppItem>> {
        return try {
            val remoteApps = remoteAppsCache ?: run {
                Log.d("AppRepository", "Fetching remote apps")
                val response = remote.getListApps()
                response.responses?.listApps?.datasets?.all?.data?.list
                    ?.mapNotNull { it?.toDomainModel() }
                    ?: emptyList()
            }

            local.insertAll(remoteApps.map { it.toLocalModel() })
            remoteAppsCache = null
            Result.success(remoteApps)
        } catch (e: Exception) {
            Log.e("AppRepository", "Error fetching app list: ${e.message}")
            remoteAppsCache = null
            Result.failure(e)
        }
    }

    /**
     * Compare local apps with remote apps and return `true` if there's any difference.
     * For simplicity, let's:
     *  - Compare size to detect new items
     *  - Then optionally check if any remote app's ID is missing locally.
     */
    override suspend fun hasNewApps(): Boolean {
        try {
            val localApps = local.getAllOnce()
            val localIds = localApps.map { it.id }.toSet()

            val response = remote.getListApps()
            val remoteApps = response.responses?.listApps?.datasets?.all?.data?.list
                ?.mapNotNull { it?.toDomainModel() }
                ?: emptyList()

            remoteAppsCache = remoteApps

            if (remoteApps.size != localApps.size) return true
            if (remoteApps.any { it.id !in localIds }) return true

            return false
        } catch (e: Exception) {
            Log.e("AppRepository", "Error checking for new apps: ${e.message}")
            return false
        }
    }

    override suspend fun addAll(appList: List<AppItem>) {
        local.insertAll(
            appList.map { it.toLocalModel() }
        )
    }
}
