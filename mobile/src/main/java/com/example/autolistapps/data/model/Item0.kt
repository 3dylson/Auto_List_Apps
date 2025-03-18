package com.example.autolistapps.data.model


import com.squareup.moshi.Json

data class Item0(
    @Json(name = "added")
    val added: String?,
    @Json(name = "apk_tags")
    val apkTags: List<String?>?,
    @Json(name = "downloads")
    val downloads: Int?,
    @Json(name = "graphic")
    val graphic: String?,
    @Json(name = "icon")
    val icon: String?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "md5sum")
    val md5sum: String?,
    @Json(name = "modified")
    val modified: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "package")
    val packageX: String?,
    @Json(name = "pdownloads")
    val pdownloads: Int?,
    @Json(name = "rating")
    val rating: Double?,
    @Json(name = "size")
    val size: Int?,
    @Json(name = "store_id")
    val storeId: Int?,
    @Json(name = "store_name")
    val storeName: String?,
    @Json(name = "updated")
    val updated: String?,
    @Json(name = "uptype")
    val uptype: String?,
    @Json(name = "vercode")
    val vercode: Int?,
    @Json(name = "vername")
    val vername: String?
)