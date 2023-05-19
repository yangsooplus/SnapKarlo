package com.yangsooplus.snapkarlo.data.remote

import com.yangsooplus.snapkarlo.data.remote.model.PromptData
import com.yangsooplus.snapkarlo.data.remote.model.T2iResponse
import retrofit2.Response

interface KarloDataSource {
    suspend fun getT2iImage(promptData: PromptData): Response<T2iResponse>
}
