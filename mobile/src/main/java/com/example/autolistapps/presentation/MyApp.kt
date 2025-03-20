package com.example.autolistapps.presentation

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        scheduleNewAppsWorker()
    }

    private fun scheduleNewAppsWorker() {
        val request =
            PeriodicWorkRequestBuilder<NewAppsWorker>(30, TimeUnit.MINUTES)
                .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "NewAppsWorker",
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            request
        )
    }
}