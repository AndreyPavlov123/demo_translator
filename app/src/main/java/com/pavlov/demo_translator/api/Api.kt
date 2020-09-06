package com.pavlov.demo_translator.api

import com.pavlov.demo_translator.api.data.MeaningFull
import com.pavlov.demo_translator.api.data.MeaningShortRoot
import retrofit2.Response
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