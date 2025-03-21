package com.example.autolistapps.di

import com.example.autolistapps.data.AppRepository
import com.example.autolistapps.data.AppRepositoryImpl
import com.example.autolistapps.data.model.sample.randomItem1
import com.example.autolistapps.data.model.sample.randomItem2
import com.example.autolistapps.domain.model.AppItem
import com.example.autolistapps.domain.toDomainModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindsAppRepository(
        appRepository: AppRepositoryImpl
    ): AppRepository
}

class TestAppRepositoryImpl @Inject constructor() : AppRepository {

    override val appList: Flow<List<AppItem>> = flowOf(listOf(randomItem1.toDomainModel(), randomItem2.toDomainModel()))

    override suspend fun refresh(): Result<List<AppItem>> {
        return Result.success(appList.first())
    }

    override fun getAppById(id: Int): Flow<AppItem?> = flow {
        emit(appList.first().firstOrNull { it.id == id })
    }

    override suspend fun hasNewApps(): Boolean = true

}