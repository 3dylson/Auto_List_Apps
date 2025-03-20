package com.example.autolistapps.di

import com.example.autolistapps.data.AppRepository
import com.example.autolistapps.data.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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