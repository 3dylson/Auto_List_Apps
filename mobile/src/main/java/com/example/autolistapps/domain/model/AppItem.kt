package com.example.autolistapps.domain.model

data class AppItem(
    val id: Int,
    val name: String?,
    val icon: String?,
    val storeName: String?,
    val graphic: String?,
    val downloads: Int?,
)
