package com.example.autolistapps.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app")
data class AppItemLocalModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String?,
    val icon: String?,
    val storeName: String?,
    val graphic: String?,
)