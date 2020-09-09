package com.pavlov.demo_translator.meaning_search.repository

import com.google.gson.GsonBuilder
import com.pavlov.demo_translator.common.Config
import com.pavlov.demo_translator.meaning_search.repository.dto.Word
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("/api/public/v1/words/search")
    suspend fun search(
        @Query("search") search: String,
        @Query("page") page: Int? = null,
        @Query("pageSize") pageSize: Int? = null,
    ): List<Word>
}

@Module
@InstallIn(ActivityComponent::class)
object SearchApiModule {

    @Provides
    fun provideSearchApi(): SearchApi {
        return Retrofit.Builder()
            .baseUrl(Config.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(SearchApi::class.java)
    }
}