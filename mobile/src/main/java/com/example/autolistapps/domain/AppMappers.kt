package com.example.autolistapps.domain

import com.example.autolistapps.data.local.AppItemLocalModel
import com.example.autolistapps.data.model.Item0
import com.example.autolistapps.domain.model.AppItem

fun AppItemLocalModel.toDomainModel(): AppItem {
    return AppItem(
        id = this.id,
        name = this.name,
        icon = this.icon,
        storeName = this.storeName,
        graphic = this.graphic,
        downloads = this.downloads
    )
}

fun AppItem.toLocalModel(): AppItemLocalModel {
    return AppItemLocalModel(
        id = this.id,
        name = this.name,
        icon = this.icon,
        storeName = this.storeName,
        graphic = this.graphic,
        downloads = this.downloads
    )
}

fun Item0.toDomainModel(): AppItem = AppItem(
    id = id,
    name = name,
    icon = icon,
    storeName = storeName,
    graphic = graphic,
    downloads = this.downloads
)
