package com.example.autolistapps.presentation

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.autolistapps.domain.CheckNewAppsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class NewAppsWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val checkNewAppsUseCase: CheckNewAppsUseCase,
    private val notificationHelper: NotificationHelper
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = try {
        val newAppsAvailable = checkNewAppsUseCase(Unit)
        if (newAppsAvailable.getOrDefault(false)) {
            notificationHelper.showNewAppsNotification()
            Log.d("NewAppsWorker", "New apps available")
        }
        Result.success()
    } catch (error: Throwable) {
        Log.e("NewAppsWorker", "Error checking for new apps", error)
        Result.failure()
    }
}