package com.yangsooplus.snapkarlo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yangsooplus.snapkarlo.data.local.model.ImageEntity

@Database(entities = [ImageEntity::class], version = 1)
@TypeConverters(LocalDateTimeConverter::class)
abstract class ImageDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
}
