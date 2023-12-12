package com.bcare.bcareapp.data.local.repository

import android.util.Log
import com.bcare.bcareapp.data.remote.response.artikel.DataItem
import com.bcare.bcareapp.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.bcare.bcareapp.data.local.result.Result
import com.bcare.bcareapp.data.remote.response.artikelDetail.Data

class ArtikelRepository private constructor(
    private val articleApi : ApiService
){
    fun getListArticle(): Flow<Result<List<DataItem>>> = flow {
        emit(Result.Loading)
        try {
            val response = articleApi.getAllArticle()
            emit(Result.Success(response.data))
        } catch (e: Exception) {
            Log.d("ArticleRepository", "getListArticle: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getDetailArticle(id: Int): Flow<Result<Data>> = flow {
        emit(Result.Loading)
        try {
            val response = articleApi.getDetailArticle(id)
            emit(Result.Success(response.data))
        } catch (e: Exception) {
            Log.d("ArticleRepository", "getDetailArticle: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: ArtikelRepository? = null
        fun getInstance(
            apiService: ApiService
        ): ArtikelRepository = instance ?: synchronized(this) {
            instance ?: ArtikelRepository(apiService)
        }.also { instance = it }
    }
}