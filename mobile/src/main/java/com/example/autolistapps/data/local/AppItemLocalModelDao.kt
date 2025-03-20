package com.example.autolistapps.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppItemLocalModelDao {
    @Query("SELECT * FROM app")
    fun getAll(): Flow<List<AppItemLocalModel>>

    @Query("SELECT * FROM app WHERE id = :id")
    fun getById(id: Int): Flow<AppItemLocalModel?>

    @Insert
    suspend fun insertAll(appList: List<AppItemLocalModel>)

}