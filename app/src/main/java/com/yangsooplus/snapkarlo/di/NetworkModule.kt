package com.yangsooplus.snapkarlo.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.yangsooplus.snapkarlo.BuildConfig
import com.yangsooplus.snapkarlo.data.remote.KarloService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(KaKaoInterceptor())
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.kakaobrain.com/v1/inference/karlo/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideKarloService(retrofit: Retrofit): KarloService {
        return retrofit.create(KarloService::class.java)
    }
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
