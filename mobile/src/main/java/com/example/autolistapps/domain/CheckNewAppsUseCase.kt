package com.example.autolistapps.domain

import android.util.Log
import com.example.autolistapps.data.AppRepository
import com.example.autolistapps.data.AppRepositoryImpl
import com.example.autolistapps.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CheckNewAppsUseCase @Inject constructor(
    private val appsRepository: AppRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Unit, Boolean>(dispatcher) {

    override suspend fun execute(parameters: Unit): Boolean {
        val hasNewApps = appsRepository.hasNewApps()
        if (hasNewApps) {
            Log.d("CheckNewAppsUseCase", "New apps available")
            appsRepository.refresh()
        }
        return hasNewApps
    }
}