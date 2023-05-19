package com.yangsooplus.snapkarlo.data.local

import com.yangsooplus.snapkarlo.data.local.model.ImageEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GalleryRepository @Inject constructor(
    private val galleryLocalDataSource: GalleryLocalDataSource
) {

    suspend fun getAll(): Flow<List<ImageEntity>> {
        return galleryLocalDataSource.getAll()
    }

    suspend fun findByKeyword(keyword: String): Flow<List<ImageEntity>> {
        return galleryLocalDataSource.findByKeyword(keyword)
    }

    suspend fun delete(imageEntity: ImageEntity) {
        galleryLocalDataSource.delete(imageEntity)
    }

    suspend fun deleteById(id: String) {
        galleryLocalDataSource.deleteById(id)
    }
}