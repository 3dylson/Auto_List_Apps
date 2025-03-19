package com.example.autolistapps.domain

import com.example.autolistapps.data.AppRepository
import kotlinx.coroutines.CoroutineDispatcher

class CheckNewAppsUseCase(private val appsRepository: AppRepository, dispatcher: CoroutineDispatcher): UseCase<Unit, Boolean>(dispatcher)  {

    override suspend fun execute(parameters: Unit): Boolean = appsRepository.hasNewApps()
}