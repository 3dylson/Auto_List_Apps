package com.example.autolistapps.model

data class Data(
    val hidden: Int,
    val limit: Int,
    val list: List<Item0>,
    val next: Int,
    val offset: Int,
    val total: Int
)