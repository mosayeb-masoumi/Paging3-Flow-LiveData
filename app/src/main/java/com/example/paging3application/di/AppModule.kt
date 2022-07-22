package com.example.paging3application.di

import com.example.paging3application.network.ApiService
import com.example.paging3application.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitClient().buildService(ApiService::class.java)
    }

}