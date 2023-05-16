package com.yangsooplus.snapkarlo.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.yangsooplus.snapkarlo.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object NetworkObject {
    private const val BASE_URL = "https://api.kakaobrain.com/v1/inference/karlo/"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(KaKaoInterceptor())
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                },
            )
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val karloService: KarloService by lazy {
        retrofit.create(KarloService::class.java)
    }

    class KaKaoInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
            val newRequest = request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "KakaoAK ${BuildConfig.key}")
                .build()

            proceed(newRequest)
        }
    }
}
