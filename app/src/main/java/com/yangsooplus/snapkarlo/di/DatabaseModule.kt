package com.yangsooplus.snapkarlo.di

import android.content.Context
import androidx.room.Room
import com.yangsooplus.snapkarlo.data.local.ImageDao
import com.yangsooplus.snapkarlo.data.local.ImageDatabase
import com.yangsooplus.snapkarlo.data.local.LocalDateTimeConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val TABLE_NAME = "gallery"

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ImageDatabase {
        return Room.databaseBuilder(context, ImageDatabase::class.java, TABLE_NAME)
            .addTypeConverter(LocalDateTimeConverter())
            .build()
    }

    @Singleton
    @Provides
    fun provideImageDao(database: ImageDatabase): ImageDao {
        return database.imageDao()
    }
}