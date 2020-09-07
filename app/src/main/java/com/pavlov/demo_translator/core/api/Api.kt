package com.pavlov.demo_translator.core.api

import com.google.gson.GsonBuilder
import com.pavlov.demo_translator.core.api.data.MeaningFull
import com.pavlov.demo_translator.core.api.data.MeaningShortRoot
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/api/public/v1/words/search")
    suspend fun search(
        @Query("search") search: String,
        @Query("page") page: Int? = null,
        @Query("pageSize") pageSize: Int? = null,
    ): Response<List<MeaningShortRoot>>

    @GET("/api/public/v1/meanings")
    suspend fun meanings(
        @Query("ids") vararg ids: Int,
    ): Response<List<MeaningFull>>
}

@Module
@InstallIn(ActivityComponent::class)
object ApiModule {

    @Provides
    fun provideApi(): Api {
        return Retrofit.Builder()
            .baseUrl("https://dictionary.skyeng.ru")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(Api::class.java)
    }
}