package com.yangsooplus.snapkarlo.data.remote

import com.yangsooplus.snapkarlo.data.remote.model.PromptData
import com.yangsooplus.snapkarlo.data.remote.model.T2iResponse
import retrofit2.Response
import javax.inject.Inject

class KarloRemoteDataSource @Inject constructor(
    private val KarloService: KarloService
) : KarloDataSource {
    override suspend fun getT2iImage(promptData: PromptData): Response<T2iResponse> {
        return KarloService.getGeneratedImageByText(promptData)
    }
}
