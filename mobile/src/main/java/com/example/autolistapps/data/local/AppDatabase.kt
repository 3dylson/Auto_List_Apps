package com.example.autolistapps.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AppItemLocalModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appItemLocalModelDao(): AppItemLocalModelDao
}