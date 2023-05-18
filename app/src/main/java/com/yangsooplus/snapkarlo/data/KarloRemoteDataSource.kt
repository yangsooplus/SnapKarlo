package com.yangsooplus.snapkarlo.data

import com.yangsooplus.snapkarlo.data.model.PromptData
import com.yangsooplus.snapkarlo.data.model.T2iResponse
import com.yangsooplus.snapkarlo.data.remote.KarloService
import retrofit2.Response
import javax.inject.Inject

class KarloRemoteDataSource @Inject constructor(
    private val KarloService: KarloService
) : KarloDataSource {
    override suspend fun getT2iImage(promptData: PromptData): Response<T2iResponse> {
        return KarloService.getGeneratedImageByText(promptData)
    }
}