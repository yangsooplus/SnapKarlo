package com.yangsooplus.snapkarlo.data

import com.yangsooplus.snapkarlo.data.local.GalleryLocalDataSource
import com.yangsooplus.snapkarlo.data.local.model.ImageEntity
import com.yangsooplus.snapkarlo.data.remote.ApiState
import com.yangsooplus.snapkarlo.data.remote.KarloRemoteDataSource
import com.yangsooplus.snapkarlo.data.remote.model.Image
import com.yangsooplus.snapkarlo.data.remote.model.PromptData
import com.yangsooplus.snapkarlo.data.remote.model.T2iResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.time.LocalDateTime
import javax.inject.Inject

class KarloRepository @Inject constructor(
    private val karloRemoteDataSource: KarloRemoteDataSource,
    private val galleryLocalDataSource: GalleryLocalDataSource
) {

    suspend fun getT2iImage(promptData: PromptData): Flow<ApiState<T2iResponse>> = flow {
        val response = karloRemoteDataSource.getT2iImage(promptData)
        if (response.isSuccessful) {
            response.body()?.let { t2i ->
                emit(ApiState.Success(t2i))
            }
        } else {
            emit(ApiState.Error(message = response.message()))
        } as Unit
    }.flowOn(Dispatchers.IO)

    suspend fun insertImage(image: Image, keyword: String) {
        galleryLocalDataSource.insertImage(
            ImageEntity(
                id = image.id,
                keyword = keyword,
                base64 = image.image,
                dateTime = LocalDateTime.now()
            )
        )
    }

}