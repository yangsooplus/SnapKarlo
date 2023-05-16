package com.yangsooplus.snapkarlo.data.remote

import com.yangsooplus.snapkarlo.data.model.Koni
import com.yangsooplus.snapkarlo.data.model.Prompt
import com.yangsooplus.snapkarlo.data.model.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface KarloService {
    @POST("t2i")
    fun getGeneratedImageByText(@Body koni: Koni): Call<Response>
}
