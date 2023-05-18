package com.yangsooplus.snapkarlo.data.remote

import com.yangsooplus.snapkarlo.data.model.PromptData
import com.yangsooplus.snapkarlo.data.model.T2iResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface KarloService {
    @POST("t2i")
    suspend fun getGeneratedImageByText(@Body promptData: PromptData): Response<T2iResponse>
}
