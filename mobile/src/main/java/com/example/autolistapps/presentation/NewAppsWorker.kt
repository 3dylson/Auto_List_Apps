package com.example.autolistapps.presentation

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class NewAppsWorker(
    appContext: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(appContext, workerParams) {

    // TODO Inject private val checkNewAppsUseCase: CheckNewAppsUseCase,
    //    private val notificationHelper: NotificationHelper

    override suspend fun doWork(): Result = try {
        // TODO:
        //val newAppsAvailable = checkNewAppsUseCase(Unit)
        val notificationHelper = NotificationHelper(applicationContext)
        val newAppsAvailable = true
        if (newAppsAvailable) {
            notificationHelper.showNewAppsNotification()
            Log.d("NewAppsWorker", "New apps available")
        }
        Result.success()
    } catch (error: Throwable) {
        Log.e("NewAppsWorker", "Error checking for new apps", error)
        Result.failure()
    }
}