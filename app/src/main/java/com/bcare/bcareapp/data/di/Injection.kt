package com.bcare.bcareapp.data.di

import android.content.Context
import com.bcare.bcareapp.data.local.repository.ArtikelRepository
import com.bcare.bcareapp.data.local.repository.UserRepository
import com.bcare.bcareapp.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService(context)
        return UserRepository(apiService)
    }

    fun provideArticleRepository(): ArtikelRepository {
        val apiService = ApiConfig.getApi()
        return ArtikelRepository.getInstance(apiService)
    }
}