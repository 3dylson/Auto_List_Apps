package com.example.autolistapps.di

import com.example.autolistapps.data.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class]
)
interface TestDataModule {

    @Binds
    abstract fun bindRepository(
        fakeRepository: TestAppRepositoryImpl
    ): AppRepository
}