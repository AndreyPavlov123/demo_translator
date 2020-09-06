package com.pavlov.demo_translator.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pavlov.demo_translator.api.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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