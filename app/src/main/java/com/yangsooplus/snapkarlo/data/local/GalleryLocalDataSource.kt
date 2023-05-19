package com.yangsooplus.snapkarlo.data.local

import com.yangsooplus.snapkarlo.data.local.model.ImageEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GalleryLocalDataSource @Inject constructor(
    private val imageDao: ImageDao
) {

    suspend fun insertImage(imageEntity: ImageEntity) {
        imageDao.insert(imageEntity)
    }

    suspend fun getAll(): Flow<List<ImageEntity>> {
        return imageDao.getAll()
    }

    suspend fun findByKeyword(keyword: String): Flow<List<ImageEntity>> {
        return imageDao.findByKeyword(keyword)
    }

    suspend fun delete(imageEntity: ImageEntity) {
        imageDao.delete(imageEntity)
    }

    suspend fun deleteById(id: String) {
        imageDao.deleteById(id)
    }
}
