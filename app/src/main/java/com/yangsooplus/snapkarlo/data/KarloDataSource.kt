package com.yangsooplus.snapkarlo.data

import com.yangsooplus.snapkarlo.data.model.PromptData
import com.yangsooplus.snapkarlo.data.model.T2iResponse
import retrofit2.Response

interface KarloDataSource {
    suspend fun getT2iImage(promptData: PromptData): Response<T2iResponse>
}
