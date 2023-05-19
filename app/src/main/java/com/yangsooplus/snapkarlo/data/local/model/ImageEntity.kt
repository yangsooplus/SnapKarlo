package com.yangsooplus.snapkarlo.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "image")
data class ImageEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "keyword")
    val keyword: String,
    @ColumnInfo(name = "base64")
    val base64: String,
    @ColumnInfo(name = "date_time")
    val dateTime: LocalDateTime
)
