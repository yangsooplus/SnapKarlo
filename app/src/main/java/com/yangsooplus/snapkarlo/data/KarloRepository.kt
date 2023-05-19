package com.yangsooplus.snapkarlo.data

import com.yangsooplus.snapkarlo.data.remote.ApiState
import com.yangsooplus.snapkarlo.data.remote.KarloRemoteDataSource
import com.yangsooplus.snapkarlo.data.remote.model.PromptData
import com.yangsooplus.snapkarlo.data.remote.model.T2iResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class KarloRepository @Inject constructor(
    private val karloRemoteDataSource: KarloRemoteDataSource
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


}