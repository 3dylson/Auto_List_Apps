package com.example.autolistapps.domain

import com.example.autolistapps.data.AppRepositoryImpl
import com.example.autolistapps.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CheckNewAppsUseCase @Inject constructor(
    private val appsRepository: AppRepositoryImpl,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Unit, Boolean>(dispatcher) {

    override suspend fun execute(parameters: Unit): Boolean = appsRepository.hasNewApps()
}