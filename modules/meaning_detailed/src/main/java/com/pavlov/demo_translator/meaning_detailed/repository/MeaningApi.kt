package com.pavlov.demo_translator.meaning_detailed.repository

import com.google.gson.GsonBuilder
import com.pavlov.demo_translator.common.NumericId
import com.pavlov.demo_translator.meaning_detailed.repository.dto.MeaningFull
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MeaningApi {

    @GET("/api/public/v1/meanings")
    suspend fun meanings(
        @Query("ids") vararg ids: NumericId,
    ): List<MeaningFull>
}

@Module
@InstallIn(ApplicationComponent::class)
object MeaningApiModule {

    @Provides
    fun provideMeaningApi(): MeaningApi {
        return Retrofit.Builder()
            .baseUrl("https://dictionary.skyeng.ru")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(MeaningApi::class.java)
    }
}