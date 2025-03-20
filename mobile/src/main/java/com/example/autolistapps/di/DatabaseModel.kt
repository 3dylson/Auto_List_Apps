package com.example.autolistapps.di

import android.content.Context
import androidx.room.Room
import com.example.autolistapps.data.local.AppDatabase
import com.example.autolistapps.data.local.AppItemLocalModelDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideAppItemLocalModelDao(appDatabase: AppDatabase): AppItemLocalModelDao {
        return appDatabase.appItemLocalModelDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }
}