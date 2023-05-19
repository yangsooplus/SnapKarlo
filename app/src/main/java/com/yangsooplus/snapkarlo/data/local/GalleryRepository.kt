package com.yangsooplus.snapkarlo.data.local

import com.yangsooplus.snapkarlo.data.Base64Converter
import com.yangsooplus.snapkarlo.data.local.model.ImageEntity
import com.yangsooplus.snapkarlo.ui.model.ImageUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GalleryRepository @Inject constructor(
    private val galleryLocalDataSource: GalleryLocalDataSource
) {

    fun getAll(): Flow<List<ImageUi>> = galleryLocalDataSource.getAll().map { entityList ->
        entityList.map {
            ImageUi(
                id = it.id,
                keyword = it.keyword,
                bitmap = Base64Converter.string2Bitmap(it.base64),
                dateTime = it.dateTime
            )
        }
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
